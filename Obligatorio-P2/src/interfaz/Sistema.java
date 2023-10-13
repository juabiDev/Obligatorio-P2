/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import dominio.*;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author
 * Dana Cizin 239510
 * Fabian Mederos 281938
 *
 */
public class Sistema {

  public static void main(String[] args) throws FileNotFoundException {
    Scanner scanner = new Scanner(System.in);
    ConsolaSoliflips consola = new ConsolaSoliflips();
    Juego juego;
    String opcionUsuario;

    do {
      System.out.println("Desea comenzar a jugar? S/N ");
      opcionUsuario = scanner.nextLine();
    } while (
      !opcionUsuario.equalsIgnoreCase("S") &&
      !opcionUsuario.equalsIgnoreCase("N")
    );

    while (opcionUsuario.equalsIgnoreCase("S")) {
      consola.mostrarMenuPrincipal();
      try {
        String opcion = scanner.nextLine().toLowerCase();

        switch (opcion) {
          case "a":
            juego = new Juego();
            juego.crearTableroDeArchivo();
            consola.imprimirTablero(juego.obtenerTableroActual());
            jugarPartida(juego, consola, scanner);
            break;
          case "b":
            juego = new Juego();
            juego.crearTableroPredefinido();
            consola.imprimirTablero(juego.obtenerTableroActual());
            jugarPartida(juego, consola, scanner);
            break;
          case "c":
            juego = new Juego();
            int[] dimensiones = obtenerEntradas(
              juego,
              scanner,
              "Ingrese cantidad de filas:",
              "Ingrese cantidad de columnas:",
              "Ingrese nivel:",
              "preJuego"
            );
            int filas = dimensiones[0];
            int columnas = dimensiones[1];
            int nivel = dimensiones[2];
            juego.crearTableroAleatorio(filas, columnas, nivel);
            consola.imprimirTablero(juego.obtenerTableroActual());
            jugarPartida(juego, consola, scanner);
            break;
          default:
            System.err.println("Por favor, ingrese una opción válida.");
            break;
        }
      } catch (RuntimeException e) {
        System.err.println("Error: " + e.getMessage());
      }
    }
  }

  public static void jugarPartida(
    Juego juego,
    ConsolaSoliflips consola,
    Scanner scanner
  ) {
    Historial historiales = juego.getHistoriales();
    boolean juegoTerminado = false;
    String mensajeFinal = "";
    while (!juegoTerminado) {
      consola.mostrarSubMenu();
      String opcion = scanner.nextLine();

      switch (opcion.toLowerCase()) {
        case "m":
          int[] posicion = obtenerEntradas(
            juego,
            scanner,
            "Ingrese posición fila:",
            "Ingrese posición columna:",
            "postJuego"
          );
          int fila = posicion[0];
          int columna = posicion[1];

          try {
            String[][] tableroAnterior = juego.obtenerTableroActual();

            juegoTerminado = juego.jugar(fila, columna);
            if (juegoTerminado) {
              mensajeFinal =
                "FELICIDADES HAS GANADO!\n" +
                "Tiempo de partida: " +
                juego.obtenerTiempoTotal();
            }

            String[][] tableroPosterior = juego.obtenerTableroActual();

            if (fila == -1 && columna == -1) {
              consola.imprimirTablero(tableroPosterior);
            } else {
              consola.imprimirTablerosLadoALado(
                tableroAnterior,
                tableroPosterior
              );
            }
          } catch (RuntimeException e) {
            String[][] tableroPosterior = juego.obtenerTableroActual();

            if (
              fila == -1 &&
              columna == -1 &&
              historiales.obtenerMovimientos().isEmpty()
            ) {
              consola.imprimirTablero(tableroPosterior);
            }

            System.err.println("Error: " + e.getMessage());
            System.out.println();
          }
          break;
        case "x":
          System.out.println("Adiós. ¡Gracias por jugar!");
          juegoTerminado = true;
          mensajeFinal = "Tiempo de partida: " + juego.obtenerTiempoTotal();
          break;
        case "h":
          if (!juego.obtenerHistorialMovimientos().isEmpty()) {
            consola.imprimirMovimientos(juego.obtenerHistorialMovimientos());
            System.out.println();
          } else {
            System.out.println("No se ha realizado ningún movimiento válido");
            System.out.println();
          }
          break;
        case "s":
          consola.imprimirMovimientos(historiales.getSolucion());
          System.out.println();
          break;
        default:
          System.err.println("Por favor, ingrese una opción válida.");
          break;
      }
    }

    if (juegoTerminado) {
      System.out.println(mensajeFinal);
      String opcionUsuario;
      do {
        System.out.println("Desea comenzar una nueva partida? S/N");
        opcionUsuario = scanner.nextLine();
      } while (
        !opcionUsuario.equalsIgnoreCase("S") &&
        !opcionUsuario.equalsIgnoreCase("N")
      );

      if (!opcionUsuario.equalsIgnoreCase("S")) {
        System.out.println("Adiós. ¡Gracias por jugar!");
        System.exit(0);
      }
    }
  }

  public static int[] obtenerEntradas(
    Juego juego,
    Scanner scanner,
    String... mensajes
  ) {
    int[] entradas = new int[mensajes.length - 1];
    String mensajeCriterioValidacion = mensajes[mensajes.length - 1];
    int filaTablero = juego.obtenerTableroActual().length;
    int columnaTablero = juego.obtenerTableroActual()[0].length;
    boolean mientras = true;

    for (int i = 0; i < mensajes.length - 1 && mientras; i++) {
      System.out.println(mensajes[i]);
      String entradaStr = scanner.nextLine();

      try {
        int entrada = Integer.parseInt(entradaStr);
        if (
          !Character.isDigit(entradaStr.charAt(0)) &&
          !(mensajeCriterioValidacion.equals("postJuego") && entrada == -1)
        ) {
          throw new NumberFormatException();
        }

        entradas[i] = entrada;

        if (i == mensajes.length - 1) {
          mientras = false;
        }
      } catch (NumberFormatException e) {
        i = i - 1;
        // Validaciones para ayudar al usuario
        if (mensajeCriterioValidacion.equals("postJuego")) {
          if (i == -1) {
            System.err.println(
              "Entrada no válida. Ingrese un número entero entre 1 y " +
              filaTablero
            );
          } else {
            System.err.println(
              "Entrada no válida. Ingrese un número entero entre 1 y " +
              columnaTablero
            );
          }
        } else {
          System.err.println(
            "Entrada no válida. Ingrese un número entero entre 1 y 8"
          );
        }
      }
    }
    return entradas;
  }
}

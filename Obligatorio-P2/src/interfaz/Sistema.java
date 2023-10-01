/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import java.util.*;
import java.io.FileNotFoundException;
import dominio.*;
/**
 *
 * @author User
 * 
 */
public class Sistema {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ConsolaSoliflips consola = new ConsolaSoliflips();
        Juego juego;
        
        while (true) {
            consola.mostrarMenuPrincipal(); 
            try {
                String opcion = scanner.nextLine().toLowerCase();

                switch (opcion) {
                    case "a":
                       juego = new Juego();
                       juego.crearTableroDeArchivo();
                       juego.guardarTablero(juego.obtenerTableroActual());
                       consola.imprimirTablero(juego.obtenerTableroActual());
                       jugarPartida(juego, consola, scanner);
                       break;
                    case "b":
                       juego = new Juego();
                       juego.crearTableroPredefinido();
                       juego.guardarTablero(juego.obtenerTableroActual());
                       consola.imprimirTablero(juego.obtenerTableroActual());
                       jugarPartida(juego, consola, scanner);
                       break;
                    case "c":
                       juego = new Juego();
                       int[] dimensiones = obtenerEntradas(scanner, "Ingrese cantidad de filas:", "Ingrese cantidad de columnas:", "Ingrese nivel:");
                       int filas = dimensiones[0];
                       int columnas = dimensiones[1];
                       int nivel = dimensiones[2];
                       juego.crearTableroAleatorio(filas, columnas, nivel);
                       juego.guardarTablero(juego.obtenerTableroActual());
                       consola.imprimirTablero(juego.obtenerTableroActual());
                       jugarPartida(juego, consola, scanner);
                       break;
                    default:
                       System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                       break;
                }   
            } catch(RuntimeException e) {
                System.out.println("Error: "+e.getMessage());
            }  
        }
    }

    public static void jugarPartida(Juego juego, ConsolaSoliflips consola, Scanner scanner) {
        Historial historiales = juego.getHistoriales();
        boolean juegoTerminado = false;
        while (!juegoTerminado) {
            consola.mostrarSubMenu();
            String opcion = scanner.nextLine();

            switch (opcion.toLowerCase()) {
                case "m":
                    int[] posicion = obtenerEntradas(scanner, "Ingrese posición fila:", "Ingrese posición columna:");
                    int fila = posicion[0];
                    int columna = posicion[1];
                    
                    try {
                        juego.jugar(fila, columna);
                        if(fila == -1 && columna == -1) {
                            String[][] tablero = juego.obtenerUltimoTablero();
                            consola.imprimirTablero(tablero);
                        } else {
                            String[][][] tableros = juego.obtenerUltimosDosTableros();
                            consola.imprimirTablerosLadoALado(tableros[0], tableros[1]);
                        }
                    } catch(RuntimeException e) {
                        System.err.println("Error: "+e.getMessage());
                    }         
                    
                    juegoTerminado = juego.juegoTerminado();
                    break;
                case "x":
                    System.out.println("Adios. ¡Gracias por jugar!");
                    juegoTerminado = true;
                    break;
                case "h":
                    consola.imprimirHistorial(juego.obtenerHistorialMovimientos());
                    break;
                case "s":
                    System.out.println(historiales.getSolucion());
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }

        if (juegoTerminado) {
            System.out.println("Tiempo de partida: " + juego.obtenerTiempoTotal());
            System.out.println("Desea comenzar una nueva partida? S/N ");
            String opcion = scanner.nextLine();

            if (!opcion.equalsIgnoreCase("S")) {
                System.out.println("Adios. ¡Gracias por jugar!");
                System.exit(0); // Salir del programa
            }
        }
    }

    public static int[] obtenerEntradas(Scanner scanner, String... mensajes) {
        int[] entradas = new int[mensajes.length];
            boolean mientras = true;
            for (int i = 0; i < mensajes.length && mientras; i++) {
                System.out.println(mensajes[i]);
                String entradaStr = scanner.nextLine();
                
                try {
                    int entrada = Integer.parseInt(entradaStr);
                    if (!(Character.isDigit(entradaStr.charAt(0)))) {
                        throw new NumberFormatException();
                    }
                    entradas[i] = entrada;
                    if(i == mensajes.length) {
                        mientras = false; 
                    }
                } catch (NumberFormatException e) {
                    i = i-1;
                    System.out.println("Entrada no válida. Ingrese un número entero válido ");
                }
            }
        return entradas;
    }
}
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
                       int[] dimensiones = obtenerEntradas(juego, scanner, "Ingrese cantidad de filas:", "Ingrese cantidad de columnas:", "Ingrese nivel:", "preJuego");
                       int filas = dimensiones[0];
                       int columnas = dimensiones[1];
                       int nivel = dimensiones[2];
                       juego.crearTableroAleatorio(filas, columnas, nivel);
                       juego.guardarTablero(juego.obtenerTableroActual());
                       consola.imprimirTablero(juego.obtenerTableroActual());
                       jugarPartida(juego, consola, scanner);
                       break;
                    default:
                       System.err.println("Opción no válida. Por favor, seleccione una opción válida.");
                       break;
                }   
            } catch(RuntimeException e) {
                System.err.println("Error: "+e.getMessage());
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
                    int[] posicion = obtenerEntradas(juego, scanner, "Ingrese posición fila:", "Ingrese posición columna:", "postJuego");
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
                        System.out.println();
                    }         
                    
                    juegoTerminado = juego.juegoTerminado();
                    break;
                case "x":
                    System.out.println("Adios. ¡Gracias por jugar!");
                    juegoTerminado = true;
                    break;
                case "h":
                    if(juego.obtenerHistorialMovimientos().size() > 0) {
                        consola.imprimirHistorial(juego.obtenerHistorialMovimientos());
                        System.out.println();
                    } else {
                        System.out.println("No se ha realizado ningun movimiento valido");
                        System.out.println();
                    }
                    break;
                case "s":
                    System.out.println(historiales.getSolucion());
                    System.out.println();
                    break;
                default:
                    System.err.println("Opción no válida. Por favor, seleccione una opción válida.");
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

    public static int[] obtenerEntradas(Juego juego, Scanner scanner, String... mensajes) {
        int[] entradas = new int[mensajes.length - 1];
        String mensajeCriterioValidacion = mensajes[mensajes.length - 1].toString();
        int filaTablero = juego.obtenerTableroActual().length;
        int columnaTablero = juego.obtenerTableroActual()[0].length;

        System.out.println("mensajes:"+mensajes[mensajes.length - 1].toString());
            boolean mientras = true;
            for (int i = 0; i < mensajes.length - 1 && mientras; i++) {
                System.out.println(mensajes[i]);
                String entradaStr = scanner.nextLine();
                
                try {
                    int entrada = Integer.parseInt(entradaStr);
                    
                    if (!Character.isDigit(entradaStr.charAt(0)) && !(mensajeCriterioValidacion.equals("postJuego") && entrada == -1)) {
                        throw new NumberFormatException();
                    }
                    
                    entradas[i] = entrada;
                    
                    if(i == mensajes.length - 1) {
                        mientras = false; 
                    }
                } catch (NumberFormatException e) {
                    i = i-1;
                  
                    // Simplemente validaciones para ayudar al usuario, al estar en el catch no intentan reemplazar las validaciones del dominio
                    if(mensajeCriterioValidacion.equals("postJuego")) {
                        if(i == -1) {
                            System.err.println("Entrada no válida. Ingrese un número entero válido entre 1 y "+filaTablero);
                        } else {
                            System.err.println("Entrada no válida. Ingrese un número entero válido entre 1 y "+columnaTablero);
                        }
                    } else {
                        System.err.println("Entrada no válida. Ingrese un número entero válido entre 1 y 8");
                    }
                }
            }
        return entradas;
    }
}
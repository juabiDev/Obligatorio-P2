/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import dominio.*;
/**
 *
 * @author User
 */
public class Sistema {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ConsolaSoliflips consola = new ConsolaSoliflips();
        Juego juego = new Juego();
        
        boolean jugar = true;
        boolean enJuego = true; // la vamos a ir controlando a medida que el usuario hace un movimiento

        /* Faltaria logica de empezar juego, o sea, mostras el tablero, unas mini instrucciones con menu, y 
        pedimos movimiento, lo ejecutamos, lo guardamos, etc.
        !! Tablero aleatorio 
        */
        
        while (jugar) {
            consola.mostrarMenuPrincipal();
            String opcion = scanner.nextLine();
            juego.iniciarJuego();
            switch (opcion.toLowerCase()) {
                case "a":
                    juego.crearTableroDeArchivo();
                    consola.imprimirTablero(juego.obtenerTableroActual());
                    jugar = false;
                    break;
                case "b":
                    juego.crearTableroPredefinido();
                    consola.imprimirTablero(juego.obtenerTableroActual());
                    jugar = false;
                    break;
                case "c":
                    Scanner in = new Scanner(System.in);
                    System.out.println("Ingrese nivel: ");
                    int nivel = in.nextInt();
                    juego.crearTableroAleatorio(nivel);
                    consola.imprimirTablero(juego.obtenerTableroActual());
                    jugar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
        
        String [][] tablero = juego.obtenerTableroActual();
        
        while(enJuego) {
            consola.mostrarSubMenu();
            String opcion = scanner.nextLine();
            consola.imprimirTablero(tablero);
            
            switch (opcion.toLowerCase()) {
                case "m":
                    System.out.println("Ingrese Movimiento: fila columna");
                    int fila = scanner.nextInt();
                    int columna = scanner.nextInt();
                    scanner.nextLine();
                    
                    juego.jugar(fila, columna);

                    consola.imprimirTablerosLadoALado(juego.obtenerTableroPrevio(), juego.obtenerTableroActual());
                    break;             
                case "x":
                    System.out.println("Adios. ¡Gracias por jugar!");
                    enJuego = false;
                    break;          
                case "h":
                    consola.imprimirHistorial(juego.obtenerHistorialMovimientos());
                    break;
                case "s":
                    System.out.println("...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
        //consola.imprimirTablero(juego.obtenerTableroActual());
    }
}

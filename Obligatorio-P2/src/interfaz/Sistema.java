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
        Tablero tablero = new Tablero();
        
        boolean jugar = true;

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
                    String[][] tabArch = juego.obtenerTableroDeArchivo();
                    consola.imprimirTablero(tabArch);
                    jugar = false;
                    break;
                case "b":
                    String[][] tabPre = juego.obtenerTableroPredefinido();
                    consola.imprimirTablero(tabPre);
                    jugar = false;
                    break;
                case "c":
                    Scanner in = new Scanner(System.in);
                    System.out.println("Ingrese nivel: ");
                    int nivel = in.nextInt();
                    String[][] tabAle = juego.obtenerTableroAleatorio(nivel);
                    consola.imprimirTablero(tabAle);
                    jugar = false;
                    break;
                case "x":
                    jugar = false;
                    System.out.println("¡Gracias por jugar! Hasta luego.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }
}

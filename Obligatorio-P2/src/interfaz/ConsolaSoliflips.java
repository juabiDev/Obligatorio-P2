/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Dell_
 */
public class ConsolaSoliflips {
    public void mostrarMenuPrincipal() {
        System.out.println("Bienvenido al juego Soliflips!");
        System.out.println("Seleccione una opción:");
        System.out.println("a) Cargar datos desde 'datos.txt'");
        System.out.println("b) Usar el tablero predefinido");
        System.out.println("c) Generar un tablero aleatorio resoluble");
        System.out.print("Opción: ");
    }
    
    public static void imprimirTablero(String[][] tablero) {
        int filas = tablero.length;
        int columnas = tablero[0].length;

        // Encontrar la longitud del número de columna más largo
        int longitudNumero = String.valueOf(columnas).length();

        // Imprimir números de columna
        System.out.print(" ");
        for (int i = 1; i <= columnas; i++) {
            String columnaFormateada = String.format("%-" + longitudNumero + "s", i);
            System.out.print("   " + columnaFormateada);
        }
        System.out.println(); // Cambiar de línea después de los números de columna

        for (int fila = 0; fila < filas; fila++) {
            // Imprimir línea superior de la celda
            System.out.print("  +");
            for (int columna = 0; columna < columnas; columna++) {
                System.out.print("---+");
            }
            System.out.println(); // Final de la línea superior de la celda
            
            // Imprimir contenido de la celda
            String numeroFila = String.valueOf(fila + 1);
            String filaFormateada = String.format("%-" + longitudNumero + "s", numeroFila);
            System.out.print(filaFormateada + " ");
            for (int columna = 0; columna < columnas; columna++) {
                String simboloFormateado = String.format("%-2s", tablero[fila][columna]);
                System.out.print("| " + simboloFormateado);
            }
            System.out.println("|"); // Final de la línea de contenido de la celda
        }

        // Imprimir línea inferior de la matriz
        System.out.print("  +");
        for (int columna = 0; columna < columnas; columna++) {
            System.out.print("---+");
        }
        System.out.println(); // Final de la línea inferior de la matriz
    }
    
    public void mostrarSubMenu() {
        System.out.println("Seleccione una opción:");
        System.out.println("M) Realizar movimiento fila columna");
        System.out.println("X) Terminar este juego");
        System.out.println("H) Mostrar historial de movimientos (fila, columna)");
        System.out.println("S) Mostrar solución");
        System.out.print("Opción: ");
    }

    public void imprimirHistorial(ArrayList movimientos) {
        System.out.println("Historial de Movimientos:");
        for(int i = 0; i < movimientos.size(); i++) {
            System.out.println(movimientos.get(i));
        }
        
    }
}

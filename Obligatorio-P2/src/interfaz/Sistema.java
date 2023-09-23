/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import java.util.*;
/**
 *
 * @author User
 */
public class Sistema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsolaSoliflips consola = new ConsolaSoliflips();
        
        boolean jugar = true;

        while (jugar) {
            consola.mostrarMenuPrincipal();
            String opcion = scanner.nextLine();

            switch (opcion.toLowerCase()) {
                case "a":
                    //lógica para cargar datos desde 'datos.txt'
                    break;
                case "b":
                    //lógica para usar el tablero predefinido
                    break;
                case "c":
                    //lógica para generar un tablero aleatorio resoluble
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
    
        // Crear un tablero de ejemplo (puedes sustituirlo con tu lógica)
        String[][] tablero = {
            {"/", "\\", "-", "|", "/", "\\", "-", "|", "\\"},
            {"|", "-", "\\", "/", "|", "\\", "-", "/", "|"},
            {"\\", "/", "|", "-", "\\", "/", "|", "-", "\\"},
            {"-", "|", "/", "\\", "-", "|", "/", "\\", "-"},
            {"|", "\\", "-", "/", "|", "\\", "-", "/", "|"}
        };

        // Imprimir el tablero con números de fila y columna
        imprimirTablero(tablero);
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
}

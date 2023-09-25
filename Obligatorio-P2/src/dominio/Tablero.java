/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Tablero {
    private int filas;
    private int columnas;
    private int nivel;
    private String [][] tableritoPrevio;
    private String [][] tableritoActual;
    
    public Tablero() { // el tablero tiene como minimo 3 filas y 3 columnas
        this.filas = 3;
        this.columnas = 3;
        this.nivel = 1;
        this.tableritoPrevio = new String[this.filas][this.columnas];
        this.tableritoActual = new String[this.filas][this.columnas];
    }
    
    public Tablero(int filas, int columnas, int nivel) {
        boolean valido = validarFilasColumnas(filas, columnas, nivel);   
        if(valido) {
            this.filas = filas;
            this.columnas = columnas;
            this.tableritoPrevio = new String[this.filas][this.columnas];
            this.tableritoActual = new String[this.filas][this.columnas];
        }
    }
    
    private boolean validarFilasColumnas(int filas, int columnas, int nivel) {
        boolean valido = false;
        
        if((filas >= 3 && filas <= 9) && (columnas >= 3 && columnas <= 9) && (nivel >= 1 && nivel <= 8)) {
           valido = true;
        }
        
        return valido;
    }
    
    public void cambiarColor(int fila, int columna) {
        
    }
    
    public void setTableritoPrevio(String[][] untablero) {
        this.tableritoPrevio = untablero;
    }
    
    public String[][] getTableritoPrevio() {
        return this.tableritoPrevio;
    }
    
    public void setTableritoActual(String[][] untablero) {
        this.tableritoActual = untablero;
    }
    
    public int getFilas() {
        return this.filas;
    }
    
    public int getColumnas() {
        return this.columnas;
    }
    
    public String[][] getTableritoActual() {
        return this.tableritoActual;
    }
    
    public static String[][] tableroAleatorio(int dificultad) {
        String pipeAzul = "\033[34m" + "| " + "\033[0m";
        String pipeRojo = "\033[31m" + "| " + "\033[0m";
        
        String guionAzul = "\033[34m" + "- " + "\033[0m";
        String guionRojo = "\033[31m" + "- " + "\033[0m";
        
        String diagonalDerechaAzul = "\033[34m" + "/ " + "\033[0m";
        String diagonalDerechaRojo = "\033[31m" + "/ " + "\033[0m";
        
        String diagonalIzquierdaAzul = "\033[34m" + "\\ " + "\033[0m";
        String diagonalIzquierdaRojo = "\033[31m" + "\\ " + "\033[0m";
        
         String[][] tablero = {
            {pipeAzul, pipeAzul, guionRojo, diagonalDerechaAzul, pipeRojo, guionRojo},
            {guionRojo , diagonalDerechaAzul, diagonalDerechaAzul, pipeAzul, guionRojo, guionRojo},
            {guionRojo, guionRojo, pipeAzul, guionRojo, diagonalDerechaRojo, guionRojo},
            {diagonalIzquierdaRojo, guionRojo, pipeRojo, diagonalIzquierdaRojo, pipeAzul, pipeRojo},
            {diagonalIzquierdaRojo, diagonalDerechaRojo, diagonalDerechaRojo, pipeAzul, diagonalDerechaAzul, diagonalIzquierdaAzul},
        };
        return tablero;
    }
    
   public static Tablero tableroDesdeArchivo() throws FileNotFoundException {
            // Abrir el archivo "datos.txt" para lectura
            Scanner input = new Scanner(new File("C:\\Users\\User\\OneDrive\\Documentos\\NetBeansProjects\\Obligatorio-P2\\Obligatorio-P2\\src\\interfaz\\datos.txt"));

            // Leer las dimensiones del tablero (m filas x n columnas)
            int m = input.nextInt();
            int n = input.nextInt();
            input.nextLine(); // Leer el salto de línea después de las dimensiones

            // Crear el tablero con las dimensiones especificadas
            String[][] tablero = new String[m][n];
            // Leer el contenido del tablero y asignar colores
            for (int fila = 0; fila < m; fila++) {
                String filaTablero = input.nextLine();
                String[] simbolos = filaTablero.split(" "); // Divide la fila en símbolos
                
                for (int columna = 0; columna < n; columna++) {
                    String simbolo = simbolos[columna]; 
                    
                    
                    // Agregar el símbolo al tablero con el color correspondiente
                    tablero[fila][columna] = simbolo; // Resetear color
                }
            }
            int nivel = input.nextInt();
            // Cerrar el archivo
            input.close();
            
            Tablero t = new Tablero(m, n, nivel);
            t.setTableritoActual(tablero);
            
            return t;
    }
    
    public static String[][] tableroPredefinido() {        
        String pipeAzul = "\033[34m" + "| " + "\033[0m";
        String pipeRojo = "\033[31m" + "| " + "\033[0m";
        
        String guionAzul = "\033[34m" + "- " + "\033[0m";
        String guionRojo = "\033[31m" + "- " + "\033[0m";
        
        String diagonalDerechaAzul = "\033[34m" + "/ " + "\033[0m";
        String diagonalDerechaRojo = "\033[31m" + "/ " + "\033[0m";
        
        String diagonalIzquierdaAzul = "\033[34m" + "\\ " + "\033[0m";
        String diagonalIzquierdaRojo = "\033[31m" + "\\ " + "\033[0m";
        
        String[][] tablero = {
            {pipeAzul, pipeAzul, guionRojo, diagonalDerechaAzul, pipeRojo, guionRojo},
            {guionRojo , diagonalDerechaAzul, diagonalDerechaAzul, pipeAzul, guionRojo, guionRojo},
            {guionRojo, guionRojo, pipeAzul, guionRojo, diagonalDerechaRojo, guionRojo},
            {diagonalIzquierdaRojo, guionRojo, pipeRojo, diagonalIzquierdaRojo, pipeAzul, pipeRojo},
            {diagonalIzquierdaRojo, diagonalDerechaRojo, diagonalDerechaRojo, pipeAzul, diagonalDerechaAzul, diagonalIzquierdaAzul},
        };
        return tablero;
    }
 
}

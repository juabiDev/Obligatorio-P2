/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author User
 */
public class Tablero {

    private static Juego juego;
    private int filas;
    private int columnas;
    private int nivel;
    private String [][] tableritoActual;
    
    public Tablero() { // el tablero tiene como minimo 3 filas y 3 columnas
        this.filas = 3;
        this.columnas = 3;
        this.nivel = 1;
        this.tableritoActual = new String[this.filas][this.columnas];
    }
    
    public Tablero(int filas, int columnas, int nivel) {
        boolean valido = validarFilasColumnas(filas, columnas, nivel);   
        if(valido) {
            this.filas = filas;
            this.columnas = columnas;
            this.tableritoActual = new String[this.filas][this.columnas];
        }
        this.juego = new Juego();
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
    
    /* public static Tablero tableroAleatorio(int dificultad) {
        String[][] tablero = {
            {"|A", "|A", "-R", "/A", "|R", "-R"},
            {"-R", "/A", "/A", "|A", "-R", "-R"},
            {"-R", "-R", "|A", "-R", "/R", "-R"},
            {"\\R", "-R", "|R", "\\R", "|A", "|R"},
            {"\\R", "/R", "/R", "|A", "/A", "\\A"}
        };
        
        Tablero t = new Tablero(5, 6, 3);
        t.setTableritoActual(tablero);
            
        return t;
    } */
    
   public static Tablero tableroDesdeArchivo() throws FileNotFoundException {
            // Abrir el archivo "datos.txt" para lectura
            Scanner input = new Scanner(new File("C:\\Users\\Dell_\\Desktop\\Prog2\\Obligatorio-P2\\Obligatorio-P2\\src\\interfaz\\datos.txt"));

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
    
    public static Tablero tableroPredefinido() {                
        String[][] tablero = {
            {"|A", "|A", "-R", "/A", "|R", "-R"},
            {"-R", "/A", "/A", "|A", "-R", "-R"},
            {"-R", "-R", "|A", "-R", "/R", "-R"},
            {"\\R", "-R", "|R", "\\R", "|A", "|R"},
            {"\\R", "/R", "/R", "|A", "/A", "\\A"}
        };
        
        Tablero t = new Tablero(5, 6, 3);
        t.setTableritoActual(tablero);
            
        return t;
    }
    
    public boolean verificarTablero() {
        String[][] tabla = this.tableritoActual;
        String color = String.valueOf(tabla[0][0].charAt(1));
        boolean retorno = true;
        
        for(int i = 0; i < tabla.length; i++) {
            for(int j = 0; j < tabla[0].length; j++) {
                String celda = tabla[i][j];
                String colorActual = String.valueOf(celda.charAt(1));
             
                if(!colorActual.equals(color)) {
                    retorno = false;
                }
            }
        }
        
        return retorno;
    }
    
     public static Tablero tableroAleatorio(int filas, int columnas, int nivel) {
        Tablero tablero = new Tablero(filas, columnas, nivel);
        Random rand = new Random();

        // Define los símbolos y colores posibles
        String[] simbolos = {"|", "-", "\\", "/"};
        String[] colores = {"R", "A"};

        // Crea el tablero lleno de símbolos y colores aleatorios
        String[][] tableroActual = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String simbolo = simbolos[rand.nextInt(simbolos.length)];
                String color = colores[rand.nextInt(colores.length)];
                tableroActual[i][j] = simbolo + color;
            }
        }
        tablero.setTableritoActual(tableroActual);

        // Aplica movimientos aleatorios para generar el nivel requerido
        for (int i = 0; i < nivel; i++) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);
            aplicarMovimientoEnCelda(tablero, fila, columna);
        }
        return tablero;
    }
     
     private static void aplicarMovimientoEnCelda(Tablero tablero, int fila, int columna) {
        // Obtener el símbolo y color actual de la celda seleccionada
        String celdaActual = tablero.getTableritoActual()[fila][columna];

        juego.aplicarMovimiento(celdaActual, fila, columna, tablero.getTableritoActual());
    }

 
}

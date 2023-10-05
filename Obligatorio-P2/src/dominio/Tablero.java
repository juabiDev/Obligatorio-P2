/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author User
 */
public class Tablero {
    private int filas;
    private int columnas;
    private int nivel;
    private String [][] tableritoActual;
    private ArrayList<Movimiento> solucionTablero;
    
    public Tablero() { // el tablero tiene como minimo 3 filas y 3 columnas
        this.filas = 3;
        this.columnas = 3;
        this.nivel = 1;
        this.tableritoActual = new String[this.filas][this.columnas];
        this.solucionTablero = new ArrayList<>();
    }
    
    public Tablero(int filas, int columnas, int nivel) {
        try {
            boolean valido = validarTablero(filas, columnas, nivel);
            if (valido) {
                this.filas = filas;
                this.columnas = columnas;
                this.tableritoActual = new String[this.filas][this.columnas];
            }
        } catch (RuntimeException e) {
             throw new RuntimeException(e.getMessage());
        }
    }
    
    public ArrayList<Movimiento> getSolucionTablero() {
        return this.solucionTablero;
    }

    public void setSolucionTablero(ArrayList<Movimiento> solucionTablero) {
        this.solucionTablero = solucionTablero;
    }
    
    public String[][] getTableritoActual() {
        return this.tableritoActual;
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
    
    private boolean validarTablero(int filas, int columnas, int nivel) {
        boolean condFilas = (filas >= 3 && filas <= 9);
        boolean condCols = (columnas >= 3 && columnas <= 9);
        boolean condNivel = (nivel >= 1 && nivel <= 8);
        boolean valido = false;
        
        if (condFilas && condCols && condNivel) {
            valido = true;
        } else {
            String mensajeError = "Errores de validación:\n";

            if (!condFilas) {
                mensajeError += "- Cantidad de filas inválida (entre 3 y 9).\n";
            }

            if (!condCols) {
                 mensajeError += "- Cantidad de columnas inválida (entre 3 y 9).\n";
            }

            if (!condNivel) {
                mensajeError += "- Nivel inválido (entre 1 y 8).\n";
            }

            throw new RuntimeException(mensajeError);
        }
        return valido;
    }
  
    public boolean verificarTablero() {
        String[][] tablerito = this.tableritoActual;
        String color = String.valueOf(tablerito[0][0].charAt(1));
        boolean retorno = true;
        
        for (String[] tablerito1 : tablerito) {
            for (int j = 0; j < tablerito[0].length; j++) {
                String celda = tablerito1[j];
                String colorActual = String.valueOf(celda.charAt(1));
                if(!colorActual.equals(color)) {
                    retorno = false;
                }
            }
        }
        return retorno;
    }
    
    public Tablero tableroDesdeArchivo() throws FileNotFoundException {
        // Abrir el archivo "datos.txt" para lectura
        Scanner input = new Scanner(new File("C:\\Users\\User\\OneDrive\\Documentos\\NetBeansProjects\\Obligatorio-P2.1\\Obligatorio-P2\\src\\interfaz\\datos.txt"));

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
        int dificultad = input.nextInt();

        ArrayList<Movimiento> aux = new ArrayList<>();

        for(int i = 0; i < dificultad; i++) {
            int fila = input.nextInt();
            int columna = input.nextInt();
            Movimiento movimiento = new Movimiento(fila,columna);
            aux.add(movimiento);
        }

        // Cerrar el archivo
        input.close();

        Tablero t = new Tablero(m, n, dificultad);
        t.setSolucionTablero(aux);
        t.setTableritoActual(tablero);

        return t;
    }
    
    public Tablero tableroPredefinido() {                
        String[][] tablero = {
            {"|A", "|A", "-R", "/A", "|R", "-R"},
            {"-R", "/A", "/A", "|A", "-R", "-R"},
            {"-R", "-R", "|A", "-R", "/R", "-R"},
            {"\\R", "-R", "|R", "\\R", "|A", "|R"},
            {"\\R", "/R", "/R", "|A", "/A", "\\A"}
        };
        
        Tablero t = new Tablero(5, 6, 3);
        //ArrayList<Movimiento> solucionTablero = new ArrayList<>();
        
        Movimiento m1 = new Movimiento(4,4);
        Movimiento m2 = new Movimiento(5,6);
        Movimiento m3 = new Movimiento(5,4);

        solucionTablero.add(m1);
        solucionTablero.add(m2);
        solucionTablero.add(m3);

        t.setSolucionTablero(solucionTablero);
        t.setTableritoActual(tablero);
            
        return t;
    }
    
    public Tablero tableroAleatorio(int filas, int columnas, int nivel) {
        Tablero tablero = new Tablero(filas, columnas, nivel);
        Random rand = new Random();

        // Define los símbolos y colores posibles
        String[] simbolos = {"|", "-", "\\", "/"};
        String[] colores = {"R", "A"};
        String color = colores[rand.nextInt(colores.length)];

        // Crea el tablero lleno de símbolos y colores aleatorios
        String[][] tableroActual = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                String simbolo = simbolos[rand.nextInt(simbolos.length)];
                tableroActual[i][j] = simbolo + color;
            }
        }
        tablero.setTableritoActual(tableroActual);

        return tablero;
    }
}

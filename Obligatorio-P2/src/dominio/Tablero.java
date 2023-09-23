/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author User
 */
public class Tablero {
    private int filas;
    private int columnas;
    private int nivel;
    private String [][] tablerito;
    
    public Tablero() { // el tablero tiene como minimo 3 filas y 3 columnas
        this.filas = 3;
        this.columnas = 3;
        this.nivel = 1;
        this.tablerito = new String[this.filas][this.columnas];
    }
    
    public Tablero(int filas, int columnas, int nivel) {
        boolean valido = validarFilasColumnas(filas, columnas, nivel);   
        if(valido) {
            this.filas = filas;
            this.columnas = columnas;
            this.tablerito = new String[this.filas][this.columnas];
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
    
    public void generarAleatorio(int dificultad) {
        
    }
    
    public void llenarTablero() { // copilot
        
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.columnas ; j++) {
                int random = (int) (Math.random() * 2);
                if (random == 0) {
                    int random2 = (int) (Math.random() * 4);
                    if (random2 == 0) {
                        this.tablerito[i][j] = "R/";
                    } else if (random2 == 1) {
                        this.tablerito[i][j] = "R\\";
                    } else if (random2 == 2) {
                        this.tablerito[i][j] = "R|";
                    } else if (random2 == 3) {
                        this.tablerito[i][j] = "R-";
                    }
                } else if (random == 1) {
                    int random2 = (int) (Math.random() * 4);
                    if (random2 == 0) {
                        this.tablerito[i][j] = "A/";
                    } else if (random2 == 1) {
                        this.tablerito[i][j] = "A\\";
                    } else if (random2 == 2) {
                        this.tablerito[i][j] = "A|";
                    } else if (random2 == 3) {
                        this.tablerito[i][j] = "A-";
                    }
                }
            }
        }
    }
    
    
}

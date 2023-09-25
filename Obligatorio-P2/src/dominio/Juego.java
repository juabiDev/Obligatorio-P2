/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Juego {
    private Tablero tablero;
    private HistorialMov historialMovimientos;
    private long tiempoInicio;
    private long tiempoFin;
    
    public Juego() {
        this.tablero = new Tablero();
        this.historialMovimientos = new HistorialMov();
        this.tiempoInicio = System.currentTimeMillis();
        this.tiempoFin = System.currentTimeMillis();
    }
    
    public void iniciarJuego() {
        tiempoInicio = System.currentTimeMillis();
    }
    
    public void guardarMovimiento(int fila, int columna) {
        Movimiento m = new Movimiento(fila,columna);
        guardarHistorial(m);
    }
    
    public boolean verificarTablero() {
        return true;
    }
    
    public long obtenerTiempoTotal() {
        return tiempoFin - tiempoInicio;
    }

    public void guardarHistorial(Movimiento m) {
        historialMovimientos.agregarMovimiento(m);
    }
    
    public ArrayList obtenerHistorialMovimientos() {
        return historialMovimientos.obtenerHistorialCompleto();
    }
    
    public void crearTableroDeArchivo() throws FileNotFoundException {
       tablero = tablero.tableroDesdeArchivo();
    }
    
    public void crearTableroAleatorio(int unaDificultad) {
        tablero = tablero.tableroAleatorio(unaDificultad);
    }
    
    public void crearTableroPredefinido() {
        tablero = tablero.tableroPredefinido();
    }
    
    public String[][] obtenerTableroActual() {
        return tablero.getTableritoActual();
    }
    
    public String[][] obtenerTableroPrevio() {
        return tablero.getTableritoPrevio();
    }
    
    public void jugar(int fila, int columna) {
        int posicionFila = fila - 1;
        int posicionColumna = columna - 1;
        
        if (posicionFila >= 0 && posicionFila < this.tablero.getFilas() && 
        posicionColumna >= 0 && posicionColumna < this.tablero.getColumnas()) {
            String [][] tablero = this.tablero.getTableritoActual();
            this.tablero.setTableritoPrevio(tablero);
            
            String celda = tablero[posicionFila][posicionColumna];

            aplicarMovimiento(celda, posicionFila, posicionColumna);
            guardarMovimiento(fila, columna);
        }
    }
    
    /*
        tenemos que guardar el tablero previo, los movimientos
        y que esto se ejecute mientras se esta jugando, mostrar en la interfaz
        el previo y resultante, luego verificar que el tablero no sea todo de un color,
        luego mostrar solucion
    
    */
    
    public void aplicarMovimiento(String celda, int fila, int columna) {
        String[][] tablero = this.tablero.getTableritoActual();

        char simbolo = celda.charAt(0);

        switch (simbolo) {
            case '|':
                for (int i = 0; i < tablero.length; i++) {
                    String actual = tablero[i][columna];
                    String simboloActual = String.valueOf(actual.charAt(0));
                    String colorActual = String.valueOf(actual.charAt(1));
                    tablero[i][columna] = simboloActual + cambiarColorOpuesto(colorActual);
                }
                break;
            case '-':
                for (int j = 0; j < tablero[0].length; j++) {
                    String actual = tablero[fila][j];
                    String simboloActual = String.valueOf(actual.charAt(0));
                    String colorActual = String.valueOf(actual.charAt(1));
                    tablero[fila][j] = simboloActual + cambiarColorOpuesto(colorActual);
                }
                break;
            case '\\':
                int diferencia = fila - columna;
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[0].length; j++) {
                        int diferencia2 = i - j;
                        if (diferencia2 == diferencia) {
                            String actual = tablero[i][j];
                            String simboloActual = String.valueOf(actual.charAt(0));
                            String colorActual = String.valueOf(actual.charAt(1));
                            tablero[i][j] = simboloActual + cambiarColorOpuesto(colorActual);
                        }
                    }
                }
                break;
            case '/':
                int suma = fila + columna;
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[0].length; j++) {
                        int suma2 = i + j;
                        if (suma2 == suma) {
                            String actual = tablero[i][j];
                            String simboloActual = String.valueOf(actual.charAt(0));
                            String colorActual = String.valueOf(actual.charAt(1));
                            tablero[i][j] = simboloActual + cambiarColorOpuesto(colorActual);
                        }
                    }
                }
                break;
            //Default ???
        }
    }

    private String cambiarColorOpuesto(String colorActual) {
        return colorActual.equals("R") ? "A" : "R";
    }
}

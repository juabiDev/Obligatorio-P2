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
    
    public void realizarMovimiento(int fila, int columna) {
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
        tablero.tableroAleatorio(unaDificultad);
    }
    
    public void crearTableroPredefinido() {
        tablero.tableroPredefinido();
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
        
        if((posicionFila >= 3 && posicionFila <= this.tablero.getFilas()) && (posicionColumna >= 3 && posicionColumna <= this.tablero.getColumnas())) {
            String [][] tablero = this.tablero.getTableritoActual();
            String celda = tablero[posicionFila][posicionColumna];

            recorrida(celda, posicionFila, posicionColumna);
            realizarMovimiento(fila, columna);
        }
    }
    
    /*
        tenemos que guardar el tablero previo, los movimientos
        y que esto se ejecute mientras se esta jugando, mostrar en la interfaz
        el previo y resultante, luego verificar que el tablero no sea todo de un color,
        luego mostrar solucion
    
    */
    
    public void recorrida(String celda, int fila, int columna) {
        String[][] tablero = this.tablero.getTableritoActual();;
        
        // Guardamos el tablero antes de modificarlo
        String [][] tableroPrevio = this.tablero.getTableritoActual();
        this.tablero.setTableritoPrevio(tableroPrevio);
        
        char simbolo = celda.charAt(0);
        char color = celda.charAt(1);
        
        if(String.valueOf(simbolo).equals("|")) {
            for (int i = 0; i < tablero.length; i++) {
                
                String actual = tablero[i][columna];
                String simboloActual = String.valueOf(actual.charAt(0));
                String colorActual = String.valueOf(actual.charAt(1));
                            
                if(String.valueOf(colorActual).equals("R")) {
                    colorActual = "A";
                    tablero[i][columna] = simboloActual + colorActual;
                            
                } else {
                    colorActual = "R";
                    tablero[i][columna] = simboloActual + colorActual;
                }
                        
            }
        }      
    
        if(String.valueOf(simbolo).equals("-")) {

            for( int j = 0; j < tablero[0].length; j++) {
                
                String actual = tablero[fila][j];
                String simboloActual = String.valueOf(actual.charAt(0));
                String colorActual = String.valueOf(actual.charAt(1));
                            
                if(String.valueOf(colorActual).equals("R")) {
                    colorActual = "A";
                    tablero[fila][j] = simboloActual + colorActual;
                            
                } else {
                    colorActual = "R";
                    tablero[fila][j] = simboloActual + colorActual;
                }
            
            }

        }
        
        if(String.valueOf(simbolo).equals("\\")) {
            int diferencia = fila - columna;
            
            for(int i = 0; i < tablero.length; i++) {
                for(int j = 0; j < tablero[0].length; j++) {
                    int diferencia2 = i - j;
                    if(diferencia2 == diferencia) {
                        
                        String actual = tablero[i][j];
                        String simboloActual = String.valueOf(actual.charAt(0));
                        String colorActual = String.valueOf(actual.charAt(1));
                
                        if(String.valueOf(colorActual).equals("R")) {
                            colorActual = "A";
                            tablero[i][j] = simboloActual + colorActual;

                        } else {
                            colorActual = "R";
                            tablero[i][j] = simboloActual + colorActual;
                        }
                    }
                    
                }
            }
        }
        
        if(String.valueOf(simbolo).equals("/")) {
            int diferencia = fila + columna;
            
            for(int i = 0; i < tablero.length; i++) {
                for(int j = 0; j < tablero[0].length; j++) {
                    int diferencia2 = i + j;
                    if(diferencia2 == diferencia) {
                        
                        System.out.println(tablero[i][j]);
                        
                        String actual = tablero[i][j];
                        String simboloActual = String.valueOf(actual.charAt(0));
                        String colorActual = String.valueOf(actual.charAt(1));
                
                        if(String.valueOf(colorActual).equals("R")) {
                            colorActual = "A";
                            tablero[i][j] = simboloActual + colorActual;

                        } else {
                            colorActual = "R";
                            tablero[i][j] = simboloActual + colorActual;
                        }
                    }
                    
                }
            }
        }
        
     
    }
  
}

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
    private HistorialTableros historialTableros;
    private long tiempoInicio;
    private long tiempoFin;
    //private ArrayList<Camion> listaCamiones;

    public Juego() {
        this.tablero = new Tablero();
        this.historialMovimientos = new HistorialMov();
        this.historialTableros = new HistorialTableros();
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
    
    public long obtenerTiempoTotal() {
        return tiempoFin - tiempoInicio;
    }

    public void guardarHistorial(Movimiento m) {
        historialMovimientos.agregarMovimiento(m);
    }
    
    public void guardarTablero(String[][] unTablero) {
        this.historialTableros.agregarTablero(unTablero);
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
        return this.tablero.getTableritoActual();
    }
    
    public void jugar(int fila, int columna) {
        int posicionFila = fila - 1;
        int posicionColumna = columna - 1;
        boolean condicion1 = posicionFila >= 0 && posicionFila < this.tablero.getFilas();
        boolean condicion2 = posicionColumna >= 0 && posicionColumna < this.tablero.getColumnas();
        boolean condicion3 = fila == -1 && columna == -1;
        
        if ((condicion1 && condicion2) || condicion3) {

            
            if(!(fila == -1 && columna == -1)) {
                String[][] tableroPrevio = this.obtenerUltimoTablero();
                String[][] tableroNuevo = copiarTablero(tableroPrevio);

                String celda = tableroNuevo[posicionFila][posicionColumna];

                aplicarMovimiento(celda, posicionFila, posicionColumna, tableroNuevo);
                
                this.historialTableros.agregarTablero(tableroNuevo);
            
                this.tablero.setTableritoActual(tableroNuevo);
                
            } else {          
                this.borrarUltimoTablero();
                
                String[][] tableroPrevio = this.obtenerUltimoTablero();
                String[][] tableroNuevo = copiarTablero(tableroPrevio);
                
                this.tablero.setTableritoActual(tableroNuevo);
            }
            
            guardarMovimiento(fila, columna);
            
            this.tiempoFin = System.currentTimeMillis();
        }
    }
    
    private String[][] copiarTablero(String[][] unTablero) {
        String[][] retorno = new String[unTablero.length][unTablero[0].length];
        
        for (int i = 0; i < unTablero.length; i++) {
            for (int j = 0; j < unTablero[0].length; j++) {
                retorno[i][j] = unTablero[i][j];
            }
        }
        
        return retorno;
    }
    
    public boolean juegoTerminado() {
        boolean retorno = this.tablero.verificarTablero();
        if(retorno) {
            this.tiempoFin = System.currentTimeMillis();
        }

        return retorno;
    }
    
    public String[][] obtenerUltimoTablero() {
        return historialTableros.obtenerUltimoTablero();
    }
    
    public String[][][] obtenerUltimosDosTableros() {
        return historialTableros.obtenerUltimosDosTableros();
    }
    
    public void borrarUltimoTablero() {
        this.historialTableros.borrarUltimoTablero();
    }
    
    /*
        tenemos que guardar el tablero previo, los movimientos
        y que esto se ejecute mientras se esta jugando, mostrar en la interfaz
        el previo y resultante, luego verificar que el tablero no sea todo de un color,
        luego mostrar solucion
    
    */
    
    public void aplicarMovimiento(String celda, int fila, int columna, String[][] tableroNuevo) {

        char simbolo = celda.charAt(0);

        switch (simbolo) {
            case '|':
                for (int i = 0; i < tableroNuevo.length; i++) {
                    String actual = tableroNuevo[i][columna];
                    String simboloActual = String.valueOf(actual.charAt(0));
                    String colorActual = String.valueOf(actual.charAt(1));
                    tableroNuevo[i][columna] = simboloActual + cambiarColorOpuesto(colorActual);
                }
                break;
            case '-':
                for (int j = 0; j < tableroNuevo[0].length; j++) {
                    String actual = tableroNuevo[fila][j];
                    String simboloActual = String.valueOf(actual.charAt(0));
                    String colorActual = String.valueOf(actual.charAt(1));
                    tableroNuevo[fila][j] = simboloActual + cambiarColorOpuesto(colorActual);
                }
                break;
            case '\\':
                int diferencia = fila - columna;
                for (int i = 0; i < tableroNuevo.length; i++) {
                    for (int j = 0; j < tableroNuevo[0].length; j++) {
                        int diferencia2 = i - j;
                        if (diferencia2 == diferencia) {
                            String actual = tableroNuevo[i][j];
                            String simboloActual = String.valueOf(actual.charAt(0));
                            String colorActual = String.valueOf(actual.charAt(1));
                            tableroNuevo[i][j] = simboloActual + cambiarColorOpuesto(colorActual);
                        }
                    }
                }
                break;
            case '/':
                int suma = fila + columna;
                for (int i = 0; i < tableroNuevo.length; i++) {
                    for (int j = 0; j < tableroNuevo[0].length; j++) {
                        int suma2 = i + j;
                        if (suma2 == suma) {
                            String actual = tableroNuevo[i][j];
                            String simboloActual = String.valueOf(actual.charAt(0));
                            String colorActual = String.valueOf(actual.charAt(1));
                            tableroNuevo[i][j] = simboloActual + cambiarColorOpuesto(colorActual);
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

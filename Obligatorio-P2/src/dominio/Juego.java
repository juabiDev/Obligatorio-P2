/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author User
 */
public class Juego {
    private Tablero tablero;
    private Historial historiales;
    private long tiempoInicio;
    private long tiempoFin;

    public Juego() {
        this.tablero = new Tablero();
        this.historiales = new Historial();
        this.tiempoInicio = System.currentTimeMillis();
        this.tiempoFin = System.currentTimeMillis();
    }
    
    /* --- Tiempo de Juego --- */
    
    public void iniciarJuego() {
        tiempoInicio = System.currentTimeMillis();
    }
    
    public long obtenerTiempoTotal() {
        return tiempoFin - tiempoInicio;
    }
    
    /* --- Historiales --- */
    
    public void guardarMovimiento(int fila, int columna) {
        Movimiento m = new Movimiento(fila,columna);
        guardarHistorialMov(m);
    }
    
    public void guardarHistorialMov(Movimiento m) {
        historiales.agregarMovimiento(m);
    }
    
    public void guardarTablero(String[][] unTablero) {
        this.historiales.agregarTablero(unTablero);
    }
    
    public ArrayList obtenerHistorialMovimientos() {
        return historiales.obtenerMovimientos();
    }
    
    public String[][] obtenerUltimoTablero() {
        return historiales.obtenerUltimoTablero();
    }
    
    public String[][][] obtenerUltimosDosTableros() {
        return historiales.obtenerUltimosDosTableros();
    }
    
    public void borrarUltimoTablero() {
        this.historiales.borrarUltimoTablero();
    }
    
    public void guardarSolucion(int fila, int columna) { 
        Movimiento m = new Movimiento(fila,columna);
        guardarHistorialSol(m);
    }
    
    public void guardarHistorialSol(Movimiento m) {
        historiales.agregarSolucion(m);
    }
        
    public ArrayList<Movimiento> getSolucion() {
        return historiales.obtenerSolucion();
    }
    
    
    /* --- Creación de Tableros --- */
    
    public void crearTableroDeArchivo() throws FileNotFoundException {
       tablero = tablero.tableroDesdeArchivo();
       
       tablero.getSolucionTablero().forEach((element) -> {
           guardarSolucion(element.getFila(),element.getColumna());
       });
    }
    
    public void crearTableroAleatorio(int filas, int columnas, int nivel) {
        
        Tablero t = tablero.tableroAleatorio(filas, columnas, nivel);
        
        Random rand = new Random();
        // Define los símbolos y colores posibles
        String[] simbolos = {"|", "-", "\\", "/"};
        String[] colores = {"R", "A"};
        
        int contadorMovimientos = 0;
                
        // Aplica movimientos aleatorios para generar el nivel requerido
        for (int i = 0; contadorMovimientos < nivel; i++) {
            int fila = rand.nextInt(filas);
            int columna = rand.nextInt(columnas);
            boolean realizado = aplicarMovimientoEnCelda(t, fila, columna);
            
            if(realizado) {
                guardarSolucion(fila+1, columna+1);
                contadorMovimientos++;
            }
                        
        }
        
        this.tablero = t;
    }

    public void crearTableroPredefinido() {
        tablero = tablero.tableroPredefinido();
        
        tablero.getSolucionTablero().forEach((element) -> {
           guardarSolucion(element.getFila(),element.getColumna());
        });
    }
    
    public String[][] obtenerTableroActual() {
        return this.tablero.getTableritoActual();
    }
    
    /* --- Jugabilidad --- */
    
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
                
                this.historiales.agregarTablero(tableroNuevo);
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
    
    public boolean juegoTerminado() {
        boolean retorno = this.tablero.verificarTablero();
        if(retorno) {
            
            this.tiempoFin = System.currentTimeMillis();
        }

        return retorno;
    }
    
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

    /* --- Funciones Auxiliares --- */
    
    private String[][] copiarTablero(String[][] unTablero) {
        String[][] retorno = new String[unTablero.length][unTablero[0].length];
        
        for (int i = 0; i < unTablero.length; i++) {
            for (int j = 0; j < unTablero[0].length; j++) {
                retorno[i][j] = unTablero[i][j];
            }
        }
        
        return retorno;
    }
        
    private String cambiarColorOpuesto(String colorActual) {
        return colorActual.equals("R") ? "A" : "R";
    }
    
    private boolean aplicarMovimientoEnCelda(Tablero tablero, int fila, int columna) { 
        // Obtener el símbolo y color actual de la celda seleccionada
        
        int posicionFila = fila + 1;
        int posicionColumna = columna + 1;

        boolean existe = existenMovimientos(posicionFila,posicionColumna);
        
        if(!existe) {
            String celdaActual = tablero.getTableritoActual()[fila][columna];
            aplicarMovimiento(celdaActual, fila, columna, tablero.getTableritoActual());
        }
        
        return !existe;

    }
    
    private boolean existenMovimientos(int fila, int columna) {
        boolean existe = false;
        
        for(Movimiento unMovimiento : getSolucion()) {
            if(unMovimiento.getColumna() == columna && unMovimiento.getFila() == fila) {
                existe = true;
            }
        }
        
        return existe;
    }
    
}

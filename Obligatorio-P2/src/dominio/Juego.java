/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.FileNotFoundException;

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
    
    public void realizarMovimiento(Movimiento m) {
        
    }
    
    public boolean verificarTablero() {
        return true;
    }
    
    public long obtenerTiempoTotal() {
        return tiempoFin - tiempoInicio;
    }

    public void guardarHistorial(Movimiento m) {
        
    }
    
    public String[][] obtenerTableroDeArchivo() throws FileNotFoundException {
        return tablero.tableroDesdeArchivo();
    }
    
    public String[][] obtenerTableroAleatorio(int unaDificultad) {
        return tablero.tableroAleatorio(unaDificultad);
    }
    
    public String[][] obtenerTableroPredefinido() {
        return tablero.tableroPredefinido();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

/**
 *
 * @author 
 * Dana Cizin 239510
 * Fabian Mederos 281938
 */
public class Movimiento {
    private int fila;
    private int columna;
    
    public Movimiento() {
        this.fila = 0;
        this.columna = 0;
    }
    
    public int getFila() {
        return this.fila;
    }
    
    public int getColumna() {
        return this.columna;
    }
     
    public Movimiento(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    @Override
    public String toString() {
        return "("+fila+","+columna+")";
    }
    
    @Override
    public boolean equals(Object obj) {
        Movimiento otroObjeto = (Movimiento) obj;
        return this.getFila() == otroObjeto.getFila() && this.getColumna() == otroObjeto.getColumna();
    } 
}

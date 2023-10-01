/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author User
 */
public class Historial {
    private ArrayList<Movimiento> solucion;
    private ArrayList<Movimiento> movimientos;
    private ArrayList<String[][]> tableros;
    
    public Historial() {
        this.solucion = new ArrayList<>();
        this.movimientos = new ArrayList<>();
        this.tableros = new ArrayList<>();
    }
    
    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }
    
    public ArrayList<Movimiento> obtenerMovimientos() {
        return movimientos;
    }
    
    public void agregarSolucion(Movimiento movimiento) {
        solucion.add(movimiento);
    }
        
    public ArrayList<Movimiento> getSolucion() {
        ArrayList<Movimiento> copiaSolucion = new ArrayList<>(solucion);
        
        copiaSolucion.addAll(obtenerMovimientos());
        
        return copiaSolucion;
    }
    
    public void agregarTablero(String[][] unTablero) {
        // Copiar el tablero y agregar la copia al historial
        String[][] copiaTablero = new String[unTablero.length][unTablero[0].length];
        
        
        for (int i = 0; i < unTablero.length; i++) {
            copiaTablero[i] = Arrays.copyOf(unTablero[i], unTablero[i].length);
        }
        
        tableros.add(copiaTablero);
    }
    
    public String[][] obtenerUltimoTablero() {
        String[][] ultimo = null;
        
        for(int i = 0; i < tableros.size(); i++) {
            if(i == tableros.size() - 1) {
                ultimo = tableros.get(i);
            }
        }

        return ultimo;
    }
    
    public String[][][] obtenerUltimosDosTableros() {
        String[][][] ultimas = new String[2][][];
        
        for(int i = 0; i < tableros.size(); i++) {
            if(i == tableros.size() - 2) {
                ultimas[0] = tableros.get(i);
            }
                        
            if(i == tableros.size() - 1) {
                ultimas[1] = tableros.get(i);
            }
        }
                
        return ultimas;
    }
    
    public ArrayList obtenerTableros() {
        return tableros;
    }
    
    public void guardarMovimiento(int fila, int columna) {
        Movimiento m = new Movimiento(fila,columna);
        guardarHistorialMov(m);
    }
    
    public void guardarHistorialMov(Movimiento m) {
       agregarMovimiento(m);
    }
    
    public void borrarUltimoTablero() {
        int cantidadDeTableros = this.obtenerTableros().size();
        
        if (cantidadDeTableros > 1) {
            tableros.remove(tableros.size() - 1);
        } else {
            throw new RuntimeException("No se puede retroceder, porque estamos en el tablero inicial.");
        }
    }
    
    public void guardarSolucion(int fila, int columna) { 
        Movimiento m = new Movimiento(fila,columna);
        guardarHistorialSol(m);
    }
    
    public void guardarHistorialSol(Movimiento m) {
        agregarSolucion(m);
    }

    
}

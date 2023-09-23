/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class HistorialMov {
    private ArrayList<Movimiento> movimientos;
    
    public HistorialMov() {
        this.movimientos = new ArrayList<>();
    }
    
    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }
    
    public ArrayList obtenerHistorialCompleto() {
        return movimientos;
    }
    
}

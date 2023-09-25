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
public class HistorialTableros {
    private ArrayList<String[][]> tableros = new ArrayList<>();
    
    public HistorialTableros() {
        this.tableros = new ArrayList<>();
    }
    
    public void agregarTablero(String[][] unTablero) {
        // Copiar el tablero y agregar la copia al historial
        String[][] copiaTablero = new String[unTablero.length][unTablero[0].length];
        for (int i = 0; i < unTablero.length; i++) {
            copiaTablero[i] = Arrays.copyOf(unTablero[i], unTablero[i].length);
        }
        tableros.add(copiaTablero);
    }
    
    public String[][] obtenerTablero(int posicion) {
        if (posicion >= 0 && posicion < tableros.size()) {
            return tableros.get(posicion);
        } else {
            return null; // O puedes lanzar una excepción si lo prefieres
        }
    }
    
    public ArrayList obtenerHistorialCompleto() {
        return tableros;
    }
    
    public void imprimirLista() {
        for (String[][] unTablero : tableros) {
            System.out.println(unTablero[0]);
        }
    }
}

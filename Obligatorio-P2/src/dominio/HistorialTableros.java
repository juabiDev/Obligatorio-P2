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
        tableros.add(unTablero);
    }
    
    public String[][] obtenerTablero(int posicion) {
        String[][] retorno = null;
        
        for(String[][] unTablero : tableros) {
            retorno = tableros.get(posicion);
        }
        
        return retorno;
    }
    
    public ArrayList obtenerHistorialCompleto() {
        return tableros;
    }
    
    public void imprimirLista() {
        for(String[][] unTablero : tableros) {
            System.out.println(Arrays.toString(unTablero[0]));
        }
    }
}

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
    
    public ArrayList obtenerHistorialCompleto() {
        return tableros;
    }
    
    public void borrarUltimoTablero() {
        if(tableros.size() > 1) {
            tableros.remove(tableros.size() - 1);
        } else {
            System.out.println("No hay tablero anterior");
        }
    }
    
    public void imprimirLista() {
        for (String[][] unTablero : tableros) {
            System.out.println(Arrays.toString(unTablero[0]));
        }
    }
}

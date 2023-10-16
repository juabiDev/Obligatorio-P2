/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author
 * Dana Cizin 239510
 * Fabian Mederos 281938
 */
public class Historial {

  private ArrayList<Movimiento> solucion;
  private ArrayList<Movimiento> movimientos;

  public Historial() {
    this.solucion = new ArrayList<>();
    this.movimientos = new ArrayList<>();
  }
  
  public ArrayList<Movimiento> obtenerMovimientos() {
    return movimientos;
  }

  public ArrayList<Movimiento> getSolucion() {
    ArrayList<Movimiento> respuestaSolucion = new ArrayList<>();
    //Agregamos movimientos sobrantes (estan en historial pero no en solucion) a la solucion
    for (int i = 0; i < this.movimientos.size(); i++) {
        if (!this.solucion.contains(this.movimientos.get(i)) && !respuestaSolucion.contains(this.movimientos.get(i))) {
            int contador = 0;
            for (int j = 0; j < this.movimientos.size(); j++) {
                if (this.movimientos.get(j).equals(this.movimientos.get(i))) {
                    contador++;
                }
            }
            if ((contador % 2) != 0) {
                respuestaSolucion.add(this.movimientos.get(i));
            }
        }
    }  
    //Agregamos a la solucion los movimientos de historial que estan par veces
    for (int i = 0; i < this.solucion.size(); i++) {
        int contador = 0;
        for (int j = 0; j < this.movimientos.size(); j++) {
            if (this.movimientos.get(j).equals(this.solucion.get(i))) {
                contador++;
            }
        }
        if ((contador % 2) == 0) {
            respuestaSolucion.add(this.solucion.get(i));
        }
    }
    return respuestaSolucion;
  }

  public void guardarMovimiento(int fila, int columna) {
    Movimiento m = new Movimiento(fila, columna);
    if (m.getColumna() == -1 && m.getFila() == -1) {
      movimientos.remove(movimientos.size() - 1);
    } else {
      movimientos.add(m);
    }
  }

  public void guardarSolucion(int fila, int columna) {
    Movimiento m = new Movimiento(fila, columna);
    solucion.add(m);
  }
}

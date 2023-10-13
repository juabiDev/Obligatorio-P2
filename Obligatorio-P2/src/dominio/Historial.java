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

  public void agregarMovimiento(Movimiento movimiento) {
    boolean existeEnSolucion = false;

    if (movimiento.getColumna() == -1 && movimiento.getFila() == -1) {
      if (!this.obtenerMovimientos().isEmpty()) {
        Movimiento ultimoMovimientoHistorial =
          this.obtenerMovimientos().get(obtenerMovimientos().size() - 1);

        for (int i = 0; i < this.solucion.size(); i++) {
          // Verificamos si ese ultimo movimiento es parte de la solución

          if (
            solucion.get(i).getColumna() ==
            ultimoMovimientoHistorial.getColumna() &&
            solucion.get(i).getFila() == ultimoMovimientoHistorial.getFila()
          ) {
            // puede haber mas de un elemento, pero en caso de retroceder hay que borrar solo uno

            if (!existeEnSolucion) {
              existeEnSolucion = true;
              this.solucion.remove(i);
            }
          }
        }
      }

      // Si el ultimo elemento del historial no es parte de la solucion, borramos el ultimo elemento de la solucion
      if (!existeEnSolucion) {
        solucion.remove(solucion.size() - 1);
      }

      movimientos.remove(movimientos.size() - 1);
    } else {
      solucion.add(movimiento);
      movimientos.add(movimiento);
    }
  }

  public ArrayList<Movimiento> obtenerMovimientos() {
    return movimientos;
  }

  public void agregarSolucion(Movimiento movimiento) {
    solucion.add(movimiento);
  }

  public ArrayList<Movimiento> getSolucion() {
    ArrayList<Movimiento> copiaSolucion = new ArrayList<>(solucion);

    ArrayList<Movimiento> copiaSolucionSinRepetidos = new ArrayList<>();

    for (int i = 0; i < copiaSolucion.size(); i++) {
      Movimiento unMovimiento = copiaSolucion.get(i);
      boolean sonIguales = false;
      for (int j = 0; j < copiaSolucion.size(); j++) {
        Movimiento otroMovimiento = copiaSolucion.get(j);
        if (unMovimiento.equals(otroMovimiento) && i != j) {
          sonIguales = true;
        }
      }

      if (!sonIguales) {
        copiaSolucionSinRepetidos.add(unMovimiento);
      }
    }
    return copiaSolucionSinRepetidos;
  }

  public void guardarMovimiento(int fila, int columna) {
    Movimiento m = new Movimiento(fila, columna);
    guardarHistorialMov(m);
  }

  private void guardarHistorialMov(Movimiento m) {
    agregarMovimiento(m);
  }

  public void guardarSolucion(int fila, int columna) {
    Movimiento m = new Movimiento(fila, columna);
    guardarHistorialSol(m);
  }

  private void guardarHistorialSol(Movimiento m) {
    agregarSolucion(m);
  }
}

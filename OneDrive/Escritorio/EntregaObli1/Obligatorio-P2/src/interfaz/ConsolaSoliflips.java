/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

import java.util.ArrayList;

/**
 *
 * @author
 * Dana Cizin 239510
 * Fabian Mederos 281938
 */
public class ConsolaSoliflips {

  public void mostrarMenuPrincipal() {
    System.out.println("Bienvenido al juego Soliflips!");
    System.out.println("Seleccione una opción de tablero para comenzar:");
    System.out.println("a) Cargar datos desde archivo 'datos.txt'");
    System.out.println("b) Usar el tablero predefinido");
    System.out.println("c) Generar un tablero aleatorio");
    System.out.print("Opción: ");
  }

  public void imprimirTablero(String[][] tablero) {
    int filas = tablero.length;
    int columnas = tablero[0].length;

    // Encontrar la longitud del número de columna más largo
    int longitudNumero = String.valueOf(columnas).length();

    // Definir los códigos ANSI para los colores
    String colorRojo = "\033[31m";
    String colorAzul = "\033[34m";
    String colorReset = "\033[0m"; // Restablecer el color al predeterminado

    // Imprimir números de columna
    System.out.print(" ");
    for (int i = 1; i <= columnas; i++) {
      String columnaFormateada = String.format("%-" + longitudNumero + "s", i);
      System.out.print("   " + columnaFormateada);
    }
    System.out.println();

    for (int fila = 0; fila < filas; fila++) {
      // Imprimir línea superior de la celda
      System.out.print("  +");
      for (int columna = 0; columna < columnas; columna++) {
        System.out.print("---+");
      }
      System.out.println();

      // Imprimir contenido de la celda
      String numeroFila = String.valueOf(fila + 1);
      String filaFormateada = String.format(
        "%-" + longitudNumero + "s",
        numeroFila
      );
      System.out.print(filaFormateada + " ");
      for (int columna = 0; columna < columnas; columna++) {
        String celda = tablero[fila][columna];
        char simbolo = celda.charAt(0);
        char color = celda.charAt(1);

        String colorCelda = (color == 'R') ? colorRojo : colorAzul;

        // Formatear y mostrar el símbolo con el color apropiado
        String simboloFormateado = String.format(
          "%-3s",
          colorCelda + simbolo + colorReset
        );
        System.out.print("| " + simboloFormateado + " ");
      }
      System.out.println("|");
    }

    // Imprimir línea inferior de la matriz
    System.out.print("  +");
    for (int columna = 0; columna < columnas; columna++) {
      System.out.print("---+");
    }
    System.out.println();
  }

  public void mostrarSubMenu() {
    System.out.println("Seleccione una opción:");
    System.out.println("M) Realizar movimiento fila columna");
    System.out.println("X) Terminar este juego");
    System.out.println("H) Mostrar historial de movimientos (fila, columna)");
    System.out.println("S) Mostrar solución");
    System.out.print("Opción: ");
    System.out.println();
  }

  public void imprimirMovimientos(ArrayList movimientos) {
    for (int i = 0; i < movimientos.size(); i++) {
      System.out.print(movimientos.get(i));
    }
  }

  public void imprimirTablerosLadoALado(
    String[][] tableroPrevio,
    String[][] tableroActual
  ) {
    int filas = tableroActual.length;
    int columnas = tableroActual[0].length;

    // Definir los códigos ANSI para los colores
    String colorRojo = "\033[31m";
    String colorAzul = "\033[34m";
    String colorReset = "\033[0m";

    // Encontrar la longitud del número de columna más largo
    int longitudNumero = String.valueOf(columnas).length();

    // Imprimir números de columna para el tablero previo
    System.out.print(" ");
    for (int i = 1; i <= columnas; i++) {
      String columnaFormateada = String.format("%-" + longitudNumero + "s", i);
      System.out.print("   " + columnaFormateada);
    }

    System.out.print("          ");

    // Imprimir números de columna para el tablero actual
    for (int i = 1; i <= columnas; i++) {
      String columnaFormateada = String.format("%-" + longitudNumero + "s", i);
      System.out.print("   " + columnaFormateada);
    }
    System.out.println();

    for (int fila = 0; fila < filas; fila++) {
      // Línea superior de las celdas para el tablero previo
      System.out.print("  +");
      for (int columna = 0; columna < columnas; columna++) {
        System.out.print("---+");
      }
      System.out.print("  ==>  ");

      // Línea superior de las celdas para el tablero actual
      System.out.print("  +");
      for (int columna = 0; columna < columnas; columna++) {
        System.out.print("---+");
      }
      System.out.println();

      // Imprime números de fila para el tablero previo
      String filaFormateada = String.format(
        "%-" + longitudNumero + "s",
        fila + 1
      );
      System.out.print(filaFormateada + " ");
      for (int columna = 0; columna < columnas; columna++) {
        String celda = tableroPrevio[fila][columna];
        char simbolo = celda.charAt(0);
        char color = celda.charAt(1);

        // Determinar el color a aplicar
        String colorCelda = (color == 'R') ? colorRojo : colorAzul;

        String simboloFormateado = String.format(
          "%-3s",
          colorCelda + simbolo + colorReset
        );
        System.out.print("| " + simboloFormateado + " ");
      }

      System.out.print("|  ==>  ");

      // Imprime números de fila para el tablero actual
      System.out.print(filaFormateada + " ");
      for (int columna = 0; columna < columnas; columna++) {
        String celda = tableroActual[fila][columna];
        char simbolo = celda.charAt(0);
        char color = celda.charAt(1);

        // Determinar el color a aplicar
        String colorCelda = (color == 'R') ? colorRojo : colorAzul;

        String simboloFormateado = String.format(
          "%-3s",
          colorCelda + simbolo + colorReset
        );
        System.out.print("| " + simboloFormateado + " ");
      }
      System.out.println("|");
    }

    // Línea inferior de las matrices para el tablero previo
    System.out.print("  +");
    for (int columna = 0; columna < columnas; columna++) {
      System.out.print("---+");
    }
    System.out.print("  ==>  ");

    // Línea inferior de las matrices para el tablero actual
    System.out.print("  +");
    for (int columna = 0; columna < columnas; columna++) {
      System.out.print("---+");
    }
    System.out.println();
  }
}

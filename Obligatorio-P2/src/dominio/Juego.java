/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dominio;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 
 * Dana Cizin 239510
 * Fabian Mederos 281938
 */
public class Juego {
    private Tablero tablero;
    private Historial historiales;
    private long tiempoInicio;
    private long tiempoFin;

    
    public Juego() {
        this.tablero = new Tablero();
        this.historiales = new Historial();
        this.tiempoInicio = System.currentTimeMillis();
        this.tiempoFin = System.currentTimeMillis();
    }

    public Historial getHistoriales() {
        return historiales;
    }
    
    public String[][] obtenerTableroActual() {
        return this.tablero.getTableritoActual();
    }
        
    /* --- Tiempo de Juego --- */
    
    public void iniciarJuego() {
        tiempoInicio = System.currentTimeMillis();
    }
    
    public String obtenerTiempoTotal() {
        long tiempoTotalSegundos = (tiempoFin - tiempoInicio) / 1000;

        long horas = tiempoTotalSegundos / 3600;
        long minutos = (tiempoTotalSegundos % 3600) / 60;
        long segundos = tiempoTotalSegundos % 60;

        String tiempoTotalFormato = String.format("%02d:%02d:%02d", horas, minutos, segundos);

        return tiempoTotalFormato;
    }
    
    /* --- Historiales desde Sistema --- */

    
    public ArrayList obtenerHistorialMovimientos() {
        return historiales.obtenerMovimientos();
    }
    
    /* --- Creación de Tableros --- */
    
    public void crearTableroDeArchivo() throws FileNotFoundException {
       tablero = tablero.tableroDesdeArchivo();
       
       tablero.getSolucionTablero().forEach((element) -> {
           historiales.guardarSolucion(element.getFila(),element.getColumna());
       });
    }
    
    public void crearTableroAleatorio(int filas, int columnas, int nivel) {
        
        Random rand = new Random();
        Tablero t;
        do {

            t = tablero.tableroAleatorio(filas, columnas, nivel);

            int contadorMovimientos = 0;

            // Aplica movimientos aleatorios para generar el nivel requerido
            for (int i = 0; contadorMovimientos < nivel; i++) {
                int fila = rand.nextInt(filas);
                int columna = rand.nextInt(columnas);
                boolean realizado = aplicarMovimientoEnCelda(t, fila, columna);

                if (realizado) {
                    historiales.guardarSolucion(fila + 1, columna + 1);
                    contadorMovimientos++;
                }
            }


        } while (t.verificarTablero()); 
        
        this.tablero = t;
    }

    public void crearTableroPredefinido() {
        tablero = tablero.tableroPredefinido();
        
        tablero.getSolucionTablero().forEach((element) -> {
           historiales.guardarSolucion(element.getFila(),element.getColumna());
        });
    }
    
    /* --- Jugabilidad --- */
    
    public boolean jugar(int fila, int columna) {
        int posicionFila = fila - 1;
        int posicionColumna = columna - 1;
        boolean filaValida = posicionFila >= 0 && posicionFila < this.tablero.getTableritoActual().length;
        boolean columnaValida = posicionColumna >= 0 && posicionColumna < this.tablero.getTableritoActual()[0].length;
        boolean FilaColumnaRetroceso = fila == -1 && columna == -1;
        boolean tableroCompletado = this.tablero.verificarTablero();
        
        try {
            if (filaValida && columnaValida || FilaColumnaRetroceso) {
                
                if(!FilaColumnaRetroceso) {
                    String[][] tableroPrevio = tablero.getTableritoActual();
                    String[][] tableroNuevo = copiarTablero(tableroPrevio);

                    String celda = tableroNuevo[posicionFila][posicionColumna];

                    aplicarMovimiento(celda, posicionFila, posicionColumna, tableroNuevo);

                    this.tablero.setTableritoActual(tableroNuevo);

                } else {          
                    String[][] tableroPrevio = tablero.getTableritoActual();
                    String[][] tableroNuevo = copiarTablero(tableroPrevio);
                    if(historiales.obtenerMovimientos().size() > 0) {
                        Movimiento ultimoMovimiento = historiales.obtenerMovimientos().get(historiales.obtenerMovimientos().size() - 1);
                        String celda = tableroNuevo[ultimoMovimiento.getFila() - 1][ultimoMovimiento.getColumna() - 1];

                        System.out.println("fila:"+ultimoMovimiento.getFila());
                        System.out.println("columna:"+ultimoMovimiento.getColumna());

                        aplicarMovimiento(celda, ultimoMovimiento.getFila(), ultimoMovimiento.getColumna(), tableroNuevo);

                        this.tablero.setTableritoActual(tableroNuevo);
                    } else {
                        
                        throw new RuntimeException("No se puede aplicar paso de retroceso. Tablero sin modificaciones:");
                        
                    }

                }
                
                historiales.guardarMovimiento(fila, columna);
                tableroCompletado = this.tablero.verificarTablero();
                this.tiempoFin = System.currentTimeMillis();
            } else {
                throw new RuntimeException("Movimiento inválido.");
            }
            
        } catch(RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
        
        return tableroCompletado;
    }
    
    public void aplicarMovimiento(String celda, int fila, int columna, String[][] tableroNuevo) {
        char simbolo = celda.charAt(0);
        
        switch (simbolo) {
            case '|':
                for (int i = 0; i < tableroNuevo.length; i++) {
                    String actual = tableroNuevo[i][columna];
                    String simboloActual = String.valueOf(actual.charAt(0));
                    String colorActual = String.valueOf(actual.charAt(1));
                    tableroNuevo[i][columna] = simboloActual + cambiarColorOpuesto(colorActual);
                }
                break;
            case '-':
                for (int j = 0; j < tableroNuevo[0].length; j++) {
                    String actual = tableroNuevo[fila][j];
                    String simboloActual = String.valueOf(actual.charAt(0));
                    String colorActual = String.valueOf(actual.charAt(1));
                    tableroNuevo[fila][j] = simboloActual + cambiarColorOpuesto(colorActual);
                }
                break;
            case '\\':
                int diferencia = fila - columna;
                for (int i = 0; i < tableroNuevo.length; i++) {
                    for (int j = 0; j < tableroNuevo[0].length; j++) {
                        int diferencia2 = i - j;
                        if (diferencia2 == diferencia) {
                            String actual = tableroNuevo[i][j];
                            String simboloActual = String.valueOf(actual.charAt(0));
                            String colorActual = String.valueOf(actual.charAt(1));
                            tableroNuevo[i][j] = simboloActual + cambiarColorOpuesto(colorActual);
                        }
                    }
                }
                break;
            case '/':
                int suma = fila + columna;
                for (int i = 0; i < tableroNuevo.length; i++) {
                    for (int j = 0; j < tableroNuevo[0].length; j++) {
                        int suma2 = i + j;
                        if (suma2 == suma) {
                            String actual = tableroNuevo[i][j];
                            String simboloActual = String.valueOf(actual.charAt(0));
                            String colorActual = String.valueOf(actual.charAt(1));
                            tableroNuevo[i][j] = simboloActual + cambiarColorOpuesto(colorActual);
                        }
                    }
                }
                break;
            //Default ???
        }
    }

    /* --- Funciones Auxiliares --- */
    
    private String[][] copiarTablero(String[][] unTablero) {
        String[][] retorno = new String[unTablero.length][unTablero[0].length];
        
        for (int i = 0; i < unTablero.length; i++) {
           // ME SUGIERE: System.arraycopy(unTablero[i], 0, retorno[i], 0, unTablero[0].length);
            for (int j = 0; j < unTablero[0].length; j++) {
                retorno[i][j] = unTablero[i][j];
            }
        }
        return retorno;
    }
        
    private String cambiarColorOpuesto(String colorActual) {
        return colorActual.equals("R") ? "A" : "R";
    }
    
    private boolean aplicarMovimientoEnCelda(Tablero tablero, int fila, int columna) { 
        int posicionFila = fila + 1;
        int posicionColumna = columna + 1;

        boolean existe = existenMovimientos(posicionFila,posicionColumna);
        
        if(!existe) {
            String celdaActual = tablero.getTableritoActual()[fila][columna];
            aplicarMovimiento(celdaActual, fila, columna, tablero.getTableritoActual());
        }
        
        return !existe;
    }
    
    private boolean existenMovimientos(int fila, int columna) {
        boolean existe = false;
        
        for(Movimiento unMovimiento : historiales.getSolucion()) {
            if(unMovimiento.getColumna() == columna && unMovimiento.getFila() == fila) {
                existe = true;
            }
        }
        
        return existe;
    }
}

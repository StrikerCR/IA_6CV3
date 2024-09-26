package laboratorio3_2_ia_crr_fbdp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Laberinto {

    // Movimientos posibles: derecha, abajo, izquierda, arriba
    private static final int[][] posibilidades = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // Método que resuelve el laberinto utilizando BFS con Queue
    public static boolean resolver(int[][] laberinto, int xInicial, int yInicial, int xSalida, int ySalida) {
        Queue<int[]> cola = new LinkedList<>();  // Cambiamos Stack por Queue
        List<int[]> recorrido = new ArrayList<>();  // Lista para almacenar el camino

        cola.add(new int[]{xInicial, yInicial});  // Comenzamos en la entrada del laberinto

        while (!cola.isEmpty()) {
            int[] posicionActual = cola.poll();  // Extraemos la primera posición agregada (FIFO)
            int x = posicionActual[0];
            int y = posicionActual[1];

            // Añadimos la posición actual al recorrido
            recorrido.add(new int[]{x, y});

            // Si llegamos a la salida, imprimimos el recorrido
            if (x == xSalida && y == ySalida) {
                System.out.println("¡Salida encontrada!");
                imprimirRecorrido(recorrido);
                return true;
            }

            // Verificamos si la posición actual es válida
            if (esValido(laberinto, x, y)) {
                laberinto[x][y] = 2;  // Marcamos la posición como visitada

                // Exploramos los nuevos caminos
                for (int[] movimiento : posibilidades) {
                    int nuevoX = x + movimiento[0];
                    int nuevoY = y + movimiento[1];
                    cola.add(new int[]{nuevoX, nuevoY});  // Agregamos los nuevos caminos a la cola
                }
            }
        }

        // Si no se encuentra salida, se indica
        System.out.println("No se encontró la salida.");
        return false;
    }

    // Método para imprimir el recorrido almacenado en la lista
    private static void imprimirRecorrido(List<int[]> recorrido) {
        System.out.println("Recorrido desde el inicio hasta la salida:");
        for (int[] posicion : recorrido) {
            System.out.println("Posición: (" + posicion[0] + ", " + posicion[1] + ")");
        }
    }

    // Verifica si una posición es válida y no ha sido visitada
    private static boolean esValido(int[][] laberinto, int x, int y) {
        return x >= 0 && x < laberinto.length && y >= 0 && y < laberinto[0].length && laberinto[x][y] == 0;
    }

    // Método principal
    public static void main(String[] args) {
        // Definimos el laberinto: 1 = pared, 0 = camino
        int[][] laberinto = {
            {1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0},
            {1, 1, 1, 1, 1}
        };

        // Ejecutamos la resolución del laberinto desde la posición inicial a la de salida
        resolver(laberinto, 0, 1, 3, 4);
    }
}
package laboratorio4_ia_crr_fbdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 * 
 **/

public class LaboratorioAEstrella {
    // Movimientos posibles: arriba, abajo, izquierda, derecha
    static int[][] movimiento = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    
    /*static int[][] laberinto = {
        { 1, 0, 1, 1, 1 },
        { 1, 0, 0, 0, 1 },
        { 1, 1, 1, 0, 1 },
        { 1, 0, 0, 0, 0 },
        { 1, 1, 1, 1, 1 }
    };*/

    static int[][] laberinto = {
        { 1, 0, 1, 1, 1, 1, 1, 0, 1, 1 },
        { 1, 0, 0, 0, 0, 1, 1, 0, 0, 1 },
        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1 },
        { 1, 1, 0, 0, 0, 0, 1, 0, 0, 0 },
        { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
        { 1, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
        { 1, 0, 1, 0, 0, 0, 1, 0, 1, 1 },
        { 1, 0, 1, 1, 1, 0, 1, 0, 0, 1 },
        { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 }
    };
    

    // Calcula la distancia entre dos puntos (punto actual al punto final) en un espacio de cuadrícula.
    // Se ocupa debido a que solo permite movimientos verticales u horizontales.
    public static int distancia_manhattan(Nodo nodo, Nodo fin) {
        return Math.abs(nodo.x - fin.x) + Math.abs(nodo.y - fin.y);
    }

    public static List<Nodo> nodos_vecinos(Nodo nodo, Nodo[][] nodos) {
        List<Nodo> vecinos = new ArrayList<>();
        for (int[] dir : movimiento) {
            int vecinoX = nodo.x + dir[0];
            int vecinoY = nodo.y + dir[1];
            
            if (vecinoX >= 0 && vecinoX < laberinto.length && vecinoY >= 0 && vecinoY < laberinto[0].length) {
                if (laberinto[vecinoX][vecinoY] == 0) {
                    vecinos.add(nodos[vecinoX][vecinoY]);
                }
            }
        }
        return vecinos;
    }

    public static List<Nodo> caminoFinal(Nodo fin) {
        List<Nodo> camino = new ArrayList<>();
        Nodo nodo_actual = fin;
        while (nodo_actual != null) {
            camino.add(nodo_actual);
            nodo_actual = nodo_actual.nodo_padre;
        }
        Collections.reverse(camino); //Para que se muestre de inicio a fin
        return camino;
    }

    // Implementación del algoritmo A*
    public static List<Nodo> a_estrella(Nodo inicio, Nodo fin) {
        PriorityQueue<Nodo> cola_prioridad = new PriorityQueue<>();
        Set<Nodo> nodos_visitados = new HashSet<>();
        
        inicio.costo_ini = 0;
        inicio.costo_manhattan = distancia_manhattan(inicio, fin);
        cola_prioridad.add(inicio);
        
        while (!cola_prioridad.isEmpty()) {
            Nodo nodo_actual = cola_prioridad.poll();
            
            // Si llegamos al nodo objetivo, reconstruimos el camino
            if (nodo_actual.x == fin.x && nodo_actual.y == fin.y) {
                return caminoFinal(nodo_actual);
            }
            
            nodos_visitados.add(nodo_actual);
            
            for (Nodo vecino : nodos_vecinos(nodo_actual, convertirLaberinto())) {
                if (nodos_visitados.contains(vecino)) {
                    continue;
                }

                int costo_aproximado = nodo_actual.costo_ini + 1;
                
                if (costo_aproximado < vecino.costo_ini) {
                    vecino.costo_ini = costo_aproximado;
                    vecino.costo_manhattan = distancia_manhattan(vecino, fin);
                    vecino.nodo_padre = nodo_actual;

                    if (!cola_prioridad.contains(vecino)) {
                        cola_prioridad.add(vecino);
                    }
                }
            }
        }
        return null; 
    }

    public static Nodo[][] convertirLaberinto() {
        Nodo[][] nodos = new Nodo[laberinto.length][laberinto[0].length];
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {
                nodos[i][j] = new Nodo(i, j);
            }
        }
        return nodos;
    }

    public static void main(String[] args) {
        // Definir el nodo de inicio y el nodo objetivo
        Nodo inicio = new Nodo(0, 1); 
        Nodo fin = new Nodo(9, 8);
        long tiempoI = System.currentTimeMillis();
        List<Nodo> camino = a_estrella(inicio, fin);
        
        if (camino != null) {
            System.out.println("Camino encontrado:");
            for (Nodo nodo : camino) {
                System.out.println("(" + nodo.x + ", " + nodo.y + ")");
            }
        } else {
            System.out.println("No se encontró un camino.");
        }
        long tiempoF = System.currentTimeMillis();
        System.out.println("La operación tomó " + (tiempoF-tiempoI) + " milisegundos");
    }
}

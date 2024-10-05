package laboratorio4_ia_crr_fbdp;

import java.util.*;

/**
 * 
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 * 
 **/

// Clase para representar una celda en la matriz del laberinto
class Nodo implements Comparable<Nodo> {
    int x, y;  
    int costo_ini, costo_manhattan; 
    Nodo nodo_padre;  

    public Nodo(int x, int y) {
        this.x = x;
        this.y = y;
        this.costo_ini = Integer.MAX_VALUE;  // Inicializamos con un valor grande
        this.costo_manhattan = 0;
        this.nodo_padre = null;
    }

    public int costo_total() {
        return this.costo_ini + this.costo_manhattan;
    }

    // Para ordenar los nodos en la cola de prioridad por el costo_total
    @Override
    public int compareTo(Nodo nodo_aux) {
        return Integer.compare(this.costo_total(), nodo_aux.costo_total());
    }
}

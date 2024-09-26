package laboratorio3_1_ia_crr_fbdp;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 * 
 **/

public class Puzzle4 {

    static private Queue<Nodo> nodos = new LinkedList<>();
    static private boolean solucionEncontrada = false;  // Variable para detener cuando se encuentre la solución

    public static void main(String[] args) {

        System.out.println("Bienvenido a puzzle 4");
        System.out.println("Revolviendo.....");
        System.out.println("Queda así:");

        // Crear un arreglo del 1 al 4
        Integer[] arreglo = {1, 2, 3, 4};

        // Crear una lista mutable a partir del arreglo
        List<Integer> lista = new ArrayList<>(Arrays.asList(arreglo));

        // Revolver la lista
        Collections.shuffle(lista);
        
        System.out.println(lista);

        // Convertir la lista revuelta en un arreglo de int
        int[] arregloRevuelto = new int[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            arregloRevuelto[i] = lista.get(i);
        }
        
        // Crear nodo raíz con el arreglo revuelto
        Nodo nodoRaiz = new Nodo(arregloRevuelto);
        nodos.add(nodoRaiz);

        // Ejecutar BFS
        bfs();

        if (solucionEncontrada) {
            System.out.println("Solución encontrada");
        } else {
            System.out.println("No se encontró la solución.");
        }
    }

    // Método que ejecuta el algoritmo BFS
    static public void bfs() {
        while (!nodos.isEmpty() && !solucionEncontrada) {
            Nodo nodoActual = nodos.poll(); // Sacar el primer nodo de la cola

            // Verificar si el nodo actual es la solución
            if (esSolucion(nodoActual.getNumeros())) {
                solucionEncontrada = true;
                System.out.println("Solución encontrada: " + Arrays.toString(nodoActual.getNumeros()));
                return;
            }

            // Generar los hijos del nodo actual y agregarlos a la cola
            int[] estadoActual = nodoActual.getNumeros();
            nodos.add(generaHijo(estadoActual, 0)); // Hijo con el primer swap
            nodos.add(generaHijo(estadoActual, 1)); // Hijo con el segundo swap
            nodos.add(generaHijo(estadoActual, 2)); // Hijo con el tercer swap
            nodos.add(generaHijo(estadoActual, 3)); // Hijo con el cuarto swap
        }
    }

    // Método para generar un nodo hijo a partir de un estado
    static public Nodo generaHijo(int[] inicio, int ban) {
        int[] nuevoEstado = Arrays.copyOf(inicio, inicio.length); // Copiar el estado para evitar mutación

        int aux;
        switch (ban) {
            case 1:  // Intercambiar los primeros dos elementos
                aux = nuevoEstado[0];
                nuevoEstado[0] = nuevoEstado[1];
                nuevoEstado[1] = aux;
                break;
            case 2:  // Intercambiar los elementos del medio
                aux = nuevoEstado[1];
                nuevoEstado[1] = nuevoEstado[2];
                nuevoEstado[2] = aux;
                break;
            case 3:  // Intercambiar los últimos dos elementos
                aux = nuevoEstado[2];
                nuevoEstado[2] = nuevoEstado[3];
                nuevoEstado[3] = aux;
                break;
        }

        return new Nodo(nuevoEstado);
    }

    // Método para verificar si el arreglo está ordenado (solución 1, 2, 3, 4)
    static public boolean esSolucion(int[] estado) {
        int[] solucion = {1, 2, 3, 4};
        return Arrays.equals(estado, solucion);
    }
    
}

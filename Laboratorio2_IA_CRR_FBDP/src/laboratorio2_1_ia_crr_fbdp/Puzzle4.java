package laboratorio2_1_ia_crr_fbdp;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * 
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 * 
**/

public class Puzzle4 {

    static private Stack<Nodo> nodos = new Stack<>();
    static private boolean solucionEncontrada = false;  // Variable para detener la recursión cuando se encuentre la solución

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
        Nodo nodito = new Nodo(arregloRevuelto, generaArbol(arregloRevuelto, 0, 0), generaArbol(arregloRevuelto, 1, 0), generaArbol(arregloRevuelto, 2, 0), generaArbol(arregloRevuelto, 3, 0));
        nodos.push(nodito);

        if (solucionEncontrada) {
            System.out.println("Solución encontrada");
        } else {
            System.out.println("No se encontró la solución.");
        }

    }

    // Método recursivo para generar el árbol con condición de parada
    static public Nodo generaArbol(int[] inicio, int ban, int profundidad) {

        // Si ya se ha encontrado la solución, detener la recursión
        if (solucionEncontrada) {
            return null;
        }

        // Verificar si el arreglo es la solución
        if (esSolucion(inicio)) {
            solucionEncontrada = true;
            System.out.println("Solución encontrada: " + Arrays.toString(inicio));
            return new Nodo(inicio);  // Crear el nodo con la solución y detener la recursión
        }

        // Limitar la profundidad del árbol
        if (profundidad > 10) {  // Ajusta la profundidad máxima según sea necesario
            return null;
        }

        Nodo nodo = null;
        int aux;
        

        switch (ban) {
            case 0:
                //System.out.println("***************************");
                /*for (int i = 0; i < 4; i++) {
                    System.out.println("i: " + inicio[i]);
                }*/
                nodo = new Nodo(inicio);  // No se hace ningún intercambio
                break;
            case 1:  // Intercambiar los primeros dos elementos
                aux = inicio[0];
                inicio[0] = inicio[1];
                inicio[1] = aux;
                
                nodo = new Nodo(inicio, generaArbol(inicio, 0, profundidad + 1), generaArbol(inicio, 1, profundidad + 1), generaArbol(inicio, 2, profundidad + 1), generaArbol(inicio, 3, profundidad + 1));
                break;
            case 2:  // Intercambiar los elementos del medio
                aux = inicio[1];
                inicio[1] = inicio[2];
                inicio[2] = aux;
                nodo = new Nodo(inicio, generaArbol(inicio, 0, profundidad + 1), generaArbol(inicio, 1, profundidad + 1), generaArbol(inicio, 2, profundidad + 1), generaArbol(inicio, 3, profundidad + 1));
                break;
            case 3:  // Intercambiar los últimos dos elementos
                aux = inicio[2];
                inicio[2] = inicio[3];
                inicio[3] = aux;
                nodo = new Nodo(inicio, generaArbol(inicio, 0, profundidad + 1), generaArbol(inicio, 1, profundidad + 1), generaArbol(inicio, 2, profundidad + 1), generaArbol(inicio, 3, profundidad + 1));
                break;
            default:
                throw new AssertionError();
        }

        // Agregar nodo a la pila de nodos
        nodos.push(nodo);
        return nodo;
    }

    // Método para verificar si el arreglo está ordenado (solución 1, 2, 3, 4)
    static public boolean esSolucion(int[] estado) {
        int[] solucion = {1, 2, 3, 4};
        return Arrays.equals(estado, solucion);
    }
    
}

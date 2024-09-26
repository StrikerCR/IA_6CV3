package laboratorio3_1_ia_crr_fbdp;

/**
 *
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 * 
**/

public class Nodo {
    
    private int[] numeros;
    private Nodo nodo;
    private Nodo posDerecho;
    private Nodo posIzquierdo;
    private Nodo posCentro;

    public Nodo(int[] numeros, Nodo nodo,Nodo posDerecho, Nodo posIzquierdo, Nodo posCentro){
        this.numeros = numeros;
        this.nodo = nodo;
        this.posDerecho = posDerecho;
        this.posCentro = posCentro;
        this.posIzquierdo = posIzquierdo;
    }
    
    public Nodo(int[] numeros){
        this.numeros = numeros;
    }

    public int[] getNumeros() {
        return numeros;
    }

    public Nodo getNodo() {
        return nodo;
    }

    public Nodo getPosDerecho() {
        return posDerecho;
    }

    public Nodo getPosIzquierdo() {
        return posIzquierdo;
    }

    public Nodo getPosCentro() {
        return posCentro;
    }
    
}

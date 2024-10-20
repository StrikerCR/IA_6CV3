package laboratorio5_ia_crr_fbdp;

/**
 * 
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 * 
 **/

public class Laboratorio5_IA_CRR_FBDP {

    static final double THRESHOLD = 0.00001; // Umbral de parada
    static final int MAX_ITERACIONES = 100000;  // Número máximo de iteraciones
    static final double LEARNING_RATE = 0.001; // Tamaño de paso para el descenso por gradiente

    public static void main(String[] args) {
        double x = generarNumeroAleatorio(-5, 5);
        double y = generarNumeroAleatorio(-5, 5);

        double resAnterior = Double.MAX_VALUE;
        int iteraciones = 0;
        boolean continuarBusqueda = true;

        while (continuarBusqueda && iteraciones < MAX_ITERACIONES) {
            iteraciones++;

            // Calcular el valor actual de la función
            double resultado = funcionHimmelblau(x, y);

            // Verificar si el punto encontrado es un mínimo
            if (Math.abs(resultado - resAnterior) < THRESHOLD) {
                System.out.println("Condición de parada alcanzada.");
                continuarBusqueda = false;
                break;
            }

            resAnterior = resultado;

            // Mostrar el progreso
            System.out.println("Iteración " + iteraciones + ": x = " + x + ", y = " + y + ", resultado = " + resultado);

            // Obtener las derivadas parciales para x e y
            double[] gradiente = calcularGradiente(x, y);

            // Actualizar los valores de x e y moviéndose en la dirección del gradiente
            x = x - LEARNING_RATE * gradiente[0];
            y = y - LEARNING_RATE * gradiente[1];
        }

        System.out.println("Búsqueda finalizada después de " + iteraciones + " iteraciones.");
        System.out.println("Mínimo encontrado: x = " + x + ", y = " + y + ", resultado = " + funcionHimmelblau(x, y));
    }

    // Generar un número aleatorio en el rango [min, max]
    public static double generarNumeroAleatorio(double min, double max) {
        return min + (max - min) * Math.random();
    }

    // Función de Himmelblau
    public static double funcionHimmelblau(double x, double y) {
        return Math.pow(x * x + y - 11, 2) + Math.pow(x + y * y - 7, 2);
    }

    // Calcular el gradiente de la función de Himmelblau (las derivadas parciales)
    public static double[] calcularGradiente(double x, double y) {
        // Derivada parcial con respecto a x
        double dfdx = 4 * x * (x * x + y - 11) + 2 * (x + y * y - 7);
        // Derivada parcial con respecto a y
        double dfdy = 2 * (x * x + y - 11) + 4 * y * (x + y * y - 7);

        return new double[]{dfdx, dfdy};
    }
}

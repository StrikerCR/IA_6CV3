package laboratorio6_ia_crr_fbdp;

import java.util.Random;

/**
 * 
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 * 
 **/

public class Laboratorio6_IA_CRR_FBDP {

     // Función de Himmelblau
    public static double himmelblau(double x, double y) {
        return Math.pow((x * x + y - 11), 2) + Math.pow((x + y * y - 7), 2);
    }

    // Función de recocido simulado
    public static double[] simulatedAnnealing(double initialTemp, double finalTemp, double alpha, int maxIter) {
        Random rand = new Random();
        double x = -5 + (10) * rand.nextDouble();  // Inicializando x entre -5 y 5
        double y = -5 + (10) * rand.nextDouble();  // Inicializando y entre -5 y 5
        
        double currentCost = himmelblau(x, y);
        double temp = initialTemp;

        double[] currentSolution = {x, y};

        while (temp > finalTemp) {
            for (int i = 0; i < maxIter; i++) {
                // Generar un nuevo punto vecino cercano al actual
                double newX = currentSolution[0] + (rand.nextDouble() - 0.5);  // Entre -0.5 y 0.5
                double newY = currentSolution[1] + (rand.nextDouble() - 0.5);  // Entre -0.5 y 0.5

                // Evaluar el nuevo punto
                double newCost = himmelblau(newX, newY);

                // Decidir si aceptar el nuevo punto
                if (newCost < currentCost || Math.exp((currentCost - newCost) / temp) > rand.nextDouble()) {
                    currentSolution[0] = newX;
                    currentSolution[1] = newY;
                    currentCost = newCost;
                }
            }

            // Enfriar la temperatura
            temp *= alpha;
        }

        return currentSolution;
    }

    public static void main(String[] args) {
        double initialTemp = 10000;
        double finalTemp = 0.001;
        double alpha = 0.9;
        int maxIter = 100;

        double[] solution = simulatedAnnealing(initialTemp, finalTemp, alpha, maxIter);
        double x = solution[0];
        double y = solution[1];

        System.out.println("Valores optimizados:");
        System.out.println("x = " + x);
        System.out.println("y = " + y);
        System.out.println("Valor mínimo de la función de Himmelblau: " + himmelblau(x, y));
    }
    
}

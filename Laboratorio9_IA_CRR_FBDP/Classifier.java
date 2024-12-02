import java.util.List;

public class Classifier {

    public static double euclideanDistance(double[] a, double[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) {
            throw new IllegalArgumentException("Error: Uno de los arreglos está vacío o es nulo.");
        }
    
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }    

    public static String oneNN(double[] testPoint, List<double[]> trainData, List<String> trainLabels) {
        double minDistance = Double.MAX_VALUE;
        String closestLabel = null;

        for (int i = 0; i < trainData.size(); i++) {
            double distance = euclideanDistance(testPoint, trainData.get(i));
            if (distance < minDistance) {
                minDistance = distance;
                closestLabel = trainLabels.get(i);
            }
        }
        return closestLabel;
    }
}

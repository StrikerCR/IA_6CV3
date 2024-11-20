import java.util.*;

public class Classifier {

    public static double euclideanDistance(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Los vectores tienen diferente numero de caracteristicas.");
        }
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow(a[i] - b[i], 2);
        }
        return Math.sqrt(sum);
    }

    public static String kNN(double[] testInstance, List<double[]> trainData, List<String> trainLabels, int k) {
        if (trainData.isEmpty() || trainLabels.isEmpty()) {
            throw new IllegalArgumentException("El conjunto de entrenamiento esta vacio.");
        }
        if (k > trainData.size()) {
            throw new IllegalArgumentException("El valor de K es mayor que el tamano del conjunto de entrenamiento.");
        }

        PriorityQueue<double[]> neighbors = new PriorityQueue<>(Comparator.comparingDouble(o -> o[0]));
        for (int i = 0; i < trainData.size(); i++) {
            double distance = euclideanDistance(testInstance, trainData.get(i));
            neighbors.add(new double[]{distance, i});
        }

        Map<String, Integer> classVotes = new HashMap<>();
        for (int i = 0; i < k && !neighbors.isEmpty(); i++) {
            int neighborIndex = (int) neighbors.poll()[1];
            String label = trainLabels.get(neighborIndex);
            classVotes.put(label, classVotes.getOrDefault(label, 0) + 1);
        }

        return Collections.max(classVotes.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static String naiveBayes(double[] testInstance, List<double[]> trainData, List<String> trainLabels) {
        // Naive Bayes Implementation
        Map<String, double[]> means = new HashMap<>();
        Map<String, double[]> variances = new HashMap<>();
        Map<String, Integer> classCounts = new HashMap<>();

        for (int i = 0; i < trainData.size(); i++) {
            String label = trainLabels.get(i);
            classCounts.put(label, classCounts.getOrDefault(label, 0) + 1);
            if (!means.containsKey(label)) {
                means.put(label, new double[trainData.get(0).length]);
                variances.put(label, new double[trainData.get(0).length]);
            }
            double[] features = trainData.get(i);
            for (int j = 0; j < features.length; j++) {
                means.get(label)[j] += features[j];
            }
        }
        for (String label : means.keySet()) {
            for (int j = 0; j < means.get(label).length; j++) {
                means.get(label)[j] /= classCounts.get(label);
            }
        }
        for (int i = 0; i < trainData.size(); i++) {
            String label = trainLabels.get(i);
            double[] features = trainData.get(i);
            for (int j = 0; j < features.length; j++) {
                variances.get(label)[j] += Math.pow(features[j] - means.get(label)[j], 2);
            }
        }
        for (String label : variances.keySet()) {
            for (int j = 0; j < variances.get(label).length; j++) {
                variances.get(label)[j] /= classCounts.get(label);
            }
        }

        Map<String, Double> probabilities = new HashMap<>();
        for (String label : means.keySet()) {
            double logProb = Math.log((double) classCounts.get(label) / trainData.size());
            for (int j = 0; j < testInstance.length; j++) {
                double mean = means.get(label)[j];
                double variance = variances.get(label)[j];
                double x = testInstance[j];
                if (variance > 0) {
                    logProb += Math.log(1 / Math.sqrt(2 * Math.PI * variance)) - Math.pow(x - mean, 2) / (2 * variance);
                }
            }
            probabilities.put(label, logProb);
        }

        return Collections.max(probabilities.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}

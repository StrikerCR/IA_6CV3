import java.util.*;

public class Validation {

    public static double holdOut(List<double[]> data, List<String> labels, double splitRatio) {
        int splitIndex = (int) (data.size() * splitRatio);

        List<double[]> trainData = data.subList(0, splitIndex);
        List<String> trainLabels = labels.subList(0, splitIndex);

        List<double[]> testData = data.subList(splitIndex, data.size());
        List<String> testLabels = labels.subList(splitIndex, labels.size());

        return evaluate(testData, testLabels, trainData, trainLabels);
    }

    public static double kFoldCrossValidation(List<double[]> data, List<String> labels, int k) {
        int foldSize = data.size() / k;
        double totalAccuracy = 0;
    
        for (int i = 0; i < k; i++) {
            List<double[]> trainData = new ArrayList<>();
            List<String> trainLabels = new ArrayList<>();
            List<double[]> testData = new ArrayList<>();
            List<String> testLabels = new ArrayList<>();
    
            for (int j = 0; j < data.size(); j++) {
                if (j >= i * foldSize && j < (i + 1) * foldSize) {
                    testData.add(data.get(j));
                    testLabels.add(labels.get(j));
                } else {
                    trainData.add(data.get(j));
                    trainLabels.add(labels.get(j));
                }
            }
    
           // System.out.println("Fold " + i + ": Tamano de entrenamiento = " + trainData.size() + ", Tamaño de prueba = " + testData.size());
    
            if (trainData.isEmpty() || testData.isEmpty()) {
                System.out.println("Error: Una de las divisiones esta vacia.");
                continue;
            }
    
            totalAccuracy += evaluate(testData, testLabels, trainData, trainLabels);
        }
    
        return totalAccuracy / k;
    }

    public static double leaveOneOut(List<double[]> data, List<String> labels) {
        double correct = 0;

        for (int i = 0; i < data.size(); i++) {
            List<double[]> trainData = new ArrayList<>(data);
            List<String> trainLabels = new ArrayList<>(labels);

            double[] testPoint = trainData.remove(i);
            String testLabel = trainLabels.remove(i);

            String predictedLabel = Classifier.oneNN(testPoint, trainData, trainLabels);
            if (predictedLabel.equals(testLabel)) {
                correct++;
            }
        }

        return correct / data.size();
    }

    private static double evaluate(List<double[]> testData, List<String> testLabels, List<double[]> trainData, List<String> trainLabels) {
        int correct = 0;

        for (int i = 0; i < testData.size(); i++) {
            String predicted = Classifier.oneNN(testData.get(i), trainData, trainLabels);
            if (predicted.equals(testLabels.get(i))) {
                correct++;
            }
        }

        return (double) correct / testData.size();
    }
}

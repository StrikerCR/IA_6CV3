import java.util.*;

public class Validation {
    public static double evaluate(List<double[]> testData, List<String> testLabels, List<double[]> trainData,
                                  List<String> trainLabels, String classifier, int k) {
        int correct = 0;
        for (int i = 0; i < testData.size(); i++) {
            String predicted;
            if (classifier.equals("KNN")) {
                predicted = Classifier.kNN(testData.get(i), trainData, trainLabels, k);
            } else if (classifier.equals("NaiveBayes")) {
                predicted = Classifier.naiveBayes(testData.get(i), trainData, trainLabels);
            } else {
                throw new IllegalArgumentException("Clasificador desconocido: " + classifier);
            }
            if (predicted.equals(testLabels.get(i))) {
                correct++;
            }
        }
        return (double) correct / testData.size();
    }

    public static double holdOut(List<double[]> data, List<String> labels, double splitRatio, String classifier, int k) {
        int splitIndex = (int) (data.size() * splitRatio);
        List<double[]> trainData = data.subList(0, splitIndex);
        List<String> trainLabels = labels.subList(0, splitIndex);
        List<double[]> testData = data.subList(splitIndex, data.size());
        List<String> testLabels = labels.subList(splitIndex, labels.size());
        return evaluate(testData, testLabels, trainData, trainLabels, classifier, k);
    }

    public static double kFoldCrossValidation(List<double[]> data, List<String> labels, int kFolds, String classifier, int k) {
        int foldSize = data.size() / kFolds;
        double totalAccuracy = 0.0;

        for (int i = 0; i < kFolds; i++) {
            int start = i * foldSize;
            int end = (i == kFolds - 1) ? data.size() : (i + 1) * foldSize;

            List<double[]> testData = new ArrayList<>(data.subList(start, end));
            List<String> testLabels = new ArrayList<>(labels.subList(start, end));
            List<double[]> trainData = new ArrayList<>(data);
            List<String> trainLabels = new ArrayList<>(labels);

            trainData.subList(start, end).clear();
            trainLabels.subList(start, end).clear();

            totalAccuracy += evaluate(testData, testLabels, trainData, trainLabels, classifier, k);
        }

        return totalAccuracy / kFolds;
    }

    public static double leaveOneOut(List<double[]> data, List<String> labels, String classifier, int k) {
        double totalAccuracy = 0.0;

        for (int i = 0; i < data.size(); i++) {
            double[] testInstance = data.get(i);
            String testLabel = labels.get(i);

            List<double[]> trainData = new ArrayList<>(data);
            List<String> trainLabels = new ArrayList<>(labels);

            trainData.remove(i);
            trainLabels.remove(i);

            totalAccuracy += evaluate(List.of(testInstance), List.of(testLabel), trainData, trainLabels, classifier, k);
        }

        return totalAccuracy / data.size();
    }
}

import java.util.ArrayList;

public class Validation {

    public static double holdOut(ArrayList<double[]> data, ArrayList<String> labels, double splitRatio,
                                 String classifier, int param) {
        int splitIndex = (int) (data.size() * splitRatio);
        ArrayList<double[]> trainData = new ArrayList<>(data.subList(0, splitIndex));
        ArrayList<String> trainLabels = new ArrayList<>(labels.subList(0, splitIndex));
        ArrayList<double[]> testData = new ArrayList<>(data.subList(splitIndex, data.size()));
        ArrayList<String> testLabels = new ArrayList<>(labels.subList(splitIndex, labels.size()));

        return evaluate(trainData, trainLabels, testData, testLabels, classifier, param);
    }

    public static double kFoldCrossValidation(ArrayList<double[]> data, ArrayList<String> labels, int k,
                                               String classifier, int param) {
        double accuracySum = 0;
        int foldSize = data.size() / k;

        for (int i = 0; i < k; i++) {
            ArrayList<double[]> trainData = new ArrayList<>(data);
            ArrayList<String> trainLabels = new ArrayList<>(labels);
            ArrayList<double[]> testData = new ArrayList<>();
            ArrayList<String> testLabels = new ArrayList<>();

            for (int j = i * foldSize; j < (i + 1) * foldSize; j++) {
                testData.add(trainData.remove(i * foldSize));
                testLabels.add(trainLabels.remove(i * foldSize));
            }

            accuracySum += evaluate(trainData, trainLabels, testData, testLabels, classifier, param);
        }

        return accuracySum / k;
    }

    public static double leaveOneOut(ArrayList<double[]> data, ArrayList<String> labels, String classifier, int param) {
        double accuracySum = 0;

        for (int i = 0; i < data.size(); i++) {
            ArrayList<double[]> trainData = new ArrayList<>(data);
            ArrayList<String> trainLabels = new ArrayList<>(labels);
            double[] testInstance = trainData.remove(i);
            String actualLabel = trainLabels.remove(i);

            Metrics.ConfusionMatrix cm = new Metrics.ConfusionMatrix();
            if (classifier.equals("MLP")) {
                Classifier.MLPModel mlp = Classifier.trainMLP(trainData, trainLabels, param, 100, 0.01);
                String predicted = mlp.predict(testInstance);
                if (predicted.equals(actualLabel)) {
                    accuracySum++;
                }
            } else if (classifier.equals("RBF")) {
                Classifier.RBFModel rbf = Classifier.trainRBF(trainData, trainLabels, param);
                String predicted = rbf.predict(testInstance);
                if (predicted.equals(actualLabel)) {
                    accuracySum++;
                }
            }
        }

        return accuracySum / data.size();
    }

    private static double evaluate(ArrayList<double[]> trainData, ArrayList<String> trainLabels,
                                   ArrayList<double[]> testData, ArrayList<String> testLabels, String classifier, int param) {
        int correct = 0;

        if (classifier.equals("MLP")) {
            Classifier.MLPModel mlp = Classifier.trainMLP(trainData, trainLabels, param, 100, 0.01);
            for (int i = 0; i < testData.size(); i++) {
                String predicted = mlp.predict(testData.get(i));
                String actual = testLabels.get(i);
                if (predicted.equals(actual)) {
                    correct++;
                }
            }
        } else if (classifier.equals("RBF")) {
            Classifier.RBFModel rbf = Classifier.trainRBF(trainData, trainLabels, param);
            for (int i = 0; i < testData.size(); i++) {
                String predicted = rbf.predict(testData.get(i));
                String actual = testLabels.get(i);
                if (predicted.equals(actual)) {
                    correct++;
                }
            }
        }

        return (double) correct / testData.size();
    }
}

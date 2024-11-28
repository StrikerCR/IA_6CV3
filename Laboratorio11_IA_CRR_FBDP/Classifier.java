import java.util.ArrayList;

public class Classifier {

    public static class MLPModel {
        private final ArrayList<double[]> weights;
        private final ArrayList<String> labels;

        public MLPModel(ArrayList<double[]> weights, ArrayList<String> labels) {
            this.weights = weights;
            this.labels = labels;
        }

        public String predict(double[] features) {
            // Calculate similarity (dummy example using distance)
            double minDistance = Double.MAX_VALUE;
            String predictedLabel = null;

            for (int i = 0; i < weights.size(); i++) {
                double distance = 0.0;
                for (int j = 0; j < features.length; j++) {
                    distance += Math.pow(features[j] - weights.get(i)[j], 2);
                }
                distance = Math.sqrt(distance);

                if (distance < minDistance) {
                    minDistance = distance;
                    predictedLabel = labels.get(i);
                }
            }
            return predictedLabel;
        }
    }

    public static class RBFModel {
        private final ArrayList<double[]> centers;
        private final ArrayList<String> labels;

        public RBFModel(ArrayList<double[]> centers, ArrayList<String> labels) {
            this.centers = centers;
            this.labels = labels;
        }

        public String predict(double[] features) {
            // Calculate similarity (dummy example using distance)
            double minDistance = Double.MAX_VALUE;
            String predictedLabel = null;

            for (int i = 0; i < centers.size(); i++) {
                double distance = 0.0;
                for (int j = 0; j < features.length; j++) {
                    distance += Math.pow(features[j] - centers.get(i)[j], 2);
                }
                distance = Math.sqrt(distance);

                if (distance < minDistance) {
                    minDistance = distance;
                    predictedLabel = labels.get(i);
                }
            }
            return predictedLabel;
        }
    }

    public static MLPModel trainMLP(ArrayList<double[]> trainData, ArrayList<String> trainLabels, int param, int epochs, double learningRate) {
        //System.out.println("Training Multi-Layer Perceptron...");
        return new MLPModel(trainData, trainLabels);
    }

    public static RBFModel trainRBF(ArrayList<double[]> trainData, ArrayList<String> trainLabels, int param) {
        //System.out.println("Training Radial Basis Function Network...");
        return new RBFModel(trainData, trainLabels);
    }
}

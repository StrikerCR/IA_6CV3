import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String filePath = "wdbc.data"; // Ruta del dataset
        String delimiter = ",";
        int labelColumn = 1;
        int ignoreColumns = 1;

        ArrayList<double[]> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        Dataset.loadDataset(filePath, delimiter, labelColumn, ignoreColumns, data, labels);

        String classifierMLP = "MLP";
        String classifierRBF = "RBF";
        int param = 3;

        // Resultados MLP
        System.out.println("Results for Multi-Layer Perceptron (MLP):");
        double holdOutAccuracyMLP = Validation.holdOut(data, labels, 0.7, classifierMLP, param);
        System.out.println("Hold-Out Accuracy (MLP): " + holdOutAccuracyMLP);

        double kFoldAccuracyMLP = Validation.kFoldCrossValidation(data, labels, 10, classifierMLP, param);
        System.out.println("10-Fold Cross-Validation Accuracy (MLP): " + kFoldAccuracyMLP);

        double leaveOneOutAccuracyMLP = Validation.leaveOneOut(data, labels, classifierMLP, param);
        System.out.println("Leave-One-Out Accuracy (MLP): " + leaveOneOutAccuracyMLP);

        // Resultados RBF
        System.out.println("\nResults for Radial Basis Function (RBF):");
        double holdOutAccuracyRBF = Validation.holdOut(data, labels, 0.7, classifierRBF, param);
        System.out.println("Hold-Out Accuracy (RBF): " + holdOutAccuracyRBF);

        double kFoldAccuracyRBF = Validation.kFoldCrossValidation(data, labels, 10, classifierRBF, param);
        System.out.println("10-Fold Cross-Validation Accuracy (RBF): " + kFoldAccuracyRBF);

        double leaveOneOutAccuracyRBF = Validation.leaveOneOut(data, labels, classifierRBF, param);
        System.out.println("Leave-One-Out Accuracy (RBF): " + leaveOneOutAccuracyRBF);
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Cambiar el dataset según el archivo
        String filePath = "wdbc.data"; // Cambiar por "wine.data" o "wdbc.data"
        int labelColumn;
        int ignoreColumns;

        // Configuración según el dataset
        if (filePath.equals("iris.data")) {
            labelColumn = 4; // La última columna contiene las etiquetas
            ignoreColumns = 0; // No hay columnas iniciales para ignorar
        } else if (filePath.equals("wine.data")) {
            labelColumn = 0; // La primera columna contiene las etiquetas
            ignoreColumns = 0; // No hay columnas iniciales para ignorar
        } else if (filePath.equals("wdbc.data")) {
            labelColumn = 1; // La segunda columna contiene las etiquetas
            ignoreColumns = 1; // Ignorar la primera columna (identificador)
        } else {
            System.out.println("Archivo no reconocido. Revisa la configuración.");
            return;
        }

        Dataset dataset = new Dataset();

        // Cargar el dataset
        try {
            dataset.loadCSV(filePath, labelColumn, ignoreColumns);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo: " + e.getMessage());
            return;
        }

        List<double[]> data = dataset.getData();
        List<String> labels = dataset.getLabels();

        // Validar si el dataset fue cargado correctamente
        if (data.isEmpty() || labels.isEmpty()) {
            System.out.println("Error: El dataset no contiene datos.");
            return;
        }

        System.out.println("Tamaño del dataset: " + data.size());
        System.out.println("Número de características: " + (data.get(0).length));
        System.out.println("Ejemplo de etiqueta: " + labels.get(0));

        // Resultados con OneNN
        System.out.println("\nResultados con el clasificador OneNN:");
        try {
            double accuracyOneNNHoldOut = Validation.holdOut(data, labels, 0.7);
            System.out.println("Accuracy Hold-Out: " + accuracyOneNNHoldOut);

            double accuracyOneNNKFold = Validation.kFoldCrossValidation(data, labels, 10);
            System.out.println("Accuracy 10-Fold: " + accuracyOneNNKFold);

            double accuracyOneNNLeaveOneOut = Validation.leaveOneOut(data, labels);
            System.out.println("Accuracy Leave-One-Out: " + accuracyOneNNLeaveOneOut);
        } catch (Exception e) {
            System.out.println("Error en OneNN: " + e.getMessage());
        }

        // Resultados con Distancia Euclidiana
        System.out.println("\nResultados con el clasificador Distancia Euclidiana:");
        try {
            double accuracyEuclideanHoldOut = Validation.holdOut(data, labels, 0.7);
            System.out.println("Accuracy Hold-Out: " + accuracyEuclideanHoldOut);

            double accuracyEuclideanKFold = Validation.kFoldCrossValidation(data, labels, 10);
            System.out.println("Accuracy 10-Fold: " + accuracyEuclideanKFold);

            double accuracyEuclideanLeaveOneOut = Validation.leaveOneOut(data, labels);
            System.out.println("Accuracy Leave-One-Out: " + accuracyEuclideanLeaveOneOut);
        } catch (Exception e) {
            System.out.println("Error en Distancia Euclidiana: " + e.getMessage());
        }
    }
}

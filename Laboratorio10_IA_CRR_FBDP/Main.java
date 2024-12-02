import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Configuración del dataset
        String filePath = "wdbc.data"; // Cambiar por "wine.data" o "wdbc.data"
        int labelColumn;
        int ignoreColumns;

        // Configuración dinámica según el dataset
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

        ArrayList<double[]> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        // Cargar el dataset
        loadDataset(filePath, ",", labelColumn, ignoreColumns, data, labels);

        // Validar si los datos fueron cargados correctamente
        if (data.isEmpty() || labels.isEmpty()) {
            System.out.println("Error: El dataset no contiene datos.");
            return;
        }

        System.out.println("Tamaño del dataset: " + data.size());
        System.out.println("Número de características: " + data.get(0).length);
        System.out.println("Ejemplo de etiqueta: " + labels.get(0));

        int k = 3; // Valor de K para KNN

        // Resultados con KNN
        System.out.println("\nResultados con el clasificador KNN:");
        try {
            double accuracyKNNHoldOut = Validation.holdOut(data, labels, 0.7, "KNN", k);
            System.out.println("Accuracy Hold-Out: " + accuracyKNNHoldOut);

            double accuracyKNNKFold = Validation.kFoldCrossValidation(data, labels, 10, "KNN", k);
            System.out.println("Accuracy 10-Fold: " + accuracyKNNKFold);

            double accuracyKNNLeaveOneOut = Validation.leaveOneOut(data, labels, "KNN", k);
            System.out.println("Accuracy Leave-One-Out: " + accuracyKNNLeaveOneOut);
        } catch (Exception e) {
            System.out.println("Error en KNN: " + e.getMessage());
        }

        // Resultados con Naive Bayes
        System.out.println("\nResultados con el clasificador Naive Bayes:");
        try {
            double accuracyNBHoldOut = Validation.holdOut(data, labels, 0.7, "NaiveBayes", k);
            System.out.println("Accuracy Hold-Out: " + accuracyNBHoldOut);

            double accuracyNBKFold = Validation.kFoldCrossValidation(data, labels, 10, "NaiveBayes", k);
            System.out.println("Accuracy 10-Fold: " + accuracyNBKFold);

            double accuracyNBLeaveOneOut = Validation.leaveOneOut(data, labels, "NaiveBayes", k);
            System.out.println("Accuracy Leave-One-Out: " + accuracyNBLeaveOneOut);
        } catch (Exception e) {
            System.out.println("Error en Naive Bayes: " + e.getMessage());
        }
    }

    public static void loadDataset(String filePath, String delimiter, int labelColumn, int ignoreColumns,
                                   ArrayList<double[]> data, ArrayList<String> labels) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(delimiter);
                if (parts.length <= labelColumn) {
                    System.out.println("Línea inválida (menos columnas que labelColumn): " + line);
                    continue;
                }

                try {
                    // Procesar la etiqueta
                    String label = parts[labelColumn].trim();
                    labels.add(label);

                    // Procesar las características
                    double[] features = new double[parts.length - ignoreColumns - 1];
                    int featureIndex = 0;
                    for (int i = 0; i < parts.length; i++) {
                        if (i < ignoreColumns || i == labelColumn) continue;
                        features[featureIndex++] = Double.parseDouble(parts[i].trim());
                    }
                    data.add(features);

                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir valores numéricos en la línea: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}

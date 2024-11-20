import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Configuración del dataset
        String filePath = "wdbc.data"; // Ruta al dataset
        String delimiter = ","; // Delimitador usado en el archivo
        int labelColumn = 1; // indice de la columna de etiquetas (-1 para Iris, 1 WDBC, 0 Wine)
        int ignoreColumns = 1; // Número de columnas iniciales a ignorar (0 para Iris, 1 WDBC, 0 Wine)

        // Listas para datos y etiquetas
        ArrayList<double[]> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        // Cargar el dataset
        loadDataset(filePath, delimiter, labelColumn, ignoreColumns, data, labels);

        // Verificar que los datos se hayan cargado correctamente
        System.out.println("Tamaño del dataset: " + data.size());
        if (!data.isEmpty()) {
            System.out.println("Número de características: " + data.get(0).length);
        }
        System.out.println("Ejemplo de etiqueta: " + (labels.isEmpty() ? "Ninguna" : labels.get(0)));

        // Configurar el clasificador
        String classifier = "KNN"; // Opciones: "KNN" o "NaiveBayes"
        int k = 3; // Valor de K para KNN

        // Validaciones
        try {
            double accuracyHoldOut = Validation.holdOut(data, labels, 0.7, classifier, k);
            System.out.println("Accuracy Hold-Out: " + accuracyHoldOut);

            double accuracyKFold = Validation.kFoldCrossValidation(data, labels, 10, classifier, k);
            System.out.println("Accuracy 10-Fold: " + accuracyKFold);

            double accuracyLeaveOneOut = Validation.leaveOneOut(data, labels, classifier, k);
            System.out.println("Accuracy Leave-One-Out: " + accuracyLeaveOneOut);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Método para cargar un dataset desde un archivo.
     *
     * @param filePath      Ruta del archivo.
     * @param delimiter     Delimitador de las columnas (por ejemplo, "," o ";").
     * @param labelColumn   indice de la columna de etiquetas (-1 para última columna).
     * @param ignoreColumns Número de columnas iniciales a ignorar.
     * @param data          Lista donde se guardarán las características.
     * @param labels        Lista donde se guardarán las etiquetas.
     */
    public static void loadDataset(String filePath, String delimiter, int labelColumn, int ignoreColumns,
                                   ArrayList<double[]> data, ArrayList<String> labels) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            int lineNumber = 0; // Línea actual del archivo
            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim(); // Quitar espacios en blanco
                if (line.isEmpty()) continue; // Saltar líneas vacías

                try {
                    // Dividir la línea en partes
                    String[] parts = line.split(delimiter);

                    // Validar que la línea tiene suficientes columnas
                    if (parts.length <= ignoreColumns) {
                        System.out.println("Línea ignorada (columnas insuficientes): " + line);
                        continue;
                    }

                    // Determinar el índice de la etiqueta
                    int labelIndex = (labelColumn == -1) ? parts.length - 1 : labelColumn;

                    // Extraer las características numéricas
                    double[] row = new double[parts.length - ignoreColumns - 1];
                    for (int i = ignoreColumns, j = 0; i < parts.length; i++) {
                        if (i == labelIndex) continue; // Saltar la columna de la etiqueta
                        row[j++] = Double.parseDouble(parts[i]);
                    }

                    // Agregar los datos y la etiqueta
                    data.add(row);
                    labels.add(parts[labelIndex]);

                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir valores numéricos en la línea: " + lineNumber
                            + " (se descarta): " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Resumen después de cargar el dataset
        System.out.println("Tamaño del dataset: " + data.size());
        if (!data.isEmpty()) {
            System.out.println("Número de características: " + data.get(0).length);
        }
        System.out.println("Ejemplo de etiqueta: " + (labels.isEmpty() ? "Ninguna" : labels.get(0)));
    }
}

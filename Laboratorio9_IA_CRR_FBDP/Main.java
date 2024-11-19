import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ruta al archivo del dataset
        String filePath = "wdbc.data"; // Cambia según el archivo
        String delimiter = ","; // Ajusta según el separador del dataset

        ArrayList<double[]> data = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        // Configurar el índice de la columna de etiquetas y si se debe ignorar la columna ID
        int labelColumn = 1; // indice de la columna de etiquetas
        int ignoreColumns = 1; // Número de columnas iniciales que ignorar (ej., ID)

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim(); // Eliminar espacios en blanco
                if (line.isEmpty()) {
                    continue; // Ignorar líneas vacías
                }

                // Separar la línea según el delimitador
                String[] values = line.split(delimiter);
                if (values.length < ignoreColumns + 2) {
                    System.out.println("Línea inválida (esperadas al menos 2 columnas después de las ignoradas): " + line);
                    continue;
                }

                try {
                    // Construir el array de características (ignorar columnas iniciales y etiqueta)
                    double[] features = new double[values.length - ignoreColumns - 1];
                    int featureIndex = 0;

                    for (int i = 0; i < values.length; i++) {
                        if (i < ignoreColumns) {
                            continue; // Ignorar columnas iniciales (ej., ID)
                        }
                        if (i == labelColumn) {
                            labels.add(values[i]); // Asignar la etiqueta
                        } else {
                            features[featureIndex++] = Double.parseDouble(values[i]);
                        }
                    }
                    data.add(features);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir valores numéricos en la línea: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        // Verificar los datos cargados
        System.out.println("Tamaño del dataset: " + data.size());
        System.out.println("Número de características: " + (data.isEmpty() ? 0 : data.get(0).length));
        System.out.println("Ejemplo de etiqueta: " + (labels.isEmpty() ? "Ninguna" : labels.get(0)));

        // Validar el clasificador con los métodos disponibles
        double accuracyHoldOut = Validation.holdOut(data, labels, 0.7);
        System.out.println("Accuracy Hold-Out: " + accuracyHoldOut);

        double accuracyKFold = Validation.kFoldCrossValidation(data, labels, 10);
        System.out.println("Accuracy 10-Fold: " + accuracyKFold);

        double accuracyLeaveOneOut = Validation.leaveOneOut(data, labels);
        System.out.println("Accuracy Leave-One-Out: " + accuracyLeaveOneOut);
    }
}

import java.io.*;
import java.util.*;

public class Dataset {
    private List<double[]> data = new ArrayList<>();
    private List<String> labels = new ArrayList<>();

    /**
     * Carga un dataset desde un archivo CSV.
     *
     * @param filePath      Ruta al archivo.
     * @param labelColumn   indice de la columna que contiene las etiquetas.
     * @param ignoreColumns Número de columnas iniciales a ignorar (por ejemplo, identificadores).
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void loadCSV(String filePath, int labelColumn, int ignoreColumns) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            // Ignorar líneas vacías
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length <= labelColumn) {
                System.out.println("Error al procesar la línea: " + line);
                continue;
            }

            try {
                // Extraer etiqueta
                String label = parts[labelColumn].trim();
                labels.add(label);

                // Extraer características
                double[] features = new double[parts.length - ignoreColumns - 1];
                int featureIndex = 0;

                for (int i = 0; i < parts.length; i++) {
                    if (i < ignoreColumns || i == labelColumn) continue; // Ignorar columnas no relevantes
                    features[featureIndex++] = Double.parseDouble(parts[i].trim());
                }
                data.add(features);

            } catch (NumberFormatException e) {
                System.out.println("Error al procesar valores numéricos en la línea: " + line);
            }
        }
        reader.close();

        // Validar datos cargados
        if (data.isEmpty() || labels.isEmpty()) {
            System.out.println("El dataset está vacío. Revisa el archivo o el formato.");
        } else {
            System.out.println("Tamaño del dataset: " + data.size());
            System.out.println("Número de características: " + (data.get(0).length));
            System.out.println("Ejemplo de etiqueta: " + labels.get(0));
        }
    }

    public List<double[]> getData() {
        return data;
    }

    public List<String> getLabels() {
        return labels;
    }
}

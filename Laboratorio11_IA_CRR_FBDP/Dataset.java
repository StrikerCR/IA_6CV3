import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dataset {
    public static void loadDataset(String filePath, String delimiter, int labelColumn, int ignoreColumns,
                                   ArrayList<double[]> data, ArrayList<String> labels) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim(); // Elimina espacios al principio y al final
                if (line.isEmpty()) continue; // Ignora líneas vacías

                try {
                    String[] parts = line.split(delimiter);

                    // Valida que la línea tiene suficientes columnas
                    if (parts.length <= ignoreColumns) {
                        System.out.println("Línea ignorada (columnas insuficientes): " + line);
                        continue;
                    }

                    // Determina el índice de la etiqueta
                    int labelIndex = (labelColumn == -1) ? parts.length - 1 : labelColumn;

                    // Extrae las características numéricas
                    double[] row = new double[parts.length - ignoreColumns - 1];
                    for (int i = ignoreColumns, j = 0; i < parts.length; i++) {
                        if (i == labelIndex) continue; // Salta la columna de la etiqueta
                        row[j++] = Double.parseDouble(parts[i]);
                    }

                    // Agrega los datos y la etiqueta
                    data.add(row);
                    labels.add(parts[labelIndex]);

                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir valores numéricos en la línea " + lineNumber
                            + " (descartada): " + line);
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

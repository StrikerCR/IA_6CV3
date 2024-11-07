import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Laboratorio8_1_IA_CRR_FBDP {
    public static void main(String[] args) {
        String filePath = "bezdekIris.data"; // Ruta del archivo
        ArrayList<double[]> data = new ArrayList<>(); // Lista para almacenar las características numéricas
        ArrayList<String> species = new ArrayList<>(); // Lista para almacenar el nombre de la especie

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 5) {  // Verificar que tenga el formato correcto
                    double[] features = new double[4];
                    for (int i = 0; i < 4; i++) {
                        features[i] = Double.parseDouble(values[i]);
                    }
                    data.add(features);
                    species.add(values[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir los datos
        for (int i = 0; i < data.size(); i++) {
            double[] features = data.get(i);
            System.out.printf("%.1f, %.1f, %.1f, %.1f, %s\n", features[0], features[1], features[2], features[3], species.get(i));
        }
    }
}

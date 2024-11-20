import java.util.*;
import java.io.*;

public class Dataset {
    private List<double[]> data = new ArrayList<>();
    private List<String> labels = new ArrayList<>();

    public void loadCSV(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");
            double[] features = Arrays.stream(split, 0, split.length - 1)
                                       .mapToDouble(Double::parseDouble)
                                       .toArray();
            data.add(features);
            labels.add(split[split.length - 1]); // Última columna como etiqueta
        }
        br.close();
    }

    public List<double[]> getData() {
        return data;
    }

    public List<String> getLabels() {
        return labels;
    }
}

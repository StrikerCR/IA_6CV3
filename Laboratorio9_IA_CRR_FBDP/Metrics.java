import java.util.*;

public class Metrics {

    public static void confusionMatrix(List<String> trueLabels, List<String> predictedLabels) {
        Map<String, Map<String, Integer>> matrix = new HashMap<>();

        for (int i = 0; i < trueLabels.size(); i++) {
            String trueLabel = trueLabels.get(i);
            String predictedLabel = predictedLabels.get(i);

            matrix.putIfAbsent(trueLabel, new HashMap<>());
            matrix.get(trueLabel).put(predictedLabel, matrix.get(trueLabel).getOrDefault(predictedLabel, 0) + 1);
        }

        System.out.println("Confusion Matrix:");
        System.out.println(matrix);
    }
}

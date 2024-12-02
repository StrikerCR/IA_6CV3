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

    public static void classificationMetrics(List<String> trueLabels, List<String> predictedLabels) {
        int tp = 0, tn = 0, fp = 0, fn = 0;
        for (int i = 0; i < trueLabels.size(); i++) {
            String trueLabel = trueLabels.get(i);
            String predictedLabel = predictedLabels.get(i);
    
            if (trueLabel.equals("positive") && predictedLabel.equals("positive")) tp++;
            else if (trueLabel.equals("negative") && predictedLabel.equals("negative")) tn++;
            else if (trueLabel.equals("negative") && predictedLabel.equals("positive")) fp++;
            else if (trueLabel.equals("positive") && predictedLabel.equals("negative")) fn++;
        }
        System.out.println("Precision: " + (double) tp / (tp + fp));
        System.out.println("Recall: " + (double) tp / (tp + fn));
        System.out.println("F1-Score: " + (2 * tp) / (2.0 * tp + fp + fn));
    }
    
}

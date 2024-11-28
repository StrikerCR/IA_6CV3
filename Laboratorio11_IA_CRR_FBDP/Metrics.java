import java.util.HashMap;

public class Metrics {

    public static class ConfusionMatrix {
        private final HashMap<String, HashMap<String, Integer>> matrix = new HashMap<>();

        public void update(String actual, String predicted) {
            matrix.putIfAbsent(actual, new HashMap<>());
            matrix.get(actual).put(predicted, matrix.get(actual).getOrDefault(predicted, 0) + 1);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Confusion Matrix:\n");
            for (String actual : matrix.keySet()) {
                sb.append(actual).append(": ").append(matrix.get(actual)).append("\n");
            }
            return sb.toString();
        }
    }
}

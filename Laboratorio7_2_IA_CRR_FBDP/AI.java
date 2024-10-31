import java.util.ArrayList;
import java.util.Collections;

public class AI {
    private int playerId;

    public AI(int playerId) {
        this.playerId = playerId;
    }

    public int[] getBestMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[] {-1, -1};
        int depth = 4; // Profundidad máxima para la búsqueda Minimax

        ArrayList<int[]> moves = new ArrayList<>();
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board.getGrid()[row][col] == 0) { // Solo movimientos en celdas vacías
                    moves.add(new int[] {row, col});
                }
            }
        }

        // Aleatoriza el orden de evaluación de movimientos
        Collections.shuffle(moves);

        for (int[] move : moves) {
            int row = move[0];
            int col = move[1];
            if (board.makeMove(row, col, playerId)) {
                int score = minimax(board, depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                board.undoMove(row, col);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        int winner = board.checkWinner();
        if (winner == playerId) return 100;
        else if (winner != 0) return -100;
        if (depth == 0 || board.isFull()) return evaluateBoard(board);

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (board.getGrid()[row][col] == 0) { // Juega solo en celdas vacías
                        board.makeMove(row, col, playerId);
                        int eval = minimax(board, depth - 1, alpha, beta, false);
                        board.undoMove(row, col);
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            int opponentId = (playerId == 1) ? 2 : 1;
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (board.getGrid()[row][col] == 0) { // Juega solo en celdas vacías
                        board.makeMove(row, col, opponentId);
                        int eval = minimax(board, depth - 1, alpha, beta, true);
                        board.undoMove(row, col);
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return minEval;
        }
    }

    private int evaluateBoard(Board board) {
        int score = 0;

        // Valora el centro del tablero más alto
        int[][] grid = board.getGrid();
        int centerColumn = 2;
        for (int row = 0; row < 4; row++) {
            if (grid[row][centerColumn] == playerId) score += 3;
            if (grid[row][centerColumn - 1] == playerId) score += 2;
        }

        // Valora posibles alineaciones en filas, columnas y diagonales
        score += evaluateLines(board, playerId);
        score -= evaluateLines(board, (playerId == 1) ? 2 : 1);

        return score;
    }

    private int evaluateLines(Board board, int player) {
        int score = 0;
        int[][] grid = board.getGrid();

        // Revisa cada línea posible en filas, columnas y diagonales
        for (int row = 0; row < 4; row++) {
            score += evaluateLine(grid[row][0], grid[row][1], grid[row][2], grid[row][3], player);
            score += evaluateLine(grid[0][row], grid[1][row], grid[2][row], grid[3][row], player);
        }
        score += evaluateLine(grid[0][0], grid[1][1], grid[2][2], grid[3][3], player);
        score += evaluateLine(grid[0][3], grid[1][2], grid[2][1], grid[3][0], player);

        return score;
    }

    private int evaluateLine(int a, int b, int c, int d, int player) {
        int score = 0;
        int opponent = (player == 1) ? 2 : 1;

        int playerCount = (a == player ? 1 : 0) + (b == player ? 1 : 0) + (c == player ? 1 : 0) + (d == player ? 1 : 0);
        int opponentCount = (a == opponent ? 1 : 0) + (b == opponent ? 1 : 0) + (c == opponent ? 1 : 0) + (d == opponent ? 1 : 0);

        if (playerCount == 3 && opponentCount == 0) score += 5;  // Prioridad alta para 3 en línea propio
        else if (playerCount == 2 && opponentCount == 0) score += 2; // Prioridad media para 2 en línea propio
        else if (playerCount == 1 && opponentCount == 0) score += 1; // Baja prioridad para 1 en línea propio

        return score;
    }
}

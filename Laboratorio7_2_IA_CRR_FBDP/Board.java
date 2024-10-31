public class Board {
    private int[][] grid; // 0 = vacío, 1 = jugador 1, 2 = jugador 2
    private final int rows = 4;
    private final int cols = 4;

    public Board() {
        grid = new int[rows][cols];
    }

    public boolean makeMove(int row, int col, int player) {
        if (grid[row][col] == 0) {
            grid[row][col] = player;
            return true;
        }
        return false;
    }

    public void undoMove(int row, int col) {
        grid[row][col] = 0;
    }

    public boolean isFull() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 0) return false;
            }
        }
        return true;
    }

    public int checkWinner() {
        for (int i = 0; i < rows; i++) {
            if (checkLine(grid[i][0], grid[i][1], grid[i][2], grid[i][3])) return grid[i][0];
            if (checkLine(grid[0][i], grid[1][i], grid[2][i], grid[3][i])) return grid[0][i];
        }
        if (checkLine(grid[0][0], grid[1][1], grid[2][2], grid[3][3])) return grid[0][0];
        if (checkLine(grid[0][3], grid[1][2], grid[2][1], grid[3][0])) return grid[0][3];
        return 0; // Sin ganador
    }

    private boolean checkLine(int a, int b, int c, int d) {
        return (a != 0) && (a == b) && (b == c) && (c == d);
    }

    public int[][] getGrid() {
        return grid;
    }

    // Agrega este método para limpiar el tablero
    public void clear() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = 0; // Reinicia todas las celdas a 0 (vacío)
            }
        }
    }
}

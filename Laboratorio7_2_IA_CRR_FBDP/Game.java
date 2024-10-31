public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Player p1, Player p2) {
        board = new Board();
        player1 = p1;
        player2 = p2;
        currentPlayer = player1;
    }

    public boolean playTurn(int row, int col) {
        if (board.makeMove(row, col, currentPlayer.getId())) {
            if (board.checkWinner() == currentPlayer.getId()) {
                System.out.println("Player " + currentPlayer.getId() + " wins!");
            } else if (board.isFull()) {
                System.out.println("It's a tie!");
            } else {
                togglePlayer();
            }
            return true;
        }
        return false;
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}

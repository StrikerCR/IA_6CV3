public class AIPlayer extends Player {
    private AI ai;

    public AIPlayer(int id) {
        super(id);
        ai = new AI(id);
    }

    @Override
    public int[] getMove(Board board) {
        return ai.getBestMove(board);
    }
}
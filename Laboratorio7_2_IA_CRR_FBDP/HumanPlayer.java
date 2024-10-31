public class HumanPlayer extends Player {
    public HumanPlayer(int id) {
        super(id);
    }

    @Override
    public int[] getMove(Board board) {
        return null; // El movimiento lo hace el usuario en la interfaz
    }
}
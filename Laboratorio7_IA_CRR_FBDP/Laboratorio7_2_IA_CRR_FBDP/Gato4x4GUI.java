import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gato4x4GUI extends JFrame {
    private Game game;
    private JButton[][] buttons;
    private JLabel statusLabel;
    private final int rows = 4;
    private final int cols = 4;
    private Timer aiTimer;
    private final Color neonPurple = new Color(148, 0, 211); // Color morado neón
    private final Color neonBlue = new Color(0, 255, 255);   // Color azul neón

    public Gato4x4GUI() {
        setTitle("Gato 4x4");
        setSize(500, 550); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeGameMode();
        initUI();
    }

    private void initializeGameMode() {
        String[] options = {"Humano vs Humano", "Humano vs Maquina", "Maquina vs Maquina"};

        // Estilos para JOptionPane con texto en azul y botones en morado
        UIManager.put("OptionPane.background", Color.BLACK);
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("OptionPane.messageForeground", neonPurple); // Texto de JOptionPane en morado
        UIManager.put("OptionPane.messageFont", new Font("Monospaced", Font.BOLD, 16));
        
        // Estilo de botones de JOptionPane
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", neonBlue); // Texto del botón en azul
        UIManager.put("Button.font", new Font("Monospaced", Font.BOLD, 14));
        UIManager.put("Button.border", BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(neonPurple, 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        int mode = JOptionPane.showOptionDialog(
            this,
            "Seleccione el modo de juego:",
            "Modo de Juego",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        if (mode == -1) {
            JOptionPane.showMessageDialog(this, "No se selecciono ningun modo de juego. El programa se cerrara.");
            System.exit(0);
        }

        Player player1 = new HumanPlayer(1);
        Player player2 = (mode == 1) ? new AIPlayer(2) : new HumanPlayer(2);
        if (mode == 2) {
            player1 = new AIPlayer(1);
            player2 = new AIPlayer(2);
        }

        game = new Game(player1, player2);

        if (mode == 2) {
            startAutoPlay();
        }
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);

        // Panel del tablero
        JPanel boardPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
        boardPanel.setBackground(Color.BLACK);
        buttons = new JButton[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Monospaced", Font.BOLD, 28));
                button.setBackground(Color.BLACK);
                button.setForeground(neonBlue); // Texto en azul neón para X y O
                button.setFocusPainted(false);

                // Estilo retro-neon: borde morado y texto azul
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(neonPurple, 3), // Borde en morado neón
                        BorderFactory.createLineBorder(Color.BLACK, 8)
                ));
                button.setContentAreaFilled(false);
                button.setOpaque(true);

                buttons[row][col] = button;
                int finalRow = row;
                int finalCol = col;

                button.addActionListener(e -> {
                    if (game.getCurrentPlayer() instanceof HumanPlayer) {
                        if (game.playTurn(finalRow, finalCol)) {
                            updateBoard();
                            checkForEndGame();
                            updateStatusLabel();

                            if (game.getCurrentPlayer() instanceof AIPlayer) {
                                playAITurn();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Movimiento no valido. Elija otra celda.");
                        }
                    }
                });
                boardPanel.add(button);
            }
        }

        // Etiqueta de estado en morado neón
        statusLabel = new JLabel("Turno de Jugador X", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        statusLabel.setForeground(neonPurple); // Texto en morado neón
        statusLabel.setOpaque(true);
        statusLabel.setBackground(Color.BLACK);
        updateStatusLabel();

        // Boton de reinicio en morado con texto azul
        JButton restartButton = new JButton("Reiniciar");
        restartButton.setFont(new Font("Monospaced", Font.BOLD, 16));
        restartButton.setBackground(neonPurple);
        restartButton.setForeground(neonBlue); // Texto en azul neón
        restartButton.setBorder(BorderFactory.createLineBorder(neonBlue, 2));
        restartButton.addActionListener(e -> resetGame());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        bottomPanel.add(restartButton, BorderLayout.EAST);
        bottomPanel.setBackground(Color.BLACK);

        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void playAITurn() {
        if (game.getCurrentPlayer() instanceof AIPlayer) {
            int[] aiMove = game.getCurrentPlayer().getMove(game.getBoard());
            game.playTurn(aiMove[0], aiMove[1]);
            updateBoard();
            checkForEndGame();
            updateStatusLabel();
        }
    }

    private void startAutoPlay() {
        aiTimer = new Timer(1000, e -> {
            if (game.getCurrentPlayer() instanceof AIPlayer && game.getBoard().checkWinner() == 0 && !game.getBoard().isFull()) {
                playAITurn();
            } else {
                aiTimer.stop();
            }
        });
        aiTimer.start();
    }

    private void updateBoard() {
        int[][] grid = game.getBoard().getGrid();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 1) {
                    buttons[row][col].setText("X");
                    buttons[row][col].setForeground(neonBlue); // X en azul neón
                    buttons[row][col].setEnabled(false);
                } else if (grid[row][col] == 2) {
                    buttons[row][col].setText("O");
                    buttons[row][col].setForeground(neonBlue); // O en azul neón
                    buttons[row][col].setEnabled(false);
                } else {
                    buttons[row][col].setText("");
                    buttons[row][col].setEnabled(true);
                }
            }
        }
    }

    private void checkForEndGame() {
        int winner = game.getBoard().checkWinner();
        if (winner != 0) {
            JOptionPane.showMessageDialog(this, "Jugador " + (winner == 1 ? "X" : "O") + " gana!");
            resetGame();
        } else if (game.getBoard().isFull()) {
            JOptionPane.showMessageDialog(this, "Empate!");
            resetGame();
        }
    }

    private void updateStatusLabel() {
        String player = (game.getCurrentPlayer().getId() == 1) ? "X" : "O";
        statusLabel.setText("Turno de Jugador " + player);
    }

    private void resetGame() {
        if (aiTimer != null) {
            aiTimer.stop();
        }
        initializeGameMode();
        game.getBoard().clear();
        updateBoard();
        updateStatusLabel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Gato4x4GUI().setVisible(true));
    }
}

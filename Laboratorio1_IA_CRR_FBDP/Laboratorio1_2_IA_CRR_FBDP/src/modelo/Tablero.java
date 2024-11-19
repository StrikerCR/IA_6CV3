package modelo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;
import vista.FormResultado;
import vista.FormTicTacToe;

public class Tablero extends JPanel {

    private String selectedGameMode;

    private int anchoCI;
    private int altoCI;
    private int margen;
    private Color colorTablero;
    private Color colorCI;
    private TipoImagen jugadorActual;
    private TipoImagen turnoPartida;

    private Jugador jugador1;
    private Jugador jugador2;

    private ArrayList<Cuadro> cuadritos;
    private Cuadro cuadroFrontal;

    int bin;

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public Tablero(String selectedGameMode) {
        this.selectedGameMode = selectedGameMode; // Asigna correctamente el modo de juego
        init();
        // Llama a maquina automáticamente si el modo de juego es "Maquina vs Maquina"
        if (selectedGameMode.equals("Maquina vs Maquina")) {
            executor.schedule(() -> maquina(null), 500, TimeUnit.MILLISECONDS);
        }
    }

    private void init() {
        anchoCI = 80;
        altoCI = 80;
        colorCI = Color.BLUE;
        colorTablero = Color.RED;
        margen = 6;
        jugador1 = new Jugador();
        jugador2 = new Jugador();
        cuadritos = new ArrayList<>();
        jugadorActual = TipoImagen.EQUIS;
        turnoPartida = TipoImagen.EQUIS;
    }

    public void crearTablero() {
        setLayout(null);
        setSize(anchoCI * 4 + margen * 5, altoCI * 4 + margen * 5);
        setBackground(colorTablero);
        cuadroFrontal = new Cuadro(this.getWidth(), this.getHeight(), Color.RED);
        cuadroFrontal.setLocation(0, 0);
        cuadroFrontal.setOpaque(false);
        cuadroFrontal.setEnabled(false);
        add(cuadroFrontal);
        crearCuadrosInternos();
    }

    public void crearCuadrosInternos() {
        int x = margen;
        int y = margen;

        for (int i = 0; i < 4; i++) {
            x = margen;
            for (int j = 0; j < 4; j++) {
                Cuadro cuadrito = new Cuadro(anchoCI, altoCI, colorCI);
                cuadrito.setCursor(new Cursor(Cursor.HAND_CURSOR));
                cuadrito.setLocation(x, y);
                cuadrito.setI(i);
                cuadrito.setJ(j);
                add(cuadrito);
                cuadritos.add(cuadrito);
                crearEventosCuadro(cuadrito);
                x += anchoCI + margen;
            }
            y += altoCI + margen;
        }
    }

    public void crearEventosCuadro(Cuadro cuadrito) {
        MouseListener evento = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Verificar si el cuadro ya está dibujado
                if (cuadrito.isDibujado()) {
                    return;
                }

                TipoImagen tipoImagenResultado = null;

                if (selectedGameMode.equals("Humano vs Humano")) {
                    // Modo Humano vs Humano: Alternar entre EQUIS y CIRCULO en cada turno
                    if (jugadorActual == TipoImagen.EQUIS) {
                        cuadrito.setTipoImagen(TipoImagen.EQUIS);
                        jugador1.getTablero()[cuadrito.getI()][cuadrito.getJ()] = 1;
                        tipoImagenResultado = jugador1.cuatroEnRaya(jugador2);
                        bin = resultado(tipoImagenResultado, TipoImagen.EQUIS);
                        jugadorActual = TipoImagen.CIRCULO;
                        cambiarEstilos(TipoImagen.CIRCULO);
                    } else if (jugadorActual == TipoImagen.CIRCULO) {
                        cuadrito.setTipoImagen(TipoImagen.CIRCULO);
                        jugador2.getTablero()[cuadrito.getI()][cuadrito.getJ()] = 1;
                        tipoImagenResultado = jugador2.cuatroEnRaya(jugador1);
                        bin = resultado(tipoImagenResultado, TipoImagen.CIRCULO);
                        jugadorActual = TipoImagen.EQUIS;
                        cambiarEstilos(TipoImagen.EQUIS);
                    }
                } else if (selectedGameMode.equals("Jugador vs Maquina") && jugadorActual == TipoImagen.EQUIS) {
                    // Modo Jugador vs Maquina: Solo responde al clic del jugador humano (EQUIS)
                    cuadrito.setTipoImagen(TipoImagen.EQUIS);
                    jugador1.getTablero()[cuadrito.getI()][cuadrito.getJ()] = 1;
                    tipoImagenResultado = jugador1.cuatroEnRaya(jugador2);
                    bin = resultado(tipoImagenResultado, TipoImagen.EQUIS);
                    jugadorActual = TipoImagen.CIRCULO;
                    cambiarEstilos(TipoImagen.CIRCULO);
                    // Llamar a la máquina para el turno de CIRCULO
                    if (bin == 0) {
                        maquina(tipoImagenResultado);
                    }
                }

                cuadrito.setDibujado(true);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        cuadrito.addMouseListener(evento);
    }

    public void maquina(TipoImagen tipoImagenResultado) {
        // Verificar que el modo de juego esté definido y que `cuadritos` esté correctamente inicializado
        if (selectedGameMode == null || cuadritos == null || cuadritos.isEmpty()) {
            System.out.println("Error: Modo de juego no definido o lista de cuadros no inicializada.");
            return;
        }

        int random = (int) (Math.random() * cuadritos.size()); // Asegura un índice válido
        while (cuadritos.get(random).isDibujado()) {
            random = (int) (Math.random() * cuadritos.size());
        }

        if (selectedGameMode.equals("Jugador vs Maquina") && jugadorActual == TipoImagen.CIRCULO) {
            // Ejecuta movimiento automático solo para CIRCULO
            Cuadro cuadrito = cuadritos.get(random);
            cuadrito.setTipoImagen(TipoImagen.CIRCULO);
            jugador2.getTablero()[cuadrito.getI()][cuadrito.getJ()] = 1;
            tipoImagenResultado = jugador2.cuatroEnRaya(jugador1);
            bin = resultado(tipoImagenResultado, TipoImagen.CIRCULO);
            jugadorActual = TipoImagen.EQUIS;
            cambiarEstilos(TipoImagen.EQUIS);
            cuadrito.setDibujado(true);
            repaint();
        } else if (selectedGameMode.equals("Maquina vs Maquina")) {
            // Realizar el movimiento automático
            Cuadro cuadrito = cuadritos.get(random);
            if (jugadorActual == TipoImagen.EQUIS) {
                cuadrito.setTipoImagen(TipoImagen.EQUIS);
                jugador1.getTablero()[cuadrito.getI()][cuadrito.getJ()] = 1;
                tipoImagenResultado = jugador1.cuatroEnRaya(jugador2);
                bin = resultado(tipoImagenResultado, TipoImagen.EQUIS);
                jugadorActual = TipoImagen.CIRCULO;
                cambiarEstilos(TipoImagen.CIRCULO);
            } else if (jugadorActual == TipoImagen.CIRCULO) {
                cuadrito.setTipoImagen(TipoImagen.CIRCULO);
                jugador2.getTablero()[cuadrito.getI()][cuadrito.getJ()] = 1;
                tipoImagenResultado = jugador2.cuatroEnRaya(jugador1);
                bin = resultado(tipoImagenResultado, TipoImagen.CIRCULO);
                jugadorActual = TipoImagen.EQUIS;
                cambiarEstilos(TipoImagen.EQUIS);
            }
            cuadrito.setDibujado(true);
            repaint();

            // Llamada recursiva para el siguiente turno en Maquina vs Maquina si no hay ganador
            if (bin == 0) {
                // Usa un retardo para que el juego progrese automáticamente
                executor.schedule(() -> maquina(null), 500, TimeUnit.MILLISECONDS);
            }
        }
    }

    public void cambiarEstilos(TipoImagen jugadorAct) {
        if (jugadorAct == TipoImagen.CIRCULO) {
            FormTicTacToe.imgJugadorEquis.setRuta(Ruta.JUGADORAUXILLAR);
            FormTicTacToe.imgJugadorEquis.repaint();
            FormTicTacToe.nombreJugadorEquis.setForeground(new Color(240, 240, 240, 100));

            FormTicTacToe.imgJugadorCirculo.setRuta(Ruta.JUGADORCIRCULO);
            FormTicTacToe.imgJugadorCirculo.repaint();
            FormTicTacToe.nombreJugadorCirculo.setForeground(new Color(255, 200, 255));
        } else if (jugadorAct == TipoImagen.EQUIS) {
            FormTicTacToe.imgJugadorCirculo.setRuta(Ruta.JUGADORAUXILLAR);
            FormTicTacToe.imgJugadorCirculo.repaint();
            FormTicTacToe.nombreJugadorCirculo.setForeground(new Color(240, 240, 240, 100));

            FormTicTacToe.imgJugadorEquis.setRuta(Ruta.JUGADOREQUIS);
            FormTicTacToe.imgJugadorEquis.repaint();
            FormTicTacToe.nombreJugadorEquis.setForeground(new Color(180, 232, 255));
        }
    }

    public int resultado(TipoImagen tipoImagenResultado, TipoImagen jugadorGanador) {
        int bin = 0;

        if (tipoImagenResultado == TipoImagen.EMPATE) {
            FormResultado formResultado = new FormResultado(TipoImagen.EMPATE, this);
            formResultado.setVisible(true);
            System.out.println("Empate");
        } else if (tipoImagenResultado != null) {
            System.out.println("Hay un ganador");
            bin = 1;
            Ruta.cambiarRutas(jugadorGanador);
            cuadroFrontal.setTipoImagen(tipoImagenResultado);
            FormResultado formResultado = new FormResultado(jugadorGanador, this);
            formResultado.setVisible(true);
        }

        return bin;
    }

    public void reiniciarTablero(TipoImagen ganador) {
        desactivarCuadros(false);
        borrarImagenes();
        cuadroFrontal.setTipoImagen(null);
        if (ganador == TipoImagen.EQUIS) {
            int puntajeNuevo = Integer.parseInt(FormTicTacToe.puntajeEquis.getText()) + 1;
            FormTicTacToe.puntajeEquis.setText(String.valueOf(puntajeNuevo));
        } else if (ganador == TipoImagen.CIRCULO) {
            int puntajeNuevo = Integer.parseInt(FormTicTacToe.puntajeCirculo.getText()) + 1;
            FormTicTacToe.puntajeCirculo.setText(String.valueOf(puntajeNuevo));
        }

        jugador1.limpiar();
        jugador2.limpiar();

        if (turnoPartida == TipoImagen.EQUIS) {
            jugadorActual = TipoImagen.CIRCULO;
            turnoPartida = TipoImagen.CIRCULO;
            maquina(null);
        } else if (turnoPartida == TipoImagen.CIRCULO) {
            jugadorActual = TipoImagen.EQUIS;
            turnoPartida = TipoImagen.EQUIS;
        }
        jugador1.limpiar();
        jugador2.limpiar();
        repaint();
    }

    public void desactivarCuadros(boolean valor) {
        for (Cuadro cuadrito : cuadritos) {
            cuadrito.setDibujado(valor);
        }
    }

    public void borrarImagenes() {
        for (Cuadro cuadrito : cuadritos) {
            cuadrito.setTipoImagen(null);
        }
    }

    public TipoImagen getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(TipoImagen jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public ArrayList<Cuadro> getCuadritos() {
        return cuadritos;
    }

    public void setCuadritos(ArrayList<Cuadro> cuadritos) {
        this.cuadritos = cuadritos;
    }

    public int getAnchoCI() {
        return anchoCI;
    }

    public void setAnchoCI(int anchoCI) {
        this.anchoCI = anchoCI;
    }

    public int getAltoCI() {
        return altoCI;
    }

    public void setAltoCI(int altoCI) {
        this.altoCI = altoCI;
    }

    public int getMargen() {
        return margen;
    }

    public void setMargen(int margen) {
        this.margen = margen;
    }

    public Color getColorTablero() {
        return colorTablero;
    }

    public void setColorTablero(Color colorTablero) {
        this.colorTablero = colorTablero;
    }

    public Color getColorCI() {
        return colorCI;
    }

    public void setColorCI(Color colorCI) {
        this.colorCI = colorCI;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    private int[][] grid = new int[4][4];
    private final int rows = 4;
    private final int cols = 4;
    private int currentPlayer = 1;

    // Método mejorado de la IA para obtener la mejor jugada utilizando un minimax optimizado
    public int[] getBestMove(int playerId) {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[] {-1, -1};

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == 0) {
                    makeMove(row, col, playerId);
                    int score = minimax(Integer.MIN_VALUE, Integer.MAX_VALUE, false, playerId);
                    undoMove(row, col);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[] {row, col};
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(int alpha, int beta, boolean isMaximizing, int playerId) {
        int winner = checkWinner();
        int opponentId = (playerId == 1) ? 2 : 1;

        if (winner == playerId) return 1000;  // IA gana
        if (winner == opponentId) return -1000;  // Oponente gana
        if (isFull()) return 0;  // Empate

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row][col] == 0) {
                        makeMove(row, col, playerId);
                        int eval = minimax(alpha, beta, false, playerId);
                        undoMove(row, col);
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) break;  // Poda alfa-beta
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (grid[row][col] == 0) {
                        makeMove(row, col, opponentId);
                        int eval = minimax(alpha, beta, true, playerId);
                        undoMove(row, col);
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) break;  // Poda alfa-beta
                    }
                }
            }
            return minEval;
        }
    }

    public boolean makeMove(int row, int col, int player) {
        if (grid[row][col] == 0 && player == currentPlayer) {
            grid[row][col] = player;
            togglePlayer();
            return true;
        }
        return false;
    }

    public void undoMove(int row, int col) {
        grid[row][col] = 0;
        togglePlayer();
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
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
        return 0;
    }

    private boolean checkLine(int a, int b, int c, int d) {
        return (a != 0) && (a == b) && (b == c) && (c == d);
    }

    public void resetBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = 0;
            }
        }
        currentPlayer = 1;
    }
    
}


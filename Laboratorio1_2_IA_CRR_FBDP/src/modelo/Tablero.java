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

/**
 *
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 *
 *
 */
public class Tablero extends JPanel {

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

    public Tablero() {
        init();
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

        MouseListener evento;
        evento = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (cuadrito.isDibujado()) {
                    return;
                }
                TipoImagen tipoImagenResultado = null;
                if (jugadorActual == TipoImagen.EQUIS) {
                    cuadrito.setTipoImagen(TipoImagen.EQUIS);
                    jugador1.getTablero()[cuadrito.getI()][cuadrito.getJ()] = 1;
                    tipoImagenResultado = jugador1.cuatroEnRaya(jugador2);
                    bin = resultado(tipoImagenResultado, TipoImagen.EQUIS);
                    jugadorActual = TipoImagen.CIRCULO;
                    cambiarEstilos(TipoImagen.CIRCULO);
                }
                cuadrito.setDibujado(true);
                repaint();
                if (bin == 0) {
                    maquina(tipoImagenResultado);
                }
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

        int random = (int) (Math.random() * 16);
        while (cuadritos.get(random).isDibujado()) {
            random = (int) (Math.random() * 16);
        }

        if (jugadorActual == TipoImagen.CIRCULO) {
            cuadritos.get(random).setTipoImagen(TipoImagen.CIRCULO);
            jugador2.getTablero()[cuadritos.get(random).getI()][cuadritos.get(random).getJ()] = 1;
            tipoImagenResultado = jugador2.cuatroEnRaya(jugador1);
            bin = resultado(tipoImagenResultado, TipoImagen.CIRCULO);
            jugadorActual = TipoImagen.EQUIS;
            cambiarEstilos(TipoImagen.EQUIS);
        }

        cuadritos.get(random).setDibujado(true);
        repaint();
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
    
    public void reiniciarTablero(TipoImagen ganador){
        desactivarCuadros(false);
        borrarImagenes();
        cuadroFrontal.setTipoImagen(null);
        if(ganador == TipoImagen.EQUIS){
            int puntajeNuevo = Integer.parseInt(FormTicTacToe.puntajeEquis.getText()) + 1;
            FormTicTacToe.puntajeEquis.setText(String.valueOf(puntajeNuevo));
        } else if(ganador == TipoImagen.CIRCULO){
            int puntajeNuevo = Integer.parseInt(FormTicTacToe.puntajeCirculo.getText()) + 1;
            FormTicTacToe.puntajeCirculo.setText(String.valueOf(puntajeNuevo));
        }
        
        jugador1.limpiar();
        jugador2.limpiar();
        
        if(turnoPartida == TipoImagen.EQUIS){
            jugadorActual = TipoImagen.CIRCULO;
            turnoPartida = TipoImagen.CIRCULO;
            maquina(null);
        } else if(turnoPartida == TipoImagen.CIRCULO){
            jugadorActual = TipoImagen.EQUIS;
            turnoPartida = TipoImagen.EQUIS;
        }
        jugador1.limpiar();
        jugador2.limpiar();
        repaint();
    }
    
    public void desactivarCuadros(boolean valor){
        for (Cuadro cuadrito : cuadritos) {
            cuadrito.setDibujado(valor);
        }
    }
    
    public void borrarImagenes(){
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

}

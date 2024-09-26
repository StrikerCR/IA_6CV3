/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Color;
import java.awt.Image;
import javax.swing.JLabel;
import modelo.Imagen;
import modelo.Jugador;
import modelo.Tablero;
import modelo.TipoImagen;

/**
 *
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 *
**/

public class FormTicTacToe extends javax.swing.JFrame {
    
    public static Imagen imgJugadorEquis;
    public static Imagen imgJugadorCirculo;
    public static JLabel nombreJugadorEquis;
    public static JLabel nombreJugadorCirculo;
    public static JLabel puntajeEquis;
    public static JLabel puntajeCirculo;
    
    private Jugador jugador1, jugador2;
    private Tablero tablero;

    public FormTicTacToe(Jugador jugador1, Jugador jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        init();
        imgJugadorEquis =  jugadorEquis;
        imgJugadorCirculo = jugadorCirculo;
        nombreJugadorEquis = lblNombreJugador1;
        nombreJugadorCirculo = lblNombreJugador2;
        puntajeEquis = lblPuntajeJ1;
        puntajeCirculo = lblPuntajeJ2;
        tablero.cambiarEstilos(TipoImagen.EQUIS);
    }
    
    public void init(){
        
        setUndecorated(true);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        panelFondo.setOpaque(false);
        setBackground(new Color(0,0,0,0));
        
        lblNombreJugador1.setText(jugador1.getNombre());
        lblNombreJugador2.setText(jugador2.getNombre());
        
        tablero = new Tablero();
        tablero.setJugador1(jugador1);
        tablero.setJugador2(jugador2);
        tablero.setAltoCI(110);
        tablero.setAnchoCI(110);
        tablero.setMargen(5);
        tablero.setColorCI(new Color(14,19,43));
        tablero.setColorTablero(new Color(239,180,255));
        tablero.setLocation(65,205);
        tablero.crearTablero();
        tablero.setVisible(true);
        
        panelFondo.add(tablero);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        panelFondo = new modelo.PanelRedondeado();
        lblCierre = new javax.swing.JLabel();
        jugadorCirculo = new modelo.Imagen();
        jugadorEquis = new modelo.Imagen();
        lblNombreJugador2 = new javax.swing.JLabel();
        lblNombreJugador1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblPuntajeJ1 = new javax.swing.JLabel();
        lblPuntajeJ2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFondo.setBackground(new java.awt.Color(14, 19, 43));
        panelFondo.setPreferredSize(new java.awt.Dimension(600, 700));
        panelFondo.setLayout(null);

        lblCierre.setFont(new java.awt.Font("Eras Demi ITC", 1, 24)); // NOI18N
        lblCierre.setForeground(new java.awt.Color(240, 192, 255));
        lblCierre.setText("X");
        lblCierre.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCierre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCierreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCierreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCierreMouseExited(evt);
            }
        });
        panelFondo.add(lblCierre);
        lblCierre.setBounds(560, 20, 30, 40);

        jugadorCirculo.setText("imagen1");
        jugadorCirculo.setRuta("/resources/JugadorCirculo.png");
        panelFondo.add(jugadorCirculo);
        jugadorCirculo.setBounds(420, 60, 80, 80);

        jugadorEquis.setText("imagen1");
        jugadorEquis.setRuta("/resources/JugadorEquis.png");
        panelFondo.add(jugadorEquis);
        jugadorEquis.setBounds(90, 60, 80, 80);

        lblNombreJugador2.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        lblNombreJugador2.setForeground(new java.awt.Color(255, 200, 255));
        lblNombreJugador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJugador2.setText("NAME");
        panelFondo.add(lblNombreJugador2);
        lblNombreJugador2.setBounds(400, 150, 120, 30);

        lblNombreJugador1.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        lblNombreJugador1.setForeground(new java.awt.Color(180, 232, 255));
        lblNombreJugador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreJugador1.setText("NAME");
        panelFondo.add(lblNombreJugador1);
        lblNombreJugador1.setBounds(70, 150, 120, 30);

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 38)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("-");
        panelFondo.add(jLabel2);
        jLabel2.setBounds(290, 80, 20, 60);

        lblPuntajeJ1.setFont(new java.awt.Font("Showcard Gothic", 1, 38)); // NOI18N
        lblPuntajeJ1.setForeground(new java.awt.Color(255, 255, 255));
        lblPuntajeJ1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPuntajeJ1.setText("0");
        panelFondo.add(lblPuntajeJ1);
        lblPuntajeJ1.setBounds(200, 80, 60, 60);

        lblPuntajeJ2.setFont(new java.awt.Font("Showcard Gothic", 1, 38)); // NOI18N
        lblPuntajeJ2.setForeground(new java.awt.Color(255, 255, 255));
        lblPuntajeJ2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPuntajeJ2.setText("0");
        panelFondo.add(lblPuntajeJ2);
        lblPuntajeJ2.setBounds(330, 80, 60, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblCierreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCierreMouseClicked

    private void lblCierreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseEntered
        lblCierre.setForeground(Color.red);
    }//GEN-LAST:event_lblCierreMouseEntered

    private void lblCierreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseExited
        lblCierre.setForeground(new Color(240, 192, 255));
    }//GEN-LAST:event_lblCierreMouseExited

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private modelo.Imagen jugadorCirculo;
    private modelo.Imagen jugadorEquis;
    private javax.swing.JLabel lblCierre;
    private javax.swing.JLabel lblNombreJugador1;
    private javax.swing.JLabel lblNombreJugador2;
    private javax.swing.JLabel lblPuntajeJ1;
    private javax.swing.JLabel lblPuntajeJ2;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}

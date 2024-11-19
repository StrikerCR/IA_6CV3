package vista;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.border.Border;
import modelo.Jugador;
import modelo.TipoImagen;

/**
 *
 * @author Rafael Cabañas Rocha
 * @author Diana Paola Fernández Baños
 *
 *
 */
public class ModoJuego extends javax.swing.JFrame {

    private static String selectedGameMode = "Humano vs Humano"; // Default mode

    public ModoJuego() {

        init();

    }

    public void init() {

        setUndecorated(true);
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(new Color(0, 0, 0, 0));
        panelFondo.requestFocus();
        panelFondo.setOpaque(false);
        Border bordeBoton = BorderFactory.createLineBorder(new Color(243, 211, 246), 2);
        panelBoton.setBorder(bordeBoton);
        // Make checkboxes transparent
        jCheckBoxJJ.setOpaque(false);
        jCheckBoxJM.setOpaque(false);
        jCheckBoxMM.setOpaque(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelFondo = new modelo.PanelRedondeado();
        lblCierre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelBoton = new javax.swing.JPanel();
        lblIniciar = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jCheckBoxJM = new javax.swing.JCheckBox();
        jCheckBoxJJ = new javax.swing.JCheckBox();
        jCheckBoxMM = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelFondo.setBackground(new java.awt.Color(14, 19, 49));
        panelFondo.setForeground(new java.awt.Color(60, 63, 65));
        panelFondo.setPreferredSize(new java.awt.Dimension(416, 315));
        panelFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelFondoMousePressed(evt);
            }
        });
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
        lblCierre.setBounds(390, 10, 30, 40);

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(230, 251, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gato de 4");
        panelFondo.add(jLabel1);
        jLabel1.setBounds(0, 30, 430, 45);

        panelBoton.setBackground(new java.awt.Color(42, 22, 79));

        lblIniciar.setFont(new java.awt.Font("Gill Sans Ultra Bold", 1, 18)); // NOI18N
        lblIniciar.setForeground(new java.awt.Color(241, 227, 252));
        lblIniciar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIniciar.setText("Continuar");
        lblIniciar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblIniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblIniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblIniciarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblIniciarMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelBotonLayout = new javax.swing.GroupLayout(panelBoton);
        panelBoton.setLayout(panelBotonLayout);
        panelBotonLayout.setHorizontalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
        );
        panelBotonLayout.setVerticalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIniciar, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        panelFondo.add(panelBoton);
        panelBoton.setBounds(130, 250, 160, 50);

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 1, 19)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(230, 251, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Elige el modo de juego");
        panelFondo.add(jLabel3);
        jLabel3.setBounds(0, 80, 430, 25);

        jCheckBoxJM.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jCheckBoxJM.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxJM.setText("Jugador vs Maquina");
        jCheckBoxJM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxJMActionPerformed(evt);
            }
        });
        panelFondo.add(jCheckBoxJM);
        jCheckBoxJM.setBounds(100, 160, 220, 27);

        jCheckBoxJJ.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jCheckBoxJJ.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxJJ.setText("Jugador vs Jugador");
        jCheckBoxJJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxJJActionPerformed(evt);
            }
        });
        panelFondo.add(jCheckBoxJJ);
        jCheckBoxJJ.setBounds(100, 120, 230, 27);

        jCheckBoxMM.setFont(new java.awt.Font("Showcard Gothic", 1, 18)); // NOI18N
        jCheckBoxMM.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxMM.setText("Maquina vs Maquina");
        jCheckBoxMM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMMActionPerformed(evt);
            }
        });
        panelFondo.add(jCheckBoxMM);
        jCheckBoxMM.setBounds(100, 200, 220, 27);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelFondo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Method to set the selected game mode and uncheck other checkboxes
    private void setSelectedGameMode(String mode, JCheckBox selectedCheckBox) {
        selectedGameMode = mode;

        // Uncheck all other checkboxes except the selected one
        jCheckBoxJJ.setSelected(jCheckBoxJJ == selectedCheckBox);
        jCheckBoxJM.setSelected(jCheckBoxJM == selectedCheckBox);
        jCheckBoxMM.setSelected(jCheckBoxMM == selectedCheckBox);

    }

    public static String getSelectedGameMode() {
        return selectedGameMode;
    }

    private void lblCierreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblCierreMouseClicked

    private void lblCierreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseEntered
        lblCierre.setForeground(Color.red);
    }//GEN-LAST:event_lblCierreMouseEntered

    private void lblCierreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCierreMouseExited
        lblCierre.setForeground(new Color(240, 192, 255));
    }//GEN-LAST:event_lblCierreMouseExited

    private void panelFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelFondoMousePressed
        panelFondo.requestFocus();
    }//GEN-LAST:event_panelFondoMousePressed

    private void lblIniciarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIniciarMousePressed

        this.dispose();
        
        if(selectedGameMode.equals("Humano vs Humano")){
            FormInicio formInicio = new FormInicio(selectedGameMode);
            formInicio.setVisible(true);
        } else if(selectedGameMode.equals("Jugador vs Maquina")){
            FormInicio formInicio = new FormInicio(selectedGameMode);
            formInicio.setVisible(true);
        } else if(selectedGameMode.equals("Maquina vs Maquina")){
            Jugador jugador1 = new Jugador(TipoImagen.EQUIS);
            jugador1.setNombre("Jugador1");
            Jugador jugador2 = new Jugador(TipoImagen.CIRCULO);
            jugador2.setNombre("Jugador2");
            FormTicTacToe gatito = new FormTicTacToe(jugador1, jugador2, selectedGameMode);
            gatito.setVisible(true);
        }

    }//GEN-LAST:event_lblIniciarMousePressed

    private void lblIniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIniciarMouseExited
        panelBoton.setBackground(new Color(42, 22, 79));
    }//GEN-LAST:event_lblIniciarMouseExited

    private void lblIniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIniciarMouseEntered
        panelBoton.setBackground(new Color(126, 49, 96));
    }//GEN-LAST:event_lblIniciarMouseEntered

    private void jCheckBoxJJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxJJActionPerformed
        selectedGameMode = "Humano vs Humano";
        jCheckBoxJM.setSelected(false);
        jCheckBoxMM.setSelected(false);
    }//GEN-LAST:event_jCheckBoxJJActionPerformed

    private void jCheckBoxJMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxJMActionPerformed
        selectedGameMode = "Jugador vs Maquina";
        jCheckBoxJJ.setSelected(false);
        jCheckBoxMM.setSelected(false);
    }//GEN-LAST:event_jCheckBoxJMActionPerformed

    private void jCheckBoxMMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMMActionPerformed
        selectedGameMode = "Maquina vs Maquina";
        jCheckBoxJJ.setSelected(false);
        jCheckBoxJM.setSelected(false);
    }//GEN-LAST:event_jCheckBoxMMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxJJ;
    private javax.swing.JCheckBox jCheckBoxJM;
    private javax.swing.JCheckBox jCheckBoxMM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblCierre;
    private javax.swing.JLabel lblIniciar;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPanel panelFondo;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IU;

import LOGICA.CalificarExamen;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Cristian
 */
public class PanelResultados extends javax.swing.JPanel {
    CalificarExamen u = new CalificarExamen();
    /**
     * Creates new form PanelLogo
     */
    public PanelResultados() {
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMA = new javax.swing.JLabel();
        resultadoMatematicas = new javax.swing.JButton();
        lblCI = new javax.swing.JLabel();
        resultadoCiencias = new javax.swing.JButton();
        lblSO = new javax.swing.JLabel();
        resultadoSociales = new javax.swing.JButton();
        lblLC = new javax.swing.JLabel();
        resultadoTextual = new javax.swing.JButton();
        lblIM = new javax.swing.JLabel();
        resultadoImagen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        resultadoGlobal = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setForeground(new java.awt.Color(255, 255, 255));

        lblMA.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        lblMA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMA.setText("MATEMATICAS:");
        lblMA.setToolTipText("");
        lblMA.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultadoMatematicas.setBackground(new java.awt.Color(92, 184, 92));
        resultadoMatematicas.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        resultadoMatematicas.setForeground(new java.awt.Color(255, 255, 255));
        resultadoMatematicas.setText("0");
        resultadoMatematicas.setBorderPainted(false);
        resultadoMatematicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoMatematicasActionPerformed(evt);
            }
        });

        lblCI.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        lblCI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCI.setText("CIENCIAS:");
        lblCI.setToolTipText("");
        lblCI.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultadoCiencias.setBackground(new java.awt.Color(92, 184, 92));
        resultadoCiencias.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        resultadoCiencias.setForeground(new java.awt.Color(255, 255, 255));
        resultadoCiencias.setText("0");
        resultadoCiencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoCienciasActionPerformed(evt);
            }
        });

        lblSO.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        lblSO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSO.setText("SOCIALES:");
        lblSO.setToolTipText("");
        lblSO.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultadoSociales.setBackground(new java.awt.Color(92, 184, 92));
        resultadoSociales.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        resultadoSociales.setForeground(new java.awt.Color(255, 255, 255));
        resultadoSociales.setText("0");
        resultadoSociales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoSocialesActionPerformed(evt);
            }
        });

        lblLC.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        lblLC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLC.setText("ANALISIS TEXTUAL:");
        lblLC.setToolTipText("");
        lblLC.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultadoTextual.setBackground(new java.awt.Color(92, 184, 92));
        resultadoTextual.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        resultadoTextual.setForeground(new java.awt.Color(255, 255, 255));
        resultadoTextual.setText("0");
        resultadoTextual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoTextualActionPerformed(evt);
            }
        });

        lblIM.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        lblIM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIM.setText("ANALISIS DE IMAGEN:");
        lblIM.setToolTipText("");
        lblIM.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultadoImagen.setBackground(new java.awt.Color(92, 184, 92));
        resultadoImagen.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        resultadoImagen.setForeground(new java.awt.Color(255, 255, 255));
        resultadoImagen.setText("0");
        resultadoImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoImagenActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTAJE TOTAL ESTANDARIZADO:");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        resultadoGlobal.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        resultadoGlobal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        resultadoGlobal.setText("0");
        resultadoGlobal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        resultadoGlobal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        resultadoGlobal.setEnabled(false);
        resultadoGlobal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultadoGlobalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultadoGlobal)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                    .addComponent(lblMA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultadoMatematicas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultadoCiencias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultadoSociales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblLC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultadoTextual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resultadoImagen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resultadoGlobal, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMA)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resultadoMatematicas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblCI)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resultadoCiencias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSO)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resultadoSociales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblLC)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resultadoTextual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIM)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(resultadoImagen)
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void resultadoGlobalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoGlobalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resultadoGlobalActionPerformed

    private void resultadoMatematicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoMatematicasActionPerformed
        String str=u.getCorMA()+"/";
        JOptionPane.showMessageDialog(null, str);
    }//GEN-LAST:event_resultadoMatematicasActionPerformed

    private void resultadoCienciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoCienciasActionPerformed
        String str=u.toString("CI");
        JOptionPane.showMessageDialog(null, str);
    }//GEN-LAST:event_resultadoCienciasActionPerformed

    private void resultadoSocialesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoSocialesActionPerformed
        String str=u.toString("SO");
        JOptionPane.showMessageDialog(null, str);
    }//GEN-LAST:event_resultadoSocialesActionPerformed

    private void resultadoTextualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoTextualActionPerformed
        String str=u.toString("LC");
        JOptionPane.showMessageDialog(null, str);
    }//GEN-LAST:event_resultadoTextualActionPerformed

    private void resultadoImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultadoImagenActionPerformed
        String str=u.toString("IM");
        JOptionPane.showMessageDialog(null, str);
    }//GEN-LAST:event_resultadoImagenActionPerformed

    public void setResultados(double Global,double Textual,double Matematicas, double Ciencias,double Sociales,double Imagen){
        resultadoMatematicas.setText(String.format("%.4f",Matematicas));
        resultadoTextual.setText(String.format("%.4f",Textual));
        resultadoSociales.setText(String.format("%.4f",Sociales));
        resultadoCiencias.setText(String.format("%.4f",Ciencias));
        resultadoImagen.setText(String.format("%.4f",Imagen));
        resultadoGlobal.setText(String.format("%.5f",Global));  
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblCI;
    private javax.swing.JLabel lblIM;
    private javax.swing.JLabel lblLC;
    private javax.swing.JLabel lblMA;
    private javax.swing.JLabel lblSO;
    private javax.swing.JButton resultadoCiencias;
    private javax.swing.JTextField resultadoGlobal;
    private javax.swing.JButton resultadoImagen;
    private javax.swing.JButton resultadoMatematicas;
    private javax.swing.JButton resultadoSociales;
    private javax.swing.JButton resultadoTextual;
    // End of variables declaration//GEN-END:variables

    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }

    public JLabel getLblCI() {
        return lblCI;
    }

    public void setLblCI(JLabel lblCI) {
        this.lblCI = lblCI;
    }

    public JLabel getLblIM() {
        return lblIM;
    }

    public void setLblIM(JLabel lblIM) {
        this.lblIM = lblIM;
    }

    public JLabel getLblLC() {
        return lblLC;
    }

    public void setLblLC(JLabel lblLC) {
        this.lblLC = lblLC;
    }

    public JLabel getLblMA() {
        return lblMA;
    }

    public void setLblMA(JLabel lblMA) {
        this.lblMA = lblMA;
    }

    public JLabel getLblSO() {
        return lblSO;
    }

    public void setLblSO(JLabel lblSO) {
        this.lblSO = lblSO;
    }

    public JButton getResultadoCiencias() {
        return resultadoCiencias;
    }

    public void setResultadoCiencias(JButton resultadoCiencias) {
        this.resultadoCiencias = resultadoCiencias;
    }

    public JTextField getResultadoGlobal() {
        return resultadoGlobal;
    }

    public void setResultadoGlobal(JTextField resultadoGlobal) {
        this.resultadoGlobal = resultadoGlobal;
    }

    public JButton getResultadoImagen() {
        return resultadoImagen;
    }

    public void setResultadoImagen(JButton resultadoImagen) {
        this.resultadoImagen = resultadoImagen;
    }

    public JButton getResultadoMatematicas() {
        return resultadoMatematicas;
    }

    public void setResultadoMatematicas(JButton resultadoMatematicas) {
        this.resultadoMatematicas = resultadoMatematicas;
    }

    public JButton getResultadoSociales() {
        return resultadoSociales;
    }

    public void setResultadoSociales(JButton resultadoSociales) {
        this.resultadoSociales = resultadoSociales;
    }

    public JButton getResultadoTextual() {
        return resultadoTextual;
    }

    public void setResultadoTextual(JButton resultadoTextual) {
        this.resultadoTextual = resultadoTextual;
    }
}

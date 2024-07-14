package com.raven.form;

import com.raven.component.Form;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
public class Home_Form extends Form implements Runnable{
String hora,minutos, segundos;
Thread hilo;
    public Home_Form() {
        initComponents();
//        lblFecha.setText(fecha());
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);
    }
    
    public void hora(){
        Calendar calendario=new GregorianCalendar();
        Date horaactual = new Date();
        calendario.setTime(horaactual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) >9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
        minutos=calendario.get(Calendar.MINUTE) >9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
        segundos=calendario.get(Calendar.SECOND ) >9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);
    }
    public void run(){
        for (int i = 1; i > 0; i++) {
            if (i>0) {
                hora();
                lblHora.setText(hora+":"+minutos+":"+segundos);
            }
        }
    }
    public static String fecha (){
        Date fecha = new Date();
        SimpleDateFormat formatofecha=new SimpleDateFormat("dd/MM/yyyy");
        return formatofecha.format(fecha);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNumeroVentasHoy = new javax.swing.JTextField();
        txtMembresia = new javax.swing.JTextField();
        txtClientesRegistrados = new javax.swing.JTextField();
        txtNumerosAsistenciaHoy = new javax.swing.JTextField();

        lblHora.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 204, 0));
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHora.setText("00:00:00");
        lblHora.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Panel de control");

        txtNumeroVentasHoy.setEditable(false);
        txtNumeroVentasHoy.setEnabled(false);

        txtMembresia.setEditable(false);
        txtMembresia.setEnabled(false);
        txtMembresia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMembresiaActionPerformed(evt);
            }
        });

        txtClientesRegistrados.setEditable(false);
        txtClientesRegistrados.setEnabled(false);

        txtNumerosAsistenciaHoy.setEditable(false);
        txtNumerosAsistenciaHoy.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblHora)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtMembresia, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(73, 73, 73)
                        .addComponent(txtClientesRegistrados, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(59, 59, 59)
                        .addComponent(txtNumeroVentasHoy, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(94, 94, 94)
                        .addComponent(txtNumerosAsistenciaHoy, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(102, 102, 102))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroVentasHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtClientesRegistrados, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMembresia, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumerosAsistenciaHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 458, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMembresiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMembresiaActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtMembresiaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblHora;
    private javax.swing.JTextField txtClientesRegistrados;
    private javax.swing.JTextField txtMembresia;
    private javax.swing.JTextField txtNumeroVentasHoy;
    private javax.swing.JTextField txtNumerosAsistenciaHoy;
    // End of variables declaration//GEN-END:variables
}

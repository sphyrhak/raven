/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.raven.main;

import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author SHIRLEY
 */
public class Login1 extends javax.swing.JFrame {

    /**
     * Creates new form Login1
     */
    public Login1() {
        initComponents();
        
        cerrar();//llamando al metodo 
        this.setLocationRelativeTo(this);
        this.setLocationRelativeTo(null);//para que la interfaz salga en el medio
        this.txtOcultar.setVisible(false);
        //imagenes
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel4,"src/com/raven/icon/fondo6.jpg" );
        
        rsscalelabel.RSScaleLabel.setScaleLabel(jLabel1, "src/com/raven/icon/icon.png");
        
        /* imagenes
        SetImageLabel(jLabel4, "src/Imagenes/gym.jpg");
        SetImageLabel(jLabel7,"src/Imagenes/ojo.png");
        SetImageLabel(jLabel8,"src/Imagenes/invisible.png");       
        SetImageLabel(jLabel1, "src/Imagenes/icon.png");  */  // Especifica la ruta de la imagen correctamente
    }
    
    /**
     * Método para establecer una imagen en un JLabel
     */
   /* private void SetImageLabel(JLabel labelName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }*/


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Left = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pwdContraseña = new javax.swing.JPasswordField();
        Mostrar = new javax.swing.JCheckBox();
        txtVer = new javax.swing.JLabel();
        txtOcultar = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));

        Left.setBackground(new java.awt.Color(102, 102, 102));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));
        Left.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Sitka Display", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 51));
        jLabel2.setText("Usuario:");
        Left.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 190, 88, -1));

        jLabel3.setFont(new java.awt.Font("Sitka Display", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 51));
        jLabel3.setText("Contraseña:");
        Left.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 289, -1, -1));

        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        Left.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 234, 267, -1));

        btnIniciar.setBackground(new java.awt.Color(51, 51, 51));
        btnIniciar.setFont(new java.awt.Font("Sitka Display", 0, 20)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(255, 153, 51));
        btnIniciar.setText("Iniciar Sesion ");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        Left.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 420, -1, 49));
        Left.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 17, 166, 155));
        Left.add(pwdContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 321, 267, -1));

        Mostrar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Mostrar.setForeground(new java.awt.Color(255, 153, 51));
        Mostrar.setText("Mostrar contraseña");
        Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarActionPerformed(evt);
            }
        });
        Left.add(Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 371, -1, -1));

        txtVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ver_32px.png"))); // NOI18N
        txtVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtVerMouseClicked(evt);
            }
        });
        Left.add(txtVer, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 30, 30));

        txtOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/ocultar_32px.png"))); // NOI18N
        txtOcultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOcultarMouseClicked(evt);
            }
        });
        Left.add(txtOcultar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 40, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Left, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Left, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        jPanel1.getAccessibleContext().setAccessibleName("LOGIN");
        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // Codigo para boton iniciar 
        String usurio = "admin";
        String contraseña = "123";
        
        String Pwd = new String (pwdContraseña.getPassword());
        
        if (txtUsuario.getText().equalsIgnoreCase(usurio) && Pwd.equalsIgnoreCase(contraseña)) {
            
            
            
        }else{
            JOptionPane.showMessageDialog(null, "Usuario o contraseña es incorrecto");
        }
        
        
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarActionPerformed
        // TODO add your handling code here:
        if (Mostrar.isSelected()) {
            pwdContraseña.setEchoChar((char)0);
        }else{
            pwdContraseña.setEchoChar('*');
        }
    }//GEN-LAST:event_MostrarActionPerformed

    private void txtVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtVerMouseClicked
        txtVer.setVisible(false);
        txtOcultar.setVisible(true);
        pwdContraseña.setEchoChar((char)0);
        
    }//GEN-LAST:event_txtVerMouseClicked

    private void txtOcultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOcultarMouseClicked
     txtVer.setVisible(true);
        txtOcultar.setVisible(false);
        pwdContraseña.setEchoChar('●');
        
    }//GEN-LAST:event_txtOcultarMouseClicked

    /**
     * @param args the command line arguments
     */
    
    //Metodo para confirmar el cierre de jframe
    public void cerrar(){
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
                confirmarSalida(); 
             }
            });
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //confirmar salida
    public void confirmarSalida(){
       int valor = JOptionPane.showConfirmDialog(this, "¿Estas seguro de cerrar la aplicacion?","Advertencia",JOptionPane.YES_NO_OPTION);
        if (valor==JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Gracias por su visita, hasta pronto","Gracias",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            
        }
    }
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login1().setVisible(true);
            }
        
            
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JCheckBox Mostrar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField pwdContraseña;
    private javax.swing.JLabel txtOcultar;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JLabel txtVer;
    // End of variables declaration//GEN-END:variables
}

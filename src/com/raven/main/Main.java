package com.raven.main;

import com.raven.event.EventColorChange;
import com.raven.form.Home_Form;
import com.raven.form.Client_Form;
import com.raven.form.Product_Form;
import com.raven.form.Sale_Form;
import com.raven.form.Sale_Form2;
import com.raven.form.Setting_Form;
import com.raven.menu.EventMenu;
import com.raven.properties.SystemProperties;
import com.raven.querys.Permissions;
import com.raven.theme.SystemTheme;
import com.raven.theme.ThemeColor;
import com.raven.theme.ThemeColorChange;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    private Setting_Form settingForm;

    public Main() {
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        init();
    }

    private void init() {
        header.initMoving(this);
        header.initEvent(this, panelBackground1);
        menu.addEventMenu(new EventMenu() {
            @Override
            public void selectedMenu(int index) {
                if (index == 0) {
                    if(Permissions.getInstance().accessPanel("Inicio")){
                        mainBody.displayForm(new Home_Form());
                       
                    } 
                    
                }else if (index==1) {
                    if(Permissions.getInstance().accessPanel("Cliente")){
                        mainBody.displayForm(new Client_Form());
                    }
                }else if (index==2) {
                    if(Permissions.getInstance().accessPanel("Venta")){
                        mainBody.displayForm(new Sale_Form());
                    }
                }else if (index==3) {
                    if(Permissions.getInstance().accessPanel("Facturacion")){
                        mainBody.displayForm(new Sale_Form2());
                    }
                }else if (index==4) {
                    if(Permissions.getInstance().accessPanel("Producto")){
                        mainBody.displayForm(new Product_Form());
                    }
                }  else if (index == 5) {
                    if(Permissions.getInstance().accessPanel("Configuración")){
                        mainBody.displayForm(settingForm, "Configuracion");
                    }
                } 
            }
        });
        ThemeColorChange.getInstance().addThemes(new ThemeColor(new Color(34, 34, 34), Color.WHITE) {
            @Override
            public void onColorChange(Color color) {
                panelBackground1.setBackground(color);
            }
        });
        ThemeColorChange.getInstance().addThemes(new ThemeColor(Color.WHITE, new Color(80, 80, 80)) {
            @Override
            public void onColorChange(Color color) {
                mainBody.changeColor(color);
            }
        });
        ThemeColorChange.getInstance().initBackground(panelBackground1);
        SystemProperties pro = new SystemProperties();
        pro.loadFromFile();
        if (!pro.isDarkMode()) {
            ThemeColorChange.getInstance().setMode(ThemeColorChange.Mode.LIGHT);
            panelBackground1.setBackground(Color.WHITE);
            mainBody.changeColor(new Color(80, 80, 80));
        }
        if (pro.getBackgroundImage() != null) {
            ThemeColorChange.getInstance().changeBackgroundImage(pro.getBackgroundImage());
        }
        SystemTheme.mainColor = pro.getColor();
        settingForm = new Setting_Form();
        settingForm.setEventColorChange(new EventColorChange() {
            @Override
            public void colorChange(Color color) {
                SystemTheme.mainColor = color;
                ThemeColorChange.getInstance().ruenEventColorChange(color);
                repaint();
                pro.save("theme_color", color.getRGB() + "");
            }
        });
        settingForm.setSelectedThemeColor(pro.getColor());
        settingForm.setDarkMode(pro.isDarkMode());
        settingForm.initBackgroundImage(pro.getBackgroundImage());
        //mainBody.displayForm(new Home_Form());
        //método agrega un ActionListener al botón btn_Logout
       btn_Logout.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas cerrar sesión?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                new Login1().setVisible(true);
                dispose();
            }
        });
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground1 = new com.raven.swing.PanelBackground();
        header = new com.raven.component.Header();
        menu = new com.raven.menu.Menu();
        mainBody = new com.raven.component.MainBody();
        btn_Logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBackground1.setBackground(new java.awt.Color(34, 34, 34));

        btn_Logout.setBackground(new java.awt.Color(255, 0, 0));
        btn_Logout.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btn_Logout.setForeground(new java.awt.Color(255, 255, 255));
        btn_Logout.setText("Cerrar Sesion");

        javax.swing.GroupLayout panelBackground1Layout = new javax.swing.GroupLayout(panelBackground1);
        panelBackground1.setLayout(panelBackground1Layout);
        panelBackground1Layout.setHorizontalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btn_Logout)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBackground1Layout.setVerticalGroup(
            panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackground1Layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBackground1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addComponent(mainBody, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelBackground1Layout.createSequentialGroup()
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Logout)
                        .addGap(29, 29, 29))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Logout;
    private com.raven.component.Header header;
    private com.raven.component.MainBody mainBody;
    private com.raven.menu.Menu menu;
    private com.raven.swing.PanelBackground panelBackground1;
    // End of variables declaration//GEN-END:variables
}

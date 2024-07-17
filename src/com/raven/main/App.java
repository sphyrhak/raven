
package com.raven.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import com.raven.querys.Permissions;


public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // esto es para crear instancia de la clase login
        SwingUtilities.invokeLater(() -> {
            Login1 login = new Login1(() -> {
                startMainApp();
            });
            login.setVisible(true);
        });
    }
    private static void startMainApp(){
        // Crear y mostrar el frame principal        
        SwingUtilities.invokeLater(() -> {
            Main mainFrame = new Main();
            mainFrame.setVisible(true);
        });
    }
    
    public static void onAuthenticationSuccess(String idUser, int id_permisos, String nomnbre, String cd_usuario, int dni, String telefono) {
        // aqui se cargaran los datos del usuario
        Permissions user = Permissions.getInstance(idUser, id_permisos, nomnbre, cd_usuario, dni, telefono);

        // luego se carga el main
        startMainApp();
    }
    
}

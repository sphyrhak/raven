
package com.raven.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;



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
    
    public static void onAuthenticationSuccess() {
        // aqui se cargaran los datos del usuario
        
        // luego se carga el main
        startMainApp();
    }
    
}

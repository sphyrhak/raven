
package com.raven.main;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author JENS07
 */
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

        // Crear instancia de la clase principal y ejecutar la aplicación
        SwingUtilities.invokeLater(() -> {
            App app = new App();
            app.start();
        });
    }
    private void start(){
        // Crear y mostrar el frame principal
        Main mainFrame = new Main();
        mainFrame.setVisible(true);

        // Iniciar conexión a la base de datos en segundo plano
        //new DatabaseConnectionTask(mainFrame).execute();
    }
    
}

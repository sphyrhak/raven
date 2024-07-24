package com.raven.form;

import com.raven.component.Form;
import com.raven.connection.ConnectionDB;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Home_Form extends Form implements Runnable{
String hora,minutos, segundos;
Thread hilo;
DefaultTableModel modelo;
ConnectionDB con1 = new ConnectionDB();
    public Home_Form() {
        initComponents();
//        lblFecha.setText(fecha());
        hilo = new Thread(this);
        hilo.start();
        setVisible(true);
        mostrarUsuarios();
        
    }
    
    
    //-------------------------------
    //CODIGO PARA MOSTRAR LAS HORAS
    //-------------------------------
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
    //-------------------------------
    //-------------------------------  
    
    
    
    
    //-------------------------------    
    //METODO PARA TRAER LA TABLA USUARIOS DE LA BASE DE DATOS A LA TABLA JPANEL
    //-------------------------------
    
    void mostrarUsuarios() {
    // Títulos de las columnas según la tabla tbusuarios
    String[] titulos = {"Id_Usuario", "Contraseña", "Id_Permisos", "Nombre", "Cd_Usuario", "DNI", "Teléfono", "intentos", "ultima_Vez"};
    modelo = new DefaultTableModel(null, titulos); // Crea un modelo de tabla con los títulos de las columnas
    jTable1.setModel(modelo); // Establece el modelo a la tabla

    String sql = "SELECT * FROM tbusuario"; // Consulta SQL para obtener todos los registros

    try (Connection conet = con1.conectar(); // Establece la conexión a la base de datos
         Statement st = conet.createStatement(); // Crea una declaración para ejecutar la consulta
         ResultSet rs = st.executeQuery(sql)) { // Ejecuta la consulta y obtiene el ResultSet

        while (rs.next()) {
            // Crea un array de objetos para almacenar los datos de cada fila
            Object[] fila = new Object[9];
            fila[0] = rs.getInt("Id_Usuario");
            fila[1] = rs.getString("Contraseña");
            fila[2] = rs.getInt("Id_Permisos");
            fila[3] = rs.getString("Nombre");
            fila[4] = rs.getString("Cd_Usuario");
            fila[5] = rs.getString("DNI");
            fila[6] = rs.getString("Teléfono");
            fila[7] = rs.getInt("intentos");
            fila[8] = rs.getTimestamp("ultima_Vez"); // Asegúrate de que el tipo de datos sea correcto para este campo
            modelo.addRow(fila); // Agrega la fila al modelo de la tabla
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e.getMessage()); // Muestra un mensaje de error si ocurre una excepción
        System.out.println("Error al mostrar los datos: " + e.getMessage()); // Imprime el error en la consola
    }
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHora = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        lblHora.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lblHora.setForeground(new java.awt.Color(255, 204, 0));
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHora.setText("00:00:00");
        lblHora.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Visualización de Usuarios");

        jTable1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id_Usuario", "Contraseña", "Id_Permisos", "Nombre", "Cd_Usuario", "DNI", "Teléfono", "intentos", "ultima_vez"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1= new javax.swing.JTable(){
            public  boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jTable1.setRowHeight(25);
        jTable1.getTableHeader().setResizingAllowed(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
            jTable1.getColumnModel().getColumn(6).setResizable(false);
            jTable1.getColumnModel().getColumn(7).setResizable(false);
            jTable1.getColumnModel().getColumn(8).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(152, 954, Short.MAX_VALUE)
                .addComponent(lblHora)
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addGap(175, 175, 175))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    private javax.swing.JLabel lblHora;
    // End of variables declaration//GEN-END:variables
}

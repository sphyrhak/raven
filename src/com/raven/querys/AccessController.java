
package com.raven.querys;

import com.raven.connection.ConnectionDB;
import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;



public class AccessController {
    public String encriptar(String cadena){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(cadena.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
    }
    public User loginUser(String usuario,String contraseña) {
        User user = null;
        String contraEncriptada = encriptar(contraseña);
        Connection cn= ConnectionDB.conectar();
        String sql = "SELECT Id_Usuario, Id_Permisos, Nombre, Cd_Usuario, DNI, Teléfono FROM tbusuario WHERE Cd_Usuario = '"+usuario+"' AND Contraseña = '"+contraEncriptada+"'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()) {
                String id = rs.getString("Id_Usuario");
                int id_permisos = rs.getInt("Id_Permisos");
                String nombre = rs.getString("Nombre");
                String cd_usuario = rs.getString("Cd_Usuario");
                int dni = rs.getInt("DNI");
                String telefono = rs.getString("Teléfono");
                user = new User(id, id_permisos, nombre, cd_usuario, dni, telefono);
            }
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion"+e);
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion");
        }
        return user;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public boolean loginUser(String usuario,String contraseña) {
        boolean respuesta = false;
        String contraEncriptada = encriptar(contraseña);
        Connection cn= ConnectionDB.conectar();
        String sql = "SELECT Cd_Usuario, contraseña from tbusuario where Cd_usuario = '" + usuario + "' and contraseña = '" + contraEncriptada + "'";
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                respuesta = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion"+e);
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion");
        }
        return respuesta;
    }
}

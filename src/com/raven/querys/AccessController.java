
package com.raven.querys;

import com.raven.connection.ConnectionDB;
import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
//agregando 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class AccessController {
    // Método para encriptar la contraseña
    public String encriptar(String cadena) {
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

    // Método para iniciar sesión
    public User loginUser(String usuario, String contraseña) {
        User user = null;
        String contraEncriptada = encriptar(contraseña);
        Connection cn = ConnectionDB.conectar();
        String sql = "SELECT Id_Usuario, Id_Permisos, Nombre, Cd_Usuario, DNI, Teléfono, intentos, ultima_vez FROM tbusuario WHERE Cd_Usuario = '"+usuario+"'";
        Statement st;

        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            if(rs.next()) {
                int intentos = rs.getInt("intentos");
                String ultimaVez = rs.getString("ultima_vez");

                // Convertir ultima_vez a LocalDateTime
                LocalDateTime ultimaVezDateTime = LocalDateTime.parse(ultimaVez, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                // Verificar si el usuario está bloqueado y si se debe desbloquear automáticamente
                if(intentos >= 3) {
                    LocalDateTime ahora = LocalDateTime.now();
                    long minutosDif = ChronoUnit.MINUTES.between(ultimaVezDateTime, ahora);
                    if (minutosDif >= 15) { // 1 hora para desbloquear
                        // Restablecer intentos y permitir nuevo inicio de sesión
                        String sqlResetIntentos = "UPDATE tbusuario SET intentos = 0 WHERE Cd_Usuario = '"+usuario+"'";
                        st.executeUpdate(sqlResetIntentos);
                        intentos = 0;
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario bloqueado. Demasiados intentos fallidos.");
                        return null;
                    }
                }

                if(intentos < 3) {
                    String id = rs.getString("Id_Usuario");
                    int id_permisos = rs.getInt("Id_Permisos");
                    String nombre = rs.getString("Nombre");
                    String cd_usuario = rs.getString("Cd_Usuario");
                    int dni = rs.getInt("DNI");
                    String telefono = rs.getString("Teléfono");

                    String sqlPass = "SELECT Id_Usuario FROM tbusuario WHERE Cd_Usuario = '"+usuario+"' AND Contraseña = '"+contraEncriptada+"'";
                    ResultSet rsPass = st.executeQuery(sqlPass);

                    if(rsPass.next()) {
                        user = new User(id, id_permisos, nombre, cd_usuario, dni, telefono);
                        // Resetear los intentos fallidos y actualizar la última vez de inicio de sesión en un inicio de sesión exitoso
                        String sqlResetIntentos = "UPDATE tbusuario SET intentos = 0, ultima_vez = CURRENT_TIMESTAMP WHERE Cd_Usuario = '"+usuario+"'";
                        st.executeUpdate(sqlResetIntentos);
                    } else {
                        // Incrementar los intentos fallidos y actualizar la última vez de intento de inicio de sesión
                        intentos++;
                        String sqlUpdateIntentos = "UPDATE tbusuario SET intentos = " + intentos + ", ultima_vez = CURRENT_TIMESTAMP WHERE Cd_Usuario = '"+usuario+"'";
                        st.executeUpdate(sqlUpdateIntentos);
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta. Intentos fallidos: " + intentos);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al Iniciar Sesion: " + e);
            JOptionPane.showMessageDialog(null, "Error al Iniciar Sesion");
        }
        return user;
    }
}

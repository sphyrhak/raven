
package com.raven.querys;

import com.raven.connection.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Permissions extends User {
    
    private static Permissions instancia;
    private static ConnectionDB conexion = new ConnectionDB();
    
    private String[] access;
    private String rol;
    
    public Permissions(String access, String rol, String id, int id_permisos, String nombre, String cd_usuario, int dni, String telefono) {
        super(id, id_permisos, nombre, cd_usuario, dni, telefono);
        this.access = access.split(",");
        this.rol = rol;
    }
    
    public static Permissions getInstance(String id, int id_permisos, String nombre, String cd_usuario, int dni, String telefono) {
        if(instancia == null){
            Connection CN = null;
            PreparedStatement PS = null;
            ResultSet RS = null;
            try {
                CN = conexion.conectar();
                if (CN == null) {
                    throw new SQLException("No se pudo establecer la conexión a la base de datos.");
                }
                String SQL = "SELECT Rol, Acceso FROM permisos WHERE Id_Permisos = ?";
                PS = CN.prepareStatement(SQL);
                PS.setInt(1, id_permisos);
                RS = PS.executeQuery();
                if(RS.next()) {
                    String rol = RS.getString("Rol");
                    String access = RS.getString("Acceso");
                    instancia = new Permissions(access, rol, id, id_permisos, nombre, cd_usuario, dni, telefono);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (RS != null) RS.close();
                    if (PS != null) PS.close();
                    if (CN != null) CN.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return instancia;
    }
    
    public static Permissions getInstance() {
        if(instancia != null) {
              System.out.println("error");
        }
        return instancia;
    }
    
    public String[] getAccess() {
        return access;
    }

    public void setAccess(String[] access) {
        this.access = access;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

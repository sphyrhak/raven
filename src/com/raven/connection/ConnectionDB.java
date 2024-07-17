
package com.raven.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
    public static String url = "jdbc:mysql://localhost/gym-teamgorila?useSSL=false";
    public static String usuario = "root";
    public static String clave = "";
    public static String clase = "com.mysql.cj.jdbc.Driver";
    //conexion local
    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexion Establecida");
            return cn;
        } catch (SQLException e) {
            System.out.println("Error en la conexion local " + e);
        }
        return null;
    }
}

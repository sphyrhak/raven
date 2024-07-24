/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.querys;

import com.raven.connection.ConnectionDB;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JENS07
 */
public class Sales {
    
    private Connection cn= ConnectionDB.conectar();
    public static ConnectionDB conexion = new ConnectionDB();
    public static PreparedStatement GP;

    //INSERTAR DATOS EN LA TABLA DE LA BASE DE DATOS
    //********************************************************************************************
    public int GuardarVentas( String pFecha, String pSubTotal, String pIgv, String pTotal, int pIdCliente) {//descripcion falta
        int resultado = 0;
        Connection conexion = null;

        String sentencia_guardar = "INSERT INTO ventas (Fecha, SubTotal, IGV, Total, Id_Cliente) VALUES (?,?,?,?,?)";

        try {
            conexion = ConnectionDB.conectar();
            GP = conexion.prepareStatement(sentencia_guardar);
            
            GP.setString(1, pFecha);
            GP.setString(2, pSubTotal);
            GP.setString(3, pIgv);
            GP.setString(4, pTotal);
            GP.setInt(5, pIdCliente);
            //GP.setString(6, Descripcion);
            resultado = GP.executeUpdate();
            if (resultado > 0) {
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }
    
    public int GuardarDetalleVentas(String Id_Venta, String Id_Producto, String CantidadProd, String Precio, String TipoProducto) {
        int resultado = 0;
        Connection conexion = null;

        String sentencia_guardar = "INSERT INTO detallesventas (Id_Ventas, Id_Producto, CantidadProductos, Precio, Tipo) VALUES (?,?,?,?,?)";

        try {
            conexion = ConnectionDB.conectar();
            GP = conexion.prepareStatement(sentencia_guardar);
            GP.setString(1, Id_Venta);
            GP.setString(2, Id_Producto);
            GP.setString(3, CantidadProd);
            GP.setString(4, Precio);
            GP.setString(5, TipoProducto);
            resultado = GP.executeUpdate();
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Venta realizada con Éxito");
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }
    //************************************************************************************
      //Método para obtener el ID del producto desde la base de datos
    //-------------------------------------------------------------------------------------
    public int obtenerIdProducto(String nombreProducto) {
    int idProducto = -1;
    Connection conexion = null;
    String sql = "SELECT Id_Producto FROM productos WHERE NombreProducto = ?";

   try {
        conexion = ConnectionDB.conectar();
        GP = conexion.prepareStatement(sql);
        GP.setString(1, nombreProducto);
        ResultSet rs = GP.executeQuery();
        if (rs.next()) {
            idProducto = rs.getInt("Id_Producto");
        }
        conexion.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return idProducto;
}

// Método para obtener el ID de la membresía desde la base de datos
public int obtenerIdMembresia(String nombreMembresia) {
    int idMembresia = -1;
    Connection conexion = null;
    String sql = "SELECT id FROM membresía WHERE Plan = ?";

    try {
        conexion = ConnectionDB.conectar();
        GP = conexion.prepareStatement(sql);
        GP.setString(1, nombreMembresia);
        ResultSet rs = GP.executeQuery();
        if (rs.next()) {
            idMembresia = rs.getInt("id");
        }
        conexion.close();
    } catch (Exception e) {
        System.out.println(e);
    }
    return idMembresia;
}
 
}

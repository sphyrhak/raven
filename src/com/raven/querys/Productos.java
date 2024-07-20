/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.querys;

import com.raven.connection.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.raven.form.Product_Form;
import javax.swing.JOptionPane;

/**
 *
 * @author jabar
 */
public class Productos {
    
//-----------------------------------------------------------------
    //METODO PARA ELIMINAR UN PRODUCTO
//-----------------------------------------------------------------
    public boolean eliminarFila(int idProducto){
        boolean respuesta = false;
        String SQL = "DELETE FROM productos WHERE Id_Producto = " + idProducto;
        Connection cn = ConnectionDB.conectar();

        try{
            PreparedStatement consulta = cn.prepareStatement(SQL);
            consulta.executeUpdate();

            if (consulta.executeUpdate()>0) {
                respuesta = true;
            }
            cn.close();
        }catch(SQLException e){
            System.out.println("ERROR AL ELIMINAR LA FILa: "+e);
        }
        return respuesta;
    }
    
//-----------------------------------------------------------------
    //METODO PARA MOSTRAR LA TABLA DE BD AL JPANEL
//-----------------------------------------------------------------    

//    void mostrarTabla(){
//        ConnectionDB con1 = new ConnectionDB();
//        Connection conet;
//        DefaultTableModel modelo;
//        Statement st;
//        ResultSet rs;
//        int idc;
//        String SQL = "SELECT * FROM producto";
//        try{
//            conet = con1.conectar();
//        }
//    }
    
}

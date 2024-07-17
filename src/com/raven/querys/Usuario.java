/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.querys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.raven.connection.ConnectionDB;


public class Usuario {
    private static Usuario instancia;
    private static ConnectionDB conexion = new ConnectionDB();
    private static PreparedStatement GP;
    
    Connection CN = conexion.conectar();
    
    
    private String[] access;
    private String rol;
    private String id;
    private String nombre;
    private String cd_usuario;
    private int dni;
    private int telefono;

    public Usuario(String access, String rol, String id, String nombre, String cd_usuario, int dni, int telefono) {
        this.access = access.split(",");
        this.rol = rol;
        this.id = id;
        this.nombre = nombre;
        this.cd_usuario = cd_usuario;
        this.dni = dni;
        this.telefono = telefono;
    }
    
    public static Usuario getInstance() {
        if(instancia == null){
            // traer datos reales de la DB
            
            instancia = new Usuario("panel1,panel2,panel3","default","iod","carlos","codigo",12345678,123456789);
        }
        return instancia;
    }
    
    
}

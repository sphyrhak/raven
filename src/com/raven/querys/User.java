/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.querys;

/**
 *
 * @author jairh
 */
public class User {
    
    private String id;
    private int id_permisos;
    private String nombre;
    private String cd_usuario;
    private int dni;
    private String telefono; 

    public User(String id, int id_permisos, String nombre, String cd_usuario, int dni, String telefono) {
        this.id = id;
        this.id_permisos = id_permisos;
        this.nombre = nombre;
        this.cd_usuario = cd_usuario;
        this.dni = dni;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getId_permisos() {
        return id_permisos;
    }

    public void setId_permisos(int id_permisos) {
        this.id_permisos = id_permisos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(String cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}

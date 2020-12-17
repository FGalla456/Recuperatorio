package com.example.recuperatorio.Dominio;

import java.util.Date;

public class Usuario {
    int id;
    String nombre;
    int dni;
    Date nacimiento;
    String email;
    int idLocalidad;
    String contrasena;

    public Usuario (String nombreUsuario, Date fechaNacimiento, int DNI,
                    String emailUsuario, int id_localidad ,
                    String contrasenaUsuario){

        this.nombre = nombreUsuario;
        this.nacimiento = fechaNacimiento;
        this.dni = DNI;
        this.email = emailUsuario;
        this.idLocalidad = id_localidad;
        this.contrasena = contrasenaUsuario;
    }

    public Usuario (){};

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public Date getNacimiento() {
        return nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setNacimiento(Date nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}

package com.example.recuperatorio.Dominio;

import java.util.Date;

public class Usuario {
    private String email;
    private String nombre;
    private Integer dni;
    private String nacimiento;
    private Integer idLocalidad;
    private String contrasena;
    public Usuario (String emailUsuario, String fechaNacimiento, int DNI,
                    String nombreUsuario, int id_localidad ,
                    String contrasenaUsuario){

        this.email = emailUsuario;
        this.nombre = nombreUsuario;
        this.nacimiento = fechaNacimiento;
        this.dni = DNI;
        this.idLocalidad = id_localidad;
        this.contrasena = contrasenaUsuario;
    }

    public Usuario (){};


    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getNacimiento() {
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setNacimiento(String nacimiento) {
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

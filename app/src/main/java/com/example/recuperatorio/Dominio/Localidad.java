package com.example.recuperatorio.Dominio;

public class Localidad {

    private Integer id;
    private String nombre;

    public Localidad(Integer idLocalidad , String nane){
        this.id = idLocalidad;
        this.nombre = nane;
    }

    public Localidad(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

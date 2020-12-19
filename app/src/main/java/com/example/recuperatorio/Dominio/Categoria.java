package com.example.recuperatorio.Dominio;

public class Categoria {

    private Integer id;
    private String categoria;

    public Categoria(Integer idCategoria , String nombre){
        this.id = idCategoria;
        this.categoria = nombre;
    }

    public Categoria(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

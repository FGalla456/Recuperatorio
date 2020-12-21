package com.example.recuperatorio.Dominio;

public class Evento {
    private int id;
    private String titulo;
    private String description;
    private String fecha;
    private String hora;
    private int idCategoria;
    private Categoria Cat;

    public Evento(){}

    public Evento(int id, String titulo, String description , String fecha, String hora, int idCat){
        this.id = id;
        this.titulo = titulo;
        this.description = description;
        this.fecha = fecha;
        this.hora = hora;
        this.idCategoria = idCat;
        this.Cat = new Categoria();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria getCat() {
        return Cat;
    }

    public void setCat(Categoria cat) {
        Cat = cat;
    }
}

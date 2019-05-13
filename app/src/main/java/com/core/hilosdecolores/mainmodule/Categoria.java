package com.core.hilosdecolores.mainmodule;

public class Categoria {

    private  String categoria_titulo;
    private String categoria_descripcion;
    private String imagen;
    private  String categoria_id;



    public String getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(String categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getCategoria_titulo() {
        return categoria_titulo;
    }

    public void setCategoria_titulo(String categoria_titulo) {
        this.categoria_titulo = categoria_titulo;
    }

    public String getCategoria_descripcion() {
        return categoria_descripcion;
    }

    public void setCategoria_descripcion(String categoria_descripcion) {
        this.categoria_descripcion = categoria_descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
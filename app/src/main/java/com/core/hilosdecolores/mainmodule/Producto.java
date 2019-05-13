package com.core.hilosdecolores.mainmodule;

public class Producto {

    String producto_id;
           String producto_titulo;
    String producto_descripcion;
           String producto_precio;
    String producto_fecha;
           String producto_stats;
    String product_img;
    String ruta;


    public String getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(String producto_id) {
        this.producto_id = producto_id;
    }

    public String getProducto_titulo() {
        return producto_titulo;
    }

    public void setProducto_titulo(String producto_titulo) {
        this.producto_titulo = producto_titulo;
    }

    public String getProducto_descripcion() {
        return producto_descripcion;
    }

    public void setProducto_descripcion(String producto_descripcion) {
        this.producto_descripcion = producto_descripcion;
    }

    public String getProducto_precio() {
        return producto_precio;
    }

    public void setProducto_precio(String producto_precio) {
        this.producto_precio = producto_precio;
    }

    public String getRuta() {
        return ruta;
    }

    public String getProducto_fecha() {
        return producto_fecha;
    }

    public void setProducto_fecha(String producto_fecha) {
        this.producto_fecha = producto_fecha;
    }

    public String getProducto_stats() {
        return producto_stats;
    }

    public void setProducto_stats(String producto_stats) {
        this.producto_stats = producto_stats;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }
}

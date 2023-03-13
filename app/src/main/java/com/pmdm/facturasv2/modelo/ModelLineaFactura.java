package com.pmdm.facturasv2.modelo;

public class ModelLineaFactura {
    private int codigo;
    private int codigoFactura;
    private int cantidad;
    private String producto;

    private double precio;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(int codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ModelLineaFactura(int codigo, int codigoFactura, int cantidad, String producto, double precio) {
        this.codigo = codigo;
        this.codigoFactura = codigoFactura;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precio = precio;
    }
}

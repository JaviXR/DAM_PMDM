package com.pmdm.facturasv2.modelo;

public class ModelCliente {
    private int codigo;
    private String nombre;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ModelCliente(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}

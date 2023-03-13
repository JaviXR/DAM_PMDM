package com.pmdm.facturasv2.modelo;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmLineaFactura extends RealmObject {
    @PrimaryKey
    private ObjectId _id;
    private RealmFactura realmFactura;
    private int cantidad;
    private String producto;

    private double precio;

    public RealmLineaFactura(){} // RealmObject subclasses must provide an empty constructor

    public RealmLineaFactura(RealmFactura codigoFactura, int cantidad, String producto, double precio) {
        this.realmFactura = codigoFactura;
        this.cantidad = cantidad;
        this.producto = producto;
        this.precio = precio;
    }

    public ObjectId getId() {
        return _id;
    }

    public RealmFactura getCodigoFactura() {
        return realmFactura;
    }

    public void setCodigoFactura(RealmFactura codigoFactura) {
        this.realmFactura = codigoFactura;
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
}

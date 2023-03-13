package com.pmdm.facturasv2.modelo;

import org.bson.types.ObjectId;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

// To add an object to your Realm Schema, extend RealmObject
public class RealmFactura extends RealmObject {

    @PrimaryKey
    private ObjectId _id;
    private double total;
    private RealmCliente realmCliente;
    private Date fecha;

    public RealmFactura(){} // RealmObject subclasses must provide an empty constructor

    public RealmFactura( double total, RealmCliente codigoCliente, Date fecha) {
        this.total = total;
        this.realmCliente = codigoCliente;
        this.fecha = fecha;
    }

    public ObjectId getId() {
        return _id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public RealmCliente getCodigoCliente() {
        return realmCliente;
    }

    public void setCodigoCliente(RealmCliente codigoCliente) {
        this.realmCliente = codigoCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
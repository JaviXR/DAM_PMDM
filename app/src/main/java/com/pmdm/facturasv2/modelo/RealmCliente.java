package com.pmdm.facturasv2.modelo;

import com.pmdm.facturasv2.persistencia.RealmUtil;

import org.bson.types.ObjectId;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

// To add an object to your Realm Schema, extend RealmObject
public class RealmCliente extends RealmObject {

    @PrimaryKey
    private ObjectId _id;
    @Required
    private String nombre;


    public RealmCliente(){} // RealmObject subclasses must provide an empty constructor

    public RealmCliente(String nombre) {
        this.nombre = nombre;
    }

    public ObjectId getId() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
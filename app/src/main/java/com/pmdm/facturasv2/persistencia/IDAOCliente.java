package com.pmdm.facturasv2.persistencia;

import com.pmdm.facturasv2.modelo.ModelCliente;
import com.pmdm.facturasv2.modelo.ModelFactura;
import com.pmdm.facturasv2.modelo.RealmCliente;

import java.util.ArrayList;

public abstract class IDAOCliente  {

    public abstract ArrayList<ModelCliente> getAll();
    public abstract ModelCliente getById(int codigo);

    public static IDAOCliente getInstance()
    {
        if (AppConfig.Modo == "MEMORY")
        {
            return new DAOMemoryCliente();
        }
        if (AppConfig.Modo == "FIREBASE")
        {
            //return new DAOFirebaseCliente();
        }
        if (AppConfig.Modo == "SQLITE")
        {
            //return new DAOSQLiteCliente();
        }
        if (AppConfig.Modo == "MONGODB")
        {
            return new DAOMemoryCliente();
            //return new DAOMongoDBCliente();
        }
        return null;
    }
}

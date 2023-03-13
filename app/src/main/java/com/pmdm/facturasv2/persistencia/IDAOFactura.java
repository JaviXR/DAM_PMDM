package com.pmdm.facturasv2.persistencia;

import com.pmdm.facturasv2.modelo.ModelFactura;

import java.util.ArrayList;

public abstract class IDAOFactura {

    public abstract ArrayList<ModelFactura> getAll();
    public abstract ModelFactura getById(int codigo);

    public abstract ArrayList<ModelFactura> search(String text);

    public static IDAOFactura getInstance()
    {
        if (AppConfig.Modo == "MEMORY")
        {
            return new DAOMemoryFactura();
        }
        if (AppConfig.Modo == "FIREBASE")
        {
            //return new DAOFirebaseFactura();
        }
        if (AppConfig.Modo == "SQLITE")
        {
            //return new DAOSQLiteFactura();
        }
        if (AppConfig.Modo == "MONGODB")
        {
            return new DAOMemoryFactura();
            //return new DAOMongoDBFactura();
        }
        return null;
    }
}

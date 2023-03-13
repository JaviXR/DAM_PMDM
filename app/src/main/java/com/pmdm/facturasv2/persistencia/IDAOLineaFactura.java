package com.pmdm.facturasv2.persistencia;

import java.util.ArrayList;

import com.pmdm.facturasv2.modelo.ModelLineaFactura;

public abstract class IDAOLineaFactura {
    public abstract ArrayList<ModelLineaFactura> getByCodigoFactura(int codigoFactura);

    public abstract double getTotal(int codigoFactura);

    public static IDAOLineaFactura GetInstance()
    {
        if (AppConfig.Modo == "MEMORY")
        {
            return new DAOMemoryLineaFactura();
        }
        if (AppConfig.Modo == "FIREBASE")
        {
            //return new DAOFirebaseLineaFactura();
        }
        if (AppConfig.Modo == "SQLITE")
        {
            //return new DAOSQLiteLineaFactura();
        }
        if (AppConfig.Modo == "MONGODB")
        {
            return new DAOMemoryLineaFactura();
            //return new DAOMongoDBFactura();
        }
        return null;
    }
}

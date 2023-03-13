package com.pmdm.facturasv2.persistencia;

import com.pmdm.facturasv2.modelo.ModelIdioma;
import com.pmdm.facturasv2.modelo.ModelUsuario;

import java.util.ArrayList;

public abstract class IDAOIdioma {

    public abstract ArrayList<ModelIdioma> getAll();

    public static IDAOIdioma GetInstance()
    {
        if (AppConfig.Modo == "MEMORY")
        {
            return new DAOMemoryIdioma();
        }
        if (AppConfig.Modo == "FIREBASE")
        {
            //return new DAOFirebaseIdioma();
        }
        if (AppConfig.Modo == "SQLITE")
        {
            //return new DAOSQLiteIdioma();
        }
        if (AppConfig.Modo == "MONGODB")
        {
            return new DAOMemoryIdioma();
            //return new DAOMongoDBIdioma();
        }
        return null;
    }
}

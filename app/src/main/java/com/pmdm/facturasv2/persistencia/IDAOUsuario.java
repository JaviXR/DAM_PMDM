package com.pmdm.facturasv2.persistencia;

import com.pmdm.facturasv2.modelo.ModelUsuario;

public abstract class IDAOUsuario {
    public abstract ModelUsuario login(String usuario, String password);

    public static IDAOUsuario GetInstance()
    {
        if (AppConfig.Modo == "MEMORY")
        {
            return new DAOMemoryUsuario();
        }
        if (AppConfig.Modo == "FIREBASE")
        {
            //return new DAOFirebaseUsuario();
        }
        if (AppConfig.Modo == "SQLITE")
        {
            //return new DAOSQLiteUsuario();
        }
        if (AppConfig.Modo == "MONGODB")
        {
            return new DAOMemoryUsuario();
            //return new DAOMongoDBUsuario();
        }
        return null;
    }
}

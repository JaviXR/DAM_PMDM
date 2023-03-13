package com.pmdm.facturasv2.persistencia;

import com.pmdm.facturasv2.modelo.ModelIdioma;

import java.util.ArrayList;

public class DAOMemoryIdioma extends IDAOIdioma{


    @Override
    public ArrayList<ModelIdioma> getAll() {
        return DataMemory.getInstance().datosIdiomas;
    }
}

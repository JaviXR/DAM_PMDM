package com.pmdm.facturasv2.persistencia;

import java.util.ArrayList;

import com.pmdm.facturasv2.modelo.ModelFactura;


public class DAOMemoryFactura extends IDAOFactura{


    public ArrayList<ModelFactura> getAll(){
        return DataMemory.getInstance().datosFacturas;
    }

    public ModelFactura getById(int codigo){
        for (ModelFactura c: DataMemory.getInstance().datosFacturas) {
            if (c.getCodigo() == codigo){
                return c;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ModelFactura> search(String text) {

        return null;
    }


}

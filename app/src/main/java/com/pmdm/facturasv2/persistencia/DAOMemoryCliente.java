package com.pmdm.facturasv2.persistencia;

import java.util.ArrayList;

import com.pmdm.facturasv2.modelo.ModelCliente;

public class DAOMemoryCliente extends IDAOCliente{

    public ModelCliente getById(int codigo){
        for (ModelCliente c: DataMemory.getInstance().datosClientes) {
            if (c.getCodigo() == codigo){
                return c;
            }
        }
        return null;
    }

    public ArrayList<ModelCliente> getAll(){
        return DataMemory.getInstance().datosClientes;
    }
}

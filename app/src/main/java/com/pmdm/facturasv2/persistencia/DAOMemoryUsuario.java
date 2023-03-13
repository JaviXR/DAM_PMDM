package com.pmdm.facturasv2.persistencia;

import com.pmdm.facturasv2.modelo.ModelUsuario;

public class DAOMemoryUsuario extends IDAOUsuario{



    public ModelUsuario login(String usuario, String password){
        for (ModelUsuario u: DataMemory.getInstance().datosUsuarios) {
            if (u.getUsuario().equals(usuario) && u.getPassword().equalsIgnoreCase(password)){
                return u;
            }
        }
        return null;
    }
}

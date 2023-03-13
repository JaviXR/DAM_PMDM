package com.pmdm.facturasv2.modelo;

import java.io.Serializable;

public class ModelUsuario implements Serializable {
    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ModelUsuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
}

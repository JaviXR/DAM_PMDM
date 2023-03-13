package com.pmdm.facturasv2.modelo;

import java.util.Date;

public class ModelFactura {
    private int codigo;
    private double total;
    private int codigoCliente;

    private Date fecha;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ModelFactura(int codigo, double total, int codigoCliente, Date fecha) {
        this.codigo = codigo;
        this.total = total;
        this.codigoCliente = codigoCliente;
        this.fecha = fecha;
    }
}

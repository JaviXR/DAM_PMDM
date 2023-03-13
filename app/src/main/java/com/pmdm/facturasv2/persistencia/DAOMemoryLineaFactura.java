package com.pmdm.facturasv2.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.pmdm.facturasv2.modelo.ModelLineaFactura;

public class DAOMemoryLineaFactura extends IDAOLineaFactura {

    public ArrayList<ModelLineaFactura> getByCodigoFactura(int codigoFactura) {
        ArrayList<ModelLineaFactura> ret = new ArrayList<ModelLineaFactura>();
        for (ModelLineaFactura lf : DataMemory.getInstance().datosLineasFacturas) {
            if (lf.getCodigoFactura() == codigoFactura) {
                ret.add(lf);
            }
        }
        return ret;
    }

    @Override
    public double getTotal(int codigoFactura) {
        ArrayList<ModelLineaFactura> list = getByCodigoFactura(codigoFactura);
        int sum = 0;
        for (ModelLineaFactura linea : list) {
            sum += linea.getCantidad() * linea.getPrecio();
        }
//        double sum = list.stream()
//                .mapToDouble(obj -> obj.getCantidad() * obj.getPrecio())
//                .sum();

        return sum;
    }
}

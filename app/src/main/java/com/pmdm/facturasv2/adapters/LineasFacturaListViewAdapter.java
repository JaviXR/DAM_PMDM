package com.pmdm.facturasv2.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.pmdm.facturasv2.DetalleFacturasFragment;
import com.pmdm.facturasv2.R;
import com.pmdm.facturasv2.modelo.ModelCliente;
import com.pmdm.facturasv2.modelo.ModelFactura;
import com.pmdm.facturasv2.modelo.ModelLineaFactura;
import com.pmdm.facturasv2.persistencia.IDAOCliente;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;


//La clase CustomAdapter extiende la clase BaseAdapter.
// Esta clase es una de las dos clases de base principales que se pueden utilizar para crear un adaptador personalizado.
// La otra es ArrayAdapter
public class LineasFacturaListViewAdapter extends BaseAdapter {


    private Context _context;
    private final List<ModelLineaFactura> _items;

    public LineasFacturaListViewAdapter(@NonNull Context context, @NonNull List<ModelLineaFactura> items) {
        //super(context, R.layout.adapter_listview_facturas, items);
        _items = items;
        _context = context;
    }

    //El método getCount devuelve el número de elementos en la lista.
    @Override
    public int getCount() {
        return _items.size();
    }

    //El método getItem devuelve el elemento en la posición especificada.
    @Override
    public Object getItem(int position) {
        ModelLineaFactura lineaFactura = _items.get(position);
        return lineaFactura;
    }

    //El método getItemId devuelve la posición del elemento.
    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //En getView, primero se verifica si convertView es nula.
        //  Si es nula, se inflama el layout personalizado usando un LayoutInflater y se asigna a convertView.
        //  Si no es nula, se usa directamente.
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(_context);
            convertView = inflater.inflate(R.layout.adapter_listview_lineasfacturas, parent, false);
        }
        ModelLineaFactura lineaFactura = _items.get(position);
        //Luego, se obtiene una referencia a un TextView dentro del layout personalizado.
        TextView lblCantidad = convertView.findViewById(R.id.lblCantidad);
        TextView lblProducto = convertView.findViewById(R.id.lblProducto);
        TextView lblPrecio = convertView.findViewById(R.id.lblPrecio);

        //Finalmente, se establece el texto del TextView con el elemento correspondiente de la lista
        lblCantidad.setText(String.valueOf(lineaFactura.getCantidad()));
        lblProducto.setText(String.valueOf(lineaFactura.getProducto()));
        lblPrecio.setText(String.valueOf(lineaFactura.getPrecio()));

        //y se devuelve la View resultante
        return convertView;
    }
}




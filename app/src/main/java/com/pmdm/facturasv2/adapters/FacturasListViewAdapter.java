package com.pmdm.facturasv2.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.pmdm.facturasv2.DetalleFacturasFragment;
import com.pmdm.facturasv2.R;
import com.pmdm.facturasv2.modelo.ModelCliente;
import com.pmdm.facturasv2.modelo.ModelFactura;
import com.pmdm.facturasv2.persistencia.IDAOCliente;
import com.pmdm.facturasv2.persistencia.IDAOLineaFactura;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


//La clase CustomAdapter extiende la clase BaseAdapter.
// Esta clase es una de las dos clases de base principales que se pueden utilizar para crear un adaptador personalizado.
// La otra es ArrayAdapter
public class FacturasListViewAdapter extends BaseAdapter implements Filterable {

    private IDAOCliente idaoCliente = IDAOCliente.getInstance();

    private IDAOLineaFactura idaoLineaFactura = IDAOLineaFactura.GetInstance();

    private Context _context;

    private final List<ModelFactura> _items;

    private List<ModelFactura> _itemsFiltered;

    private CustomFilter customFilter;

    public FacturasListViewAdapter(@NonNull Context context, @NonNull List<ModelFactura> items) {
        //super(context, R.layout.adapter_listview_facturas, items);
        _items = new ArrayList<>(items);
        _itemsFiltered = new ArrayList<>(items);
        _context = context;
        customFilter = new CustomFilter();
    }

    //El método getCount devuelve el número de elementos en la lista.
    @Override
    public int getCount() {
        return _itemsFiltered.size();
    }

    //El método getItem devuelve el elemento en la posición especificada.
    @Override
    public Object getItem(int position) {
        ModelFactura factura = _itemsFiltered.get(position);
        return factura;
    }

    //El método getItemId devuelve la posición del elemento.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //El metodo getview se llama automaticamente por el sistema android cuando se necesita mostrar un elemento por pantalla.
    public View getView(int position, View convertView, ViewGroup parent) {
        //En getView, primero se verifica si convertView es nula.
        //  Si es nula, se inflama el layout personalizado usando un LayoutInflater y se asigna a convertView.
        //  Si no es nula, se usa directamente.
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(_context);
            convertView = inflater.inflate(R.layout.adapter_listview_facturas, parent, false);
        }
        ModelFactura factura = _itemsFiltered.get(position);

        //Luego, se obtiene una referencia a un TextView dentro del layout personalizado.
        TextView lblCodigoFactura = convertView.findViewById(R.id.lblCodigoFactura);
        TextView lblDateYear = convertView.findViewById(R.id.lblDateYear);
        TextView lblDateDay = convertView.findViewById(R.id.lblDateDay);
        TextView lblDateMonth = convertView.findViewById(R.id.lblDateMonth);
        TextView lblCliente = convertView.findViewById(R.id.lblCliente);
        TextView lblTotal = convertView.findViewById(R.id.lblTotal);

        //Finalmente, se establece el texto del TextView con el elemento correspondiente de la lista
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(factura.getFecha());
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String diaFormateado = String.format("%02d", dia);
        String mesFormateado = new DateFormatSymbols().getShortMonths()[mes];
        ModelCliente cliente = idaoCliente.getById(factura.getCodigoCliente());
        double total = idaoLineaFactura.getTotal(factura.getCodigo());
        lblCodigoFactura.setText(String.valueOf(factura.getCodigo()));
        lblDateYear.setText(String.valueOf(ano));
        lblDateDay.setText(diaFormateado);
        lblDateMonth.setText(mesFormateado);
        lblCliente.setText(cliente.getNombre());
        lblTotal.setText(String.valueOf(total));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea una instancia del nuevo fragment
                Fragment newFragment = new DetalleFacturasFragment();

                // Crear un bundle para pasar los parámetros
                Bundle args = new Bundle();
                args.putInt("codigoFactura", factura.getCodigo());
                // Agregar el bundle al fragmento
                newFragment.setArguments(args);


                // Aquí se declara una variable local fragmentManager del tipo FragmentManager.
                // Este será el objeto que utilizaremos para gestionar los fragmentos en la aplicación.

                // Aquí se realiza una conversión de tipos de context a FragmentActivity.
                // _context es una variable local que se pasa como parámetro en el constructor de ListViewAdapter.
                // El context es el contexto en el que se ejecuta la aplicación y se utiliza comúnmente para acceder a recursos
                // y servicios del sistema Android.

                // Pero en este caso, necesitamos una instancia de FragmentActivity para poder obtener un FragmentManager.
                // Una FragmentActivity es una actividad especial que proporciona un marco para trabajar con fragmentos.
                // Si context no es una FragmentActivity, debes hacer una conversión explícita para poder usar getSupportFragmentManager().

                // Obtiene el FragmentManager desde el contexto
                FragmentManager fragmentManager = ((FragmentActivity) _context).getSupportFragmentManager();

                // Inicia la transacción de fragmentos
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, newFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        //y se devuelve la View resultante
        return convertView;
    }

    @Override
    public Filter getFilter() {
        return customFilter;
    }

    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0) {
                results.values = new ArrayList<>(_items);
                results.count = _items.size();
            } else {
                List<ModelFactura> filtered = new ArrayList<>();
                for (ModelFactura factura : _items) {
                    ModelCliente cliente = idaoCliente.getById(factura.getCodigoCliente());
                    if (cliente.getNombre().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filtered.add(factura);
                    }
                }
                results.values = filtered;
                results.count = filtered.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            _itemsFiltered.clear();
            _itemsFiltered.addAll((List<ModelFactura>) filterResults.values);
            notifyDataSetChanged();
        }
    }

}




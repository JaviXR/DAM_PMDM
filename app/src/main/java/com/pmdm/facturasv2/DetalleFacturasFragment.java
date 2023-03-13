package com.pmdm.facturasv2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.pmdm.facturasv2.adapters.FacturasListViewAdapter;
import com.pmdm.facturasv2.adapters.LineasFacturaListViewAdapter;
import com.pmdm.facturasv2.modelo.ModelCliente;
import com.pmdm.facturasv2.modelo.ModelFactura;
import com.pmdm.facturasv2.modelo.ModelLineaFactura;
import com.pmdm.facturasv2.persistencia.IDAOCliente;
import com.pmdm.facturasv2.persistencia.IDAOFactura;
import com.pmdm.facturasv2.persistencia.IDAOLineaFactura;

import java.util.ArrayList;

public class DetalleFacturasFragment extends Fragment {

    private ListView _listView;
    private TextView _txtCodigoFactura;
    private TextView _txtCliente;
    private TextView _txtTotal;
    private ArrayList<ModelLineaFactura> _elements;
    private LineasFacturaListViewAdapter _adapter;
    private ModelFactura _factura;
    private IDAOFactura _idaoFactura = IDAOFactura.getInstance();
    private IDAOLineaFactura _idaoLineaFactura = IDAOLineaFactura.GetInstance();
    private IDAOCliente idaoCliente = IDAOCliente.getInstance();

    public DetalleFacturasFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            int codigoFactura = args.getInt("codigoFactura");
            _factura = _idaoFactura.getById(codigoFactura);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_facturas, container, false);
        _listView = view.findViewById(R.id.listView);
        _txtCodigoFactura = view.findViewById(R.id.txtCodigoFactura);
        _txtCliente = view.findViewById(R.id.txtCliente);
        _txtTotal = view.findViewById(R.id.txtTotal);

        //campos
        ModelCliente cliente = idaoCliente.getById(_factura.getCodigoCliente());
        double total = _idaoLineaFactura.getTotal(_factura.getCodigo());
        _txtCodigoFactura.setText(String.valueOf(_factura.getCodigo()));
        _txtCliente.setText(cliente.getNombre());
        _txtTotal.setText(String.valueOf(total));

        //adaptador
        _elements = _idaoLineaFactura.getByCodigoFactura(_factura.getCodigo());
        _adapter = new LineasFacturaListViewAdapter(getContext(), _elements);
        _listView.setAdapter(_adapter);

        return view;
    }
}
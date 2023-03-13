package com.pmdm.facturasv2;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pmdm.facturasv2.adapters.ClientesRecyclerViewAdapter;
import com.pmdm.facturasv2.adapters.FacturasListViewAdapter;
import com.pmdm.facturasv2.modelo.ModelCliente;
import com.pmdm.facturasv2.modelo.RealmCliente;
import com.pmdm.facturasv2.persistencia.IDAOCliente;
import com.pmdm.facturasv2.persistencia.IDAOFactura;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class MaestroClientesFragment extends Fragment {

    private FloatingActionButton _fabInsertar;
    private static RecyclerView recyclerView;
    private static ArrayList<ModelCliente> data;
    private static RecyclerView.Adapter<ClientesRecyclerViewAdapter.MyViewHolder> _adapter;
    private RecyclerView.LayoutManager layoutManager;
    public static View.OnClickListener myOnClickListener;
    private IDAOCliente _idaoCliente = IDAOCliente.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maestro_clientes, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_clients);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = _idaoCliente.getAll();
        _adapter = new ClientesRecyclerViewAdapter(getContext(), data);
        myOnClickListener = new MyOnClickListener(getContext());
        recyclerView.setAdapter(_adapter);

        return view;
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {

            //Aquí quiero hacer que la tarjeta se agrande
        }
    }
}
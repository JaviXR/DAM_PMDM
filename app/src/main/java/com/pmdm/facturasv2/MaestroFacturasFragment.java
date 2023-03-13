package com.pmdm.facturasv2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pmdm.facturasv2.adapters.FacturasListViewAdapter;
import com.pmdm.facturasv2.modelo.ModelFactura;
import com.pmdm.facturasv2.persistencia.IDAOFactura;

import java.util.ArrayList;

public class MaestroFacturasFragment extends Fragment {

    private FloatingActionButton _fabInsertar;
    private ListView _listView;
    private ArrayList<ModelFactura> _elements;
    private FacturasListViewAdapter _adapter;
    private IDAOFactura _idaoFactura = IDAOFactura.getInstance();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Obtén la referencia a la Toolbar inflada en onCreateView()
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);

        // Configura la acción de búsqueda en la barra de herramientas
        SearchView searchView = toolbar.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Implementa aquí lo que quieres hacer cuando el usuario presiona el botón de búsqueda
                _adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Implementa aquí la lógica de filtrado de resultados en base a newText
                _adapter.getFilter().filter(newText);
                return false;
            }
        });

    }

    //- LayoutInflater inflater: Este parámetro es un objeto LayoutInflater que se utiliza para inflar la vista del fragmento.
    //- ViewGroup container: Este parámetro es un ViewGroup que es el contenedor de la vista inflada.
    //- Bundle savedInstanceState: Este parámetro es un Bundle que contiene el estado guardado previamente de la actividad o fragmento.
    //  Se usa para restaurar el estado previo en caso de un cambio de orientación de la pantalla o cualquier otra acción que cause la recreación del fragmento.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maestro_facturas, container, false);
        _listView = view.findViewById(R.id.listView);
        _elements = _idaoFactura.getAll();
        _adapter = new FacturasListViewAdapter(getContext(), _elements);
        _listView.setAdapter(_adapter);

        _fabInsertar = view.findViewById(R.id.fab_insertar);
        _fabInsertar.setOnClickListener(view1 -> Toast.makeText(getActivity(), "Implementar nueva factura", Toast.LENGTH_SHORT).show());

        return view;
    }
}
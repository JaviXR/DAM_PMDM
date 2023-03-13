package com.pmdm.facturasv2;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.pmdm.facturasv2.adapters.IdiomaListViewAdapter;
import com.pmdm.facturasv2.modelo.ModelIdioma;
import com.pmdm.facturasv2.persistencia.AppConfig;
import com.pmdm.facturasv2.persistencia.IDAOFactura;
import com.pmdm.facturasv2.persistencia.IDAOIdioma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class IdiomaFragment extends Fragment {

    private View _view;
    private FragmentActivity _context;
    private ListView _listView;
    private IdiomaListViewAdapter _adapter;

    private IDAOIdioma _idaoIdioma = IDAOIdioma.GetInstance();

    public IdiomaFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        _view = inflater.inflate(R.layout.fragment_idioma, container, false);
        return _view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _context = getActivity();
        _listView = _view.findViewById(R.id.listView);
        ArrayList<ModelIdioma> idiomas = _idaoIdioma.getAll();

        /*List<ModelIdioma> idiomas = Arrays.asList(
                new ModelIdioma(1,"es", getString(R.string.espanol)),
                new ModelIdioma(2,"en", getString(R.string.ingles)));
*/
        _adapter = new IdiomaListViewAdapter(_context, idiomas);
        _listView.setAdapter(_adapter);

    }
}
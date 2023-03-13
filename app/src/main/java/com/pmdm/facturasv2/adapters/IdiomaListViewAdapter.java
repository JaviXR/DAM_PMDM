package com.pmdm.facturasv2.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;

import com.pmdm.facturasv2.MainActivity;
import com.pmdm.facturasv2.R;
import com.pmdm.facturasv2.modelo.ModelFactura;
import com.pmdm.facturasv2.modelo.ModelIdioma;
import com.pmdm.facturasv2.modelo.ModelLineaFactura;
import com.pmdm.facturasv2.persistencia.AppConfig;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class IdiomaListViewAdapter extends BaseAdapter {

    private Context _context;
    private final List<ModelIdioma> _items;

    public IdiomaListViewAdapter(@NonNull Context context, @NonNull List<ModelIdioma> items) {
        _items = items;
        _context = context;
    }

    @Override
    public int getCount() {
        return _items.size();
    }

    @Override
    public Object getItem(int position) {
        ModelIdioma idioma = _items.get(position);
        return idioma;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(_context);
            view = inflater.inflate(R.layout.adapter_listview_idioma, parent, false);
        }
        ModelIdioma idioma = _items.get(position);
        view.setTag(idioma);
        TextView lblText = view.findViewById(R.id.lblText);

        //Seguramente haya una manera más eficiente de hacer esto, creando y recorriendo un array o lista con los R.string que
        //comiencen por "language_" o algo por el estilo y así hacer más dinámico el añadir nuevos idiomas, pero no lo he logrado
        switch (idioma.getCountryCode()) {
            default:

            case "es":{

                lblText.setText(R.string.language_spanish);
                break;
            }

            case "en":{

                lblText.setText(R.string.language_english);
                break;
            }

            case "de":{

                lblText.setText(R.string.language_german);
                break;
            }

            case "fr":{

                lblText.setText(R.string.language_french);
                break;
            }
        }

        view.setOnClickListener(view1 -> {

            ModelIdioma idioma1 = ((ModelIdioma) view1.getTag());
            AppConfig.LANG_CURRENT = idioma1.getCountryCode();

            MainActivity mainActivity = (MainActivity)_context;

            //SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(mainActivity);
            String language = mainActivity.getBaseContext().getResources().getConfiguration().getLocales().get(0).getLanguage();
            if (!AppConfig.LANG_CURRENT.isEmpty() && !language.equals(AppConfig.LANG_CURRENT)) {
                _context = setLocale(_context);
            }
            mainActivity.recreate();
            Log.d("CLICK: ", idioma1.getDescription());
        });

        return view;
    }

    /**
     * Método que cambia el idioma predefinido de un Context
     * @param c Contexto a localizar
     * @return Contexto localizado
     */
    public static Context setLocale(Context c) {

        Configuration config = c.getResources().getConfiguration();
        //Locale locale = new Locale(AppConfig.getInstance(c).getProperties().getString(c.getString(R.string.props_current_lang), "es"));
        Locale locale = new Locale(AppConfig.LANG_CURRENT);
        Locale.setDefault(locale);
        config.setLocale(locale);
        return c.createConfigurationContext(config);
    }
}

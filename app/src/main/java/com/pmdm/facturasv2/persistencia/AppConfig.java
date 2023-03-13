package com.pmdm.facturasv2.persistencia;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.pmdm.facturasv2.R;

import java.io.IOException;
import java.util.Properties;

public class AppConfig {

        //Valores: MEMORY, SQLITE, FIREBASE, MONGODB
        public static String Modo = "MONGODB";
        public static String LANG_CURRENT = "es";
        public static String REALM_ID = "facturasv2-iqekb";
        private static AppConfig instance;
        private static SharedPreferences properties;


        public static AppConfig getInstance(Context c) throws IOException {
                if (instance == null)
                {
                        instance = new AppConfig(c.getApplicationContext());
                }
                return instance;
        }

        private AppConfig(Context c) {

                properties = c.getSharedPreferences("com.pmdm.facturasv2.PREFERENCES", Context.MODE_PRIVATE);

                //Modo = properties.getString(c.getString(R.string.props_mode), "MONGODB");
                //LANG_CURRENT = properties.getString(c.getString(R.string.props_current_lang), "es");
        }

        public static void saveProps(Context c) {

                new Thread(new SavingProcess(c)).start();
        }

        public static class SavingProcess implements Runnable {

                Context c;
                public SavingProcess(Context c) {
                        this.c = c;
                }

                @Override
                public void run() {
                        SharedPreferences.Editor propertyEditor = properties.edit();
                        propertyEditor.clear().putString(c.getString(R.string.props_mode), "MONGODB")
                                .putString(c.getString(R.string.props_current_lang), "es")
                                .commit();
                }
        }

        public static SharedPreferences getProperties() {
                return properties;
        }
}

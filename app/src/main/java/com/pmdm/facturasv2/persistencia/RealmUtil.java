package com.pmdm.facturasv2.persistencia;

import android.content.Context;

import com.pmdm.facturasv2.LoginActivity;
import com.pmdm.facturasv2.modelo.RealmCliente;
import com.pmdm.facturasv2.modelo.RealmFactura;
import com.pmdm.facturasv2.modelo.RealmLineaFactura;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.sync.SyncConfiguration;

public class RealmUtil {

    private static App app;
    private static Credentials credentials;
    private static SyncConfiguration config;
    static AtomicReference<User> currentUser = new AtomicReference<>();

    /**
     * Método que inicia las librerías de Realm y crea nuestra app
     * @param c Context de la Activity que inicializará el Realm
     */
    public static void iniciarRealm(Context c) {

        Realm.init(c.getApplicationContext());
        app = new App(new AppConfiguration.Builder(AppConfig.REALM_ID).build());
    }

    /**
     * Método que crea unos credenciales de inicio de sesión en MongoDB y ejecuta un hilo para conectarse.
     * @param user Email del usuario
     * @param pass Contraseña del usuario
     */
    public static void login(String user, String pass) {

        credentials = Credentials.emailPassword(user, pass);
        new Thread(new RealmWorker()).start();
    }

    /**
     * @return El achivo de configuración para un Realm sincronizado
     */
    private static SyncConfiguration getSyncConfig() {

        if (config == null) {
            config = new SyncConfiguration.Builder(currentUser.get())
                    .allowQueriesOnUiThread(true)
                    .allowWritesOnUiThread(true)
                    .waitForInitialRemoteData(1500, TimeUnit.MILLISECONDS)
                    .compactOnLaunch()
                    .build();
        }

        return config;
    }
    public static User getCurrentUser() {

        return currentUser.get();
    }

    /**
     * Clase Worker para lanzar hilos síncronos que utilizan la base de datos.
     */
    private static class RealmWorker implements Runnable {

        private Realm realm;
        private String call;
        private RealmObject item;

        /**
         * Constructor sin parámetros de RealmWorker, que llama por defecto al método de login.
         */
        public RealmWorker() {

            call = "LOGIN";
        }

        /**
         * Constructor que selecciona la acción que tendrá el hilo creado sobre la base de datos en función de los parámetros que reciba.
         * @param call Método al que se quiere llamar para interactuar con la base de datos
         * @param item Objeto con el que se realizará la acctión
         */
        public RealmWorker(String call, RealmObject item) {

            this.call = call;
            this.item = item;
        }

        @Override
        public synchronized void run() {

            switch (call) {

                case "LOGIN": {

                    realmLogin();
                    break;
                }

                case "INSERT": {

                    realmInsert();
                    realm.close();
                    break;
                }

                case "DELETE": {

                    realmDelete();
                    realm.close();
                    break;
                }

                default:
                    throw new IllegalStateException("Unexpected value: " + call);
            }
            notifyAll();
        }

        /**
         * Método de verificación de usuario de la base de datos.
         */
        private void realmLogin() {

            try {

                currentUser.set(app.login(credentials));
            } catch (Exception ignored) {}
        }

        /**
         * Método de inserción de un objeto en la base de datos. En caso de ya existir un objeto del mismo ID, lo actualizará.
         */
        private void realmInsert() {

            realm = Realm.getInstance(getSyncConfig());
            realm.executeTransaction(transaction -> {
                transaction.insertOrUpdate(item);
            });

        }

        /**
         * Método que elimina un objeto de la base de datos.
         */
        private void realmDelete() {

            if (item.getClass().equals(RealmCliente.class)) {

                realm = Realm.getInstance(getSyncConfig());
                realm.executeTransaction(transaction -> {
                    transaction.where(item.getClass()).equalTo("_id", ((RealmCliente) item).getId()).findFirst();;
                });
            } else if (item.getClass().equals(RealmFactura.class)) {

                realm = Realm.getInstance(getSyncConfig());
                realm.executeTransaction(transaction -> {
                    transaction.where(item.getClass()).equalTo("_id", ((RealmFactura) item).getId()).findFirst();;
                });
            } else if (item.getClass().equals(RealmLineaFactura.class)) {

                realm = Realm.getInstance(getSyncConfig());
                realm.executeTransaction(transaction -> {
                    transaction.where(item.getClass()).equalTo("_id", ((RealmLineaFactura) item).getId()).findFirst();;
                });
            }
        }

    }
}


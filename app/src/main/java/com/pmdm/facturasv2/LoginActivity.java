package com.pmdm.facturasv2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pmdm.facturasv2.adapters.IdiomaListViewAdapter;
import com.pmdm.facturasv2.modelo.ModelUsuario;
import com.pmdm.facturasv2.persistencia.AppConfig;
import com.pmdm.facturasv2.persistencia.IDAOUsuario;
import com.pmdm.facturasv2.persistencia.RealmUtil;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsuario;
    private EditText txtPassword;
    private Button btnLogin;
    private static final Object lock = new Object();

    //dependencias
    private IDAOUsuario daoUsuario = IDAOUsuario.GetInstance();

    private Context _context;
    //Configuración del Realm para utilizar con MongoDB Atlas

    /**
     * Método que cambia el idioma en el arranque de la Aplicación, poniendo como predeterminado el Español.
     * @param context Contexto actual pre-creación de la Activity.
     */
    @Override
    protected void attachBaseContext(Context context) {

        super.attachBaseContext(IdiomaListViewAdapter.setLocale(context));
    }

    /**
     * Método que lanza siempre la Activity al ser creada.
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        _context = this;

        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {

            String username = txtUsuario.getText().toString();
            String password = txtPassword.getText().toString();

            switch (AppConfig.Modo) {

                default:
                    break;
                case "MEMORY": {

                    //Método login memoria
                    ModelUsuario usuario = daoUsuario.login(username, password);

                    if (usuario != null) {
                        Intent intent = new Intent(_context, MainActivity.class);
                        intent.putExtra("usuario", usuario);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, R.string.msg_welcome + usuario.getUsuario(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.error_login, Toast.LENGTH_SHORT).show();
                    }

                    break;
                }

                case "MONGODB": {

                    //Método login MongoDB
                    RealmUtil.iniciarRealm(_context);

                    try {
                        synchronized (lock) {

                            RealmUtil.login(username, password);
                            lock.wait(1000);
                        }
                    } catch (InterruptedException e) {
                        Toast.makeText(LoginActivity.this, getString(R.string.error_timeout), Toast.LENGTH_SHORT).show();
                    }

                    if (RealmUtil.getCurrentUser() != null) {

                        Intent intent = new Intent(_context, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, getString(R.string.msg_welcome) + RealmUtil.getCurrentUser().getProfile().getName(), Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(LoginActivity.this, getString(R.string.error_login), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }

        });

    }

    /**
     * Método que devuelve el bloqueo del hilo
     * @return Objeto que bloquea el hilo
     */
    public static Object getLock() {
        return lock;
    }
}
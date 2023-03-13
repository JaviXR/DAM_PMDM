package com.pmdm.facturasv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.pmdm.facturasv2.adapters.IdiomaListViewAdapter;
import com.pmdm.facturasv2.persistencia.AppConfig;
import com.pmdm.facturasv2.persistencia.RealmUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View _headerLayout;
    private TextView _lblUser;
    private DrawerLayout _drawerLayout;
    private NavigationView _navView;
    private Toolbar _toolbar;
    private FragmentManager fragmentManager;
    private List<Fragment> fragments = new ArrayList<>();
    private Context _context;

    /**
     * Método que cambia el idioma en el arranque de la Activity, poniendo como predeterminado el Español.
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
        setContentView(R.layout.activity_main);
        _context = this;

        //Toolbar
        _toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);

        //Fragment Manager
        fragmentManager = getSupportFragmentManager();

        fragments.add(new DashboardFragment());
        fragments.add(new MaestroClientesFragment());
        fragments.add(new MaestroFacturasFragment());
        fragments.add(new IdiomaFragment());

        // Muestra el primer Fragment
        displayFragment(0);
        getSupportActionBar().setTitle("DASHBOARD");

        //Get bundles
        //ModelUsuario usuario = (ModelUsuario) getIntent().getSerializableExtra("usuario");

        //get Layouts
        _navView = findViewById(R.id.nav_view);
        _navView.setNavigationItemSelectedListener(new NavView_OnNavigationItemSelectedListener());
        _headerLayout = _navView.getHeaderView(0);

        //set User
        _lblUser = _headerLayout.findViewById(R.id.lblUser);
        _lblUser.setText(getString(R.string.msg_welcome) + RealmUtil.getCurrentUser().getProfile().getEmail());

        //Toogle DrawerLayout
        _drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                _drawerLayout,
                _toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        _drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Método que inicia un FragmentTransaction y cambia el Fragment visible en la Activity.
     * @param position Posición en la lista de Fragments de la que se quiere recuperar el mismo.
     */
    private void displayFragment(int position) {
        Fragment fragment = fragments.get(position);
        // Esta línea obtiene una instancia del FragmentManager y llama al método beginTransaction() en él.
        // El FragmentManager es un componente central para trabajar con fragmentos en Android.
        // La llamada a beginTransaction() inicia una transacción de fragmentos,
        // lo que significa que puedes realizar varios cambios en los fragmentos en la aplicación y luego confirmarlos o
        // deshacerlos todos juntos.

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // Esta línea llama al método replace() en la transacción de fragmentos recién iniciada.
        // replace() reemplaza un fragmento existente con un nuevo fragmento en el FragmentContainer especificado por su ID
        // (R.id.container en este caso). El nuevo fragmento es newFragment, que se creó en una línea anterior.

        transaction.replace(R.id.content_frame, fragment);

        //.addToBackStack(null)
        //Esta línea llama al método addToBackStack() en la transacción de fragmentos.
        // Agregar una transacción al back stack significa que cuando el usuario presiona el botón "Atrás" en su dispositivo,
        // la transacción se deshace y el fragmento anterior se muestra de nuevo.
        // Si no se agrega la transacción al back stack, el fragmento anterior se descartará y
        // no habrá forma de volver a él cuando se presione el botón "Atrás".

        //En Android, el método addToBackStack(String name) es parte de la clase FragmentTransaction y se utiliza para agregar una transacción
        // de fragmentos a la pila de retroceso.
        //
        //El parámetro name se utiliza para identificar la transacción en la pila de retroceso.
        // Si se especifica un nombre, se puede usar posteriormente para realizar un seguimiento de la transacción en la pila de retroceso.
        //
        //Si se pasa null como parámetro, la transacción no se identificará en la pila de retroceso.
        // Esto significa que no se guardará información sobre la transacción en la pila de retroceso, lo que significa que no se podrá acceder
        // a la transacción más tarde usando el nombre especificado.
        //
        //En general, es una buena práctica especificar un nombre para las transacciones en la pila de retroceso,
        // ya que esto puede ser útil para realizar un seguimiento de las transacciones y para facilitar la depuración de errores.
        // Sin embargo, hay algunos casos en los que puede ser apropiado usar null como parámetro,
        // como por ejemplo cuando se quiere que la transacción sea descartada de manera permanente
        // y no se deba guardar en la pila de retroceso.

        //Finalmente, esta línea llama al método commit() en la transacción de fragmentos. commit()
        // confirma los cambios y aplica la transacción, reemplazando el fragmento existente con el nuevo fragmento.
        transaction.commit();

        //En resumen, esta línea de código inicia una transacción de fragmentos,
        // reemplaza un fragmento existente con un nuevo fragmento y agrega la transacción al back stack,
        // y finalmente confirma la transacción.
    }


    /**
     * Clase que implementa OnNavigationItemSelectedListener. Hacemos Override a onNavigationItemSelected() para desplazarnos entre nuestros
     * distintos Fragments.
     */
    private class NavView_OnNavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_dashboard:
                    getSupportActionBar().setTitle(getString(R.string.menu_dashboard));
                    displayFragment(0);
                    break;
                case R.id.menu_clientes:
                    displayFragment(1);
                    getSupportActionBar().setTitle(getString(R.string.menu_clientes));
                    break;
                case R.id.menu_facturas:
                    displayFragment(2);
                    getSupportActionBar().setTitle(getString(R.string.menu_facturas));
                    break;
                case R.id.menu_idioma:
                    displayFragment(3);
                    getSupportActionBar().setTitle(getString(R.string.menu_idioma));
                    break;
                case R.id.menu_salir:
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);
                    break;
            }
            //AppConfig.saveProps(_context);
            _drawerLayout.closeDrawers();
            return true;
        }
    }

}
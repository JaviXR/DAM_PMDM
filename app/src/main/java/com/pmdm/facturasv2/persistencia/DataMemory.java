package com.pmdm.facturasv2.persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.pmdm.facturasv2.modelo.ModelCliente;
import com.pmdm.facturasv2.modelo.ModelFactura;
import com.pmdm.facturasv2.modelo.ModelIdioma;
import com.pmdm.facturasv2.modelo.ModelLineaFactura;
import com.pmdm.facturasv2.modelo.ModelUsuario;

public class DataMemory
    {
        public ArrayList<ModelCliente> datosClientes;
        public ArrayList<ModelFactura> datosFacturas;
        public ArrayList<ModelLineaFactura> datosLineasFacturas;
        public ArrayList<ModelUsuario> datosUsuarios;
        public ArrayList<ModelIdioma> datosIdiomas;

        //Propiedad estática donde se guarda la única instancia de la clase ...
        private static DataMemory instance;

        //Método estático que devuelve una unica instancia de "Singleton" ...
        public static DataMemory getInstance()
        {
            if (instance == null)
            {
                instance = new DataMemory();
            }
            return instance;
        }

        //Constructor por defecto privado para que no se pueda instanciar a menos que se use el metodo de clase "GetInstance" ...
        private DataMemory() {
            inicializarDatosUsuario();
            inicializarDatosClientes();
            inicializarDatosFacturas();
            inicializarLineasFacturas();
            inicializarDatosIdiomas();
        }

        private void inicializarDatosUsuario() {
            this.datosUsuarios = new ArrayList<ModelUsuario>();
            this.datosUsuarios.add(new ModelUsuario("usuario1", "password1"));
            this.datosUsuarios.add(new ModelUsuario("usuario2", "password2"));
        }

        private void inicializarDatosClientes(){
            this.datosClientes = new ArrayList<ModelCliente>();
            this.datosClientes.add(new ModelCliente(1,"Cliente 1"));
            this.datosClientes.add(new ModelCliente(2,"Cliente 2"));
            this.datosClientes.add(new ModelCliente(3,"Cliente 3"));
            this.datosClientes.add(new ModelCliente(4,"Cliente 4"));
        }

        private void inicializarDatosFacturas(){
            this.datosFacturas = new ArrayList<ModelFactura>();
            try {
                this.datosFacturas.add(new ModelFactura(1, 100, 1, new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-01")));
                this.datosFacturas.add(new ModelFactura(2, 200, 2, new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-02")));
                this.datosFacturas.add(new ModelFactura(3, 300, 3, new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-03")));
                this.datosFacturas.add(new ModelFactura(4, 400, 4, new SimpleDateFormat("yyyy-MM-dd").parse("2023-02-04")));
            } catch (Exception ex){
                //
            }
        }

        private void inicializarLineasFacturas(){
            this.datosLineasFacturas = new ArrayList<ModelLineaFactura>();
            this.datosLineasFacturas.add(new ModelLineaFactura(1, 1, 1, "Producto 1", 100));
            this.datosLineasFacturas.add(new ModelLineaFactura(2, 1, 2, "Producto 2", 200));
            this.datosLineasFacturas.add(new ModelLineaFactura(3, 2, 3, "Producto 3", 300));
            this.datosLineasFacturas.add(new ModelLineaFactura(4, 2, 4, "Producto 4", 400));
            this.datosLineasFacturas.add(new ModelLineaFactura(5, 3, 5, "Producto 5", 500));
            this.datosLineasFacturas.add(new ModelLineaFactura(6, 3, 6, "Producto 6", 600));
            this.datosLineasFacturas.add(new ModelLineaFactura(7, 4, 3, "Producto 7", 700));
            this.datosLineasFacturas.add(new ModelLineaFactura(8, 4, 4, "Producto 8", 800));
        }

        private void inicializarDatosIdiomas() {
            this.datosIdiomas = new ArrayList<ModelIdioma>();
            this.datosIdiomas.add(new ModelIdioma(1, "es", "Español"));
            this.datosIdiomas.add(new ModelIdioma(2, "en", "Inglés"));
            this.datosIdiomas.add(new ModelIdioma(1, "de", "Alemán"));
            this.datosIdiomas.add(new ModelIdioma(2, "fr", "Francés"));
        }
    }



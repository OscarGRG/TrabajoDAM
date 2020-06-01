package com.example.easysport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetallesRegistro extends AppCompatActivity {

    TextView lblTituloRegistro, lblFecha, lblFechaRegistro, lblhora, lblHoraRegistrada,lblTextoContenido, lblTextoRegistrado;
    Button btnVolveraLista;
    String fecha, hora, contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_registro);

        //Conexion interfaz-clase
        lblTituloRegistro = findViewById(R.id.lblTituloDetalleRegistro);
        lblFecha = findViewById(R.id.lblFecha);
        lblFechaRegistro = findViewById(R.id.lblFechaUltimaModificacion);
        lblhora = findViewById(R.id.lblHora);
        lblHoraRegistrada = findViewById(R.id.lblHoraUltimaModificacion);
        lblTextoContenido = findViewById(R.id.lblContentidoRegistro);
        lblTextoRegistrado = findViewById(R.id.lblDatosRegistrados);
        btnVolveraLista = findViewById(R.id.btVolver);

        //Asignación de fuente
        Typeface cambio_fuente = Typeface.createFromAsset(getAssets(),"baloo2semibold.ttf");
        lblTituloRegistro.setTypeface(cambio_fuente);
        lblFecha.setTypeface(cambio_fuente);
        lblFechaRegistro.setTypeface(cambio_fuente);
        lblhora.setTypeface(cambio_fuente);
        lblHoraRegistrada.setTypeface(cambio_fuente);
        lblTextoContenido.setTypeface(cambio_fuente);
        lblTextoRegistrado.setTypeface(cambio_fuente);
        btnVolveraLista.setTypeface(cambio_fuente);

        recogidaDatos();
        asignacion_datos();
    }

    //Metodo que asigna los datos del elemento seleccionado en la pantalla anterior
    private void asignacion_datos() {
        lblFechaRegistro.setText(fecha);
        lblHoraRegistrada.setText(hora);
        lblTextoRegistrado.setText(contenido);
    }

    //Metodo que recoge los datos provenientes de la seleccion de la pantalla anterior
    private void recogidaDatos() {
        Bundle parametros = this.getIntent().getExtras();
        fecha=parametros.getString("fecha");
        hora = parametros.getString("hora");
        contenido = parametros.getString("contenido");
    }

    //Metodo que permite volver al menu principal
    public void btHome (View v){
            Intent amenu = new Intent(this, Menu.class );
            startActivity(amenu);
    }

    //Metodo que nos permite volver a la lista de Registros
    public void aLista (View v){
        Intent alista = new Intent (this, ListadoRegistros.class);
        startActivity(alista);
    }
}

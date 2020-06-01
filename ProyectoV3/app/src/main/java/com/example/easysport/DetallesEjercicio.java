package com.example.easysport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetallesEjercicio extends AppCompatActivity {

    TextView titulo,howTo,zona,materiales;
    String nombre_ejercicio,realizacion,material,zona_trabajada,tabla_ejercicios;
    Button botonLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ejercicio);

        //Asociacion entre interfaz y variables
        titulo = findViewById(R.id.lblTituloNRegistro);
        howTo = findViewById(R.id.lblTextoHTDit);
        zona = findViewById(R.id.lblTextoZona);
        materiales = findViewById(R.id.lbltextoMaterial);
        botonLista = findViewById(R.id.btLista);

        //Recogida y muestreo de los datos
        recogervalores();
        mostrarValores();

        //Asignación de fuentes
        Typeface fuente = Typeface.createFromAsset(getAssets(),"baloo2semibold.ttf");
        titulo.setTypeface(fuente);
        howTo.setTypeface(fuente);
        zona.setTypeface(fuente);
        materiales.setTypeface(fuente);
        botonLista.setTypeface(fuente);

        //Scroll para la descripcion de los ejercicios
        howTo.setMovementMethod(new ScrollingMovementMethod());

    }

    //Este metodo asigna los detalles del ejercicio seleccionado a sus posiciones en la interfaz.
    private void mostrarValores() {

        howTo.setText(realizacion);
        titulo.setText(nombre_ejercicio);
        materiales.setText(material);
        zona.setText(zona_trabajada);
    }

    //Este metodo recoge los detalles del ejercicio desde la pantalla anterior.
    private void recogervalores() {

        Bundle valores = getIntent().getExtras();
        nombre_ejercicio = valores.getString("nombre_ejercicio");
        realizacion = valores.getString("realizacion");
        material = valores.getString("material");
        zona_trabajada = valores.getString("zona");
        tabla_ejercicios = valores.getString("tabla_ejercicios");
    }

    //Boton para volver al menu principal
    public void btHome(View v){
        Intent aMenu = new Intent(this, Menu.class );
        startActivity(aMenu);
    }

    //Permite volver a la lista de ejercicios
    public void btaLista(View v){
        Intent aEjercicios = new Intent(this, ListadoEjercicios.class );
        startActivity(aEjercicios);
    }
}

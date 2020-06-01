package com.example.easysport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Portada extends AppCompatActivity {

    TextView titulo;
    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        //Relacion con la interfaz
        titulo = findViewById(R.id.lbltitulo);
        boton = findViewById(R.id.btEntrar);

        //Asignar fuente
        Typeface Titl = Typeface.createFromAsset(getAssets(),"baloo2semibold.ttf");
        titulo.setTypeface(Titl);
        boton.setTypeface(Titl);
    }

        //metodo del boton que vuelve al menu principal
    public void btnHome(View v){
        Intent aPrincipal = new Intent(this, Menu.class );
        startActivity(aPrincipal);
    }
}

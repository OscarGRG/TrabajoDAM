package com.example.easysport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    TextView title;
    ImageButton clima,registro,agregarRegistro,ejercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        title = findViewById(R.id.tituloPrincipal);
        clima = findViewById(R.id.btVerClima);
        registro = findViewById(R.id.btRegistrosGuardados);
        agregarRegistro = findViewById(R.id.btCrearRegistro);
        ejercicios = findViewById(R.id.btEjercicios);

        Typeface titulo = Typeface.createFromAsset(getAssets(),"baloo2semibold.ttf");
        title.setTypeface(titulo);
    }

    //Metodo para acceder a la sección de los registros
    public void aRegistros (View v){
        Intent aListadoRegistros = new Intent(this, ListadoRegistros.class );
        startActivity(aListadoRegistros);
    }

    //Metodo para acceder a internet y comprobar el clima
    public void aClima(View v){
        Uri pagina_aemet=Uri.parse("http://www.aemet.es/es/eltiempo/prediccion/municipios");
        Intent ver_tiempo_zona=new Intent(Intent.ACTION_VIEW,pagina_aemet);
        if(ver_tiempo_zona.resolveActivity(getPackageManager())!=null){
            startActivity(ver_tiempo_zona);}
    }

    //Metodo para acceder a la lista de ejercicios
    public void aEjercicios(View v){
        Intent aEjercicios = new Intent(this, ListadoEjercicios.class );
        startActivity(aEjercicios);
    }

    //Metodo para acceder a la creación de un nuevo registro
    public void aNuevoRegistro(View v){
        Intent aNewRegistro = new Intent(this, NuevoRegistro.class);
        startActivity(aNewRegistro);
    }
}

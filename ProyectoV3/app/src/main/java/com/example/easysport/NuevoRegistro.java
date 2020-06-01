package com.example.easysport;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easysport.administracionBD.AdminSQLiteOpenHelper;
import java.util.Calendar;

import static com.example.easysport.administracionBD.ContractBD.TBRegistro.*;

public class NuevoRegistro extends AppCompatActivity {

    TextView title, contenidoFecha, hora, textoRegistro, textoFecha,textoHora;
    EditText textoRegistrar;
    Button btnGuardar;
    int d,m,a,ho,min;
    String date, time;
    String SQL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_registro);

        //Conexion entre clase y elementos de interfaz
        title = findViewById(R.id.lblRegistroNuevo);
        textoFecha = findViewById(R.id.lblTextoFecha);
        contenidoFecha = findViewById(R.id.lblFecha);
        textoHora = findViewById(R.id.lblTextoHora);
        hora = findViewById(R.id.lblHora);
        textoRegistro = findViewById(R.id.lblTextoRegistro);
        textoRegistrar = findViewById(R.id.editTextoRegistro);
        btnGuardar = findViewById(R.id.btGuardar);

        //Metodos usados
        asignarFuentes();
        registrarFechayHora();
        busqueda();
    }
    //Buscamos a ver si existe algun registro del dia
    private void busqueda(){
        //Conexion con BD
        AdminSQLiteOpenHelper conn = new AdminSQLiteOpenHelper(this, null);
        SQLiteDatabase BD = conn.getReadableDatabase();

        //Preparacion del cursor
        SQL = "SELECT "+ fecha_registro+","+contenido+" FROM "+Nombre_tabla_registros+" WHERE " +fecha_registro+" = '"+date+"'";
        Cursor cursor = BD.rawQuery(SQL, null);
        //Busqueda en la BD
        //Hay registro
        if (cursor.moveToFirst()){
            BD.close();
            cursor.close();

            //No hay registro
        }else{
            cursor.close();
            BD.close();
        }
    }

    // realiza la insercion
    public void insertarResultados(View v) {
        insertarElemento();
    }

    //Asignacion de fuentes de la clase
    //Asignación de la fuente
    private void asignarFuentes() {
        Typeface font = Typeface.createFromAsset(getAssets(), "baloo2semibold.ttf");
        textoFecha.setTypeface(font);
        contenidoFecha.setTypeface(font);
        textoHora.setTypeface(font);
        hora.setTypeface(font);
        textoRegistro.setTypeface(font);
        textoRegistrar.setTypeface(font);
        title.setTypeface(font);
        btnGuardar.setTypeface(font);
    }

    //Registro de la fecha y la hora para el registro
    private void registrarFechayHora(){
        Calendar momento_dia = Calendar.getInstance();
        d = momento_dia.get(Calendar.DAY_OF_MONTH);
        m = momento_dia.get (Calendar.MONTH)+1;
        a = momento_dia.get(Calendar.YEAR);
        //Asignacion de la fecha
        if (d<10 & m<10){date = "0"+d+" / 0"+m+" / "+a;}
        else if (d<10){date = "0"+d+" / "+m+" / "+a;}
        else if(m<10){date = ""+d+" / 0"+m+" / "+a;}
        else{date = d+" / "+m+" / "+a;}
        contenidoFecha.setText(date);

        ho = momento_dia.get(Calendar.HOUR_OF_DAY);
        min = momento_dia.get (Calendar.MINUTE);

        //Asignacion de la hora
        if (ho<10 & min<10){time = "0"+ho+"y0"+min;}
        else if (ho<10){time = "0"+ho+"y"+min;}
        else if (min<10){time = ho+"y0"+min;}
        else{time = ho+"y"+min;}
        hora.setText(time);
    }
    //Boton de acceso al menu principal
    public void btnHome(View v){
        Intent aMenu = new Intent(this, Menu.class );
        startActivity(aMenu);
    }

    private void insertarElemento(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, null);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String hora_registrar = hora.getText().toString();
        String texto_a_insertar = textoRegistrar.getText().toString();

        ContentValues valores_a_insertar = new ContentValues();
        valores_a_insertar.put(fecha_registro, date);
        valores_a_insertar.put(hora_registro, hora_registrar);
        valores_a_insertar.put(contenido, texto_a_insertar);

        bd.insert(Nombre_tabla_registros, null, valores_a_insertar);

        bd.close();

        Intent aMenu = new Intent(this, Menu.class );
        startActivity(aMenu);
    }
}



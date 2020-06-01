package com.example.easysport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easysport.administracionBD.AdminSQLiteOpenHelper;
import com.example.easysport.administracionBD.ContractBD;

import java.util.ArrayList;

import static com.example.easysport.administracionBD.ContractBD.TBRegistro.Nombre_tabla_registros;
import static com.example.easysport.administracionBD.ContractBD.TBRegistro.contenido;
import static com.example.easysport.administracionBD.ContractBD.TBRegistro.fecha_registro;
import static com.example.easysport.administracionBD.ContractBD.TBRegistro.hora_registro;

public class ListadoRegistros extends AppCompatActivity implements AdapterView.OnItemClickListener {


    LinearLayout listaRegistros;
    ListView lista;
    ArrayAdapter adaptador;
    ArrayList<String> listaStrings = new ArrayList<>();
    String eleccion,date,time,content,SQL;
    TextView title;
    Cursor cursor;
    //Prueba
    ArrayList<String> intento = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_registros);

        listaRegistros = findViewById(R.id.listadoRegistros);
        lista = findViewById(R.id.lista_registros);
        title = findViewById(R.id.lblTituloRegistros);

        //Asignacion de fuentes
        Typeface titulo = Typeface.createFromAsset(getAssets(), "baloo2semibold.ttf");
        title.setTypeface(titulo);

        //Buscamos los registros guardados
        crearLista();
        lista.setOnItemClickListener(this);
    }

    //Metodo para volver al menu principal
    public void btHome(View v) {
        Intent aMenu = new Intent(this, Menu.class);
        startActivity(aMenu);
    }

    //Este metodo busca los registros existentes para poder crear la lista
    private void buscarRegistros() {
        listaStrings.clear();

        AdminSQLiteOpenHelper conn = new AdminSQLiteOpenHelper(this, null);
        SQLiteDatabase DB = conn.getReadableDatabase();
        String SQL = "SELECT " + ContractBD.TBRegistro.fecha_registro + "," + ContractBD.TBRegistro.hora_registro + " FROM " + ContractBD.TBRegistro.Nombre_tabla_registros;

        String agregado;

        Cursor cursor = DB.rawQuery(SQL, null);
        if(cursor.getCount()>=2) {
            if (cursor.moveToFirst()) {
                while (cursor.moveToNext()) {
                    agregado = "Fecha: " + cursor.getString(0) + ". Hora: " + cursor.getString(1);
                    listaStrings.add(agregado);
                }
                cursor.close();
                DB.close();
                Toast.makeText(this, "Mostrando Lista. Encontrados " + listaStrings.size() + " elementos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No hay registros que mostrar", Toast.LENGTH_SHORT).show();
                cursor.close();
                DB.close();
            }
        }else {
            Toast.makeText(this, "No hay elementos que mostrar", Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo que crea la lista
    private void crearLista() {
        buscarRegistros();
        try {
            if (!listaStrings.isEmpty()) {
                adaptador = new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1, listaStrings);
                lista.setAdapter(adaptador);
            }
        } catch (Exception e) {
            Toast.makeText(this, "No se ha podido crear la lista", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    //En este metodo se nos permite elegir un elemento de la lista y ver sus detalles
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //Recogemos el registro que vamos a buscar:
             eleccion = lista.getItemAtPosition(position).toString();

            //Separamos los elementos (Funciona)

           String[] elementos;
        elementos = eleccion.split("\\.");
        String[] dato_fecha = elementos[0].split(":");
           String[] dato_hora = elementos[1].split(":");

            date = dato_fecha[1];
            time = dato_hora[1];


            busqueda();
    }

    private void busqueda() {
        //Conexion con BD
        AdminSQLiteOpenHelper conn = new AdminSQLiteOpenHelper(this, null);
        SQLiteDatabase BD = conn.getReadableDatabase();

        //Preparacion del cursor


              SQL = "SELECT " + fecha_registro + " ,"+hora_registro+" ," + contenido +
              " FROM " + Nombre_tabla_registros +
              " WHERE " + fecha_registro + " = '" + date.trim() + "' AND "+hora_registro+" ='"+time.trim()+"'" ;


       /* String SQL_pruebas = "SELECT "+fecha_registro+","+hora_registro+","+contenido+" FROM "+Nombre_tabla_registros+
                " WHERE "+fecha_registro+" = '"+date.trim()+"' AND "+hora_registro+"= "+time.trim() ;

        */
        cursor = BD.rawQuery(SQL, null);
        //Busqueda en la BD
        //Hay registro
        if (cursor.moveToFirst()) {
            content = cursor.getString(2);

            Intent datosRegistro = new Intent(this, DetallesRegistro.class);
            datosRegistro.putExtra("fecha", date);
            datosRegistro.putExtra("hora", time);
            datosRegistro.putExtra("contenido", content);

            cursor.close();
            BD.close();
            startActivity(datosRegistro);


        } else {
            //Toast.makeText(this, "No ", Toast.LENGTH_SHORT).show();            cursor.close();
            BD.close();
        }
    }
}


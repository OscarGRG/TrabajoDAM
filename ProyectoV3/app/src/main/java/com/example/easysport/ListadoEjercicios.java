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
import android.widget.Button;
import android.widget.ListView;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.easysport.administracionBD.AdminSQLiteOpenHelper;
import com.example.easysport.administracionBD.ContractBD;

import java.util.ArrayList;

public class ListadoEjercicios extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView tituloEjercicios;
    ListView listaEjercicios;
    String[] zonas_Spinner= {"Gluteos","Cintura y Cadera","Piernas","Abdomen"};
    Spinner spbusqueda;
    Button bt_busqueda;
    ArrayAdapter<String> adapterSpinner, adapterLista;
    ArrayList<String> ListaDatos = new ArrayList<>();
    String eleccion = "(fallo)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_ejercicios);

        //Conexion con la interfaz
        conexionInterfaz();

        //Metodos de la clase
        fuenteTitulo();
        SpinerFuncional();
        ListaOperativa();

        //Recogida de datos del elemento seleccionado de la lista
        listaEjercicios.setOnItemClickListener(this);
    }

    //Recoge los elementos del Ejercicio seleccionado, y los pasa a la pantalla de los detalles
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            eleccion =listaEjercicios.getItemAtPosition(position).toString() ;
            String SQL = "SELECT * FROM TABLA_EJERCICIO WHERE nombre_ejercicio ='" + eleccion + "'";

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, null);
            SQLiteDatabase BD = admin.getReadableDatabase();
            Cursor cursor = BD.rawQuery(SQL, null);
            cursor.moveToFirst();
            String nEjercicio =cursor.getString(0) ;
            String realizacionEjercicio = cursor.getString(1);
            String material_usado = cursor.getString(2);
            String zona_afectada = cursor.getString(3);
            String tabla_ejercicios = cursor.getString(4);

            cursor.close();
            BD.close();

            Intent aDetalleEjercicio = new Intent(this, DetallesEjercicio.class);

            aDetalleEjercicio.putExtra("nombre_ejercicio", nEjercicio);
            aDetalleEjercicio.putExtra("realizacion",realizacionEjercicio);
            aDetalleEjercicio.putExtra("material",material_usado);
            aDetalleEjercicio.putExtra("zona",zona_afectada);
            aDetalleEjercicio.putExtra("tabla_ejercicios",tabla_ejercicios);

            startActivity(aDetalleEjercicio);


    }

    //Metodo de conexion a la interfaz
    private void conexionInterfaz(){
        listaEjercicios = findViewById(R.id.lista);
        spbusqueda = findViewById(R.id.buscador);
        tituloEjercicios = findViewById(R.id.lblRegistroNuevo);
        bt_busqueda = findViewById(R.id.bt_buscar_ejercicios);
    }
    //Buscamos los ejercicios correspondientes de la base de datos
    public void btBuscarEjercicio(View v) {

            //Primero dejamos vacio el arrayList<>
            ListaDatos.clear();

            //Conexion con la BD
            AdminSQLiteOpenHelper conn = new AdminSQLiteOpenHelper(this,null);
            SQLiteDatabase DB = conn.getReadableDatabase();

            //Strings necesarios
            String categoria = spbusqueda.getSelectedItem().toString();//Aqui recoge el valor elegido en el spinner
            String elemento;

            //Sentencia SQL
            String SQL ="SELECT "+ContractBD.TBEjercicios.PK_nombre_ejercicio+" FROM "+ContractBD.TBEjercicios.Nombre_tabla_ejercicio+
                    " WHERE "+ContractBD.TBEjercicios.zona+"='"+categoria+"'";

            //Cursor para las busquedas
            Cursor conexion = DB.rawQuery(SQL,null);

            //Cargar elementos en la lista
            if (conexion.isBeforeFirst()){
                while(conexion.moveToNext()) {
                    elemento = conexion.getString(0);

                    //Añadir elementos a la lista
                    ListaDatos.add(elemento);

                }

                //Generacion de la lista de elementos
                ListaOperativa();

                //El cierre de la BD
                conexion.close();
                DB.close();

            }else {
                Toast.makeText(this, "Busqueda vacia. Prueba con otro campo", Toast.LENGTH_SHORT).show();

                //Cierres
                conexion.close();
                DB.close();
        }

    }

    //Asignacion de fuente al titulo
    private void fuenteTitulo(){
        Typeface titulo = Typeface.createFromAsset(getAssets(),"baloo2semibold.ttf");
        tituloEjercicios.setTypeface(titulo);
        bt_busqueda.setTypeface(titulo);
    }

    //Establecimiento del Spinner
    private void SpinerFuncional(){

        adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,zonas_Spinner);
        spbusqueda.setAdapter(adapterSpinner);
    }

    //Construccion de la lista
    private void ListaOperativa(){
        adapterLista = new ArrayAdapter<>(this,android.R.layout.simple_expandable_list_item_1,ListaDatos);
        listaEjercicios.setAdapter(adapterLista);
    }

    //Boton para volver al menu principal
    public void btHome(View v){
        Intent aMenu = new Intent(this, Menu.class );
        startActivity(aMenu);
    }

}

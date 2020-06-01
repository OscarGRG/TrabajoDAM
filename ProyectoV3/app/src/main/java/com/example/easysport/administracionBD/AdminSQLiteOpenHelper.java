package com.example.easysport.administracionBD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.easysport.administracionBD.ContractBD.Crear_tabla_ejercicio;
import static com.example.easysport.administracionBD.ContractBD.Crear_tabla_materiales;
import static com.example.easysport.administracionBD.ContractBD.Crear_tabla_registros;
import static com.example.easysport.administracionBD.ContractBD.Crear_tabla_tablas_ejercicios;
import static com.example.easysport.administracionBD.ContractBD.Crear_tabla_zona_trabajada;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

private static final String nombreBD = "BDEasySport.db";
private static int versionBD = 1 ;

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        super(context, nombreBD, factory, versionBD);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creacion de las tablas de datos
        db.execSQL(Crear_tabla_registros);
        db.execSQL(Crear_tabla_zona_trabajada);
        db.execSQL(Crear_tabla_tablas_ejercicios);
        db.execSQL(Crear_tabla_materiales);
        db.execSQL(Crear_tabla_ejercicio);

        //Introduccion de datos de las zonas trabajadas
        db.execSQL("INSERT INTO ZONA_TRABAJADA VALUES('Gluteos',1)");
        db.execSQL("INSERT INTO ZONA_TRABAJADA VALUES('Cintura y Cadera',2)");
        db.execSQL("INSERT INTO ZONA_TRABAJADA VALUES('Piernas',3)");
        db.execSQL("INSERT INTO ZONA_TRABAJADA VALUES('Abdomen',4)");

        //Introduccion de datos de los materiales
        db.execSQL("INSERT INTO TABLA_MATERIAL VALUES('Peso','3kg','Compacta')");
        db.execSQL("INSERT INTO TABLA_MATERIAL VALUES('Esterilla','Indiferente','Plana')");
        db.execSQL("INSERT INTO TABLA_MATERIAL VALUES('Ninguno','0kg','Inexistente')");
        db.execSQL("INSERT INTO TABLA_MATERIAL VALUES('Aro','350g','Circular')");

        //Introduccion de la tabla de las tablas de ejercicios
        db.execSQL ("INSERT INTO TABLAS_EJERCICIOS VALUES('Rutina_1',1)");

        //Introduccion de la tabla de Registro
        db.execSQL("INSERT INTO REGISTROS VALUES('dd/mm/aaaa','hoymi','texto de prueba')");

        //Introducción de datos de los ejercicios
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Laterales','Acuestate de lado sobre la esterilla y eleva tu pierna extendida y con los dedos de los" +
                " pies estirados hacia fuera, en angulo de 30º. Regresa a la posición inicial. 3 series de 20 repeticiones por pierna','Esterilla','Gluteos'," +
                "'Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Laterales Rotatorios','Acuestate de lado sobre la esterilla o toalla, eleva la pierna extendida y haz" +
                " pequeños circulos en el aire hacia uno y otro lado. Aguanta 20 segundos y descansa 10. 3 repeticiones por pierna. Puedes favorecer la " +
                "tonificacion muscular utilizando pesas','Esterilla','Gluteos','Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Sentadillas','Ponte de pie, estira los brazos y a continuacion dobla las rodillas y baja los gluteos " +
                "hasta donde la resistencia lo permita. Inicia con 3 series de 10 repeticiones. Según aumentes la resistencia, aumenta poco a poco el numero " +
                "de repeticiones por serie hasta llegar a las 20. Puedes aumentar los efectos sujetando peso con ambas manos para aumentar la resistencia'," +
                "'Peso','Gluteos','Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Elevaciones','Boca arriba, con las piernas flexionadas, eleva la cadera apretando los gluteos y coloca " +
                "las manos lo mas cerca posible de los tobillos, hasta que el cuerpo quede elevado, pero sin cargar la espalda. Se debe notar una leve presion" +
                " en la cadera y los gluteos. Inicia manteniendo cada elevación 10 segundos, y luego ve aumentando el tiempo en sesiones posteriores. 4" +
                " repeticiones','Ninguno','Gluteos','Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Torsiones','Separa las piernas ligeramente y gira el tronco desde la cintura lo máximo posible, primero" +
                " hacia la derecha y luego hacia la izquierda. Realiza 20 con las manos en las caderas, 20 con las manos en la nuca y 20 con los brazos en " +
                "cruz','Ninguno','Cintura y Cadera','Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Bailar el aro','Similar a las torsiones, permite realizar un giro completo de la cintura. Empieza" +
                " tratando de mantener el aro en movimiento 1 minuto, empezando por 3 series. Una vez vayas teniendo práctica, podrás ir aumentando el tiempo'," +
                "'Aro','Cintura y Cadera','Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Saltos cruzados','De pie con las piernas abiertas hacia los lados, empieza a dar saltos cruzando las" +
                " piernas del lado contrario, regresa a la posición original y cruza al reves. Inicia con 4 series de 25 o 30 rebotes','Ninguno','Piernas'," +
                "'Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Bicicleta','Recostado sobre una superficie plana y procurando tener la espalda bien pegada a la " +
                "esterilla, flexiona ambas piernas hasta alcanzar un angulo de 45 grados, y comienza a pedalear como si usaras una bicicleta. Este ejercicio" +
                " tambien ayudara con los abdominales. Sera mas sencillo si puedes agarrarte a algo pesado por detras con las manos','Ninguno','Piernas'," +
                "'Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Tijeras Verticales','Apoya la espalda en el suelo y usa las manos para apoyarte debajo de la espalda," +
                " por la parte baja. Eleva las piernas aproximadamente 15 cm en el aire y sube y baja las piernas secuencialmente. Manten el movimiento 20" +
                " segundos y descansa 10. Repite 4 veces','Ninguno','Abdomen','Rutina_1')");
        db.execSQL("INSERT INTO TABLA_EJERCICIO VALUES('Plancha','Existen numerosas variantes (lateral, plancha con flexión, a una mano, con una pierna " +
                "levantada). La versión normal empieza en el suelo, aprieta el estómago y procede a soportar tu peso sobre los codos y las puntas de los " +
                "dedos de los pies durante 15 – 20 Segundos. La espalda debe estar completamente recta. También puede hacerse con los brazos totalmente " +
                "extendidos. Aumenta gradualmente el tiempo cada día que pasa','Ninguno','Abdomen','Rutina_1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS TABLA_MATERIAL");
        db.execSQL("DROP TABLE IF EXISTS TABLAS_EJERCICIOS");
        db.execSQL("DROP TABLE IF EXISTS ZONA_TRABAJADA");
        db.execSQL("DROP TABLE IF EXISTS TABLA_EJERCICIO");
        db.execSQL("DROP TABLE IF EXISTS REGISTRO");
        onCreate(db);
    }
}

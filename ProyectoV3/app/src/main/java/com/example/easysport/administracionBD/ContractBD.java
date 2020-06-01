package com.example.easysport.administracionBD;
import android.provider.BaseColumns;

public class ContractBD {

    private ContractBD() {
    }

    //Tabla REGISTRO
    public static class TBRegistro implements BaseColumns {
        public static final String Nombre_tabla_registros = "REGISTROS";
        public static final String fecha_registro = "fecha_registro";
        public static final String hora_registro = "hora_registro";
        public static final String contenido = "contenido";
    }

    //Tabla TABLA_MATERIAL
    public static class TBMateriales implements BaseColumns {
        public static final String Nombre_tabla_materiales = "TABLA_MATERIAL";
        public static final String PK_nombre_material = "nombre_material";
        public static final String peso = "peso";
        public static final String forma = "forma";
    }

    //Tabla ZONA_TRABAJADA
    public static class TBZonas implements BaseColumns {
        public static final String Nombre_tabla_zona_trabajada = "ZONA_TRABAJADA";
        public static final String PK_nombre_zona = "Nombre_zona";
        public static final String id_zona = "id_zona";

    }

    //TABLA TABLAS_EJERCICIOS
    public static class TBTablas implements BaseColumns {
        public static final String Nombre_tabla_tablas_de_ejercicios = "TABLAS_EJERCICIOS";
        public static final String PK_nombre_tabla = "Nombre_tabla";
        public static final String id_tabla = "id_tabla";

    }

    //Tabla TABLA_EJERCICIO
    public static class TBEjercicios implements BaseColumns {
        public static final String Nombre_tabla_ejercicio = "TABLA_EJERCICIO";
        public static final String PK_nombre_ejercicio = "nombre_ejercicio";
        public static final String realizacion = "realizacion";
        public static final String material_usado = "material_usado";
        public static final String zona = "zona";
        public static final String tabla = "tabla_perteneciente";
    }

    //Creacion de las tablas de la BD
    public static final String Crear_tabla_registros = "CREATE TABLE " + ContractBD.TBRegistro.Nombre_tabla_registros + "(" + ContractBD.TBRegistro.fecha_registro + " varchar(50), " + ContractBD.TBRegistro.hora_registro + " varchar(50)," + ContractBD.TBRegistro.contenido + " text)";
    public static final String Crear_tabla_zona_trabajada = "CREATE TABLE " + ContractBD.TBZonas.Nombre_tabla_zona_trabajada + "(" + ContractBD.TBZonas.PK_nombre_zona + " varchar(20) primary key, " + ContractBD.TBZonas.id_zona + " integer)";
    public static final String Crear_tabla_tablas_ejercicios = "CREATE TABLE " + ContractBD.TBTablas.Nombre_tabla_tablas_de_ejercicios + "(" + ContractBD.TBTablas.PK_nombre_tabla + " varchar(20) primary key, " + ContractBD.TBTablas.id_tabla + " integer)";
    public static final String Crear_tabla_materiales = "CREATE TABLE " + ContractBD.TBMateriales.Nombre_tabla_materiales + "(" + ContractBD.TBMateriales.PK_nombre_material + " varchar(20) primary key, " + ContractBD.TBMateriales.peso + " varchar(10), " + ContractBD.TBMateriales.forma + " varchar(20))";
    public static final String Crear_tabla_ejercicio = "CREATE TABLE " + ContractBD.TBEjercicios.Nombre_tabla_ejercicio + "(" + ContractBD.TBEjercicios.PK_nombre_ejercicio + " varchar(30) primary key," + ContractBD.TBEjercicios.realizacion + " text," + ContractBD.TBEjercicios.material_usado + " varchar(20)," + ContractBD.TBEjercicios.zona + " varchar(20)," + ContractBD.TBEjercicios.tabla + " varchar(20)," +
            "foreign key(" + ContractBD.TBEjercicios.material_usado + ") references " + ContractBD.TBMateriales.Nombre_tabla_materiales + "(" + ContractBD.TBMateriales.PK_nombre_material + ")," +
            "foreign key(" + ContractBD.TBEjercicios.zona + ") references " + ContractBD.TBZonas.Nombre_tabla_zona_trabajada + "(" + ContractBD.TBZonas.PK_nombre_zona + ")," +
            "foreign key (" + ContractBD.TBEjercicios.tabla + ") references " + ContractBD.TBTablas.Nombre_tabla_tablas_de_ejercicios + "(" + ContractBD.TBTablas.PK_nombre_tabla + "))";

}


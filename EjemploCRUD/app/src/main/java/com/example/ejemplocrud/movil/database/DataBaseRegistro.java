package com.example.ejemplocrud.movil.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseRegistro extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "registro.db";
    public static final String TABLE_DATOS = "t_contactos";
    public static final String TABLE_ALUMNO = "alm_alumno";
    public static final String TABLE_MATERIA = "mat_materia";
    public static final String TABLE_GRADO = "grd_grado";
    public static final String TABLE_MATERIAXGRADO = "mxg_materiasxgrado";

    public DataBaseRegistro(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DATOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre VARCHAR NOT NULL," +
                "grado VARCHAR NOT NULL," +
                "materias VARCHAR)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_ALUMNO + "(" +
                "alm_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "alm_codigo NVARCHAR NOT NULL," +
                "alm_nombre NVARCHAR NOT NULL," +
                "alm_edad INTEGER NOT NULL," +
                "alm_sexo NVARCHAR NOT NULL," +
                "alm_id_grd INTEGER NOT NULL," +
                "alm_observacion NVARCHAR NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MATERIA + "(" +
                "mat_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "mat_nombre NVARCHAR NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_GRADO + "(" +
                "grd_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "grd_nombre NVARCHAR NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_MATERIAXGRADO + "(" +
                "mxg_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "mxg_id_grd NVARCHAR NOT NULL, " +
                "mxg_id_mat)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_DATOS);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_ALUMNO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_GRADO);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MATERIA);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_MATERIAXGRADO);

        onCreate(sqLiteDatabase);
    }
}
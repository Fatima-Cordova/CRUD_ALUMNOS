package com.example.ejemplocrud.movil.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;


import com.example.ejemplocrud.movil.models.Alumno;

import java.util.ArrayList;
import java.util.List;

public class DataBaseAlumnos extends DataBaseRegistro {

    Context context;

    public DataBaseAlumnos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarAlumno(String nombre, String grado, String materias) {

        long id = 0;

        try {
            DataBaseRegistro dbHelper = new DataBaseRegistro(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("grado", grado);
            values.put("materias", materias);

            id = db.insert(TABLE_DATOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public List<String> getAllResults(){
        List<String> labels = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATOS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public ArrayList<Alumno> mostrarAlumnos() {

        DataBaseRegistro dataBaseRegistro = new DataBaseRegistro(context);
        SQLiteDatabase db = dataBaseRegistro.getWritableDatabase();

        ArrayList<Alumno> listaAlumno = new ArrayList<>();
        Alumno alumno;
        Cursor cursorAlumno;

        cursorAlumno = db.rawQuery("SELECT * FROM " + TABLE_DATOS + " ORDER BY nombre ASC", null);

        if (cursorAlumno.moveToFirst()) {
            do {
                alumno = new Alumno();
                alumno.setId(cursorAlumno.getInt(0));
                alumno.setNombre(cursorAlumno.getString(1));
                alumno.setGrado(cursorAlumno.getString(2));
                alumno.setMaterias(cursorAlumno.getString(3));
                listaAlumno.add(alumno);
            } while (cursorAlumno.moveToNext());
        }

        cursorAlumno.close();

        return listaAlumno;
    }

    public Alumno verAlumno(int id) {

        DataBaseRegistro dataBaseRegistro = new DataBaseRegistro(context);
        SQLiteDatabase db = dataBaseRegistro.getWritableDatabase();

        Alumno alumno = null;
        Cursor cursorAlumno;

        cursorAlumno = db.rawQuery("SELECT * FROM " + TABLE_DATOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorAlumno.moveToFirst()) {
            alumno = new Alumno();
            alumno.setId(cursorAlumno.getInt(0));
            alumno.setNombre(cursorAlumno.getString(1));
            alumno.setGrado(cursorAlumno.getString(2));
            alumno.setMaterias(cursorAlumno.getString(3));
        }

        cursorAlumno.close();

        return alumno;
    }

    public boolean editarAlumno(int id, String nombre, String grado, String materias) {

        boolean correcto = false;

        DataBaseRegistro dataBaseRegistro = new DataBaseRegistro(context);
        SQLiteDatabase db = dataBaseRegistro.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_DATOS + " SET nombre = '" + nombre + "', grado = '" + grado + "', materias = '" + materias + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarAlumno(int id) {

        boolean correcto = false;

        DataBaseRegistro dataBaseRegistro = new DataBaseRegistro(context);
        SQLiteDatabase db = dataBaseRegistro.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_DATOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}

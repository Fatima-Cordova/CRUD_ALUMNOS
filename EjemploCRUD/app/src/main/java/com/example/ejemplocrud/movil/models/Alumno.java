package com.example.ejemplocrud.movil.models;

public class Alumno {

    private int alm_id;
    private String alm_nombre;
    private String mat_nombre;
    private String grd_nombre;

    public int getId() {
        return alm_id;
    }

    public void setId(int id) {
        this.alm_id = id;
    }

    public String getNombre() {
        return alm_nombre;
    }

    public void setNombre(String nombre) {
        this.alm_nombre = nombre;
    }

    public String getGrado() {
        return mat_nombre;
    }

    public void setGrado(String grado) {
        this.mat_nombre = grado;
    }

    public String getMaterias() {
        return grd_nombre;
    }

    public void setMaterias(String materia) {
        this.grd_nombre = materia;
    }
}

package com.example.ejemplocrud.movil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.ejemplocrud.R;
import com.example.ejemplocrud.movil.adapter.AdapterRecyclerAlumnos;
import com.example.ejemplocrud.movil.database.DataBaseAlumnos;
import com.example.ejemplocrud.movil.models.Alumno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    RecyclerView listaContactos;
    ArrayList<Alumno> listaArrayContactos;
    FloatingActionButton fabNuevo;
    AdapterRecyclerAlumnos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        spinner.setOnItemSelectedListener(MainActivity.this);

        loadSpinnerData();

        DataBaseAlumnos dataBaseAlumnos = new DataBaseAlumnos(MainActivity.this);

        listaArrayContactos = new ArrayList<>();

        adapter = new AdapterRecyclerAlumnos(dataBaseAlumnos.mostrarAlumnos());
        listaContactos.setAdapter(adapter);
    }

    private void loadSpinnerData() {
        DataBaseAlumnos db = new DataBaseAlumnos(getApplicationContext());
        List<String> labels = db.getAllResults();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
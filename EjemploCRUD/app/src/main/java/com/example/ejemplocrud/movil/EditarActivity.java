package com.example.ejemplocrud.movil;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ejemplocrud.R;
import com.example.ejemplocrud.movil.database.DataBaseAlumnos;
import com.example.ejemplocrud.movil.models.Alumno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtGrado, txtMaterias;
    Button btnGuardar;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Alumno alumno;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtGrado = findViewById(R.id.txtGrado);
        txtMaterias = findViewById(R.id.txtMat);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.btnEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.btnEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DataBaseAlumnos dataBaseAlumnos = new DataBaseAlumnos(EditarActivity.this);
        alumno = dataBaseAlumnos.verAlumno(id);

        if (alumno != null) {
            txtNombre.setText(alumno.getNombre());
            txtGrado.setText(alumno.getGrado());
            txtMaterias.setText(alumno.getMaterias());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtGrado.getText().toString().equals("")) {
                    correcto = dataBaseAlumnos.editarAlumno(id, txtNombre.getText().toString(), txtGrado.getText().toString(), txtMaterias.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
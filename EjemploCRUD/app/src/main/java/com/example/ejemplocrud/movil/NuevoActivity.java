package com.example.ejemplocrud.movil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.ejemplocrud.R;
import com.example.ejemplocrud.movil.database.DataBaseAlumnos;

public class NuevoActivity extends AppCompatActivity {

    private EditText txtNombre, txtGrado, txtMaterias;
    private Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtGrado = findViewById(R.id.txtGrado);
        txtMaterias = findViewById(R.id.txtMat);
        btnGuarda = findViewById(R.id.btnGuardar);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtGrado.getText().toString().equals("")) {

                    DataBaseAlumnos dataBaseAlumnos = new DataBaseAlumnos(NuevoActivity.this);
                    long id = dataBaseAlumnos.insertarAlumno(txtNombre.getText().toString(), txtGrado.getText().toString(), txtMaterias.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtGrado.setText("");
        txtMaterias.setText("");
    }
}
package com.example.ejemplocrud.movil.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejemplocrud.R;
import com.example.ejemplocrud.movil.VerActivity;
import com.example.ejemplocrud.movil.models.Alumno;

import java.util.ArrayList;

public class AdapterRecyclerAlumnos extends RecyclerView.Adapter<AdapterRecyclerAlumnos.AlumnoViewHolder> {

    ArrayList<Alumno> listaAlumno;
    ArrayList<Alumno> listaOriginal;

    public AdapterRecyclerAlumnos(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumno = listaAlumnos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaAlumnos);
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_items_alumno, null, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        holder.viewNombre.setText(listaAlumno.get(position).getNombre());
        holder.viewGrado.setText(listaAlumno.get(position).getGrado());
        holder.viewMaterias.setText(listaAlumno.get(position).getMaterias());
    }


    @Override
    public int getItemCount() {
        return listaAlumno.size();
    }

    public class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewGrado, viewMaterias;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewGrado = itemView.findViewById(R.id.viewGrado);
            viewMaterias = itemView.findViewById(R.id.viewMateria);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaAlumno.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}

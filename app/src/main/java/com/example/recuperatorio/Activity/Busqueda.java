package com.example.recuperatorio.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recuperatorio.AccesoDatos.BuscarEvento;
import com.example.recuperatorio.AccesoDatos.ObtenerEventos;
import com.example.recuperatorio.AccesoDatos.ObtenerLocalidades;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.Interface.Comunicacion;
import com.example.recuperatorio.R;

public class Busqueda extends AppCompatActivity implements Comunicacion {

    private TextView Texto;
    private Evento ev;
    private ListView lvEventos;
    private Button btnAceptar;
    private ProgressBar pb;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        btnAceptar = (Button) findViewById(R.id.btn_Aceptar);
        Texto = findViewById(R.id.txt_Busqueda);
        lvEventos = (ListView) this.findViewById(R.id.lvEventos);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                busqueda(view);
            }
        });
        pb = findViewById(R.id.progressBar1);
    }

    public void busqueda(View view)
    {
        btnAceptar.setEnabled(false);
        pb.setVisibility(view.VISIBLE);
        v = view;
        if(Texto.getText().length() == 0)
        {
            ev = new Evento();
            ev.setTitulo(Texto.getText().toString());
            ObtenerEventos task = new ObtenerEventos(lvEventos, ev, this, Busqueda.this);
            task.execute();
        }
        else
        {
            BuscarEvento task = new BuscarEvento(this , Texto.getText().toString(), Busqueda.this);
            task.execute();
        }
    }

    @Override
    public void showMessage(String msg) {
        btnAceptar.setEnabled(true);
        pb.setVisibility(v.INVISIBLE);
        Toast.makeText(this , msg , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad, Usuario U) {
    }
}

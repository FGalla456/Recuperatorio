package com.example.recuperatorio.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recuperatorio.AccesoDatos.BuscarEvento;
import com.example.recuperatorio.AccesoDatos.ObtenerEventos;
import com.example.recuperatorio.AccesoDatos.ObtenerLocalidades;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.R;

public class Busqueda extends AppCompatActivity {

    private TextView Texto;
    private Evento ev;
    private ListView lvEventos;
    private Button btnAceptar;

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
                busqueda();
            }
        });
    }

    public void busqueda()
    {
        if(Texto.getText().length() == 0)
        {
            ev = new Evento();
            ev.setTitulo(Texto.getText().toString());
            ObtenerEventos task = new ObtenerEventos(lvEventos, ev, this);
            task.execute();
        }
        else
        {
            BuscarEvento task = new BuscarEvento(this , Texto.getText().toString());
            task.execute();
        }
    }

}

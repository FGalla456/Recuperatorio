package com.example.recuperatorio.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.BuscarEvento;
import com.example.recuperatorio.AccesoDatos.ObtenerEventos;
import com.example.recuperatorio.Adapter.EventoAdapter;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.Interface.Comunicacion;
import com.example.recuperatorio.Interface.Registrarse;
import com.example.recuperatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentEventFragment extends Fragment implements Comunicacion {

    private View view;
    private TextView Texto;
    private Evento ev;
    private ListView lvEventos;
    private Button btnAceptar;
    private ProgressBar pb;

    public static FragmentEventFragment newInstance() {
        return new FragmentEventFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_busqueda,container,false);
        btnAceptar = (Button) view.findViewById(R.id.btn_Aceptar);
        Texto = view.findViewById(R.id.txt_Busqueda);
        lvEventos = (ListView) view.findViewById(R.id.lvEventos);
        pb = view.findViewById(R.id.progressBar1);
        btnAceptar.setEnabled(true);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                busqueda();
            }
        });
        return view;
    }

    public void busqueda()
    {
        btnAceptar.setEnabled(false);
        pb.setVisibility(view.VISIBLE);
        if(Texto.getText().length() == 0)
        {
            ev = new Evento();
            ev.setTitulo(Texto.getText().toString());
            ObtenerEventos task = new ObtenerEventos(lvEventos, ev, view.getContext(), FragmentEventFragment.this);
            task.execute();
        }
        else
        {
            BuscarEvento task = new BuscarEvento(view.getContext() , Texto.getText().toString(),FragmentEventFragment.this);
            task.execute();
            Texto.setText("");
        }
    }

    @Override
    public void showMessage(String msg) {
        btnAceptar.setEnabled(true);
        pb.setVisibility(view.INVISIBLE);
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad, Usuario U) {

    }

}
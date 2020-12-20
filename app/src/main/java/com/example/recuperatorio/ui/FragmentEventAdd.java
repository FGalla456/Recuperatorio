package com.example.recuperatorio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.InsertarEvento;
import com.example.recuperatorio.AccesoDatos.ObtenerCategorias;
import com.example.recuperatorio.AccesoDatos.ObtenerEventos;
import com.example.recuperatorio.Adapter.EventoAdapter;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.R;
import com.example.recuperatorio.ui.FragmentEventAddFragment;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentEventAdd extends Fragment {

    private View view;
    private TextView titulo;
    private TextView descripcion;
    private EditText fecha;
    private TextView hora;
    private TextView categoria;
    private Evento event;
    private String stringDate = "";
    private Button btnAgregar;
    private Spinner categorias;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event_add_activity,container,false);
        titulo = view.findViewById(R.id.titulo);
        descripcion = view.findViewById(R.id.descripcion);
        fecha = view.findViewById(R.id.fecha);
        hora = view.findViewById(R.id.hora);
        btnAgregar = view.findViewById(R.id.add_event);
        categorias = view.findViewById(R.id.categoria);
        ObtenerCategorias task = new ObtenerCategorias(categorias, view.getContext());
        task.execute();

        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addEvent();
            }
        });

        fecha.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2020:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                        stringDate = String.format("%02d%02d%02d", year, mon, day);
                        stringDate = String.format("%s-%s-%s" , stringDate.substring(0, 4),
                                stringDate.substring(4, 6),
                                stringDate.substring(6, 8));
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    fecha.setText(current);
                    fecha.setSelection(sel < current.length() ? sel : current.length());

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }

    public void addEvent(){
        if(titulo.getText().length() > 0 &&
           descripcion.getText().length() > 0 &&
           fecha.getText().length() > 0 &&
           hora.getText().length() > 0)
        {
            event = new Evento();
            event.setTitulo(titulo.getText().toString());
            event.setDescription(descripcion.getText().toString());
            event.setFecha(stringDate.replace('/','-') );
            event.setHora(hora.getText().toString());
            event.setIdCategoria(1);
            InsertarEvento task = new InsertarEvento(event, view.getContext());
            task.execute();
        }
    }
}
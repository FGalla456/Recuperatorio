package com.example.recuperatorio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.Dominio.Localidad;
import com.example.recuperatorio.R;

import java.util.List;

public class AdapterLocalidad extends ArrayAdapter<Localidad>{

    public AdapterLocalidad(Context context, Spinner localidades){
        super(context, R.layout.activity_register, (List<Localidad>) localidades);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.activity_register, null);

        Spinner localidades = (Spinner) item.findViewById(R.id.cbLocalidad);

        //localidades.set(getItem(position).getNombre()+"");

        return item;
    }
}

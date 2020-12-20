package com.example.recuperatorio.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.Dominio.Localidad;
import com.example.recuperatorio.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterLocalidad extends ArrayAdapter<Localidad>{

    private Activity Context;
    private ArrayList<Localidad> l;

    public AdapterLocalidad(Activity context, int resource ,ArrayList localidades){
        super(context, R.layout.activity_register, localidades);
        this.Context = context;
        this.l = localidades;
    }

    public View cargaSpinner(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        row  = inflater.inflate(R.layout.dropdown_value, null);

        Localidad loc = l.get(position);

        if(loc != null)
        {
            TextView drinkName = (TextView) row.findViewById(R.id.item_value);

            if(drinkName != null){
                drinkName.setText(loc.getNombre());
            }

        }

        return row;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {   // This view starts when we click the spinner.
        return cargaSpinner(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return cargaSpinner(position, convertView, parent);
    }
}

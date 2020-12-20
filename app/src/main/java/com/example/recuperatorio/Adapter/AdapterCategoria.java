package com.example.recuperatorio.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.recuperatorio.Dominio.Categoria;
import com.example.recuperatorio.R;

import java.util.ArrayList;

public class AdapterCategoria extends ArrayAdapter<Categoria> {

    private Activity Context;
    private ArrayList<Categoria> c;

    public AdapterCategoria(Activity context, int resource ,ArrayList Categorias){
        super(context, R.layout.fragment_event_add_activity, Categorias);
        this.Context = context;
        this.c = Categorias;
    }

    public View cargaSpinner(int position, View convertView, ViewGroup parent){
        View row = convertView;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        row  = inflater.inflate(R.layout.dropdown_value, null);

        Categoria cat = c.get(position);

        if(cat != null)
        {

            TextView drinkName = (TextView) row.findViewById(R.id.item_value);

            if(drinkName != null){
                drinkName.setText(cat.getCategoria());
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

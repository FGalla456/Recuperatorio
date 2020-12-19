package com.example.recuperatorio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.R;

import java.util.ArrayList;

public class EventoAdapter extends BaseAdapter {
    private ArrayList<Evento> eventos;
    private Context context;

    public EventoAdapter(Context context, ArrayList<Evento> eventos) {
        this.context = context;
        this.eventos = eventos;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Evento getItem(int position) {
        return eventos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = convertView;
        if (convertView == null){
            view = inflater.inflate(R.layout.card_template, null);
        }

        TextView titulo = (TextView) view.findViewById(R.id.txt_titulo);
        TextView descripcion = (TextView) view.findViewById(R.id.txt_descripcion);
        TextView fecha = (TextView) view.findViewById(R.id.txt_fecha);
        TextView hora = (TextView) view.findViewById(R.id.txt_hora);
        TextView categoria = (TextView) view.findViewById(R.id.txt_categoria);
        titulo.setText(getItem(position).getTitulo());
        descripcion.setText(getItem(position).getDescription());
        fecha.setText(getItem(position).getFecha());
        hora.setText(getItem(position).getHora());
        categoria.setText(getItem(position).getIdCategoria());


        return view;
    }
}

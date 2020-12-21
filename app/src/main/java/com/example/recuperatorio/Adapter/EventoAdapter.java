package com.example.recuperatorio.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.R;

public class EventoAdapter extends ArrayAdapter<Evento> {

    public EventoAdapter(Context context, List<Evento> eventos) {
        super(context, R.layout.card_template, eventos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.card_template, null);

        TextView tvtitulo = (TextView) item.findViewById(R.id.txt_titulo);
        TextView tvdescripcion = (TextView) item.findViewById(R.id.txt_descripcion);
        TextView tvfecha = (TextView) item.findViewById(R.id.txt_fecha);
        TextView tvhora = (TextView) item.findViewById(R.id.txt_hora);
        TextView tvcategoria = (TextView) item.findViewById(R.id.txt_categoria);

        tvtitulo.setText(getItem(position).getTitulo()+"");
        tvdescripcion.setText(getItem(position).getDescription()+"");
        tvfecha.setText(getItem(position).getFecha());
        tvhora.setText(getItem(position).getHora());
        tvcategoria.setText(getItem(position).getCat().getCategoria()+"");

        return item;
    }
}

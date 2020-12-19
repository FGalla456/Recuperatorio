package com.example.recuperatorio.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.R;

public class AdapterUsuario extends BaseAdapter {

    //private Usuario U;
    private ArrayList<Usuario> usuarios;
    private Context context;

    public AdapterUsuario(ArrayList<Usuario> users, Context context)
    {
        this.context = context;
        this.usuarios = users;
    }

    @Override
    public int getCount() {
        return this.usuarios.size();
    }

    @Override
    public Object getItem(int i) {
        return this.usuarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = convertView;
        if (convertView == null){
            view = inflater.inflate(R.layout.activity_log_in, null);
        }

        TextView tvCorreo = view.findViewById(R.id.txt_Correo);
        TextView tvContrasena =  view.findViewById(R.id.txt_Contrasena);

        tvCorreo.setText(getItem(i).toString());
        tvContrasena.setText(getItem(i).toString());

        return view;
    }
}

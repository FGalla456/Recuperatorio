package com.example.recuperatorio.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import com.example.recuperatorio.AccesoDatos.UsuarioDao;
import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.R;

import java.util.List;

public class AdapterUsuario extends ArrayAdapter<Usuario>  {

    //private Usuario U;
    private UsuarioDao ud;

    public AdapterUsuario(List<Usuario> u, Context context)
    {
        super(context, R.layout.activity_log_in,u);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.activity_log_in, null);

        TextView tvCorreo = (TextView) item.findViewById(R.id.lbl_Correo);
        TextView tvContrasena = (TextView) item.findViewById(R.id.lbl_Contrasena);

        tvCorreo.setText(getItem(position).getEmail()+"");
        tvContrasena.setText(getItem(position).getContrasena()+"");

        return item;
    }
}

package com.example.recuperatorio.Negocio;
import android.content.Context;
import android.widget.Spinner;

import com.example.recuperatorio.AccesoDatos.UsuarioDao;
import com.example.recuperatorio.Dominio.Usuario;

public class N_Usuario {

    private Usuario U;
    private UsuarioDao ud;

    public Usuario  BuscarUsuario(String Correo, String Contrasena , Context context)
    {
        ud = new UsuarioDao(Correo, Contrasena , context);
        U = ud.DevolverUsuario(Correo,Contrasena);
        return U;
    }

}

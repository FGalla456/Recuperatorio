package com.example.recuperatorio.Negocio;
import com.example.recuperatorio.AccesoDatos.UsuarioDao;
import com.example.recuperatorio.Dominio.Usuario;

public class N_Usuario {

    private Usuario U;
    private UsuarioDao ud;

    public Usuario  BuscarUsuario(String Correo, String Contrasena)
    {
        //U = ud.DevolverUsuario(Correo,Contrasena);
        return U;
    }

}

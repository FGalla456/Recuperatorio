package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Statement;

import com.example.recuperatorio.Dominio.Usuario;

import java.util.ArrayList;

public class InsertarUsuario extends AsyncTask<String, Void, String> {

    private Usuario user;
    private Context context;
    private DataDB db;

    public InsertarUsuario(Usuario u, Context ct)
    {
        user = u;
        context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        db = new DataDB();
        user.getNacimiento();
        String consultaSQL = "INSERT INTO usuarios (nombre, dni, nacimiento, email, id_localidad, contrasena) " +
                "VALUES ('"+user.getNombre()+"', "+user.getDni()+" ,'"+user.getNacimiento()+"', " +
                "'"+ user.getEmail() +"', "+ user.getIdLocalidad()+", '"+user.getContrasena()+"')";
        try {
            Statement st = db.AccesoDatos();
            st.execute(consultaSQL);
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "Conexion no exitosa";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response.equals("Conexion exitosa"))
        {
            Toast.makeText(context ,"El Usuario se cargó exitosamente",Toast.LENGTH_LONG).show();
        }

    }
}

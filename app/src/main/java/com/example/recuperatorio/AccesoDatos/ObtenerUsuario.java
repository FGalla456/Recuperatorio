package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.Statement;

import com.example.recuperatorio.Interface.Comunicacion;
import com.example.recuperatorio.Activity.MenuActivity;
import com.example.recuperatorio.Dominio.Usuario;

public class ObtenerUsuario extends AsyncTask<String, Void, String> {

    private Comunicacion comunication;
    private Usuario user;
    private Context context;
    private DataDB db;
    private Usuario U = new Usuario();
    private String response;

    public ObtenerUsuario(Usuario u, Context ct , Comunicacion c)
    {
        user = u;
        context = ct;
        this.comunication = c;
    }

    @Override
    protected String doInBackground(String... strings) {
        response="";
        db = new DataDB();
        String consulta = "SELECT * from usuarios where email = '" + user.getEmail() + "' and contrasena = '" + user.getContrasena() + "'";
        try {
            Statement st = db.AccesoDatos();
            ResultSet rs = st.executeQuery(consulta);

            while(rs.next()) {
                U.setEmail(rs.getString("email"));
                U.setContrasena(rs.getString("contrasena"));
                U.setDni(rs.getInt("dni"));
                U.setIdLocalidad(rs.getInt("id_localidad"));
                U.setNacimiento(rs.getString("nacimiento"));
                U.setNombre(rs.getString("nombre"));
                response = "Conexion exitosa";
            }
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "Eror, no se encontro un usuario";

        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response.equals("Conexion exitosa")){
            this.comunication.showMessage("Datos Correctos");
            this.comunication.lanzarActividad(MenuActivity.class , U);
        }
        else{
            this.comunication.showMessage("Datos Incorrectos");
        }
    }

}

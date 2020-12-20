package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.Statement;
import com.example.recuperatorio.Dominio.Usuario;

import java.util.ArrayList;

public class ObtenerUsuario extends AsyncTask<String, Void, String> {

    private Usuario user;
    private Context context;
    private DataDB db;
    private Usuario U;
    private String response;

    public ObtenerUsuario(Usuario u, Context ct)
    {
        user = u;
        context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        U = new Usuario();
        response="";
        db = new DataDB();
        String consulta = "SELECT * from usuarios where email = '" + user.getEmail() + "' , and contrasena = '" + user.getContrasena() + "'";
        try {
            Statement st = db.AccesoDatos();
            ResultSet rs = st.executeQuery(consulta);

            while(rs.next()) {
                U.setId(rs.getInt("id"));
                U.setContrasena(rs.getString("contrasena"));
                U.setDni(rs.getInt("dni"));
                U.setEmail(rs.getString("email"));
                U.setIdLocalidad(rs.getInt("id_localidad"));
                U.setNacimiento(rs.getString("nacimiento"));
                U.setNombre(rs.getString("nombre"));
            }
            response = "Conexion exitosa";
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
            user = U;
            if(user == null){
                Toast.makeText(context ," Usuario no existente.",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context ," Usuario no existente.",Toast.LENGTH_SHORT).show();
        }
    }

}

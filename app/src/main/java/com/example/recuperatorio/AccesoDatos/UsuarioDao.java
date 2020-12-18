package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.recuperatorio.Adapter.AdapterUsuario;
import com.example.recuperatorio.Dominio.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import kotlin.text.UStringsKt;

public class UsuarioDao extends AsyncTask<String, Void, String> {
    private DataDB db;
    private String Correo;
    private String Contra;
    Usuario U = new Usuario();
    private Context context;

    private static ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();

    public UsuarioDao(String correo, String Contrasena, Context c){
        Correo = correo;
        Contra = Contrasena;
        context = c;
    }

   /* public String SaveUser(Usuario user){
        String response = "";
        try {
            //Statement st = db.AccesoDatos();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            st.execute("INSERT INTO usuarios (nombre, dni, nacimiento, email, id_localidad, contrasena) " +
                    "VALUES ('"+user.getNombre()+"', "+user.getDni()+" ,"+user.getNacimiento()+", " +
                    "'"+ user.getEmail() +"', "+ user.getIdLocalidad()+", '"+user.getContrasena()+"')");
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "Conexion no exitosa";
        }
        return response;

    }*/

  /*  public Usuario DevolverUsuario(String Correo, String Contrasena)
    {
        Usuario U = new Usuario();
        db = new DataDB();
         try {
             Statement st = db.AccesoDatos();
             Class.forName("com.mysql.jdbc.Driver");
             Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * from usuarios when email = '" + Correo + "' , and contrasena = '" + Contrasena + "'");

             while(rs.next()) {
                 U.setId(rs.getInt("id"));
                 U.setContrasena(rs.getString("contrasena"));
                 U.setDni(rs.getInt("dni"));
                 U.setEmail(rs.getString("email"));
                 U.setIdLocalidad(rs.getInt("id_localidad"));
                 U.setNacimiento(rs.getDate("nacimiento"));
                 U.setNombre(rs.getString("nombre"));
             }
        return U;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return U;
    }*/

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        //db = new DataDB();
        try {
            //Statement st = db.AccesoDatos();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * from usuarios when email = '" + Correo + "' , and contrasena = '" + Contra + "'");
            response = "Exito";
            while(rs.next()) {
                U.setId(rs.getInt("id"));
                U.setContrasena(rs.getString("contrasena"));
                U.setDni(rs.getInt("dni"));
                U.setEmail(rs.getString("email"));
                U.setIdLocalidad(rs.getInt("id_localidad"));
                U.setNacimiento(rs.getDate("nacimiento"));
                U.setNombre(rs.getString("nombre"));
                listaUsuario.add(U);
            }
            return response;
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "Error";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        AdapterUsuario adapter = new AdapterUsuario(listaUsuario,context);


    }
}


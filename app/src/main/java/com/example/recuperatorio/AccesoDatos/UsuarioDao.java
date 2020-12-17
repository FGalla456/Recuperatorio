package com.example.recuperatorio.AccesoDatos;

import com.example.recuperatorio.Dominio.Usuario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import kotlin.text.UStringsKt;

public class UsuarioDao {

    public String SaveUser(Usuario user){
        String response = "";
        try {
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

    }

   /* public Usuario DevolverUsuario(String Correo, String Contrasena)
    {
        Usuario U;
        // "select * from usuarios when email = '" + Correo + "' , and contrasena = '" + Contrasena + "'";

        return U;
    }*/
}


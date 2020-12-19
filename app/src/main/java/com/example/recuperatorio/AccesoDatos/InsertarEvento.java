package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.Dominio.Usuario;

import java.sql.Statement;

public class InsertarEvento extends AsyncTask<String, Void, String>{

    private Evento event;
    private Context context;
    private DataDB db;

    public InsertarEvento(Evento e, Context ct)
    {
        event = e;
        context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        db = new DataDB();
        String consultaSQL = "INSERT INTO eventos (titulo, descripcion, fecha, hora, id_categoria) " +
                "VALUES ('"+event.getTitulo()+"', '"+event.getDescription()+"' ,'"+event.getFecha()+"', " +
                "'"+ event.getHora() +"', "+ event.getIdCategoria()+")";
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
            Toast.makeText(context ,"El Evento se cargó exitosamente",Toast.LENGTH_LONG).show();
        }
    }

}

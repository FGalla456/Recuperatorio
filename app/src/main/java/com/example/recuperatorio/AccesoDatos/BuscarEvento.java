package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recuperatorio.Dominio.Categoria;
import com.example.recuperatorio.Dominio.Evento;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BuscarEvento extends AsyncTask<String, Void, String> {

    private Context context;
    private DataDB db;
    private String titulo;
    private static ArrayList<Evento> listaEvento = new ArrayList<Evento>();

    public BuscarEvento(Context ct, String title)
    {
        context = ct;
        titulo = title;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        db = new DataDB();
        String consultaSQL = "SELECTO * FROM eventos where titulo = '" +titulo+"'";
        try {
            Statement st = db.AccesoDatos();
            ResultSet rs = st.executeQuery(consultaSQL);

            Evento ev;
            while(rs.next()) {
                ev = new Evento();
                ev.setId(rs.getInt("id"));
                ev.setDescription(rs.getString("descripcion"));
                ev.setFecha(rs.getString("fecha"));
                ev.setHora(rs.getString("hora"));
                ev.setIdCategoria(rs.getInt("categoria"));
                listaEvento.add(ev);
            }
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

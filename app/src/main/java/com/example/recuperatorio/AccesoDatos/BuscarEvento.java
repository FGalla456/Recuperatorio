package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recuperatorio.Adapter.EventoAdapter;
import com.example.recuperatorio.Dominio.Categoria;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.Interface.Comunicacion;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BuscarEvento extends AsyncTask<String, Void, String>{

    private Comunicacion comunication;
    private Context context;
    private DataDB db;
    private String titulo;
    private static ArrayList<Evento> listaEvento = new ArrayList<Evento>();
    private ListView lvEventos;


    public BuscarEvento(ListView le, Context ct, String title, Comunicacion c)
    {
        context = ct;
        titulo = title;
        lvEventos = le;
        this.comunication = c;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        Categoria Cat;
        db = new DataDB();
        String consultaSQL = "SELECT * FROM eventos inner join categorias c on id_categoria = c.id where titulo = '" +titulo+"'";
        try {
            Statement st = db.AccesoDatos();
            ResultSet rs = st.executeQuery(consultaSQL);

            Evento ev;
            while(rs.next()) {
                ev = new Evento();
                ev.setId(rs.getInt("id"));
                ev.setTitulo(rs.getString("titulo"));
                ev.setDescription(rs.getString("descripcion"));
                ev.setFecha(rs.getString("fecha"));
                ev.setHora(rs.getString("hora"));
                ev.setIdCategoria(rs.getInt("id_categoria"));
                Cat = new Categoria(rs.getInt(7),rs.getString("categoria"));
                ev.setCat(Cat);
                listaEvento.add(ev);
                response = "Conexion exitosa";
            }
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
            this.comunication.showMessage("Eventos Encontrados");
            EventoAdapter adapter = new EventoAdapter(context, listaEvento);
            lvEventos.setAdapter(adapter);
        }
        else{
            this.comunication.showMessage("No se encontraron resultados");
        }
    }
}

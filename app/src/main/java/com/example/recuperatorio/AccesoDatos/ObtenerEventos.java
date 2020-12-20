package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recuperatorio.Adapter.AdapterCategoria;
import com.example.recuperatorio.Adapter.EventoAdapter;
import com.example.recuperatorio.Dominio.Categoria;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.Dominio.Usuario;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ObtenerEventos extends AsyncTask<String, Void, String>{

    private Evento event;
    private Context context;
    private DataDB db;
    private Evento even;
    private String response;
    private static ArrayList<Evento> listaEventos = new ArrayList<Evento>();
    private ListView lvEventos;

    public ObtenerEventos(ListView le, Evento e, Context ct)
    {
        event = e;
        context = ct;
        lvEventos = le;
    }

    @Override
    protected String doInBackground(String... strings) {

        response="";
        db = new DataDB();
        if(listaEventos.size() > 0){
            listaEventos.removeAll(listaEventos);
        }
        String consulta = "SELECT * from eventos";
        try {
            Statement st = db.AccesoDatos();
            ResultSet rs = st.executeQuery(consulta);

            while(rs.next()) {
                even = new Evento();
                even.setId(rs.getInt("id"));
                even.setTitulo(rs.getString("titulo"));
                even.setDescription(rs.getString("descripcion"));
                even.setFecha(rs.getString("fecha"));
                even.setHora(rs.getString("hora"));
                even.setIdCategoria(rs.getInt("id_categoria"));
                listaEventos.add(even);
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            response = "Eror, no se encontraron eventos";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response.equals("Conexion exitosa")){
            EventoAdapter adapter = new EventoAdapter(context, listaEventos);
            lvEventos.setAdapter(adapter);
            event = even;
            if(event == null){
                Toast.makeText(context ," No hay Eventos cargados.",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context ," No hay Eventos cargados",Toast.LENGTH_SHORT).show();
        }
    }
}

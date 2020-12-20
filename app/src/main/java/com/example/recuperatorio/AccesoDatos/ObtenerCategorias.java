package com.example.recuperatorio.AccesoDatos;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recuperatorio.Adapter.AdapterCategoria;
import com.example.recuperatorio.Dominio.Categoria;
import com.example.recuperatorio.R;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ObtenerCategorias extends AsyncTask<String, Void, String>{

    private ArrayAdapter<Categoria> adapterSpinnerCategoria, adapterSpinner;
    private Spinner spCategoriaLogin, spCategoria;
    private Context context;
    private DataDB db;

    private static String result2;
    private static ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();

    public ObtenerCategorias(Spinner sp, Context ct){
        this.spCategoria = sp;
        this.context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        db = new DataDB();
        if(listaCategorias.size() > 0){
            listaCategorias.removeAll(listaCategorias);
        }
        try {
            Statement st = db.AccesoDatos();
            ResultSet rs = st.executeQuery("SELECT * FROM categorias");
            result2 = " ";

            Categoria cat;
            while(rs.next()) {
                cat = new Categoria();
                cat.setId(rs.getInt("id"));
                cat.setCategoria(rs.getString("categoria"));
                listaCategorias.add(cat);
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if(response.equals("Conexion exitosa")){
            AdapterCategoria adploc = new AdapterCategoria((Activity) context, R.layout.dropdown_value, listaCategorias);
            spCategoria.setAdapter(adploc);
        }
    }

}

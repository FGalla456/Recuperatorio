package com.example.recuperatorio.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.recuperatorio.Adapter.AdapterCategoria;
import com.example.recuperatorio.Adapter.AdapterLocalidad;
import com.example.recuperatorio.Dominio.Localidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ObtenerLocalidades extends AsyncTask<String, Void, String> {

    private ArrayAdapter<Localidad> adapterSpinnerLogin, adapterSpinner;
    private Spinner spLocalidadLogin, spLocalidad;
    private Context context;
    private DataDB db;

    private static String result2;
    private static ArrayList<Localidad> listaLocalidades = new ArrayList<Localidad>();

    public ObtenerLocalidades(Spinner sp, Context ct){
        this.spLocalidad = sp;
        this.context = ct;
    }

    @Override
    protected String doInBackground(String... strings) {
        String response = "";
        db = new DataDB();
        if(listaLocalidades.size() > 0){
            listaLocalidades.removeAll(listaLocalidades);
        }
        try {
            Statement st = db.AccesoDatos();
            ResultSet rs = st.executeQuery("SELECT * FROM localidades");
            result2 = " ";

            Localidad loc;
            while(rs.next()) {
                loc = new Localidad();
                loc.setId(rs.getInt("id"));
                loc.setNombre(rs.getString("localidad"));
                listaLocalidades.add(loc);
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
            adapterSpinner = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listaLocalidades);
            spLocalidad.setAdapter(adapterSpinner);
        }
    }
}

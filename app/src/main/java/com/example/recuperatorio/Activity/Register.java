package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.UsuarioDao;
import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends AppCompatActivity {

    private EditText nombre;
    private EditText dni;
    private EditText email;
    private EditText fecha;
    private EditText contrasena;
    private EditText contraseniaRepeat;
    private Toast alertEmpty;
    private UsuarioDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre = (EditText) findViewById(R.id.edt_nombre);
        email = (EditText) findViewById(R.id.edt_email);
        dni = (EditText) findViewById(R.id.edt_dni);
        fecha = (EditText) findViewById(R.id.edt_fecha);
        contrasena = (EditText) findViewById(R.id.edt_contrasenia);
        contraseniaRepeat = (EditText) findViewById(R.id.edt_repetirContrasenia);
        alertEmpty = Toast.makeText(getApplicationContext(), "Debe completar todos los campos.", Toast.LENGTH_SHORT);

    }
    public void register(View view){
        String nombreUser = nombre.getText().toString();
        String dniUser = dni.getText().toString();
        String fechaUser = fecha.getText().toString();
        String emailUser = email.getText().toString();
        String idLocalidad = nombre.getText().toString();
        String contrasenaUser = contrasena.getText().toString();
        String repetirContraseniaUser = contraseniaRepeat.toString();
        if(!nombreUser.isEmpty() && !emailUser.isEmpty() && !dniUser.isEmpty() && !fechaUser.isEmpty()
                && !emailUser.isEmpty() && !idLocalidad.isEmpty() && !contrasenaUser.isEmpty() && !repetirContraseniaUser.isEmpty()){
            if(contrasenaUser.equals(repetirContraseniaUser)){
                Usuario user = new Usuario(nombreUser, getFecha(fechaUser) ,Integer.parseInt(dniUser),emailUser,'1',contrasenaUser);
                userDao = new UsuarioDao();
                userDao.SaveUser(user);
            }
        }else{
            alertEmpty.show();
        }
    }

    public Date getFecha(String fecha){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = format.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
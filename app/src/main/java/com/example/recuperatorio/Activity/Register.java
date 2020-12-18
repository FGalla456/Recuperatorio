package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.UsuarioDao;
import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Register extends AppCompatActivity {

    private TextView nombre;
    private TextView dni;
    private TextView email;
    private EditText fecha;
    private TextView contrasena;
    private TextView contraseniaRepeat;
    private UsuarioDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre =  findViewById(R.id.txtNombre);
        email =  findViewById(R.id.txt_Correo);
        dni =  findViewById(R.id.txt_dni);
        fecha =  findViewById(R.id.txt_fecha);
        contrasena =  findViewById(R.id.txt_Contrasena);
        contraseniaRepeat = findViewById(R.id.txt_repetirContrasenia);
    }

    public void register(View view){
        String nombreUser = nombre.getText().toString();
        String dniUser = dni.getText().toString();
        String fechaUser = fecha.getText().toString();
        String emailUser = email.getText().toString();
        String idLocalidad = nombre.getText().toString();
        String contrasenaUser = contrasena.getText().toString();
        String repetirContraseniaUser = contraseniaRepeat.toString();
        if(!ValidarCampos()){
            if(contrasenaUser.equals(repetirContraseniaUser)){
                Usuario user = new Usuario(nombreUser, getFecha(fechaUser) ,Integer.parseInt(dniUser),emailUser,'1',contrasenaUser);
                //userDao = new UsuarioDao();
               // userDao.SaveUser(user);
            }
            else
            {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG);
            }
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

    private boolean ValidarCampos()
    {
        boolean Error = false;
        String DatosError = "";
        if(nombre.getText().length() < 1)
        {
            DatosError += "-Nombre Invalido";
            Error = true;
        }
        SaltoLinea(DatosError);
        if(dni.getText().length() < 3)
        {
            DatosError += "-DNI Invalido";
            Error = true;
        }
        SaltoLinea(DatosError);
        if(email.getText().length() < 2)
        {
            DatosError += "-Correo Invalido";
            Error = true;
        }
        SaltoLinea(DatosError);
        if(contrasena.getText().length() < 6)
        {
            DatosError += " -Contraseña Invalida";
            Error = true;
        }

        Toast.makeText(this, DatosError, Toast.LENGTH_LONG).show();
        return  Error;
    }

    private String SaltoLinea(String texto)
    {
        if(texto != "")
        {
            texto += "\n";
        }
        return texto;
    }

}
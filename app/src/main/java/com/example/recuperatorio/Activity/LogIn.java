package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.ObtenerUsuario;
import com.example.recuperatorio.R;
import com.example.recuperatorio.Dominio.Usuario;

public class LogIn extends AppCompatActivity implements  Comunicacion{

    private TextView Correo;
    private TextView Contrasena;
    private TextView Correo1;
    private TextView Contrasena1;
    private Usuario U = new Usuario();
    private String NombreUsuario;
    private String CorreoUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Correo = findViewById(R.id.txt_Correo);
        Contrasena = findViewById(R.id.txt_Contrasena);
        /*Correo1 = findViewById(R.id.lbl_Correo);
        Contrasena1= findViewById(R.id.lbl_Contrasena);*/
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void login(View view)
    {
        if(!ValidarCampos())
        {
            if(Correo.getText().toString() != "" && Contrasena.getText().toString() != "")
            {
                U.setEmail(Correo.getText().toString());
                U.setContrasena(Contrasena.getText().toString());
                try {
                ObtenerUsuario task = new ObtenerUsuario(U, view.getContext(), LogIn.this);
                task.execute();
                    /*Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);*/
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this ,"Error al Iniciar sesion",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public void Redirigir(View view)
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private boolean ValidarCampos()
    {
        boolean Error = false;
        String DatosError = "";
        if(Correo.getText().length() < 2)
        {
            DatosError += "- Correo Invalido";
            Error = true;
        }
        else
        {
            if(!isEmailValid(Correo.getText().toString()))
            {
                DatosError += "- Correo Invalido";
                Error = true;
            }
        }
        if (Contrasena.getText().length() < 6)
        {
            if(DatosError == "")
            {
                DatosError += "- Contraseña Invalida";
            }
            else
            {
                DatosError += "\n- Contraseña Invalida";
            }
            Error = true;
        }

        if(Error == true)
        {
            Toast.makeText(this, DatosError, Toast.LENGTH_LONG).show();
        }
        return  Error;
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this , msg , Toast.LENGTH_LONG).show();
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad , Usuario U) {
        Intent intent = new Intent(this , tipoActividad);
        intent.putExtra("Correo", U.getEmail());
        intent.putExtra("Nombre", U.getNombre());
        startActivity(intent);
    }
}
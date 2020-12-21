package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.ObtenerUsuario;
import com.example.recuperatorio.Interface.Comunicacion;
import com.example.recuperatorio.R;
import com.example.recuperatorio.Dominio.Usuario;

public class LogIn extends AppCompatActivity implements Comunicacion {

    private TextView Correo;
    private TextView Contrasena;
    private Usuario U = new Usuario();
    private ProgressBar pb;
    private TextView registrarse;
    private Button btnIniciar;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Correo = findViewById(R.id.txt_Correo);
        Contrasena = findViewById(R.id.txt_Contrasena);
        pb = findViewById(R.id.progressBar1);
        registrarse = findViewById(R.id.txt_Registrese);
        btnIniciar = findViewById(R.id.btn_Login);
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void login(View view)
    {
        if(!ValidarCampos())
        {
            v = view;
            U.setEmail(Correo.getText().toString());
            U.setContrasena(Contrasena.getText().toString());
            pb.setVisibility(view.VISIBLE);
            btnIniciar.setEnabled(false);
            registrarse.setEnabled(false);
            try {
                ObtenerUsuario task = new ObtenerUsuario(U, view.getContext(), LogIn.this);
                task.execute();
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this ,"Error al Iniciar sesion",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, DatosError, Toast.LENGTH_SHORT).show();
        }
        return  Error;
    }

    @Override
    public void showMessage(String msg) {
        btnIniciar.setEnabled(true);
        registrarse.setEnabled(true);
        pb.setVisibility(v.INVISIBLE);
        Toast.makeText(this , msg , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad , Usuario U) {
        Intent intent = new Intent(this , tipoActividad);
        intent.putExtra("Correo", U.getEmail());
        intent.putExtra("Nombre", U.getNombre());
        startActivity(intent);
    }
}
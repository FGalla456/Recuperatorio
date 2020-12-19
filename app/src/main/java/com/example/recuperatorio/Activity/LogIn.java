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

public class LogIn extends AppCompatActivity {

    private TextView Correo;
    private TextView Contrasena;
    private TextView Correo1;
    private TextView Contrasena1;
    private Usuario U = new Usuario();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Correo = findViewById(R.id.txt_Correo);
        Contrasena = findViewById(R.id.txt_Contrasena);
        /*Correo1 = findViewById(R.id.lbl_Correo);
        Contrasena1= findViewById(R.id.lbl_Contrasena);*/
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
                ObtenerUsuario task = new ObtenerUsuario(U, view.getContext());
                task.execute();
                    Intent intent = new Intent(this, MenuActivity.class);
                    startActivity(intent);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(this ,"Error al Iniciar sesion",Toast.LENGTH_SHORT).show();
                }

            }
            /*if(U != null){
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }
            else{
                Toast error = Toast.makeText(getApplicationContext(), "No se encontro usuario con esos datos.", Toast.LENGTH_LONG);
                error.show();
            }*/
        }
      /*  if(username.getText().length() > 0 && password.getText().length() > 0){
            AdminSQLite admin = new AdminSQLite(this, "BaseDatosTp3", null, 1);
            SQLiteDatabase BasedeDatos = admin.getWritableDatabase();

            String Username = username.getText().toString();
            String Password = password.getText().toString();
            Cursor fila = BasedeDatos.rawQuery("select nombre from usuarios where nombre ='"+ Username +"'" , null);
            if(fila.moveToFirst()){
                Cursor fila2 = BasedeDatos.rawQuery("select id from usuarios where contrasenia ='" + Password +"' and nombre = '"+Username+"'", null);
                if(fila2.moveToFirst()){
                    BasedeDatos.close();
                    SharedPreferences preferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("id", Integer.parseInt(fila2.getString(0)));
                    editor.commit();
                    Intent menu = new Intent(this, MenuActivity.class);
                    menu.putExtra("id_user", fila2.getString(0));
                    startActivity(menu);
                }else{
                    alertErrorPass.show();
                }
            }else{
                alertErrorUser.show();
            }
        }else{
            if(!alertEmpty.getView().isShown()){
                alertEmpty.show();
            }
        }*/
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
            if(!Correo.getText().toString().contains("@"))
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
}
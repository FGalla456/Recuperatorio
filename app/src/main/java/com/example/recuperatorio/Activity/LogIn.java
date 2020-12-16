package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.example.recuperatorio.R;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void login(View view){
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
}
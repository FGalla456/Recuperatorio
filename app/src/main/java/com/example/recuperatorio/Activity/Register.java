package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recuperatorio.R;

public class Register extends AppCompatActivity {

    private EditText nombre;
    private EditText email;
    private EditText contrasenia;
    private EditText contraseniaRepeat;
    private Toast alertEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void register(View view){
        String nombreUser = nombre.getText().toString();
        String emailUser = email.getText().toString();
        String contraseniaUser = contrasenia.getText().toString();
        String contraseniaRepeatUser = contraseniaRepeat.getText().toString();
        if(!nombreUser.isEmpty() && !emailUser.isEmpty() && !contraseniaUser.isEmpty() && !contraseniaRepeatUser.isEmpty()){
            if(contraseniaUser.equals(contraseniaRepeatUser)){
            }
        }else{
            alertEmpty.show();
        }
    }
}
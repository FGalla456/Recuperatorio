package com.example.recuperatorio.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recuperatorio.R;

public class Busqueda extends AppCompatActivity {

    private TextView Texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);
        Texto = findViewById(R.id.txt_Busqueda);
    }

    public void busqueda(View view)
    {
        if(Texto.getText().length() == 0)
        {

        }
        else
        {

        }
    }

}

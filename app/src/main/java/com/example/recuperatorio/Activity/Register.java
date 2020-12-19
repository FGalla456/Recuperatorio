package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.InsertarUsuario;
import com.example.recuperatorio.AccesoDatos.ObtenerLocalidades;
import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.R;

import java.util.Calendar;

public class Register extends AppCompatActivity {

    private TextView nombre;
    private TextView dni;
    private TextView email;
    private EditText fecha;
    private TextView contrasena;
    private TextView contraseniaRepeat;
    private Spinner localidades;
    private View view;
    private String stringDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        view = this.getWindow().getDecorView().findViewById(android.R.id.content);
        localidades = findViewById(R.id.cbLocalidad);
        ObtenerLocalidades task = new ObtenerLocalidades(localidades, view.getContext());
        task.execute();
        nombre =  findViewById(R.id.txtNombre);
        email =  findViewById(R.id.txt_Correo);
        dni =  findViewById(R.id.txt_dni);
        fecha =  findViewById(R.id.txt_fecha);
        contrasena =  findViewById(R.id.txt_Contrasena);
        contraseniaRepeat = findViewById(R.id.txt_repetirContrasenia);

        fecha.addTextChangedListener(new TextWatcher() {
            private String current = "";
            private String ddmmyyyy = "DDMMYYYY";
            private Calendar cal = Calendar.getInstance();
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]", "");
                    String cleanC = current.replaceAll("[^\\d.]", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    //Fix for pressing delete next to a forward slash
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        if(mon > 12) mon = 12;
                        cal.set(Calendar.MONTH, mon-1);

                        year = (year<1900)?1900:(year>2100)?2020:year;
                        cal.set(Calendar.YEAR, year);

                        day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
                        clean = String.format("%02d%02d%02d",day, mon, year);
                        stringDate = String.format("%02d%02d%02d", year, mon, day);
                        stringDate = String.format("%s-%s-%s" , stringDate.substring(0, 4),
                                stringDate.substring(4, 6),
                                stringDate.substring(6, 8));
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = sel < 0 ? 0 : sel;
                    current = clean;
                    fecha.setText(current);
                    fecha.setSelection(sel < current.length() ? sel : current.length());

                }
            }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });

    }

    public void register(View view){
        String nombreUser = nombre.getText().toString();
        String dniUser = dni.getText().toString();
        String emailUser = email.getText().toString();
        String idLocalidad = nombre.getText().toString();
        String contrasenaUser = contrasena.getText().toString();
        String repetirContraseniaUser = contraseniaRepeat.getText().toString();
        if(!ValidarCampos()){
            if(contrasenaUser.equals(repetirContraseniaUser)){
                Usuario user = new Usuario(nombreUser, stringDate.replace('/','-') ,Integer.parseInt(dniUser),emailUser,'1',contrasenaUser);
                InsertarUsuario task = new InsertarUsuario(user, view.getContext());
                task.execute();
            }
            else
            {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG);
            }
        }
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
        else{
            if(!isEmailValid(email.getText().toString())){
                DatosError += "-Email Invalido";
                Error = true;
            }
        }
        SaltoLinea(DatosError);
        if(contrasena.getText().length() < 6)
        {
            DatosError += " -Contraseña Invalida";
            Error = true;
        }
        if(Error){
            Toast.makeText(this, DatosError, Toast.LENGTH_LONG).show();
        }
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
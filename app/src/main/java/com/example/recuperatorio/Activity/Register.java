package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.recuperatorio.Dominio.Localidad;
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
    private Spinner spLocalidades;
    private View view;
    private String stringDate = "";
    Boolean Error = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spLocalidades = findViewById(R.id.sp_Localidad);
        ObtenerLocalidades task = new ObtenerLocalidades(spLocalidades, this);
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
        Localidad LocalidadSelec = (Localidad) spLocalidades.getSelectedItem();
        ValidarCampos();
        if(!Error){
                Usuario user = new Usuario(nombreUser, stringDate.replace('/','-') ,Integer.parseInt(dniUser),emailUser,'1',contrasenaUser);
                InsertarUsuario task = new InsertarUsuario(user, view.getContext());
                task.execute();
                Intent intent = new Intent(this, LogIn.class);
                startActivity(intent);
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_LONG).show();
            }
        }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void ValidarCampos()
    {
        String DatosError = "";
        if(nombre.getText().length() < 1)
        {
            DatosError = SaltoLinea(DatosError, "-Nombre Invalido");
        }
        if(dni.getText().length() == 0)
        {
            DatosError = SaltoLinea(DatosError, "-Ingrese el DNI");
        }
        else{
            if(dni.getText().length() != 8){
            DatosError = SaltoLinea(DatosError, "-DNI Invalido");
            }
        }
        if(email.getText().length() == 0)
        {
            DatosError = SaltoLinea(DatosError, "-Ingrese un Correo");
        }
        else{
            if(!isEmailValid(email.getText().toString())){
                DatosError = SaltoLinea(DatosError, "-Correo Invalido");
            }
        }
        if(contrasena.getText().length() == 0)
        {
            DatosError = SaltoLinea(DatosError, "-Contraseña Vacia");
        }
        else {
            if(contrasena.getText().length() > 6){
                DatosError = SaltoLinea(DatosError, "Ingrese una Contraseña con al menos 6 caractere");
            }
        }
        if(contrasena.getText().equals(contraseniaRepeat.getText())) {

            DatosError = SaltoLinea(DatosError, "las Contraseñas no coinciden");
        }
        if(Error){
            Toast.makeText(this, DatosError, Toast.LENGTH_LONG).show();
            Error = false;
        }
    }

    private String SaltoLinea(String Datos, String texto)
    {
        Error = true;
        if(Datos != "")
        {
            Datos += "\n";
        }
        Datos += texto;
        return Datos;
    }

}
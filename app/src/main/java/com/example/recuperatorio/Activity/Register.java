package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recuperatorio.AccesoDatos.InsertarUsuario;
import com.example.recuperatorio.AccesoDatos.ObtenerLocalidades;
import com.example.recuperatorio.Dominio.Localidad;
import com.example.recuperatorio.Dominio.Usuario;
import com.example.recuperatorio.Interface.Registrarse;
import com.example.recuperatorio.R;

import java.util.Calendar;

public class Register extends AppCompatActivity implements Registrarse {

    private TextView nombre;
    private TextView dni;
    private TextView email;
    private EditText fecha;
    private TextView contrasena;
    private TextView contraseniaRepeat;
    private Spinner spLocalidades;
    private ProgressBar pb;
    private String stringDate = "";
    Boolean Error = false;
    private Button btnRegister;
    private Button btnReturn;
    private View v;

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
        pb = findViewById(R.id.progressBar1);
        btnRegister = findViewById(R.id.btn_aceptar);
        btnReturn = findViewById(R.id.btn_regresar);

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
        String contrasenaUser = contrasena.getText().toString();
        Localidad LocalidadSelec = (Localidad) spLocalidades.getSelectedItem();
        v = view;
        ValidarCampos();
        if(!Error){
            pb.setVisibility(view.VISIBLE);
            btnRegister.setEnabled(false);
            btnReturn.setEnabled(false);
            Usuario user = new Usuario(nombreUser, stringDate.replace('/','-') ,Integer.parseInt(dniUser), emailUser, LocalidadSelec.getId(),contrasenaUser);
            InsertarUsuario task = new InsertarUsuario(user, view.getContext(), Register.this);
            task.execute();
            }
        Error = false;
        }

    public void returnLogin (View view){
        lanzarActividad(LogIn.class);
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
            Toast.makeText(this, DatosError, Toast.LENGTH_SHORT).show();
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

    @Override
    public void showMessage(String msg) {
        btnRegister.setEnabled(true);
        btnReturn.setEnabled(true);
        pb.setVisibility(v.INVISIBLE);
        Toast.makeText(this , msg , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void lanzarActividad(Class<?> tipoActividad) {
        Intent intent = new Intent(this , tipoActividad);
        startActivity(intent);
    }


}
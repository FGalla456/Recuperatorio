package com.example.recuperatorio.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.recuperatorio.Adapter.EventoAdapter;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FragmentEventFragment extends Fragment {

    private EventViewModel mViewModel;
    private AlertDialog dialogEvento, dialogEliminar;
    private AlertDialog.Builder builderE, builderB;
    private Toast alertEmpty, alertExito, alertError, alertBorrado;
    private Integer idUser;
    private EventoAdapter adapter;
    private GridView grid;
    private ArrayList<Evento> lista;
    private Evento selected;

    public static FragmentEventFragment newInstance() {
        return new FragmentEventFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        View root = inflater.inflate(R.layout.event_fragment, container, false);

        builderB = new AlertDialog.Builder(getActivity());
        builderE = new AlertDialog.Builder(getActivity());

        SharedPreferences preferences = this.getActivity().getSharedPreferences("usuario", Context.MODE_PRIVATE);
        idUser = preferences.getInt("id", -1);

        /*FloatingActionButton fab = (FloatingActionButton) root.findViewById(R.id.btn_AgregarEvento);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEvento();
            }
        });

        adapter = new EventoAdapter(root.getContext(), loadParqueos());
        grid = (GridView) root.findViewById(R.id.gv_parqueos);
        grid.setAdapter(adapter);

        alertEmpty = Toast.makeText(getActivity(), "Debe completar todos los campos.", Toast.LENGTH_SHORT);
        alertExito = Toast.makeText(getActivity(), "Parqueo registrado exitosamente", Toast.LENGTH_SHORT);
        alertError = Toast.makeText(getActivity(), "Los campos deben estar completos para cargar el parqueo", Toast.LENGTH_LONG);
        alertBorrado = Toast.makeText(getActivity(), "El parqueo fue borrado con éxito", Toast.LENGTH_LONG);

        builderP.setView(customLayout);
        builderP.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                EditText mat = customLayout.findViewById(R.id.matricula);
                EditText tie = customLayout.findViewById(R.id.tiempo);

                if (!mat.getText().toString().isEmpty() && !tie.getText().toString().isEmpty()) {
                    if (cargarParqueo(mat.getText().toString(), Integer.parseInt(tie.getText().toString()))) {
                        alertExito.show();
                        mat.setText("");
                        tie.setText("");


                    }
                } else {
                    alertError.show();
                }
            }

        });
        builderP.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        dialogParqueo = builderP.create();
        dialogParqueo.setTitle("Cargar parqueo");

        builderB.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                borrarParqueo(selected.getId());
                alertBorrado.show();

            }

        });
        builderB.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        dialogEliminar = builderB.create();
        dialogEliminar.setTitle("Eliminar parqueo");

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                selected = lista.get(position);
                dialogEliminar.show();
            }
        });

        return root;
    }

   /* public boolean cargarParqueo(String matriculaCargada, int tiempoCargado) {
        String matricula = matriculaCargada;
        int tiempo = tiempoCargado;
        if (!matricula.isEmpty() && tiempo > 0) {
            AdminSQLite admin = new AdminSQLite(getActivity(), "BaseDatosTp3", null, 1);
            SQLiteDatabase BaseDatos = admin.getWritableDatabase();
            try {
                ContentValues registro = new ContentValues();
                registro.put("patente", matriculaCargada.toUpperCase());
                registro.put("tiempo", tiempoCargado);
                registro.put("id_usuario", idUser);
                BaseDatos.insert("parqueos", null, registro);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                BaseDatos.close();
                adapter = new ParqueoAdapter(getContext(), loadParqueos());
                grid.setAdapter(adapter);
            }
        }
        return true;
    }

    public void showEvento(){
        dialogEvento.show();
    }

    /*public ArrayList<Evento> loadParqueos(){
        AdminSQLite admin = new AdminSQLite(getContext(), "BaseDatosTp3", null, 1);
        SQLiteDatabase BasedeDatos = admin.getWritableDatabase();
        lista = new ArrayList<>();

        Cursor parqueos = BasedeDatos.rawQuery("select id, patente, tiempo from parqueos where id_usuario ="+ idUser , null);
        if(parqueos.moveToFirst()){
            while(parqueos.moveToNext()){
                lista.add(new Parqueo(Integer.parseInt(parqueos.getString(0)), parqueos.getString(1), Integer.parseInt(parqueos.getString(2))));
            }
        }
        return lista;
    }

    }*/

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EventViewModel.class);
        // TODO: Use the ViewModel
    }*/
        return inflater.inflate(R.layout.activity_busqueda, container, false);
    }
}
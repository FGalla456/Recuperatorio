package com.example.recuperatorio.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.recuperatorio.Adapter.EventoAdapter;
import com.example.recuperatorio.Dominio.Evento;
import com.example.recuperatorio.ui.FragmentEventAddFragment;

import java.util.ArrayList;

public class FragmentEventAdd extends Fragment {

   /* private static final String arg_param1 = "param1";
    private static final String arg_param2 = "param2";

    private String param1;
    private String param2;

    public FragmentEventAdd(){

    }


    public static FragmentEventAdd newInstance(String param1 , String param2) {
        FragmentEventAdd fragment = new FragmentEventAdd();
        Bundle args = new Bundle();
        args.putString(arg_param1 , param1);
        args.putString(arg_param2 , param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            param1 = getArguments().getString(arg_param1);
            param2 = getArguments().getString(arg_param2);
        }
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
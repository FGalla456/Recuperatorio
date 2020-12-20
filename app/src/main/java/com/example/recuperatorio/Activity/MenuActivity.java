package com.example.recuperatorio.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.recuperatorio.R;
import com.example.recuperatorio.ui.FragmentEventAdd;
import com.example.recuperatorio.ui.FragmentEventFragment;
import com.google.android.material.navigation.NavigationView;

public class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawlayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private AlertDialog dialogAccount;
    private AlertDialog.Builder builder;
    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle toggle;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String Nombre = intent.getStringExtra("Nombre");
        String Correo = intent.getStringExtra("Correo");
        setContentView(R.layout.activity_menuprincipal);
        drawlayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        toggle = new ActionBarDrawerToggle(this, drawlayout, toolbar, R.string.navigation_drawler_open, R.string.navigation_drawler_close);
        drawlayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.header_user);
        TextView navEmail = (TextView) headerView.findViewById(R.id.header_email);
        navUsername.setText(Nombre);
        navEmail.setText(Correo);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, new FragmentEventAdd()).commit();
        getSupportActionBar().setSubtitle("Agregar Evento");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_add_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, new FragmentEventAdd()).commit();
                getSupportActionBar().setSubtitle("Agregar Evento");
                drawlayout.close();
                break;
            case R.id.nav_list_events:
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments, new FragmentEventFragment()).commit();
                getSupportActionBar().setSubtitle("Mis Eventos");
                drawlayout.close();
                break;
            case R.id.nav_logout:
                logout();
        }
        drawlayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawlayout.isDrawerOpen(GravityCompat.START)){
            drawlayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences("usuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id", -1);
        editor.commit();
        Intent inicio = new Intent(this, LogIn.class);
        startActivity(inicio);
    }
}
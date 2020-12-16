package com.example.recuperatorio.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.recuperatorio.R;
import com.example.recuperatorio.ui.FragmentEventFragment;

public class fragment_event extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentEventFragment.newInstance())
                    .commitNow();
        }
    }
}
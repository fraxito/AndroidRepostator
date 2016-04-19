package com.example.xp.repostator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class EntradaDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_datos);
                Fragment fragmento = new Repostaje();
                FragmentTransaction cambio = getSupportFragmentManager().beginTransaction();
                cambio.replace(R.id.marcoInicial, fragmento);
                cambio.commit();
    }
}

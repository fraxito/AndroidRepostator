package com.example.xp.repostator;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Repostaje extends Fragment {


    public Repostaje() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repostaje, container, false);
    }

    private void escribeDatos(){
        EditText editPrecio = (EditText) getActivity().findViewById(R.id.precio);
        EditText editKilometos = (EditText) getActivity().findViewById(R.id.kilometros);
        EditText editLitros = (EditText) getActivity().findViewById(R.id.litros);
        DatePicker editFecha = (DatePicker) getActivity().findViewById(R.id.fecha);
        SharedPreferences sharedPref = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int size = sharedPref.getInt("listado_size", 0);
        size++;

        String precio = editPrecio.getText().toString();
        if (precio.equals("")){precio = "0";}

        String kilometros = editKilometos.getText().toString();
        if (kilometros.equals("")){kilometros = "0";}

        String litros = editLitros.getText().toString();
        if (litros.equals("")){litros = "0";}

        editor.putString("precio_" + size, precio);
        editor.putString("kilometros_" + size, kilometros);
        editor.putString("litros_" + size, litros);

        editor.putInt("dia_" + size, editFecha.getDayOfMonth());
        editor.putInt("mes_" + size, editFecha.getMonth());
        editor.putInt("year_" + size, editFecha.getYear());

        editor.putInt("listado_size", size);

        editor.commit();

    }

    @Override
    public void onStart(){
        super.onStart();
        final EditText editPrecio = (EditText) getActivity().findViewById(R.id.precio);
        editPrecio.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    escribeDatos();
                    getActivity().finish();
                }
              return false;
            }
        });

        Button botonGuardar = (Button) getActivity().findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                escribeDatos();
                getActivity().finish();
            }
        });
    }



}

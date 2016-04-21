package com.example.xp.repostator;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class ActualizaDatos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_repostaje);
    }

    private void leeDatos(int indice){
        EditText editPrecio = (EditText)findViewById(R.id.precio);
        EditText editKilometos = (EditText) findViewById(R.id.kilometros);
        EditText editLitros = (EditText) findViewById(R.id.litros);
        DatePicker editFecha = (DatePicker) findViewById(R.id.fecha);
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);

        String precio = sharedPref.getString("precio_" + indice, null);
        String kilometros = sharedPref.getString("kilometros_" + indice, null);
        String litros = sharedPref.getString("litros_" + indice, null);

        int dia =  sharedPref.getInt("dia_" + indice, 0) ;
        int mes =  sharedPref.getInt("mes_" + indice, 0) ;
        int year =  sharedPref.getInt("year_" + indice, 0);

        editPrecio.setText(precio);
        editKilometos.setText(kilometros);
        editLitros.setText(litros);

        editFecha.updateDate(year, mes,dia);

    }

    private void escribeDatos(){
        EditText editPrecio = (EditText) findViewById(R.id.precio);
        EditText editKilometos = (EditText) findViewById(R.id.kilometros);
        EditText editLitros = (EditText) findViewById(R.id.litros);
        DatePicker editFecha = (DatePicker) findViewById(R.id.fecha);
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int size = sharedPref.getInt("listado_size", 0);
        size++;

        editor.putString("precio_" + size, editPrecio.getText().toString());
        editor.putString("kilometros_" + size, editKilometos.getText().toString());
        editor.putString("litros_" + size, editLitros.getText().toString());

        editor.putInt("dia_" + size, editFecha.getDayOfMonth());
        editor.putInt("mes_" + size, editFecha.getMonth());
        editor.putInt("year_" + size, editFecha.getYear());

        editor.putInt("listado_size", size);

        editor.commit();

    }

    @Override
    public void onStart(){
        super.onStart();

        Bundle parametros = getIntent().getExtras();
        if (parametros != null){
            int posicion = parametros.getInt("posicion");
            posicion++;

            leeDatos(posicion);
        }

        final EditText editPrecio = (EditText) findViewById(R.id.precio);
        editPrecio.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    escribeDatos();
                    finish();
                }
                return false;
            }
        });

    }

}

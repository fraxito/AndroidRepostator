package com.example.xp.repostator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent ventana = new Intent(this, EntradaDatos.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(ventana);

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onStart() {
        super.onStart();
        ArrayList<String> listadoRepostajes = new ArrayList <String>();
        ListView listaVista ;
        SharedPreferences sharedPref = getSharedPreferences("datos", Context.MODE_PRIVATE);
        //cargo la lista de repostajes de preferences al arraylist
        int size = sharedPref.getInt("listado_size", 0);   //  <---- el tamaño de la lista de datos

        String precio = "";
        String kilometros = "";
        String litros = "";
        String dia = "";
        String mes = "";
        String year = "";
        double costeTotal = 0;
        for(int i = 1 ; i <= size; i++)
        {
            precio = sharedPref.getString("precio_" + i, null);
            kilometros = sharedPref.getString("kilometros_" + i, null);
            litros = sharedPref.getString("litros_" + i, null);

            costeTotal = (Double.valueOf(precio) * Double.valueOf(litros));

            dia =  String.valueOf( sharedPref.getInt("dia_" + i, 0) );
            mes =  String.valueOf( sharedPref.getInt("mes_" + i, 0) );
            year =  String.valueOf( sharedPref.getInt("year_" + i, 0) );

            listadoRepostajes.add(kilometros + " km " + litros + " L " + precio + " €/L  total: " + String.format("%.2f", costeTotal) + " " +  dia + "-" + mes + "-" + year);
        }

        ///poblamos el listview con el arraylist

        listaVista = (ListView) findViewById(R.id.marcoLista);

        // Este es el array adapter, necesita el activity como primer parámetro
        // , el tipo de listView como segundo parámetro y el array de datos
        // como tercer parámetro.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listadoRepostajes );

        listaVista.setAdapter(arrayAdapter);

        listaVista.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            final Intent ventana = new Intent(MainActivity.this, EntradaDatos.class);

            @Override
            public void onItemClick(AdapterView<?>adapter,View v, int position, long l){

                String item = (String) adapter.getItemAtPosition(position);
                Log.e("app1", item);
                ventana.putExtra("precio", item);
                startActivity(ventana);

            }


        });

    }

    }

package com.example.xp.repostator;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;


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

    @Override
    public void onStart(){
        super.onStart();



        final EditText editKilometros =(EditText) getActivity().findViewById(R.id.kilometros);

        final EditText editPrecio =(EditText) getActivity().findViewById(R.id.precio);
        editPrecio.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    SharedPreferences sharedPref = getActivity().getSharedPreferences("datos",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    //leo el tamaño de la lista de datos que tengo guardado en el shared preferences
                    int size = sharedPref.getInt("listado_size", 0);
                    size++;
                    //guardo el contenido de la caja de texto "precio" en una variable del sharedpreferences
                    editor.putString("repostaje_"+size, editPrecio.getText().toString());
                    editor.putInt("listado_size", size);
                    editor.commit();

                   handled = true;

                }
              return handled;
            }
        });

     
    }

}

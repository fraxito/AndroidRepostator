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

    @Override
    public void onStart(){
        super.onStart();

        final EditText editPrecio = (EditText) getActivity().findViewById(R.id.precio);
        editPrecio.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
                if (actionId == EditorInfo.IME_ACTION_DONE){
                   SharedPreferences sp = getActivity().getSharedPreferences("datos", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    int size = sp.getInt("Listado_size", 0);
                    size++;
                    //Log.e("app1", String.valueOf(size));
                    editor.putString("Repostaje_" + size, editPrecio.getText().toString());
                    editor.putInt("Listado_size", size);
                    editor.commit();
                   //Log.e("app1", sp.getString("Repostaje_" + size, ""));
                    getActivity().finish();
                }
              return false;
            }
        });
    }



}

package com.example.myfragmentmvp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.example.myfragmentmvp.Fragments.TablaAdministrador;
import com.example.myfragmentmvp.Fragments.TablaAlumnos;
import com.example.myfragmentmvp.Helpers.Helpers;
import com.example.myfragmentmvp.R;

public class TablaPrincipal extends AppCompatActivity implements TablaAlumnos.OnFragmentInteractionListener, TablaAdministrador.OnFragmentInteractionListener {
    TablaAdministrador TablaAdministrador;
    TablaAlumnos TablaAlumnos;
    private static TablaPrincipal TablaPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabla_principal);
        TablaAdministrador = new TablaAdministrador();
        TablaAlumnos = new TablaAlumnos();
        if (Helpers.admin == true){
            getSupportFragmentManager().beginTransaction().add(R.id.fragment2,TablaAlumnos).commit();
        }else{
            getSupportFragmentManager().beginTransaction().add(R.id.fragment2,TablaAdministrador).commit();
        }
    }

    public static Context context() {
        return TablaPrincipal.getApplicationContext();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

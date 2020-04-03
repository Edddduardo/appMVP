package com.example.myfragmentmvp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfragmentmvp.Models.Carrera;
import com.example.myfragmentmvp.Presenters.FormCarreraPresenter;
import com.example.myfragmentmvp.R;

public class FormCarrera extends AppCompatActivity implements FormCarreraPresenter.View {

    EditText etNombrec;
    EditText etPeriodo;
    EditText etCodigo;
    Button btnNuevaCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_carrera);
        etNombrec = findViewById(R.id.etNombrec2);
        etPeriodo = findViewById(R.id.etPerido2);
        etCodigo = findViewById(R.id.etCodigo2);
        btnNuevaCarrera = findViewById(R.id.btnEditarCarrera);
        btnNuevaCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    crearCarrera();
                   /* Intent intent = new Intent(FormAlumno.this , FormAlumno.class);
                    startActivity(intent);
                    */
                }catch (Exception e){}

            }
        });
    }

    @Override
    public void crearCarrera() {
        Carrera ca = new Carrera();
        ca.nombre = String.valueOf(etNombrec.getText());
        ca.periodo = String.valueOf(etPeriodo.getText());
        ca.codigo = String.valueOf(etCodigo.getText());
        Carrera.newCarrera(ca,this);
        Intent intent = new Intent(FormCarrera.this , TablaPrincipal.class);
        startActivity(intent);
    }

    @Override
    public void dialogNotificación(String titulo, String texto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo)
                .setMessage(texto);
        builder.create().show();
    }
}

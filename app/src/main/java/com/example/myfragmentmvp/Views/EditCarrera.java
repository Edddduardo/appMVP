package com.example.myfragmentmvp.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfragmentmvp.Helpers.Helpers;
import com.example.myfragmentmvp.Models.Carrera;
import com.example.myfragmentmvp.Presenters.EditCarreraPresenter;
import com.example.myfragmentmvp.R;

public class EditCarrera extends AppCompatActivity implements EditCarreraPresenter.View {
    public EditText etNombrec2;
    public EditText etPerido2;
    public EditText etCodigo2;
    public Button btnEditarCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_carrera);
        etNombrec2 = findViewById(R.id.etNombrec2);
        etPerido2 = findViewById(R.id.etPerido2);
        etCodigo2 = findViewById(R.id.etCodigo2);
        btnEditarCarrera = findViewById(R.id.btnEditarCarrera);
        getCarrera(Helpers.select,this);
        btnEditarCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Carrera ca = new Carrera();
                    ca.nombre = String.valueOf(etNombrec2.getText());
                    ca.periodo = String.valueOf(etPerido2.getText());
                    ca.codigo = String.valueOf(etCodigo2.getText());
                    updateCarrera(Helpers.select,ca);
                    Intent intent = new Intent(EditCarrera.this , TablaPrincipal.class);
                    startActivity(intent);
                }catch (Exception e){}
            }
        });
    }

    @Override
    public void getCarrera(int id, EditCarrera view) {
        Carrera.getCarrera2(Helpers.select,this);
    }

    @Override
    public void updateCarrera(int id, Carrera carrera) {
        Carrera.updateCarrera(id,carrera);
    }
}

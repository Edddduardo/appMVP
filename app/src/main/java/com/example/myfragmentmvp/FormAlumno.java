package com.example.myfragmentmvp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfragmentmvp.Models.Alumno;
import com.example.myfragmentmvp.Presenters.FormAlumnoPresenter;
import com.example.myfragmentmvp.Views.TablaPrincipal;

public class FormAlumno extends AppCompatActivity implements FormAlumnoPresenter.View {
    EditText etNombre;
    EditText etApellido;
    EditText etEdad;
    EditText etGenero;
    EditText etDireccion;
    Button btnNuevoAlumno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_alumno);
        etNombre = findViewById(R.id.etNombrec2);
        etApellido = findViewById(R.id.etPerido2);
        etGenero = findViewById(R.id.etGenero2);
        etDireccion = findViewById(R.id.etDireccion2);
        etEdad = findViewById(R.id.etCodigo2);
        btnNuevoAlumno = findViewById(R.id.btnEditarCarrera);
        btnNuevoAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    crearAlumno();
                   /* Intent intent = new Intent(FormAlumno.this , FormAlumno.class);
                    startActivity(intent);
                    */
                }catch (Exception e){}
                //Navegar a pagina de editar
            }
        });
    }

    @Override
    public void crearAlumno() {
        Alumno al = new Alumno();
        al.nombre = String.valueOf(etNombre.getText());
        al.apellidos = String.valueOf(etApellido.getText());
        al.edad = String.valueOf(etEdad.getText());
        al.sexo = String.valueOf(etGenero.getText());
        al.direccion = String.valueOf(etDireccion.getText());
        Alumno.newAlumno(al,this);
        Intent intent = new Intent(FormAlumno.this , TablaPrincipal.class);
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

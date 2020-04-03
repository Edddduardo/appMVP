package com.example.myfragmentmvp.Views;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;

        import com.example.myfragmentmvp.Helpers.Helpers;
        import com.example.myfragmentmvp.Models.Alumno;
        import com.example.myfragmentmvp.Presenters.EditAlumnoPresenter;
        import com.example.myfragmentmvp.R;

public class EditAlumno extends AppCompatActivity implements EditAlumnoPresenter.View {
    public EditText etNombre2;
    public EditText etApellido2;
    public EditText etEdad2;
    public EditText etGenero2;
    public EditText etDireccion2;
    public Button btnEditarAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alumno);
        etNombre2 = findViewById(R.id.etNombrec2);
        etApellido2 = findViewById(R.id.etPerido2);
        etGenero2 = findViewById(R.id.etGenero2);
        etDireccion2 = findViewById(R.id.etDireccion2);
        etEdad2 = findViewById(R.id.etCodigo2);
        btnEditarAlumno = findViewById(R.id.btnEditarCarrera);
        getAlumno(Helpers.select,this);
        btnEditarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Alumno al = new Alumno();
                    al.nombre = String.valueOf(etNombre2.getText());
                    al.apellidos = String.valueOf(etApellido2.getText());
                    al.sexo = String.valueOf(etGenero2.getText());
                    al.edad = String.valueOf(etEdad2.getText());
                    al.direccion = String.valueOf(etDireccion2.getText());
                    updateAlumno(Helpers.select,al);
                    Intent intent = new Intent(EditAlumno.this , TablaPrincipal.class);
                    startActivity(intent);
                }catch (Exception e){}
            }
        });
    }

    @Override
    public void getAlumno(final int id, final EditAlumno view) {
        Alumno.getAlumno2(Helpers.select,this);
    }

    @Override
    public void updateAlumno(int id, Alumno alumno) {
        Alumno.updateAlumno(Helpers.select,alumno);
    }
}

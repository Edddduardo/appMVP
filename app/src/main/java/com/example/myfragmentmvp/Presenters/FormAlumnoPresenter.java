package com.example.myfragmentmvp.Presenters;

import com.example.myfragmentmvp.Models.Alumno;

public class FormAlumnoPresenter {
    View view;
    public FormAlumnoPresenter(View view){
        this.view = view;
    }

    public interface View{
        void crearAlumno();
        void dialogNotificación(String titulo, String texto);
    }
}

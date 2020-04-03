package com.example.myfragmentmvp.Presenters;

import com.example.myfragmentmvp.Models.Alumno;

public class AlumnoPresenter {
    View view;
    public AlumnoPresenter(View view){
        this.view = view;
    }

    public interface View{
       void cargarTabla();
       void getAlumno(int id);
       void dialogAlumno(Alumno alumno);
       void deleteAlumno(int id);
       void dialogNotificación(String titulo,String texto);
    }
}

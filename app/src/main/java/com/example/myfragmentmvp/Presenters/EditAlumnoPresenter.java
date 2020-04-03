package com.example.myfragmentmvp.Presenters;

import com.example.myfragmentmvp.Models.Alumno;
import com.example.myfragmentmvp.Views.EditAlumno;

public class EditAlumnoPresenter {
    View view;
    public EditAlumnoPresenter(View view){
        this.view = view;
    }

    public interface View{
        void getAlumno(int id,final EditAlumno view);
        void updateAlumno(int id, Alumno alumno);
    }
}

package com.example.myfragmentmvp.Presenters;

import android.view.View;

import com.example.myfragmentmvp.Models.Carrera;

public class CarreraPresenter {
    View view;
    public CarreraPresenter(View view){
        this.view = view;
    }

    public interface View{
        void cargarTabla();
        void getCarrera(int id);
        void dialogCarrera(Carrera carrera);
        void deleteCarrera(int id);
        void dialogNotificación(String titulo,String texto);
    }
}

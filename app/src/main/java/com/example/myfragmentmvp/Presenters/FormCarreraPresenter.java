package com.example.myfragmentmvp.Presenters;

public class FormCarreraPresenter {
    View view;
    public FormCarreraPresenter(View view){
        this.view = view;
    }

    public interface View{
        void crearCarrera();
        void dialogNotificación(String titulo, String texto);
    }
}

package com.example.myfragmentmvp.Presenters;

import com.example.myfragmentmvp.Views.EditCarrera;
import com.example.myfragmentmvp.Models.Carrera;

public class EditCarreraPresenter {
    View view;
    public EditCarreraPresenter(View view){
        this.view = view;
    }

    public interface View{
        void getCarrera(int id,final EditCarrera view);
        void updateCarrera(int id, Carrera carrera);
    }
}

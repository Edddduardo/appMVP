package com.example.myfragmentmvp.Presenters;

        import android.view.View;

        import org.json.JSONException;

public class LoginPresenter {
    View view;
    public LoginPresenter(View view){
        this.view = view;
    }

    public interface View{
        void entrar() throws JSONException;
    }

}

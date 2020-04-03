package com.example.myfragmentmvp.Views;

import androidx.appcompat.app.AppCompatActivity;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myfragmentmvp.Enums.Enums;
import com.example.myfragmentmvp.Helpers.Helpers;
import com.example.myfragmentmvp.HelpersServices.HelpersService;
import com.example.myfragmentmvp.Models.Login;
import com.example.myfragmentmvp.Presenters.LoginPresenter;
import com.example.myfragmentmvp.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity implements LoginPresenter.View {
    EditText usuario;
    EditText password;
    Button entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.editText);
        password = (EditText)findViewById(R.id.editText2);
        entrar = (Button)findViewById(R.id.btnentrar);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrar();
            }
        });
    }
    /*
    public void loggin(View view) throws JSONException {
        EditText editText = findViewById(R.id.editText);
        EditText editText2 = findViewById(R.id.editText2);

        JSONObject oJSONObject = new JSONObject();
        oJSONObject.put("username",editText.getText().toString());
        oJSONObject.put("password",editText2.getText().toString());
        System.out.println(editText.getText().toString());
        System.out.println(editText2.getText().toString());
        ByteArrayEntity oEntity = null;
        try {
            oEntity = new ByteArrayEntity(oJSONObject.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        oEntity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        AsyncHttpClient cliente = HelpersService.getClient();
        cliente.post(getApplicationContext(), Helpers.URL+Enums.login, (HttpEntity) oEntity, "application/json", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String resp = new String(responseBody);
                String res="nohaytoken";
                boolean bol = false;
                try {
                    JSONObject testV =new JSONObject(new String(responseBody));
                    res = testV.get("token").toString();
                    bol = Boolean.parseBoolean(testV.get("super").toString());
                }catch (Exception e){
                    System.out.println(e);
                }
                System.out.println("..................");
                System.out.println(bol);
                System.out.println("..................");
                System.out.println(res);
                Helpers.token=res;
                Helpers.admin=bol;
                Intent intent = new Intent(MainActivity.this , TablaPrincipal.class);
                startActivity(intent);
                System.out.println("..................");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
    */
    @Override
    public void entrar(){
        EditText editText = findViewById(R.id.editText);
        EditText editText2 = findViewById(R.id.editText2);

        Login.IniciarSesión(editText.getText().toString(),editText2.getText().toString());
        if (Login.log == true){
            Intent intent = new Intent(MainActivity.this , TablaPrincipal.class);
            startActivity(intent);
        }
    }
}

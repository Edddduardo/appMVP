package com.example.myfragmentmvp.Models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.myfragmentmvp.Enums.Enums;
import com.example.myfragmentmvp.Helpers.Helpers;
import com.example.myfragmentmvp.HelpersServices.HelpersService;
import com.example.myfragmentmvp.Views.MainActivity;
import com.example.myfragmentmvp.Views.TablaPrincipal;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class Login {
    public static String token;
    public static boolean log = false;
    private static String url = Helpers.URL+Enums.login;

    public static void IniciarSesión(String usuario, String contraseña, final MainActivity view) {
        RequestParams params = new RequestParams();
        params.put("username", usuario);
        params.put("password", contraseña);
        AsyncHttpClient client = new AsyncHttpClient();
        client.post( url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    String respuesta = new String(responseBody);
                    if(respuesta.equals("404")){
                       System.out.println("Usuario no encontrado");
                        //Toast.makeText(v, "Usuario NO Encontrado", Toast.LENGTH_LONG).show();
                    }else{
                        System.out.println("Usuario encontrado");
                        try {
                            JSONObject response =new JSONObject(new String(responseBody));
                            Login.token = response.getString("token");

                            Login.log = true;
                            String username = "Un usuario";
                            Helpers.admin = response.getBoolean("super");
                            Intent intent = new Intent(view , TablaPrincipal.class);
                            view.startActivity(intent);
                        } catch (Exception e) {

                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                JSONArray response = null;
                try {
                    response = new JSONArray(new String(responseBody));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (statusCode == 404) {

                    //Toast.makeText(getApplicationContext(), "404 ! respuesta: "+ rs, Toast.LENGTH_LONG).show();
                    String rs = new String(responseBody);
                    //Toast.makeText(getApplicationContext(),rs, Toast.LENGTH_LONG).show();
                    System.out.println("404 ! respuesta: "+ rs);
                    //view.view.loginAlert("404 ! respuesta: "+ rs);
                } else if (statusCode == 500) {
                    //Toast.makeText(getApplicationContext(), "500 !", Toast.LENGTH_LONG).show();
                    System.out.println("500 !");
                    //view.view.loginAlert("500 !");
                } else if (statusCode == 403) {
                    //Toast.makeText(getApplicationContext(), "403 !", Toast.LENGTH_LONG).show();
                    System.out.println("403 !");
                    //view.view.loginAlert("403 !");
                } else {
                    //Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    System.out.println(error.toString());
                    //view.view.loginAlert(error.toString());
                }
            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });
    }
}

package com.example.myfragmentmvp.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfragmentmvp.EditCarrera;
import com.example.myfragmentmvp.Enums.Enums;
import com.example.myfragmentmvp.Views.FormCarrera;
import com.example.myfragmentmvp.Fragments.TablaAdministrador;
import com.example.myfragmentmvp.Helpers.Helpers;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Carrera {
    public String nombre;
    public String periodo;
    public String codigo;
    private static Carrera carrera;
    private static String urlCarreras = Helpers.URL+ Enums.getCarreras;


    public static void newCarrera(final Carrera carrera,final FormCarrera view){
        RequestParams params = new RequestParams();
        params.put("name", carrera.nombre);
        params.put("periodo", carrera.periodo);
        params.put("codigo", carrera.codigo);
        AsyncHttpClient client = new AsyncHttpClient();
        String token = Login.token;
        client.addHeader("Authorization", "Token "+ token);
        client.post( urlCarreras, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("No se murió al eliminar");
                view.dialogNotificación("Nueva carrera",
                        "Nombre: "+ carrera.nombre +
                                " \n Periodo: " + carrera.periodo +
                                " \n Codigo: "+ carrera.codigo);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Se murió al Crear");
            }
        });
    }

    public static Carrera getCarrera(int id,final TablaAdministrador view){
        System.out.println("getCarrera");
        Carrera carrera = new Carrera();
        String token = Login.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get( urlCarreras+id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {
                        JSONObject response =new JSONObject(new String(responseBody));
                        Carrera ca1 = new Carrera();
                        ca1.nombre = response.getString("name");
                        ca1.periodo = response.getString("periodo");
                        ca1.codigo = response.getString("codigo");
                        view.dialogCarrera(ca1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onRetry(int retryNo) {
                System.out.println(retryNo);
            }
        });
        return carrera;
    }

    public static void getCarrera2(int id,final EditCarrera view){
        System.out.println("getCarrera2");
        final Carrera[] carrera2 = new Carrera[1];
        String token = Login.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get( urlCarreras+ id, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {
                        JSONObject response =new JSONObject(new String(responseBody));
                        view.etNombrec2.setText(String.valueOf(response.getString("name")));
                        view.etPerido2.setText(String.valueOf(response.getString("periodo")));
                        view.etCodigo2.setText(String.valueOf(response.getString("codigo")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    public static void deleteCarrera(int id, final TablaAdministrador view){
        String token = Login.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.delete( urlCarreras+ id, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("Se borró esa wea ");
                view.dialogNotificación("Eliminación","Se ha eliminado un alumno correctamente");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Se murió al eliminar");
            }
        });
    }

    public static void updateCarrera(int id,Carrera carrera){
        System.out.println("updateAlumno");
        RequestParams params = new RequestParams();
        params.put("name", carrera.nombre);
        params.put("periodo", carrera.periodo);
        params.put("codigo", carrera.codigo);
        AsyncHttpClient client = new AsyncHttpClient();
        String token = Login.token;
        client.addHeader("Authorization", "Token "+ token);
        client.put( urlCarreras+id, params, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("Se ha modificado correctamente ");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("No se modificó nada ");
            }
        });
    }

    public void cargarTabla(final TablaAdministrador view) {
        String token = Login.token;
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.addHeader("Authorization", "Token "+ token);
        cliente.get(Helpers.URL+ Enums.getCarreras,new JsonHttpResponseHandler(){
            @SuppressLint("ResourceType")
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("ESTO ES ONSUCCESS");
                Context con = view.getContext();
                try {
                    System.out.println("ESTO ES EL TRYYYYYY");
                    //System.out.println(response.toString());
                    TableRow tr_head = new TableRow(con);
                    tr_head.setId(10);
                    tr_head.setBackgroundColor(Color.GRAY);
                    TextView label_date = new TextView(con);
                    label_date.setId(20);
                    label_date.setText("ID");
                    label_date.setTextColor(Color.WHITE);
                    label_date.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_date);

                    TextView label_name = new TextView(con);
                    label_name.setId(20);
                    label_name.setText("Nombre");
                    label_name.setTextColor(Color.WHITE);
                    label_name.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_name);// add the column to the table row here
                    tr_head.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    TextView label_carrera = new TextView(con);
                    label_carrera.setId(21);
                    label_carrera.setText("Periodo");
                    label_carrera.setTextColor(Color.WHITE);
                    label_carrera.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_carrera);// add the column to the table row here

                    TextView label_mas = new TextView(con);
                    label_mas.setId(21);
                    label_mas.setText("Ver");
                    label_mas.setTextColor(Color.WHITE);
                    label_mas.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_mas);// add the column to the table row here

                    TextView label_del = new TextView(con);
                    label_del.setId(21);
                    label_del.setText("Del");
                    label_del.setTextColor(Color.WHITE);
                    label_del.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_del);// add the column to the table row here


                    TextView label_edit = new TextView(con);
                    label_edit.setId(21);
                    label_edit.setText("Edit");
                    label_edit.setTextColor(Color.WHITE);
                    label_edit.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_edit);// add the column to the table row here

                    view.tl.addView(tr_head, new TableLayout.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    for (int i = 0; i <= response.length()-1 ; i++){
                        System.out.println("forsito");
                        final JSONObject temp = response.getJSONObject(i);
                        System.out.println(temp.get("id").toString());
                        TableRow tr = new TableRow(con);
                        tr.setBackgroundColor(Color.WHITE);
                        tr.setId(100+i);
                        tr.setLayoutParams(new TableRow.LayoutParams(
                                TableRow.LayoutParams.FILL_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));

                        TextView labelID = new TextView(con);
                        labelID.setId(i+200);
                        labelID.setText(temp.get("id").toString());
                        labelID.setPadding(2, 5, 5, 0);
                        labelID.setTextColor(Color.BLACK);
                        tr.addView(labelID);

                        TextView labelNombre = new TextView(con);
                        labelNombre.setId(i+200);
                        labelNombre.setText(temp.get("name").toString());
                        labelNombre.setPadding(2, 5, 5, 0);
                        labelNombre.setTextColor(Color.BLACK);
                        tr.addView(labelNombre);


                        TextView labelCarrera = new TextView(con);
                        labelCarrera.setId(i+200);
                        labelCarrera.setText(temp.get("periodo").toString());
                        labelCarrera.setPadding(2, 0, 5, 0);
                        labelCarrera.setTextColor(Color.BLACK);
                        tr.addView(labelCarrera);


                        Button btnCosa = new Button(con);
                        btnCosa.setId(i+200);
                        btnCosa.setText("+");
                        btnCosa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String ids = temp.get("id").toString();
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");

                                    //getData(iden);
                                    getCarrera(Integer.parseInt(ids),view);
                                    System.out.println(temp.get("id").toString());
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+Helpers.select);
                                //Navegar a pagina de editar
                            }
                        });
                        tr.addView(btnCosa);

                        Button btnDel = new Button(con);
                        btnDel.setId(i+200);
                        btnDel.setText("-");
                        btnDel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String ids = temp.get("id").toString();
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");

                                    deleteCarrera(Integer.parseInt(ids),view);
                                    System.out.println(temp.get("id").toString());
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+Helpers.select);

                            }
                        });
                        tr.addView(btnDel);


                        Button btnEdit = new Button(con);
                        btnEdit.setId(i+200);
                        btnEdit.setText("E");
                        btnEdit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String ids = temp.get("id").toString();
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                    Helpers.select = Integer.parseInt(ids);
                                    System.out.println(temp.get("id").toString());
                                    Intent intent = new Intent(view.getContext() , EditCarrera.class);
                                    view.startActivity(intent);
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+Helpers.select);
                                //Navegar a pagina de editar
                            }
                        });
                        tr.addView(btnEdit);


                        view.tl.addView(tr, new TableLayout.LayoutParams(
                                TableRow.LayoutParams.FILL_PARENT,
                                TableRow.LayoutParams.WRAP_CONTENT));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable error, JSONObject response) {
                JSONObject arr = null;
                Context con = view.getContext();
                try {
                    Toast.makeText(con, "Error al obtener datos, quizá se murió el server", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

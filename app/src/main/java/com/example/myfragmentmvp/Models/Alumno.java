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

import com.example.myfragmentmvp.Enums.Enums;
import com.example.myfragmentmvp.Views.FormAlumno;
import com.example.myfragmentmvp.Fragments.TablaAlumnos;
import com.example.myfragmentmvp.Helpers.Helpers;
import com.example.myfragmentmvp.Views.EditAlumno;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class Alumno {

    public String id;
    public String nombre;
    public String apellidos;
    public String edad;
    public String sexo;
    public String direccion;
    public static String iden;
    private static Alumno alumno;

    private static String urlAlumnos = Helpers.URL+ Enums.getAlumnos;

    public static void newAlumno(final Alumno alumno,final FormAlumno view){
        RequestParams params = new RequestParams();
        params.put("name", alumno.nombre);
        params.put("lastname", alumno.apellidos);
        params.put("age", alumno.edad);
        params.put("address", alumno.direccion);
        params.put("gender", alumno.sexo);
        params.put("carrera", 1);
        AsyncHttpClient client = new AsyncHttpClient();
        String token = Login.token;
        client.addHeader("Authorization", "Token "+ token);
        client.post( urlAlumnos, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("No se murió al eliminar");
                view.dialogNotificación("Nuevo usuario",
                        "Nombre: "+ alumno.nombre +
                                " \n Apellido: " + alumno.apellidos +
                                " \n Edad: "+ alumno.edad+
                                " \n Sexo: "+ alumno.sexo+
                                " \n Direccion: "+ alumno.direccion);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                System.out.println("Se murió al Crear");
            }
        });
    }

    public static Alumno getAlumno(int id,final TablaAlumnos view){
        System.out.println("GetAlumno");
        Alumno alumno = new Alumno();
        String token = Login.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get( urlAlumnos+ id, new AsyncHttpResponseHandler() {
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
                        Alumno alumno1 = new Alumno();
                        alumno1.nombre = response.getString("name");
                        alumno1.apellidos = response.getString("lastname");
                        alumno1.edad = response.getString("age");
                        alumno1.sexo = response.getString("gender");
                        alumno1.direccion = response.getString("address");
                        alumno1.id = response.getString("id");
                        view.dialogAlumno(alumno1);
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
        //System.out.println(alumno.nombre+" "+alumno.apellidos+" "+alumno.edad+" "+alumno.direccion+" ");
        return alumno;
    }

    public static void getAlumno2(int id,final EditAlumno view){
        System.out.println("GetAlumno");
        final Alumno[] alumno2 = new Alumno[1];
        String token = Login.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get( urlAlumnos+ id, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("ESTADO:  " + statusCode);
                if(statusCode == 200){
                    try {
                        JSONObject response =new JSONObject(new String(responseBody));
                        view.etNombre2.setText(String.valueOf(response.getString("name")));
                        view.etApellido2.setText(String.valueOf(response.getString("lastname")));
                        view.etEdad2.setText(String.valueOf(response.getString("age")));
                        view.etGenero2.setText(String.valueOf(response.getString("gender")));
                        view.etDireccion2.setText(String.valueOf(response.getString("address")));
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

    public static void deleteAlumno(int id, final TablaAlumnos view){
        String token = Login.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.delete( Helpers.URL +Enums.getAlumnos+ id, new AsyncHttpResponseHandler() {

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

    public static void updateAlumno(int id,Alumno alumno){
        System.out.println("updateAlumno");
        RequestParams params = new RequestParams();
        params.put("name", alumno.nombre);
        params.put("lastname", alumno.apellidos);
        params.put("age", alumno.edad);
        params.put("address", alumno.direccion);
        params.put("gender", alumno.sexo);
        params.put("carrera", 1);
        AsyncHttpClient client = new AsyncHttpClient();
        String token = Login.token;
        client.addHeader("Authorization", "Token "+ token);
        client.put( urlAlumnos+id, params, new AsyncHttpResponseHandler() {

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

    public static void cargarTabla(final TablaAlumnos view){
        String token = Login.token;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "Token "+ token);
        client.get(Helpers.URL+ Enums.getAlumnos,new JsonHttpResponseHandler(){
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
                    label_name.setText("Name");
                    label_name.setTextColor(Color.WHITE);
                    label_name.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_name);// add the column to the table row here

                    TextView label_carrera = new TextView(con);
                    label_carrera.setId(21);
                    label_carrera.setText("Lastname");
                    label_carrera.setTextColor(Color.WHITE);
                    label_carrera.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_carrera);// add the column to the table row here

                    TextView label_mas = new TextView(con);
                    label_mas.setId(21);
                    label_mas.setText("More");
                    label_mas.setTextColor(Color.WHITE);
                    label_mas.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_mas);// add the column to the table row here

                    TextView label_del = new TextView(con);
                    label_del.setId(21);
                    label_del.setText("Delete");
                    label_del.setTextColor(Color.WHITE);
                    label_del.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_del);// add the column to the table row here

                    TextView label_edit = new TextView(con);
                    label_edit.setId(21);
                    label_edit.setText("Edit");
                    label_edit.setTextColor(Color.WHITE);
                    label_edit.setPadding(5, 5, 50, 5);
                    tr_head.addView(label_edit);// add the column to the table row here

                    tr_head.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.MATCH_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));




                    view.tl.addView(tr_head, new TableLayout.LayoutParams(
                            TableRow.LayoutParams.FILL_PARENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    for (int i = 0; i <= response.length()-1 ; i++){
                        System.out.println("forsito");
                        final JSONObject temp = response.getJSONObject(i);
                        System.out.println(temp.get("name").toString());
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
                        labelCarrera.setText(temp.get("lastname").toString());
                        labelCarrera.setPadding(2, 0, 5, 0);
                        labelCarrera.setTextColor(Color.BLACK);
                        tr.addView(labelCarrera);

                        Button btnCosa = new Button(con);
                        btnCosa.setWidth(6);
                        btnCosa.setId(i+200);
                        btnCosa.setText("+");
                        btnCosa.setBackgroundColor(Color.parseColor("#40c4ff"));
                        btnCosa.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String ids = temp.get("id").toString();
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                    iden = ids;
                                    view.getAlumno(Integer.parseInt(iden));
                                    System.out.println(temp.get("id").toString());
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+iden);
                                //Navegar a pagina de editar
                            }
                        });
                        tr.addView(btnCosa);

                        Button btnEliminar = new Button(con);
                        btnEliminar.setWidth(6);
                        btnEliminar.setId(i+200);
                        btnEliminar.setText("-");
                        btnEliminar.setBackgroundColor(Color.parseColor("#f44336"));
                        btnEliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String ids = temp.get("id").toString();
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                    iden = ids;
                                    view.deleteAlumno(Integer.parseInt(iden));
                                    System.out.println(temp.get("id").toString());
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+iden);
                                //Navegar a pagina de editar
                            }
                        });
                        tr.addView(btnEliminar);

                        Button btnEditar = new Button(con);
                        btnEditar.setWidth(6);
                        btnEditar.setId(i+200);
                        btnEditar.setText("E");
                        btnEditar.setBackgroundColor(Color.parseColor("#ffd600"));
                        btnEditar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    String ids = temp.get("id").toString();
                                    Helpers.select = Integer.parseInt(ids);
                                    Intent intent = new Intent(view.getContext() , EditAlumno.class);
                                    view.startActivity(intent);
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+iden);
                            }
                        });
                        tr.addView(btnEditar);


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
                    Toast.makeText(con, String.valueOf(error), Toast.LENGTH_LONG).show();
                    Toast.makeText(con, "Error al obtener datos, quizá se murió el server", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
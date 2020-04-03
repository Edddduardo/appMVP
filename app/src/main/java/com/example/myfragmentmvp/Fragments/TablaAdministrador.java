package com.example.myfragmentmvp.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import cz.msebera.android.httpclient.Header;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfragmentmvp.EditCarrera;
import com.example.myfragmentmvp.Enums.Enums;
import com.example.myfragmentmvp.Views.FormCarrera;
import com.example.myfragmentmvp.Helpers.Helpers;
import com.example.myfragmentmvp.HelpersServices.HelpersService;
import com.example.myfragmentmvp.Models.Carrera;
import com.example.myfragmentmvp.Models.Login;
import com.example.myfragmentmvp.Presenters.CarreraPresenter;
import com.example.myfragmentmvp.R;
import com.example.myfragmentmvp.Views.TablaPrincipal;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TablaAdministrador.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TablaAdministrador#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TablaAdministrador extends Fragment implements CarreraPresenter.View {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnNuevaCarrera;
    public static String iden="a";
    public TableLayout tl;
    private static TablaPrincipal mCurrentInstance;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TablaAdministrador() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TablaAdministrador.
     */
    // TODO: Rename and change types and number of parameters
    public static TablaAdministrador newInstance(String param1, String param2) {
        TablaAdministrador fragment = new TablaAdministrador();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static Context context() {
        return mCurrentInstance.getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tabla_administrador, container, false);
        btnNuevaCarrera = view.findViewById(R.id.btnEditarCarrera);
        btnNuevaCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getContext() , FormCarrera.class);
                    startActivity(intent);
                }catch (Exception e){}
                System.out.println("Se eligió el id "+iden);
                //Navegar a pagina de editar
            }
        });
        tl = (TableLayout) view.findViewById(R.id.tl);
        cargarTabla();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void getData(String id){
        AsyncHttpClient cliente = HelpersService.getClientToken();
        cliente.get(Helpers.URL+Enums.getCarreras+id, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                System.out.println("-------------------------");
                String x = new String(responseBody);
                System.out.println(x);
                System.out.println("-------------------------");
                AlertDialog.Builder alerta = new AlertDialog.Builder(getView().getContext());
                alerta.setMessage(x);
                alerta.setCancelable(false);
                alerta.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onFinish();
                    }
                });
                AlertDialog titlulo = alerta.create();
                titlulo.show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    @Override
    public void cargarTabla() {
        String token = Login.token;
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.addHeader("Authorization", "Token "+ token);
        cliente.get(Helpers.URL+ Enums.getCarreras,new JsonHttpResponseHandler(){
            @SuppressLint("ResourceType")
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                System.out.println("ESTO ES ONSUCCESS");
                Context con = getView().getContext();
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

                    tl.addView(tr_head, new TableLayout.LayoutParams(
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
                                    iden = ids;
                                    //getData(iden);
                                    getCarrera(Integer.parseInt(ids));
                                    System.out.println(temp.get("id").toString());
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+iden);
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
                                    iden = ids;
                                    deleteCarrera(Integer.parseInt(ids));
                                    System.out.println(temp.get("id").toString());
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+iden);

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
                                    iden = ids;
                                    Helpers.select = Integer.parseInt(ids);
                                    System.out.println(temp.get("id").toString());
                                    Intent intent = new Intent(getContext() , EditCarrera.class);
                                    startActivity(intent);
                                    System.out.println(".-..-.-.-.-.-.-..-.-.-");
                                }catch (Exception e){}
                                System.out.println("Se eligió el id "+iden);
                                //Navegar a pagina de editar
                            }
                        });
                        tr.addView(btnEdit);


                        tl.addView(tr, new TableLayout.LayoutParams(
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
                Context con = getView().getContext();
                try {
                    Toast.makeText(con, "Error al obtener datos, quizá se murió el server", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void getCarrera(int id) {
        Carrera.getCarrera(id,this);
    }

    @Override
    public void dialogCarrera(Carrera carrera) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Información del alumno seleccionado")
                .setMessage("Nombre: "+ carrera.nombre +
                        " \n Periodo: " + carrera.periodo +
                        " \n Codigo: "+ carrera.codigo);
        builder.create().show();
    }

    @Override
    public void deleteCarrera(int id) {
        Carrera.deleteCarrera(id,this);
    }

    @Override
    public void dialogNotificación(String titulo, String texto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titulo)
                .setMessage(texto);
        builder.create().show();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

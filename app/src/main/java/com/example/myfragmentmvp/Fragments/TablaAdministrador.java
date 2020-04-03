package com.example.myfragmentmvp.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.myfragmentmvp.Views.FormCarrera;
import com.example.myfragmentmvp.Models.Carrera;
import com.example.myfragmentmvp.Presenters.CarreraPresenter;
import com.example.myfragmentmvp.R;
import com.example.myfragmentmvp.Views.TablaPrincipal;

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
        btnNuevaCarrera.setBackgroundColor(Color.parseColor("#33691e"));
        btnNuevaCarrera.setTextColor(Color.parseColor("#f9f9f9"));
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




    @Override
    public void cargarTabla() {
        Carrera.cargarTabla(this);
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

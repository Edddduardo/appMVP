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

import com.example.myfragmentmvp.Views.FormAlumno;
import com.example.myfragmentmvp.Models.Alumno;
import com.example.myfragmentmvp.Presenters.AlumnoPresenter;
import com.example.myfragmentmvp.R;

public class TablaAlumnos extends Fragment implements AlumnoPresenter.View{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Button btnNuevoAlumno;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static String iden="a";
    public static TableLayout tl;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    AlumnoPresenter present;
    private OnFragmentInteractionListener mListener;

    public TablaAlumnos() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TablaAlumnos newInstance(String param1, String param2) {
        TablaAlumnos fragment = new TablaAlumnos();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        present = new AlumnoPresenter(this);
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_tabla_alumnos, container, false);
        btnNuevoAlumno = view.findViewById(R.id.btnEditarCarrera);
        btnNuevoAlumno.setBackgroundColor(Color.parseColor("#33691e"));
        btnNuevoAlumno.setTextColor(Color.parseColor("#f9f9f9"));
        btnNuevoAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(getContext() , FormAlumno.class);
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
        Alumno.cargarTabla(this);
    }

    @Override
    public void getAlumno(int id) {
        Alumno.getAlumno(id,this);
    }

    @Override
    public void dialogAlumno(Alumno alumno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Información del alumno seleccionado")
                .setMessage("Nombre: "+ alumno.nombre +
                        " \n Apellido: " + alumno.apellidos +
                        " \n Edad: "+ alumno.edad+
                        " \n Sexo: "+ alumno.sexo+
                        " \n Direccion: "+ alumno.direccion);

        builder.create().show();
    }

    @Override
    public void deleteAlumno(int id) {
        Alumno.deleteAlumno(id,this);
    }

    @Override
    public void dialogNotificación(String titulo, String texto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(titulo)
                .setMessage(texto);
        builder.create().show();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

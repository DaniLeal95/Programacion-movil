package com.iesnervion.dleal.examensegundaevaluacion.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.iesnervion.dleal.examensegundaevaluacion.R;
import com.iesnervion.dleal.examensegundaevaluacion.SqLite.UtilidadesBBDD;
import com.iesnervion.dleal.examensegundaevaluacion.dummy.Persona;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailEditPersona.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailEditPersona#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailEditPersona extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView nombre,edad,telefono;
    private RadioButton hombre,mujer;
    private Button aceptar;

    private char sexo;

    public DetailEditPersona() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailEditPersona.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailEditPersona newInstance(String param1, String param2) {
        DetailEditPersona fragment = new DetailEditPersona();
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
        View v =  inflater.inflate(R.layout.fragment_detail_edit_persona, container, false);
        sexo = ' ';
        this.nombre = (TextView) v.findViewById(R.id.txtnombrefragment);
        this.edad = (TextView) v.findViewById(R.id.txtEdadfragment);
        this.telefono = (TextView) v.findViewById(R.id.txttelefonofragment);
        this.hombre = (RadioButton) v.findViewById(R.id.rbhombre);
        this.mujer = (RadioButton) v.findViewById(R.id.rbmujer);
        this.aceptar = (Button) v.findViewById(R.id.btnAceptar);
        aceptar.setOnClickListener(this);
        hombre.setOnCheckedChangeListener(this);
        mujer.setOnCheckedChangeListener(this);
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        UtilidadesBBDD u = new UtilidadesBBDD(v.getContext());
        if(this.nombre.getText()!="" && sexo!=' ' ) {
            Persona p = new Persona(-1, this.nombre.getText().toString(), Integer.parseInt(edad.getText().toString()), telefono.getText().toString(), sexo);
            u.insertPersona(p);
            nombre.setText("");
            edad.setText("");
            telefono.setText("");
            hombre.setChecked(false);
            mujer.setChecked(false);

        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            switch (buttonView.getId()) {
                case R.id.rbhombre:
                    sexo = 'H';
                    break;
                case R.id.rbmujer:
                    sexo = 'M';
                    break;
            }
        }
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

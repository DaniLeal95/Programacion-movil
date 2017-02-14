package com.iesnervion.dleal.appfebrerobar.Fragments;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.MiarrayAdapterCuenta;
import com.iesnervion.dleal.appfebrerobar.Callbacks.PrincipalCallBack;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.R;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.datos.Listados;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CuentaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentaFragment extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Producto> productos = null;
    private Customfont lblnumMesa,lblPrecio;
    private CuentaFragment main=this;
    private Cuenta cuenta;
    private int idcuenta;
    private int nummesa;
    private String nombremesa;

    private OnFragmentInteractionListener mListener;

    public CuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cuenta, container, false);

        Utilidades utilidades = new Utilidades(v.getContext());

        Intent i= getActivity().getIntent();
        Bundle bundle=i.getExtras();



        nummesa=(Integer) bundle.get("nummesa");

        this.cuenta=utilidades.getCuenta();

        lblnumMesa = (Customfont) v.findViewById(R.id.lblnumMesa);
        lblPrecio = (Customfont) v.findViewById(R.id.lblprecio);



        //TODO : Cambiar esto a la cuenta original que me de la api

        //Cuenta c = ((Listados) getActivity().getApplication()).getCuenta();


        //double preciofinal= Math.floor(c.getPreciofinal()*100)/100;
        //lblPrecio.setText(""+c.ge+"€");



        lblnumMesa.setText(""+lblnumMesa.getText()+" "+String.valueOf(nummesa));
        if(bundle.get("nombreCuenta")!=null){
            nombremesa=(String) bundle.get("nombreCuenta");
            lblnumMesa.setText(""+lblnumMesa.getText()+" - "+nombremesa);
        }


        setListAdapter(new MiarrayAdapterCuenta(v.getContext(),R.layout.cuenta,cuenta.getDetallesCuentas()));
        return v;
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


//Esto ya no es necesario, PERO POR SI ACASO
/*
        //Droga dura que entiendo yo
        //Ordenamos el list
        for (int i=0;i<productos.size();i++){
            boolean encontrado = false;
            int uds=0;
            //Comprobamos que el producto a insertar no este insertado ya.
            for(int j=0;j< productosordenados.size()&& !encontrado;j++) {
                if(productosordenados.get(j).getIdproducto()==productos.get(i).getIdproducto()) {
                    encontrado = true;
                }
            }

            //Si no esta lo insertamos en el listOrdenado
            if(!encontrado){
                productosordenados.add(productos.get(i));



            //Ahora contamos las uds de cada producto
            for (int j=0;j<productos.size();j++){
                if(productos.get(i).getIdproducto()==productos.get(j).getIdproducto()){
                    uds++;
                }

            }


            cantidades.add(uds);
            }
        }
*/
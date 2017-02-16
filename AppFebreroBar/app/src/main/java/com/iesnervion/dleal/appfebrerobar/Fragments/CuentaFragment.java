package com.iesnervion.dleal.appfebrerobar.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.ArrayAdapteryViewHolder.MiarrayAdapterCuenta;
import com.iesnervion.dleal.appfebrerobar.Callbacks.ObtenerCuentaFragmentCallback;
import com.iesnervion.dleal.appfebrerobar.Callbacks.ObtenerCuentaLoginCallback;
import com.iesnervion.dleal.appfebrerobar.InterfacesApi.IBar;
import com.iesnervion.dleal.appfebrerobar.R;
import com.iesnervion.dleal.appfebrerobar.Utilidades.Utilidades;
import com.iesnervion.dleal.appfebrerobar.customfont.Customfont;
import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

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
public class CuentaFragment extends ListFragment implements DialogInterface.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AlertDialog dialog;
    private boolean hayconexion;

    private List<Producto> productos = null;
    private Customfont lblnumMesa,lblPrecio;
    private CuentaFragment main=this;
    private int nummesa;
    private String nombremesa;
    SwipeRefreshLayout mSwipeRefreshLayout;


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
        if(bundle.get("nummesa")!=null)
            nummesa=(Integer) bundle.get("nummesa");


        //this.cuenta=utilidades.getCuenta();


        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.activity_main_swipe_refresh_layout);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //refreshContent();
                main.getCuenta(main.nummesa);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        lblnumMesa = (Customfont) v.findViewById(R.id.lblnumMesa);
        lblPrecio = (Customfont) v.findViewById(R.id.lblprecio);





        lblnumMesa.setText(""+lblnumMesa.getText()+" "+String.valueOf(nummesa));
        if(bundle.get("nombreCuenta")!=null){
            nombremesa=(String) bundle.get("nombreCuenta");
            lblnumMesa.setText(""+lblnumMesa.getText()+" - "+nombremesa);
        }


        hayconexion= utilidades.hasActiveInternetConnection();
        //Llamada a la api

        if(hayconexion)this.getCuenta(this.nummesa);
        else {
            dialog = new AlertDialog.Builder(main.getContext())
                    .setTitle("Conexion fallida")
                    .setMessage("Por favor, revisa tu conexion.")
                    .setPositiveButton("Reintentar", this)
                    .create();
            dialog.show();
        }


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


    //LLAMADA API

    public void getCuenta(int nummesa){
        Retrofit retrofit;

        ObtenerCuentaFragmentCallback adminCallback = new ObtenerCuentaFragmentCallback(main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://dleal.ciclo.iesnervion.es/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IBar adminInter = retrofit.create(IBar.class);
        String base64 = codifica64();
        adminInter.getCuentaxmesa(nummesa,base64).enqueue(adminCallback);
    }
    public String codifica64() {

        String credentials = "user:user";
        String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
        //String auth ="Basic dXNlcjp1c2Vy";
        return auth;
    }
    public void esperaCuenta(Cuenta cuenta){

        double preciofinal =Math.floor(cuenta.getPreciofinal()*100)/100;
        lblPrecio.setText(preciofinal+"€");

        ArrayAdapter arrayAdapter=new MiarrayAdapterCuenta(this.getContext(),R.layout.cuenta,cuenta.getDetallesCuentas());
        this.setListAdapter(arrayAdapter);
    }


    //TODO HACER ESTA FUNCIONALIDAD
    public void cuentaExpirada(){
        Toast.makeText(this.getContext(),"Su cuenta ha expirado",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Utilidades u = new Utilidades(this.main.getContext());
        hayconexion = u.hasActiveInternetConnection();
        if(!hayconexion) {
            dialog = new AlertDialog.Builder(main.getContext())
                    .setTitle("Conexion fallida")
                    .setMessage("Por favor, revisa tu conexion.")
                    .setPositiveButton("Reintentar", this)
                    .create();
            dialog.show();
        }
        else{
            this.getCuenta(this.nummesa);
        }
    }

}


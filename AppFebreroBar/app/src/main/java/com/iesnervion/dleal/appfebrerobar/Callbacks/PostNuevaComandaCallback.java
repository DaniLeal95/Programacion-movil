package com.iesnervion.dleal.appfebrerobar.Callbacks;

import android.widget.Toast;

import com.iesnervion.dleal.appfebrerobar.NuevaComanda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dleal on 15/02/17.
 */

public class PostNuevaComandaCallback implements Callback<Object> {
    private NuevaComanda main;
    public PostNuevaComandaCallback(NuevaComanda main){this.main=main;}

    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {

        main.recogerCuentaApi();
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        Toast.makeText(main.getApplicationContext(),t.toString(),Toast.LENGTH_SHORT).show();
    }
}

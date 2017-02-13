package com.iesnervion.dleal.appfebrerobar.Callbacks;

import android.util.JsonReader;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.iesnervion.dleal.appfebrerobar.LoginMesa;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Dani on 13/02/2017.
 */

public class PostCuentaCallback implements Callback<Object> {
    private LoginMesa main;

    public PostCuentaCallback(LoginMesa main) {
        this.main = main;
    }


    @Override
    public void onResponse(Call<Object> call, Response<Object> response) {
        int id=0 ;

        try {
            JSONObject jsonObj = new JSONObject(response.body().toString());

            id=jsonObj.getInt("idcuenta");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(main.getApplicationContext(),""+id,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure(Call<Object> call, Throwable t) {
        Toast.makeText(main.getApplicationContext(),toString(),Toast.LENGTH_SHORT).show();
    }
}

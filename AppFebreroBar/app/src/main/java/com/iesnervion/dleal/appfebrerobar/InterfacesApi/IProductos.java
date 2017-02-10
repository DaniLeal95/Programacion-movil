package com.iesnervion.dleal.appfebrerobar.InterfacesApi;

import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.http.GET;
//import retrofit2.http.Header;
//import retrofit2.http.Path;

/**
 * Created by dleal on 9/02/17.
 */

public interface IProductos {
    @GET("Producto")
    Call<List<Producto>> getProductos(@Header("Authorization") String  base64);
}

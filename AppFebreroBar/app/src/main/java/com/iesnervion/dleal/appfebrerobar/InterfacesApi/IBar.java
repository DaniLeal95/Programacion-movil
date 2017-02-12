package com.iesnervion.dleal.appfebrerobar.InterfacesApi;

import com.iesnervion.dleal.appfebrerobar.model.Cuenta;
import com.iesnervion.dleal.appfebrerobar.model.DetallesCuenta;
import com.iesnervion.dleal.appfebrerobar.model.Mesa;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

public interface IBar {
    @GET("Producto")
    Call<List<Producto>> getProductos(@Header("Authorization") String  base64);

    @GET("Mesa")
    Call<List<Mesa>> getMesas(@Header("Authorization")String base64);

    @GET("Cuenta/{id}/")
    Call<Cuenta> getCuenta(@Path("id") Integer id, @Header("Authorization")String base64);

    @POST("Cuenta")
    Call<Integer> postCuenta(@Header("Authorization")String base64,@Body Cuenta cuenta);

    @PUT("Cuenta/{id}/DetallesCuenta")
    void putDetallesCuenta(@Header("Authorization")String base64, @Body List<DetallesCuenta> detallesCuentas);

}

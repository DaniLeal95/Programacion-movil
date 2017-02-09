package com.iesnervion.dleal.appfebrerobar.Callbacks;

import com.iesnervion.dleal.appfebrerobar.Inicial;
import com.iesnervion.dleal.appfebrerobar.model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by dleal on 9/02/17.
 */

public class ProductosCallback implements Callback<List<Producto>> {

    private List<Producto> productos;
    private Inicial main;

    public ProductosCallback(Inicial main) {
        this.main = main;
    }

    @Override
    public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
        productos = response.body();
        main.rellenaProductos(this);
    }

    @Override
    public void onFailure(Call<List<Producto>> call, Throwable t) {
        int i = 0;
        i++;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    public Inicial getMain() {
        return main;
    }

    public void setMain(Inicial main) {
        this.main = main;
    }
}

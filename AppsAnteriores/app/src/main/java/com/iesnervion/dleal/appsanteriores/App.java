package com.iesnervion.dleal.appsanteriores;

/**
 * Created by dleal on 16/11/16.
 */

public class App {

    private String url;
    private int image;
    private String titulo;

    public App(){
        url="";
        image=0;
        titulo="";
    }

    public App(String url,int image,String titulo){
        this.url=url;
        this.image=image;
        this.titulo=titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

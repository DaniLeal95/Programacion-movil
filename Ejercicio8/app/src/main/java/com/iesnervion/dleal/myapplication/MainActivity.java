package com.iesnervion.dleal.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button uno;
    private Button dos;
    private Button tres;
    private Button cuatro;
    private Button cinco;
    private Button seis;
    private Button siete;
    private Button ocho;
    private Button nueve;
    private Button cero;
    private Button sumar;
    private Button restar;
    private Button multiplicacion;
    private Button division;
    private Button igual;
    private Button punto;
    private Button atras;
    private TextView texto;

    private Vector<String> calculo=new Vector(0,1);
    private String resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uno=(Button)findViewById(R.id.uno);
        dos=(Button)findViewById(R.id.dos);
        tres=(Button)findViewById(R.id.tres);
        cuatro=(Button)findViewById(R.id.cuatro);
        cinco=(Button)findViewById(R.id.cinco);
        seis=(Button)findViewById(R.id.seis);
        siete=(Button)findViewById(R.id.siete);
        ocho=(Button)findViewById(R.id.ocho);
        nueve=(Button)findViewById(R.id.nueve);
        cero=(Button)findViewById(R.id.cero);
        sumar=(Button)findViewById(R.id.sumar);
        restar=(Button)findViewById(R.id.restar);
        multiplicacion=(Button)findViewById(R.id.multiplicar);
        division=(Button)findViewById(R.id.dividir);
        igual=(Button)findViewById(R.id.igual);
        punto=(Button)findViewById(R.id.punto);
        atras=(Button)findViewById(R.id.borrar);

        texto=(TextView)findViewById(R.id.texto);


        uno.setOnClickListener(this);
        dos.setOnClickListener(this);
        tres.setOnClickListener(this);
        cuatro.setOnClickListener(this);
        cinco.setOnClickListener(this);
        seis.setOnClickListener(this);
        siete.setOnClickListener(this);
        ocho.setOnClickListener(this);
        nueve.setOnClickListener(this);
        cero.setOnClickListener(this);texto.setText(texto.getText().toString().concat("7"));

        sumar.setOnClickListener(this);
        restar.setOnClickListener(this);
        multiplicacion.setOnClickListener(this);
        division.setOnClickListener(this);
        igual.setOnClickListener(this);
        punto.setOnClickListener(this);
        atras.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.uno:

                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("1"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    this.calculo.add(texto.getText().toString());
                    texto.setText("1");
                }
                else{
                    texto.setText("1");
                }

                break;
            case R.id.dos:

                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("2"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("2");
                }
                else{
                    texto.setText("2");
                }

                break;
            case R.id.tres:

                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("3"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("3");
                }
                else{
                    texto.setText("3");
                }
                break;
            case R.id.cuatro:

                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("4"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("4");
                }
                else{
                    texto.setText("4");
                }

                break;
            case R.id.cinco:
                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("5"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("5");
                }
                else{
                    texto.setText("5");
                }
                break;
            case R.id.seis:
                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("6"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("6");
                }
                else{
                    texto.setText("6");
                }
                break;
            case R.id.siete:
                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("7"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("7");
                }
                else{
                    texto.setText("7");
                }
                break;
            case R.id.ocho:
                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("8"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("8");
                }
                else{
                    texto.setText("8");
                }
                break;
            case R.id.nueve:
                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("9"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("9");
                }
                else{
                    texto.setText("9");
                }
                break;
            case R.id.cero:
                if(texto.getText()!="" && texto.getText()!="+" && texto.getText()!="-" && texto.getText()!="*" && texto.getText()!="/"){
                    texto.setText(texto.getText().toString().concat("0"));

                }
                else if(texto.getText()=="+" || texto.getText()=="-" || texto.getText()=="*" || texto.getText()=="/"){
                    calculo.add(texto.getText().toString());
                    texto.setText("0");
                }
                else{
                    texto.setText("0");
                }
                break;

            case R.id.sumar:
                if(texto.getText()!="-" && texto.getText()!="+" && texto.getText()!="*" && texto.getText()!="/") {
                    calculo.add(texto.getText().toString());
                }
                texto.setText("+");

                break;
            case R.id.restar:
                if(texto.getText()!="-" && texto.getText()!="+" && texto.getText()!="*" && texto.getText()!="/") {
                    calculo.add(texto.getText().toString());
                }
                texto.setText("-");
                break;
            case R.id.multiplicar:
                if(texto.getText().toString()!="-" && texto.getText().toString()!="+" && texto.getText().toString()!="*" && texto.getText().toString()!="/") {
                    this.calculo.add(texto.getText().toString());
                }
                texto.setText("*");
                break;
            case R.id.dividir:
                if(texto.getText()!="-" && texto.getText()!="+" && texto.getText()!="*" && texto.getText()!="/") {
                    calculo.add(texto.getText().toString());
                }
                texto.setText("/");
                break;

            case R.id.igual:
                if(texto.getText()!="-" && texto.getText()!="+" && texto.getText()!="*" && texto.getText()!="/") {
                    calculo.add(texto.getText().toString());
                }
                resultado=this.Calcular();
                texto.setText(resultado);
                break;
            case R.id.punto:
                break;
            case R.id.borrar:
                if(texto.getText()!=""){

                }
                break;
        }
    }

    public String Calcular(){
        int resultado=0;

        for(int i=0;i<this.calculo.size();i=i+3){

            if(i%2==0){
                switch(this.calculo.elementAt(i+1)){
                    case "+":
                        resultado=Integer.parseInt(calculo.elementAt(i))+Integer.parseInt(calculo.elementAt(i+2));
                        break;
                    case "-":
                        resultado=Integer.parseInt(calculo.elementAt(i))-Integer.parseInt(calculo.elementAt(i+2));
                        break;
                    case "*":
                        resultado=Integer.parseInt(calculo.elementAt(i))*Integer.parseInt(calculo.elementAt(i+2));
                        break;
                    case "/":
                        resultado=Integer.parseInt(calculo.elementAt(i))/Integer.parseInt(calculo.elementAt(i+2));
                        break;
                }

            }
            else{
                switch(this.calculo.elementAt(i)){
                    case "+":
                        resultado+=Integer.parseInt(calculo.elementAt(i+1));
                        break;
                    case "-":
                        resultado-=Integer.parseInt(calculo.elementAt(i+1));
                        break;
                    case "*":
                        resultado*=Integer.parseInt(calculo.elementAt(i+1));
                        break;
                    case "/":
                        resultado/=Integer.parseInt(calculo.elementAt(i+1));
                        break;
                }
            }
        }

        return String.valueOf(resultado);
    }
}

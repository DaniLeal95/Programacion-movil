package com.iesnervion.dleal.nbaficheros.Utilidades;

import android.content.Context;


import com.iesnervion.dleal.nbaficheros.model.Jugador;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

/**
 * Created by dleal on 15/12/16.
 */

public class Utilidades {
    public static Context context;

    public Utilidades(Context context1){

        context = context1;
    }

    /*
           * Estudio de la interfaz de CogerUltimaID
           *
           * Comentario:
           * 	El metodo recogera la Id escrita en ese fichero(long) y la devolvera asociada al nombre
           * Cabecera:
           * 	long CogerUltimaID()
           * Precondiciones:Nada
           *
           * Entradas:Nada
           * Salidas:un long (ID)
           * Postcondiciones:
           * 	El long retornara asociado al nombre -> Funcion
           * */
    public Vector<Jugador> getJugadores(String nombreFichero){


        File f=new File(context.getFilesDir(),nombreFichero);
        Vector<Jugador> jugadores=new Vector<>(0,1);

        if(f.exists()){						//Si el archivo existe pues leemos de el la id
            FileInputStream fis=null;
            ObjectInputStream ois=null;
            try{
                fis=new FileInputStream(f);
                ois=new ObjectInputStream(fis);


                long ultimaid=this.cogerUltimaId("idjugadores.dat");
                for (long i=0;i<ultimaid;i++){
                    Jugador jugador=(Jugador)ois.readObject();
                    jugadores.add(jugador);

                }
            }catch(EOFException eofe){

            }catch(IOException ioe){
                System.out.println(ioe);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally{
                try{
                    ois.close();
                }catch(IOException ioe){
                    System.out.println(ioe);
                }
            }
        }// fin if


    return jugadores;
    }


    /*Escribir Fichero ClienteMaestro
     * 	Breve comentario:
     * 		El metodo escribirá en el fichero ClienteMaestro un objeto clienteImp que recibirá por parametros
     * 	Cabecera:
     * 		void escribirCliente(ClienteImp cliente)
     * 	Precondiciones:
     * 		Nada
     * 	Entradas:
     * 		un objeto ClienteImp
     * 	Salidas:
     * 		Nada
     * 	Postcondiciones:
     * 		Escribirá el objeto en el fichero.
     * */
    public void escribirJugador(Vector<Jugador> jugadores){

        
        File f=null;
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        try{

            f=new File(context.getFilesDir(),"jugadores.dat");
            fos=new FileOutputStream(f,false);
            oos=new ObjectOutputStream(fos);



			for (int i=0;i<jugadores.size();i++){
                oos.writeObject(jugadores.elementAt(i));
            }


        }catch(IOException ioe){
            System.out.println(ioe);
        }finally{

            try{
                if(oos!=null){
                    oos.close();
                    fos.close();
                }
            }catch(IOException ioe){
                System.out.println(ioe);
            }

        }
    }

	/*
	   * Estudio de la interfaz de escribirUltimaID
	   *
	   * Comentario:
	   * 	El metodo sobreescribir� lacogerUltimaId Id escrita en el fichero
	   * Cabecera:
	   * 	void escribirUltimaID(long id)
	   * Precondiciones:Nada
	   *
	   * Entradas:Nada
	   * Salidas:sobreescribira el fichero
	   * Postcondiciones:
	   * 	nada
	   * */

    public void escribirUltimaId(long id, String nombreFichero){
        File f=null;
        FileOutputStream fos= null;
        ObjectOutputStream oos=null;

        try{
            f=new File(context.getFilesDir(),nombreFichero);
            fos=new FileOutputStream(f,false);
            oos=new ObjectOutputStream(fos);

            oos.writeLong(id);
        }catch(IOException ioe){
            System.out.println(ioe);
        }finally{
            try{
                oos.close();
            }catch(IOException ioe){
                System.out.println(ioe);
            }
        }

    }

    /*
       * Estudio de la interfaz de CogerUltimaID
       *
       * Comentario:
       * 	El metodo recogera la Id escrita en ese fichero(long) y la devolvera asociada al nombre
       * Cabecera:
       * 	long CogerUltimaID()
       * Precondiciones:Nada
       *
       * Entradas:Nada
       * Salidas:un long (ID)
       * Postcondiciones:
       * 	El long retornara asociado al nombre -> Funcion
       * */
    public long cogerUltimaId(String nombreFichero){
        long id=0;
        File f=new File(context.getFilesDir(),nombreFichero);


        if(f.exists()){						//Si el archivo existe pues leemos de el la id
            FileInputStream fis=null;
            ObjectInputStream ois=null;
            try{
                fis=new FileInputStream(f);
                ois=new ObjectInputStream(fis);

                long aux=ois.readLong();
                while(aux!=-1){
                    id=aux;
                    aux=ois.readLong();
                }
            }catch(EOFException eofe){

            }catch(IOException ioe){
                System.out.println(ioe);
            }
            finally{
                try{
                    ois.close();
                }catch(IOException ioe){
                    System.out.println(ioe);
                }
            }
        }// fin if


        return id;
    }

}

package br.edu.ctup.bestreads.DAO;

import android.content.Context;
import android.database.Cursor;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.edu.ctup.bestreads.Model.Pasta;

public class PastaDAO {
    public static long cadastrarPasta(Context context, Pasta pasta){
        HelperDAO helper = new HelperDAO(context);
        return helper.cadastrarPasta(pasta);
    }

    public  static ArrayList<Pasta> listarPasta (Context context){
        HelperDAO helper = new HelperDAO(context);
        Cursor cursor = helper.listarPasta();
        ArrayList<Pasta> pastas = new ArrayList<>();
        String [] nomePasta;
        if(cursor.getColumnCount()>0){
            do{
               //pastas.add(cursor.getColumnNames("idPasta"));
            }while (cursor.moveToNext());
        }
        return  null;
    }
}

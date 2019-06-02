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

    public static ArrayList<Pasta> listarPastas(Context context){
        HelperDAO helper = new HelperDAO(context);
        return helper.listarPastas();
    }
}

package br.edu.ctup.bestreads.DAO;

import android.content.Context;
import android.database.Cursor;
import android.view.View;

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

    public static long excluirPasta(Context context, Pasta pasta){
        HelperDAO helper = new HelperDAO(context);
        return helper.removerPasta(pasta);
    }

    public static long editarPasta(Context context, Pasta pasta){
        HelperDAO helper = new HelperDAO(context);
        return helper.alterarPasta(pasta);
    }
}

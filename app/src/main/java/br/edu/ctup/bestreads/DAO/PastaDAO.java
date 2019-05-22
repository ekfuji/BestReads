package br.edu.ctup.bestreads.DAO;

import android.content.Context;

import br.edu.ctup.bestreads.Model.Pasta;

public class PastaDAO {
    public static long cadastrarPasta(Context context, Pasta pasta){
        HelperDAO helper = new HelperDAO(context);
        return helper.cadastrarPasta(pasta);
    }
}

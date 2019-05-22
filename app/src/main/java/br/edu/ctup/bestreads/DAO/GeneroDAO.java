package br.edu.ctup.bestreads.DAO;

import android.content.Context;

import br.edu.ctup.bestreads.Model.Genero;

public class GeneroDAO {

    public static long cadastrarGenero(Context context, Genero genero){
        HelperDAO helper = new HelperDAO(context);
        return helper.cadastrarGenero(genero);
    }

}

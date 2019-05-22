package br.edu.ctup.bestreads.DAO;

import android.content.Context;

import br.edu.ctup.bestreads.Model.Autor;

public class AutorDAO {

    public static long cadastrarAutor(Context context, Autor autor){
        HelperDAO helper = new HelperDAO(context);
        return helper.cadastrarAutor(autor);
    }
}

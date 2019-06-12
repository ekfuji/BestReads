package br.edu.ctup.bestreads.DAO;

import android.content.Context;

import java.util.ArrayList;

import br.edu.ctup.bestreads.Model.Livro;

public class LivroDAO {

    public static long cadastrarPasta(Context context, Livro livro){
        HelperDAO helper = new HelperDAO(context);
        return helper.cadastrarLivro(livro);
    }

    public static ArrayList<Livro> listarLivros(Context context){
        HelperDAO helper = new HelperDAO(context);
        return helper.listarLivros();
    }
}

package br.edu.ctup.bestreads.DAO;

import android.content.Context;

import java.text.Normalizer;
import java.util.ArrayList;

import br.edu.ctup.bestreads.Model.Acervo;
import br.edu.ctup.bestreads.Model.Livro;

public class LivroDAO {

    public static long cadastrarLivro(Context context, Livro livro){
        HelperDAO helper = new HelperDAO(context);
        return helper.cadastrarLivro(livro);
    }

    public static ArrayList<Livro> listarLivros(Context context){
        HelperDAO helper = new HelperDAO(context);
        return helper.listarLivros();
    }

    public static ArrayList<Livro> listarLivrosPorPasta(Context context, int idPasta){
        HelperDAO helper = new HelperDAO(context);
        return helper.listarLivrosPorPasta(idPasta);
    }

    public static long cadastarLivroAcervo(Context context, Acervo acervo){
        HelperDAO helper = new HelperDAO(context);
        return helper.cadastrarAcervo(acervo);
    }

    public static Livro buscarLivroPorNome(Context context, String nomeLivro){
        HelperDAO helper = new HelperDAO(context);
        return helper.buscarLivroPorNome(nomeLivro);
    }

    public static Livro BuscarLivroPorId(Context context, int idLivro){
        HelperDAO helper = new HelperDAO(context);
        return helper.buscarLivroPorId(idLivro);
    }

    public static long editarLivro(Context context, Livro livro){
        HelperDAO helper = new HelperDAO(context);
        return helper.alterarLivro(livro);
    }

    public static long excluirLivro(Context context, Livro livro) {
        HelperDAO helper = new HelperDAO(context);
        return helper.removerLivro(livro);
    }

    public static Livro buscarLivroAutorGenero(Context context, int idLivro){
        HelperDAO helper = new HelperDAO(context);
        Livro livro = LivroDAO.BuscarLivroPorId(context,idLivro);
        return helper.buscarLivroAutorGenero(livro);
    }

}
package br.edu.ctup.bestreads.DAO;

import android.content.Context;

import java.text.Normalizer;

import br.edu.ctup.bestreads.Model.Autor;

public class AutorDAO {

    public static long cadastrarAutor(Context context, Autor autor){
        HelperDAO helper = new HelperDAO(context);
        autor.setNomeAutor(Normalizer.normalize(autor.getNomeAutor(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toUpperCase());
        autor.setLocalidade("SEM LOCALIDADE");
        return helper.cadastrarAutor(autor);
    }

    public static Autor buscarAutorPorNome(Context context, String nomeAutor){
        HelperDAO helper = new HelperDAO(context);
        nomeAutor = Normalizer.normalize(nomeAutor, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        return helper.buscarAutorPorNome(nomeAutor);
    }
}
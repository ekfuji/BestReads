package br.edu.ctup.bestreads.DAO;

import android.content.Context;

import br.edu.ctup.bestreads.Model.Genero;
import java.text.Normalizer;

public class GeneroDAO {

    public static long cadastrarGenero(Context context, Genero genero){
        HelperDAO helper = new HelperDAO(context);
        genero.setNomeGenero(Normalizer.normalize(genero.getNomeGenero(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toUpperCase());
        return helper.cadastrarGenero(genero);
    }

    public static Genero buscarGeneroPorNome(Context context, String nomeGenero){
        HelperDAO helper = new HelperDAO(context);
        nomeGenero = Normalizer.normalize(nomeGenero, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toUpperCase();
        return helper.buscarGeneroPorNome(nomeGenero);
    }

}
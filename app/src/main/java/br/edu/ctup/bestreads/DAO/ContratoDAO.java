package br.edu.ctup.bestreads.DAO;

import android.provider.BaseColumns;

public final class ContratoDAO {

    public static abstract class TabelaGenero implements BaseColumns {
        public static final String NOME_DA_TABELA = "Genero";
        public static final String COLUNA_ID = "Id";
        public static final String COLUNA_NOME_GENERO = "Nome";
    }

    public static abstract class TabelaAutor implements BaseColumns {
        public static final String NOME_DA_TABELA = "Autor";
        public static final String COLUNA_ID = "Id";
        public static final String COLUNA_NOME_AUTOR = "NomeAutor";
        public static final String COLUNA_LOCALIDADE = "Localidade";
    }


    public static abstract class TabelaPasta implements BaseColumns {
        public static final String NOME_DA_TABELA = "Pasta";
        public static final String COLUNA_ID = "Id";
        public static final String COLUNA_NOME_PASTA = "NomePasta";

    }

    public static abstract class TabelaLivro implements BaseColumns {
        public static final String NOME_DA_TABELA = "Livro";
        public static final String COLUNA_ID = "Id";
        public static final String COLUNA_NOME_LIVRO = "NomeLivro";
        public static final String COLUNA_LIDO = "Lido";
        public static final String COLUNA_ANO_PUBLICACAO = "AnoPublicacao";
        public static final String COLUNA_FOTO_LIVRO = "FotoLivro";
        public static final String COLUNA_ID_GENERO = "IdGenero";
        public static final String COLUNA_ID_AUTOR = "IdAutor";
    }

    public static abstract class TabelaAvaliacao implements BaseColumns {
        public static final String NOME_DA_TABELA = "Avaliacao";
        public static final String COLUNA_ID = "Id";
        public static final String COLUNA_DATA = "Data";
        public static final String COLUNA_NOTA = "Nota";
        public static final String COLUNA_PARECER = "Parecer";
        public static final String COLUNA_IDLIVRO = "IdLivro";
    }

    public static abstract class TabelaAcervo implements BaseColumns {
        public static final String NOME_DA_TABELA = "Acervo";
        public static final String COLUNA_ID = "Id";
        public static final String COLUNA_IDLIVRO = "IdLivro";
        public static final String COLUNA_IDPASTA = "IdPasta";
    }


}

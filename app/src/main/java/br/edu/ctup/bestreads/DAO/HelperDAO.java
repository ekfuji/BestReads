package br.edu.ctup.bestreads.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import br.edu.ctup.bestreads.Model.Acervo;
import br.edu.ctup.bestreads.Model.Autor;
import br.edu.ctup.bestreads.Model.Avaliacao;
import br.edu.ctup.bestreads.Model.Genero;
import br.edu.ctup.bestreads.Model.Livro;
import br.edu.ctup.bestreads.Model.Pasta;

public class HelperDAO extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "DbBestReads";
    private  static  final int VERSION = 1;
    private static final String TIPO_TEXTO = " TEXT";
    private static final String TIPO_INTEIRO = " INTEGER";
    private static final String TIPO_BLOB = " BLOB";
    private static final String VIRGULA = ", ";


    private static final String SQL_CRIAR_TABELA_GENERO =
            "CREATE TABLE IF NOT EXISTS " + ContratoDAO.TabelaGenero.NOME_DA_TABELA + " (" +
                    ContratoDAO.TabelaGenero.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    ContratoDAO.TabelaGenero.COLUNA_NOME_GENERO + TIPO_TEXTO + " NOT NULL"+ ")";

    private static final String SQL_DELETAR_TABELA_GENERO =
            "DROP TABLE IF EXISTS " + ContratoDAO.TabelaGenero.NOME_DA_TABELA;


    private static final String SQL_CRIAR_TABELA_AUTOR =
            "CREATE TABLE IF NOT EXISTS " + ContratoDAO.TabelaAutor.NOME_DA_TABELA + " (" +
                    ContratoDAO.TabelaAutor.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    ContratoDAO.TabelaAutor.COLUNA_LOCALIDADE + TIPO_TEXTO + " NOT NULL" + VIRGULA +
                    ContratoDAO.TabelaAutor.COLUNA_NOME_AUTOR + TIPO_TEXTO + ")";

    private static final String SQL_DELETAR_TABELA_AUTOR =
            "DROP TABLE IF EXISTS " + ContratoDAO.TabelaAutor.NOME_DA_TABELA;


    private static final String SQL_CRIAR_TABELA_PASTA =
            "CREATE TABLE IF NOT EXISTS " + ContratoDAO.TabelaPasta.NOME_DA_TABELA + " (" +
                    ContratoDAO.TabelaPasta.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA + TIPO_TEXTO + " NOT NULL" + ")";

    private static final String SQL_DELETAR_TABELA_PASTA =
            "DROP TABLE IF EXISTS " + ContratoDAO.TabelaPasta.NOME_DA_TABELA;


    private static final String SQL_CRIAR_TABELA_LIVRO=
            "CREATE TABLE IF NOT EXISTS " + ContratoDAO.TabelaLivro.NOME_DA_TABELA + " (" +
                    ContratoDAO.TabelaLivro.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    ContratoDAO.TabelaLivro.COLUNA_NOME_LIVRO + TIPO_TEXTO + " NOT NULL" + VIRGULA +
                    ContratoDAO.TabelaLivro.COLUNA_LIDO + TIPO_INTEIRO + " NOT NULL" + VIRGULA +
                    ContratoDAO.TabelaLivro.COLUNA_ANO_PUBLICACAO + TIPO_TEXTO +  VIRGULA +
                    ContratoDAO.TabelaLivro.COLUNA_FOTO_LIVRO + TIPO_BLOB +  VIRGULA +
                    ContratoDAO.TabelaLivro.COLUNA_ID_AUTOR + TIPO_INTEIRO + " NOT NULL" + VIRGULA +
                    ContratoDAO.TabelaLivro.COLUNA_ID_GENERO + TIPO_INTEIRO + " NOT NULL" + VIRGULA +
                    "FOREIGN KEY (IdAutor) REFERENCES " + ContratoDAO.TabelaAutor.COLUNA_ID + VIRGULA +
                    "FOREIGN KEY (IdGenero) REFERENCES " + ContratoDAO.TabelaGenero.COLUNA_ID + ")";

    private static final String SQL_DELETAR_TABELA_LIVRO =
            "DROP TABLE IF EXISTS " + ContratoDAO.TabelaLivro.NOME_DA_TABELA;

    private static final String SQL_CRIAR_TABELA_AVALIACAO=
            "CREATE TABLE IF NOT EXISTS " + ContratoDAO.TabelaAvaliacao.NOME_DA_TABELA + " (" +
                    ContratoDAO.TabelaAvaliacao.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    ContratoDAO.TabelaAvaliacao.COLUNA_NOTA + TIPO_INTEIRO + " NOT NULL" + VIRGULA +
                    ContratoDAO.TabelaAvaliacao.COLUNA_PARECER + TIPO_TEXTO + VIRGULA +
                    ContratoDAO.TabelaAvaliacao.COLUNA_DATA + TIPO_TEXTO + " NOT NULL" + VIRGULA +
                    ContratoDAO.TabelaAvaliacao.COLUNA_IDLIVRO + TIPO_INTEIRO + " NOT NULL" + VIRGULA +
                    "FOREIGN KEY (IdLivro) REFERENCES " + ContratoDAO.TabelaLivro.COLUNA_ID + ")";

    private static final String SQL_DELETAR_TABELA_AVALIACAO =
            "DROP TABLE IF EXISTS " + ContratoDAO.TabelaAvaliacao.NOME_DA_TABELA;

    private static final String SQL_CRIAR_TABELA_ACERVO=
            "CREATE TABLE IF NOT EXISTS " + ContratoDAO.TabelaAcervo.NOME_DA_TABELA + " (" +
                    ContratoDAO.TabelaAcervo.COLUNA_ID + TIPO_INTEIRO + " PRIMARY KEY AUTOINCREMENT" + VIRGULA +
                    ContratoDAO.TabelaAcervo.COLUNA_IDLIVRO + TIPO_INTEIRO + " NOT NULL" + VIRGULA +
                    ContratoDAO.TabelaAcervo.COLUNA_IDPASTA + TIPO_INTEIRO + " NOT NULL" + VIRGULA +
                    "FOREIGN KEY (IdLivro) REFERENCES " + ContratoDAO.TabelaLivro.COLUNA_ID + VIRGULA +
                    "FOREIGN KEY (IdPasta) REFERENCES " + ContratoDAO.TabelaPasta.COLUNA_ID + ")";

    private static final String SQL_DELETAR_TABELA_ACERVO =
            "DROP TABLE IF EXISTS " + ContratoDAO.TabelaAcervo.NOME_DA_TABELA;


    public HelperDAO(Context context) {
        super(context, NOME_BANCO, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.v("CRIAR_BANCO ", SQL_CRIAR_TABELA_GENERO);
        db.execSQL(SQL_CRIAR_TABELA_GENERO);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_AUTOR);
        db.execSQL(SQL_CRIAR_TABELA_AUTOR);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_PASTA);
        db.execSQL(SQL_CRIAR_TABELA_PASTA);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_LIVRO);
        db.execSQL(SQL_CRIAR_TABELA_LIVRO);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_AVALIACAO);
        db.execSQL(SQL_CRIAR_TABELA_AVALIACAO);
        Log.v("CRIAR_BANCO", SQL_CRIAR_TABELA_ACERVO);
        db.execSQL(SQL_CRIAR_TABELA_ACERVO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.v("ATUALIZAR_BANCO", SQL_DELETAR_TABELA_GENERO);
        db.execSQL(SQL_DELETAR_TABELA_GENERO);
        Log.v("ATUALIZAR_BANCO", SQL_DELETAR_TABELA_AUTOR);
        db.execSQL(SQL_DELETAR_TABELA_AUTOR);
        Log.v("ATUALIZAR_BANCO", SQL_DELETAR_TABELA_PASTA);
        db.execSQL(SQL_DELETAR_TABELA_PASTA);
        Log.v("ATUALIZAR_BANCO", SQL_DELETAR_TABELA_LIVRO);
        db.execSQL(SQL_DELETAR_TABELA_LIVRO);
        Log.v("ATUALIZAR_BANCO", SQL_DELETAR_TABELA_AVALIACAO);
        db.execSQL(SQL_DELETAR_TABELA_AVALIACAO);
        Log.v("ATUALIZAR_BANCO", SQL_DELETAR_TABELA_ACERVO);
        db.execSQL(SQL_DELETAR_TABELA_ACERVO);

    }

    public long cadastrarGenero(Genero genero){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContratoDAO.TabelaGenero.COLUNA_NOME_GENERO, genero.getNomeGenero());
        return db.insert(ContratoDAO.TabelaGenero.NOME_DA_TABELA, null, values);

    }

    public long cadastrarAutor(Autor autor){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContratoDAO.TabelaAutor.COLUNA_NOME_AUTOR, autor.getNomeAutor());
        values.put(ContratoDAO.TabelaAutor.COLUNA_LOCALIDADE, autor.getLocalidade());
        return db.insert(ContratoDAO.TabelaAutor.NOME_DA_TABELA, null, values);
    }

    public long cadastrarPasta(Pasta pasta){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA, pasta.getNomePasta());
        return db.insert(ContratoDAO.TabelaPasta.NOME_DA_TABELA, null, values);
    }

    public ArrayList<Pasta> listarPastas() {
        ArrayList<Pasta> pastas = new ArrayList<Pasta>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaPasta.COLUNA_ID,
                ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA,
        };

        Cursor cursor = db.query(ContratoDAO.TabelaPasta.NOME_DA_TABELA,
                colunas, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Pasta p = new Pasta();
                p.setIdPasta(cursor.getInt(0));
                p.setNomePasta(cursor.getString(1));

                pastas.add(p);
            } while (cursor.moveToNext());
        }
        return pastas;
    }

    public ArrayList<String>  listarPastasPorNome(){
        ArrayList<String> pastas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA,
        };

        Cursor cursor = db.query(ContratoDAO.TabelaPasta.NOME_DA_TABELA,
                colunas, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {


                pastas.add(cursor.getString(cursor.getColumnIndex(ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA)));
            } while (cursor.moveToNext());
        }
        return pastas;
    }

    public ArrayList<Pasta> encontrarPastaPorNome(String nome) {
        ArrayList<Pasta> pastas = new ArrayList<Pasta>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaPasta.COLUNA_ID,
                ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA,
        };

        Cursor cursor = db.query(ContratoDAO.TabelaPasta.NOME_DA_TABELA,
                colunas, "NomePasta LIKE ?", new String [] {"%"+ nome + "%"}, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Pasta p = new Pasta();
                p.setIdPasta(cursor.getInt(0));
                p.setNomePasta(cursor.getString(1));

                pastas.add(p);
            } while (cursor.moveToNext());
        }
        return pastas;
    }


    public Genero buscarGeneroPorNome(String nomeGenero){
        SQLiteDatabase db = getReadableDatabase();
        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaGenero.COLUNA_ID,
                ContratoDAO.TabelaGenero.COLUNA_NOME_GENERO,
        };

        Cursor cursor = db.query(ContratoDAO.TabelaGenero.NOME_DA_TABELA,
                colunas, ContratoDAO.TabelaGenero.COLUNA_NOME_GENERO+ "=?", new String [] { nomeGenero }, null, null, null);

        cursor.moveToFirst();
        Genero g = new Genero();
        if (cursor.getCount() > 0) {
            do {
                g.setIdGenero(cursor.getInt(0));
                g.setNomeGenero(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return g;
    }

    public Autor buscarAutorPorNome(String nomeAutor){
        SQLiteDatabase db = getReadableDatabase();
        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaAutor.COLUNA_ID,
                ContratoDAO.TabelaAutor.COLUNA_NOME_AUTOR,
        };

        Cursor cursor = db.query(ContratoDAO.TabelaAutor.NOME_DA_TABELA,
                colunas, ContratoDAO.TabelaAutor.COLUNA_NOME_AUTOR+ "=?", new String [] { nomeAutor }, null, null, null);

        cursor.moveToFirst();
        Autor a = new Autor();
        if (cursor.getCount() > 0) {
            do {
                a.setIdAutor(cursor.getInt(0));
                a.setNomeAutor(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return a;
    }

    public Pasta buscarPastaPorId(int idPasta){
        SQLiteDatabase db = getReadableDatabase();
    //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaPasta.COLUNA_ID,
                ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA,
        };

        Cursor cursor = db.query(ContratoDAO.TabelaPasta.NOME_DA_TABELA,
                colunas, ContratoDAO.TabelaPasta.COLUNA_ID + "=?", new String [] { String.valueOf(idPasta) }, null, null, null);

        cursor.moveToFirst();
        Pasta p = new Pasta();
        if (cursor.getCount() > 0) {
            do {
                p.setIdPasta(cursor.getInt(0));
                p.setNomePasta(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return p;

    }

    public long alterarPasta(Pasta p) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ContratoDAO.TabelaPasta.COLUNA_NOME_PASTA, p.getNomePasta());

        String condicao = ContratoDAO.TabelaPasta.COLUNA_ID + " = ?";
        String[] argumentos = {
                String.valueOf(p.getIdPasta())
        };

        return db.update(ContratoDAO.TabelaPasta.NOME_DA_TABELA, values,
                condicao, argumentos);
    }

    public long removerPasta(Pasta c){
        SQLiteDatabase db = getWritableDatabase();

        String condicao = ContratoDAO.TabelaPasta.COLUNA_ID + " = ?";
        String[] argumentos = {
                String.valueOf(c.getIdPasta())
        };
        return db.delete(ContratoDAO.TabelaPasta.NOME_DA_TABELA,
                condicao, argumentos);
    }

    public long cadastrarLivro(Livro livro){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContratoDAO.TabelaLivro.COLUNA_NOME_LIVRO, livro.getNome());
        values.put(ContratoDAO.TabelaLivro.COLUNA_ANO_PUBLICACAO, livro.getAnoPublicacao());
        values.put(ContratoDAO.TabelaLivro.COLUNA_LIDO, livro.getLido());
        values.put(ContratoDAO.TabelaLivro.COLUNA_FOTO_LIVRO, livro.getFotoLivro());
        values.put(ContratoDAO.TabelaLivro.COLUNA_ID_GENERO, livro.getIdGenero());
        values.put(ContratoDAO.TabelaLivro.COLUNA_ID_AUTOR, livro.getIdAutor());
        return db.insert(ContratoDAO.TabelaLivro.NOME_DA_TABELA, null, values);
    }

    public ArrayList<Livro> listarLivrosPorPasta(int idPasta){
        ArrayList<Livro> livros = new ArrayList<Livro>();
        SQLiteDatabase db = getReadableDatabase();


        //Definir quais colunas vão retornar da tabela
       /* String[] colunas = {
                ContratoDAO.TabelaLivro.COLUNA_ID,
                ContratoDAO.TabelaLivro.COLUNA_NOME_LIVRO,
        };*/

        Cursor cursor = db.rawQuery("Select * From Livro l Join Acervo a ON a.IdLivro = l.Id Join " +
                "Pasta p ON a.IdPasta = p.Id where a.IdPasta =?",new String[] {String.valueOf(idPasta)});
                cursor.moveToFirst();



        if (cursor.getCount() > 0) {
            do {
                Livro l = new Livro();
                l.setIdLivro(cursor.getInt(0));
                l.setNome(cursor.getString(1));
                l.setLido(cursor.getInt(2));
                l.setAnoPublicacao(cursor.getString(3));
                l.setFotoLivro(cursor.getBlob(4));
                l.setIdAutor(cursor.getInt(5));
                l.setIdGenero(cursor.getInt(6));
                livros.add(l);
            } while (cursor.moveToNext());
        }
        return livros;
    }

    public Livro buscarLivroPorNome(String nomeLivro){
        SQLiteDatabase db = getReadableDatabase();
        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaLivro.COLUNA_ID,
                ContratoDAO.TabelaLivro.COLUNA_NOME_LIVRO,
                ContratoDAO.TabelaLivro.COLUNA_LIDO,
                ContratoDAO.TabelaLivro.COLUNA_ANO_PUBLICACAO,
                ContratoDAO.TabelaLivro.COLUNA_FOTO_LIVRO,
                ContratoDAO.TabelaLivro.COLUNA_ID_AUTOR,
                ContratoDAO.TabelaLivro.COLUNA_ID_GENERO
        };

        Cursor cursor = db.query(ContratoDAO.TabelaLivro.NOME_DA_TABELA,
                colunas, ContratoDAO.TabelaLivro.COLUNA_NOME_LIVRO + "=?", new String [] { nomeLivro }, null, null, null);

        cursor.moveToFirst();
        Livro l = new Livro();
        if (cursor.getCount() > 0) {
            do {
                l.setIdLivro(cursor.getInt(0));
                l.setNome(cursor.getString(1));
                l.setLido(cursor.getInt(2));
                l.setAnoPublicacao(cursor.getString(3));
                l.setFotoLivro(cursor.getBlob(4));
                l.setIdAutor(cursor.getInt(5));
                l.setIdGenero(cursor.getInt(6));
            } while (cursor.moveToNext());
        }
        return l;
    }


    public ArrayList<Livro> listarLivros() {
        ArrayList<Livro> livros = new ArrayList<Livro>();
        SQLiteDatabase db = getReadableDatabase();

        //Definir quais colunas vão retornar da tabela
        String[] colunas = {
                ContratoDAO.TabelaLivro.COLUNA_ID,
                ContratoDAO.TabelaLivro.COLUNA_NOME_LIVRO,
                ContratoDAO.TabelaLivro.COLUNA_FOTO_LIVRO,
                ContratoDAO.TabelaLivro.COLUNA_ID_AUTOR
        };

        Cursor cursor = db.query(ContratoDAO.TabelaLivro.NOME_DA_TABELA,
                colunas, null, null, null, null, null);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                Livro l = new Livro();
                l.setIdLivro(cursor.getInt(0));
                l.setNome(cursor.getString(1));
                l.setFotoLivro(cursor.getBlob(2));
                l.setIdAutor(cursor.getInt(3));
                livros.add(l);
            } while (cursor.moveToNext());
        }
        return livros;
    }

    public long cadastrarAvaliacao(Avaliacao avaliacao){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContratoDAO.TabelaAvaliacao.COLUNA_NOTA, avaliacao.getNota());
        values.put(ContratoDAO.TabelaAvaliacao.COLUNA_DATA, avaliacao.getData());
        values.put(ContratoDAO.TabelaAvaliacao.COLUNA_PARECER, avaliacao.getParecer());
        values.put(ContratoDAO.TabelaAvaliacao.COLUNA_IDLIVRO, avaliacao.getIdLivro());
        return db.insert(ContratoDAO.TabelaAvaliacao.NOME_DA_TABELA, null, values);
    }

    public long cadastrarAcervo(Acervo acervo){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ContratoDAO.TabelaAcervo.COLUNA_IDLIVRO, acervo.getIdLivro());
        values.put(ContratoDAO.TabelaAcervo.COLUNA_IDPASTA, acervo.getIdPasta());
        return db.insert(ContratoDAO.TabelaAcervo.NOME_DA_TABELA, null, values);
    }


}

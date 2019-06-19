package br.edu.ctup.bestreads.Model;

import android.graphics.Bitmap;

public class Livro {
    private int idLivro;
    private String nome;
    private String anoPublicacao;
    private String nomeAutor;
    private int lido;
    private byte[] fotoLivro;
    private int idGenero;
    private int idAutor;


    public Livro() {
    }

    public Livro(int idLivro, String nome, String anoPublicacao, String nomeAutor, int lido, byte[] fotoLivro, int idGenero, int idAutor) {
        this.idLivro = idLivro;
        this.nome = nome;
        this.anoPublicacao = anoPublicacao;
        this.nomeAutor = nomeAutor;
        this.lido = lido;
        this.fotoLivro = fotoLivro;
        this.idGenero = idGenero;
        this.idAutor = idAutor;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public int getLido() {
        return lido;
    }

    public void setLido(int lido) {
        this.lido = lido;
    }

    public byte[] getFotoLivro() {
        return fotoLivro;
    }

    public void setFotoLivro(byte[] fotoLivro) {
        this.fotoLivro = fotoLivro;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
}

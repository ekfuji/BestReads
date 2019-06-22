package br.edu.ctup.bestreads.Model;

import android.graphics.Bitmap;

public class Livro {
    private int idLivro;
    private String nome;
    private String anoPublicacao;
    private int lido;
    private byte[] fotoLivro;
    private String nomeAutor;
    private String nomeGenero;
    private int idGenero;
    private int idAutor;
    private int idAvaliacao;

    public Livro(int idLivro, String nome, String anoPublicacao, int lido, byte[] fotoLivro, String nomeAutor, String nomeGenero, int idGenero, int idAutor, int idAvaliacao) {
        this.idLivro = idLivro;
        this.nome = nome;
        this.anoPublicacao = anoPublicacao;
        this.lido = lido;
        this.fotoLivro = fotoLivro;
        this.nomeAutor = nomeAutor;
        this.nomeGenero = nomeGenero;
        this.idGenero = idGenero;
        this.idAutor = idAutor;
        this.idAvaliacao = idAvaliacao;
    }

    public Livro() {
    }


    public int getIdAvaliacao(){ return idAvaliacao;}

    public void setIdAvaliacao(int idAvaliacao) { this.idAvaliacao = idAvaliacao; }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
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
package br.edu.ctup.bestreads.Model;

public class Livro {
    private int idLivro;
    private String nome;
    private String anoPublicacao;
    private int lido;
    private String fotoLivro;
    private int idGenero;
    private int idAutor;

    public Livro(int idLivro, String nome, String anoPublicacao, int lido, String fotoLivro, int idGenero, int idAutor) {
        this.idLivro = idLivro;
        this.nome = nome;
        this.anoPublicacao = anoPublicacao;
        this.lido = lido;
        this.fotoLivro = fotoLivro;
        this.idGenero = idGenero;
        this.idAutor = idAutor;
    }

    public Livro() {
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

    public String getFotoLivro() {
        return fotoLivro;
    }

    public void setFotoLivro(String fotoLivro) {
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

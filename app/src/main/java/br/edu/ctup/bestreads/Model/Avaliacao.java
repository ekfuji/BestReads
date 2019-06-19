package br.edu.ctup.bestreads.Model;

public class Avaliacao {
    private int idAvaliacao;
    private float nota;
    private String data;
    private String parecer;
    private int idLivro;

    public Avaliacao(int idAvaliacao, float nota, String data, String parecer, int idLivro) {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.data = data;
        this.parecer = parecer;
        this.idLivro = idLivro;
    }

    public Avaliacao() {
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
}

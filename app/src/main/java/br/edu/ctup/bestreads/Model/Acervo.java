package br.edu.ctup.bestreads.Model;

public class Acervo {
    private int idAcervo;
    private int idPasta;
    private int idLivro;

    public Acervo(int idAcervo, int idPasta, int idLivro) {
        this.idAcervo = idAcervo;
        this.idPasta = idPasta;
        this.idLivro = idLivro;
    }

    public Acervo() {
    }

    public int getIdAcervo() {
        return idAcervo;
    }

    public void setIdAcervo(int idAcervo) {
        this.idAcervo = idAcervo;
    }

    public int getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(int idPasta) {
        this.idPasta = idPasta;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }
}

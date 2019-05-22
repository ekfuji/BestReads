package br.edu.ctup.bestreads.Model;

public class Autor {
    private int idAutor;
    private String nomeAutor;
    private String localidade;

    public Autor(int idAutor, String nomeAutor, String localidade) {
        this.idAutor = idAutor;
        this.nomeAutor = nomeAutor;
        this.localidade = localidade;
    }

    public Autor() {
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
}

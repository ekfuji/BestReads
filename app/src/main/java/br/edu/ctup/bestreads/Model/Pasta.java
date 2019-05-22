package br.edu.ctup.bestreads.Model;

public class Pasta {
    private int idPasta;
    private int nomePasta;

    public Pasta(int idPasta, int nomePasta) {
        this.idPasta = idPasta;
        this.nomePasta = nomePasta;
    }

    public Pasta() {
    }

    public int getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(int idPasta) {
        this.idPasta = idPasta;
    }

    public int getNomePasta() {
        return nomePasta;
    }

    public void setNomePasta(int nomePasta) {
        this.nomePasta = nomePasta;
    }
}

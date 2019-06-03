package br.edu.ctup.bestreads.Model;

public class Pasta {
    private int idPasta;
    private String nomePasta;

    public Pasta() {
    }

    public Pasta(int idPasta, String nomePasta) {
        this.idPasta = idPasta;
        this.nomePasta = nomePasta;
    }

    public  void changeNomePasta(String nome){
        this.nomePasta = nome;
    }

    public int getIdPasta() {
        return idPasta;
    }

    public void setIdPasta(int idPasta) {
        this.idPasta = idPasta;
    }

    public String getNomePasta() {
        return nomePasta;
    }

    public void setNomePasta(String nomePasta) {
        this.nomePasta = nomePasta;
    }
}

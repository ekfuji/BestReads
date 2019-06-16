package br.edu.ctup.bestreads.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Pasta implements Parcelable {
    private int idPasta;
    private String nomePasta;

    public Pasta() {
    }

    public Pasta(int idPasta, String nomePasta) {
        this.idPasta = idPasta;
        this.nomePasta = nomePasta;
    }

    protected Pasta(Parcel in) {
        idPasta = in.readInt();
        nomePasta = in.readString();
    }

    public static final Creator<Pasta> CREATOR = new Creator<Pasta>() {
        @Override
        public Pasta createFromParcel(Parcel in) {
            return new Pasta(in);
        }

        @Override
        public Pasta[] newArray(int size) {
            return new Pasta[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idPasta);
        dest.writeString(nomePasta);
    }
}

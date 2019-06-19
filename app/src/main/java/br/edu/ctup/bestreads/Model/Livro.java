package br.edu.ctup.bestreads.Model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Livro implements Parcelable {
    private int idLivro;
    private String nome;
    private String anoPublicacao;
    private int lido;
    private byte[] fotoLivro;
    private int idGenero;
    private String nomeGenero,nomeAutor;
    private int idAutor;

    public static final Creator<Livro> CREATOR = new Creator<Livro>() {
        @Override
        public Livro createFromParcel(Parcel in) {
            return new Livro(in);
        }

        @Override
        public Livro[] newArray(int size) {
            return new Livro[size];
        }
    };

    public Livro(Parcel in) {
        idLivro = in.readInt();
        nome = in.readString();
    }

    public Livro()
    {
    }

    public Livro(int idLivro,int idPasta, String nome, String anoPublicacao, int lido, byte[] fotoLivro, Bitmap imgBitmap, int idGenero, int idAutor, String nomeAutor, String nomeGenero) {
        this.idLivro = idLivro;
        this.nome = nome;
        this.anoPublicacao = anoPublicacao;
        this.lido = lido;
        this.fotoLivro = fotoLivro;
        this.idGenero = idGenero;
        this.idAutor = idAutor;
        this.nomeGenero = nomeGenero;
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

    public String getNomeGenero(){ return nomeGenero;}

    public void setNomeGenero(String nomeGenero){ this.nomeGenero =nomeGenero; }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNomeAutor(){ return nomeGenero;}

    public void setNomeAutor(String nomeAutor){ this.nomeAutor = nomeAutor; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idLivro);
        dest.writeString(nome);
    }
}

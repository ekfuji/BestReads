package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import br.edu.ctup.bestreads.DAO.AutorDAO;
import br.edu.ctup.bestreads.DAO.GeneroDAO;
import br.edu.ctup.bestreads.DAO.LivroDAO;
import br.edu.ctup.bestreads.Model.Acervo;
import br.edu.ctup.bestreads.Model.Autor;
import br.edu.ctup.bestreads.Model.Genero;
import br.edu.ctup.bestreads.Model.Livro;
import br.edu.ctup.bestreads.R;

public class CadastrarLivroActivity extends AppCompatActivity {

    private TextView txtNomeLivro, txtGeneroLivro, txtAutorLivro, txtAnoLivro;
    private CheckBox checkboxLido;
    private ImageView imageLivro;
    private int idPasta;
    private int idLido = 0;
    public byte [] imgLivro;
    private Bitmap imgBitMap;
    public static final int PICK_IMAGE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);
        Intent getDados = getIntent();
        idPasta = getDados.getIntExtra("idPasta",0);
        txtNomeLivro =  findViewById(R.id.txt_nome_livro);
        txtGeneroLivro = findViewById(R.id.txt_genero_livro);
        txtAnoLivro = findViewById(R.id.txt_ano_publicacao_livro);
        txtAutorLivro = findViewById(R.id.txt_autor_livro);
        checkboxLido = findViewById(R.id.checkbox_lido);
        imageLivro = findViewById(R.id.image_livro);

    }

    public void salvarLivro(View view) {
        Livro livro = new Livro();
        Autor autor = new Autor();
        Genero genero = new Genero();
        Acervo acervo = new Acervo();


        livro.setNome(txtNomeLivro.getText().toString());
        String nomeGenero = txtGeneroLivro.getText().toString();
        genero =  GeneroDAO.buscarGeneroPorNome(this,nomeGenero);
        if(genero.getIdGenero() == 0){
            genero.setNomeGenero(nomeGenero);
            GeneroDAO.cadastrarGenero(this,genero);
            genero = GeneroDAO.buscarGeneroPorNome(this,nomeGenero);
        }
        livro.setIdGenero(genero.getIdGenero());

        String nomeAutor = txtAutorLivro.getText().toString();
        autor = AutorDAO.buscarAutorPorNome(this,nomeAutor);

        if(autor.getIdAutor() == 0){
            autor.setNomeAutor(nomeAutor);
            AutorDAO.cadastrarAutor(this,autor);
            autor = AutorDAO.buscarAutorPorNome(this,nomeAutor);
        }
        livro.setIdAutor(autor.getIdAutor());
        livro.setLido(idLido);
        livro.setAnoPublicacao(txtAnoLivro.getText().toString());

        try {
            imgLivro = converterImagemViewParaByte();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        livro.setFotoLivro(imgLivro);
        LivroDAO.cadastrarLivro(this,livro);

        livro = LivroDAO.buscarLivroPorNome(this, txtNomeLivro.getText().toString());

        acervo.setIdLivro(livro.getIdLivro());
        acervo.setIdPasta(idPasta);

        LivroDAO.cadastrarLivroAcervo(this,acervo);

        Intent intentOrigem = new Intent(CadastrarLivroActivity.this, HomePastaActivity.class);
        intentOrigem.putExtra("idPasta",idPasta);
        startActivity(intentOrigem);
    }


    private byte [] converterImagemViewParaByte(){
        imgBitMap = ((BitmapDrawable)imageLivro.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream(imgBitMap.getWidth() * imgBitMap.getHeight());
        imgBitMap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void anexarImagem(View view) {
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(i, "Selecione uma imagem"), PICK_IMAGE);
    }

    public void checkedLido(View view) {
        if(checkboxLido.isChecked()){
            idLido = 1;
        }
        else {
            idLido = 0;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != CadastrarLivroActivity.RESULT_CANCELED){
            if(requestCode == PICK_IMAGE){
                Uri selectedImage = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                    imgBitMap = BitmapFactory.decodeStream(inputStream);
                    imageLivro.setImageBitmap(imgBitMap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
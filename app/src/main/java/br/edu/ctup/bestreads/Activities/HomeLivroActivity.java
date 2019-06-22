package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.edu.ctup.bestreads.DAO.AutorDAO;
import br.edu.ctup.bestreads.DAO.LivroDAO;
import br.edu.ctup.bestreads.Model.Avaliacao;
import br.edu.ctup.bestreads.Model.Livro;
import br.edu.ctup.bestreads.R;

public class HomeLivroActivity extends AppCompatActivity {

    private ImageView imageViewLivro, imageViewLido;
    private TextView txtNomeLivro, txtAutorLivro, txtGeneroLivro, txtAnoPublicacao, txtLido, txtAvaliado;
    private Button btnAvaliar;
    private Livro livro;
    private int idLivro, idPasta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_livro);
        Intent getDados = getIntent();
        idLivro = getDados.getIntExtra("idLivro",0);
        idPasta = getDados.getIntExtra("idPasta",0);
        livro = LivroDAO.buscarLivroAutorGenero(this,idLivro);
        imageViewLivro = findViewById(R.id.image_livro_home);
        imageViewLido = findViewById(R.id.image_lido_home);
        txtNomeLivro = findViewById(R.id.nome_livro_home);
        txtAutorLivro = findViewById(R.id.nome_autor_livro_home);
        txtGeneroLivro = findViewById(R.id.nome_genero_livro_home);
        txtAnoPublicacao = findViewById(R.id.ano_publicao_livro_home);
        txtLido = findViewById(R.id.lido_home);
        txtAvaliado = findViewById(R.id.txt_avalidado);
        btnAvaliar = findViewById(R.id.btn_avaliar_livro_home);


        Toast.makeText(this, String.valueOf(idPasta), Toast.LENGTH_SHORT).show();
        imageViewLivro.setImageBitmap(converterImagemParaBitMap());
        txtNomeLivro.setText(livro.getNome());
        txtAutorLivro.setText(livro.getNomeAutor());
        txtGeneroLivro.setText(livro.getNomeGenero());
        txtAnoPublicacao.setText(livro.getAnoPublicacao());

        if(livro.getLido() == 1){
            txtLido.setText("Lido");
            imageViewLido.setImageResource(R.drawable.ic_lido);
            Avaliacao avaliacao = LivroDAO.buscarAvaliacaoPorIdLivro(this,livro.getIdLivro());
            btnAvaliar.setVisibility(View.VISIBLE);
            if(avaliacao.getIdAvaliacao() == 0){
                btnAvaliar.setEnabled(true);
                //btnAvaliar.setBackgroundResource(R.drawable.);
            }
            else {
                btnAvaliar.setEnabled(false);
                //btnAvaliar.setBackgroundResource(R.drawable.);
                txtAvaliado.setVisibility(View.VISIBLE);
            }

        }
        else {
            txtLido.setText("Não lido");

        }
    }

    private Bitmap converterImagemParaBitMap() {
        try{
            /*ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(livroAtual.getFotoLivro());
            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);*/
            Bitmap bitmap = BitmapFactory.decodeByteArray(livro.getFotoLivro(),0,livro.getFotoLivro().length);
            ByteArrayOutputStream stream = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
            bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
            return bitmap;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void avaliarLivro(View view) {
        Intent intentOrigem = new Intent(HomeLivroActivity.this, CadastrarAvaliacaoActivity.class);
        intentOrigem.putExtra("idLivro",livro.getIdLivro());
        intentOrigem.putExtra("idPasta",idPasta);
        startActivity(intentOrigem);
    }
}

package br.edu.ctup.bestreads.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class EditarLivroActivity extends AppCompatActivity {

    private AlertDialog alerta;
    private Livro livro;
    private TextView txtNomeLivro, txtGeneroLivro, txtAutorLivro, txtAnoLivro;
    private CheckBox checkboxLido;
    private ImageView imageLivro;
    private int idPasta, idLivro;
    private int idLido = 0;
    public byte [] imgLivro;
    private Bitmap imgBitMap;
    public static final int PICK_IMAGE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livro);
        Intent getDados = getIntent();
        idPasta = getDados.getIntExtra("idPasta",0);
        idLivro = getDados.getIntExtra("idLivro",0);

        livro = LivroDAO.buscarLivroAutorGenero(this,idLivro);

        txtNomeLivro =  findViewById(R.id.txt_nome_livro_editado);
        txtGeneroLivro = findViewById(R.id.txt_genero_livro_editado);
        txtAnoLivro = findViewById(R.id.txt_ano_publicacao_livro_editado);
        txtAutorLivro = findViewById(R.id.txt_autor_livro_editado);
        checkboxLido = findViewById(R.id.checkbox_lido_editado);
        imageLivro = findViewById(R.id.image_livro_editado);

        txtNomeLivro.setText(livro.getNome());
        txtGeneroLivro.setText(livro.getNomeGenero());
        txtAutorLivro.setText(livro.getNomeAutor());
        txtAnoLivro.setText(livro.getAnoPublicacao());
        imageLivro.setImageBitmap(converterImagemParaBitMap());

        if(livro.getLido() == 1){
            checkboxLido.setChecked(true);
        }


    }

    public void editarLivro(View view) {
        Autor autor = new Autor();
        Genero genero = new Genero();


        livro.setNome(txtNomeLivro.getText().toString());
        String nomeGenero = txtGeneroLivro.getText().toString();
        String nomeAutor = txtAutorLivro.getText().toString();
        if(txtNomeLivro.getText().toString().isEmpty()
                || txtGeneroLivro.getText().toString().isEmpty()
                || txtAutorLivro.getText().toString().isEmpty()){

            exibirAlertDialogCampoVazio();
            return;
        }else {
            genero =  GeneroDAO.buscarGeneroPorNome(this,nomeGenero);
            if(genero.getIdGenero() == 0){
                genero.setNomeGenero(nomeGenero);
                GeneroDAO.cadastrarGenero(this,genero);
                genero = GeneroDAO.buscarGeneroPorNome(this,nomeGenero);
            }
            livro.setIdGenero(genero.getIdGenero());


            autor = AutorDAO.buscarAutorPorNome(this,nomeAutor);

            if(autor.getIdAutor() == 0){
                autor.setNomeAutor(nomeAutor);
                AutorDAO.cadastrarAutor(this,autor);
                autor = AutorDAO.buscarAutorPorNome(this,nomeAutor);
            }
            livro.setIdAutor(autor.getIdAutor());

            if(checkboxLido.isChecked()){
                idLido = 1;
            }
            else {
                idLido = 0;
            }

            livro.setLido(idLido);
            livro.setAnoPublicacao(txtAnoLivro.getText().toString());

            try {
                imgLivro = converterImagemViewParaByte();
            }
            catch (Exception e){
                e.printStackTrace();
            }

            livro.setFotoLivro(imgLivro);
            LivroDAO.editarLivro(this,livro);
            exibirAlertDialogSalvoComSucesso();
            Intent intentOrigem = new Intent(EditarLivroActivity.this, HomePastaActivity.class);
            intentOrigem.putExtra("idPasta",idPasta);
            startActivity(intentOrigem);
        }
    }


    private byte [] converterImagemViewParaByte(){
        imgBitMap = ((BitmapDrawable)imageLivro.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream(imgBitMap.getWidth() * imgBitMap.getHeight());
        imgBitMap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void anexarImagemEditada(View view) {
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

    private void exibirAlertDialogCampoVazio() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("ATENÇÃO");
        //define a mensagem
        builder.setMessage("Os Campos (Nome, Genero e Autor não podem estar vazios, preencha e clique em salvar!");
        //define um botão como positivo
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alerta.dismiss();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void exibirAlertDialogSalvoComSucesso() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Sucesso");
        //define a mensagem
        builder.setMessage("O livro " + livro.getNome() + " foi alterado com sucesso!");
        //define um botão como positivo
        builder.setPositiveButton("Home Page", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(EditarLivroActivity.this, "Home Page=" + arg1, Toast.LENGTH_SHORT).show();
                Intent intentOrigem = new Intent(EditarLivroActivity.this, HomePastaActivity.class);
                startActivity(intentOrigem);
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private Bitmap converterImagemParaBitMap(){
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
}
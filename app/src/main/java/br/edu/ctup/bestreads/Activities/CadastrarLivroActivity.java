package br.edu.ctup.bestreads.Activities;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.NotificationCompat;
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
    private AlertDialog alerta;
    private Livro livro;
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
        livro = new Livro();
        Autor autor = new Autor();
        Genero genero = new Genero();
        Acervo acervo = new Acervo();


        livro.setNome(txtNomeLivro.getText().toString());
        String nomeGenero = txtGeneroLivro.getText().toString();
        String nomeAutor = txtAutorLivro.getText().toString();
        if(txtNomeLivro.getText().toString().isEmpty() || txtAutorLivro.getText().toString().isEmpty() || txtGeneroLivro.getText().toString().isEmpty() ){
            exibirAlertDialogCampoVazio();
            return;
        } else {
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


            final NotificationManager notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            //Criar um canal de comunicação
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String nome = "CANAL_COMUNICACAO";
                String descricao = "DESCRICAO_CANAL";
                int prioridade = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel notificationChannel =
                        new NotificationChannel("idCanal01", nome, prioridade);
                notificationChannel.setDescription(descricao);
                notificationManager.createNotificationChannel(notificationChannel);
            }



            //Ação do clique da notificação
            Intent intentOrigem = new Intent();
            //intentOrigem.putExtra("idPasta",livro.getIdLivro());
            PendingIntent pendingIntent =
                    PendingIntent.getActivity(this, 0,intentOrigem, 0);

            //Criar a notificação
            final NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(this, "idCanal01");
            builder.setContentText("Livro Salvo com Sucesso!");
            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setContentIntent(pendingIntent);

            Thread thread = new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        notificationManager.notify(1, builder.build());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            thread.start();
        LivroDAO.cadastrarLivroAcervo(this,acervo);
        exibirAlertDialogSalvoComSucesso();
        Intent intentLivro = new Intent(CadastrarLivroActivity.this, HomePastaActivity.class);
       // intentLivro.putExtra("idLivro",livro.getIdLivro());
        intentLivro.putExtra("idPasta",idPasta);
        startActivity(intentLivro);
        }

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

    private void exibirAlertDialogCampoVazio() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("ATENÇÃO");
        //define a mensagem
        builder.setMessage( "Os Campos (Nome, Genero e Autor não podem estar vazios, preencha e clique em salvar!");
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
        builder.setMessage("O livro " + livro.getNome() + " foi adicionado com sucesso!");
        //define um botão como positivo
        builder.setPositiveButton("Home Page", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intentOrigem = new Intent(CadastrarLivroActivity.this, HomePastaActivity.class);
                startActivity(intentOrigem);
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
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
                //Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
            }
        }



    }
}
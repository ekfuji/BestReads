package br.edu.ctup.bestreads.Activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.edu.ctup.bestreads.DAO.LivroDAO;
import br.edu.ctup.bestreads.Model.Avaliacao;
import br.edu.ctup.bestreads.R;

public class CadastrarAvaliacaoActivity extends AppCompatActivity {
    Calendar myCalendar;
    EditText dataAvaliacao, txtAvaliacao;
    private RatingBar notaAvaliacao;
    DatePickerDialog.OnDateSetListener date;
    private int idLivro;
    private int idPasta;
    private int tipoLista;
    private Button btnSalvar;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_avaliacao);
        dataAvaliacao = findViewById(R.id.txt_data_avaliacao);
        txtAvaliacao =findViewById(R.id.txt_avaliacao);
        notaAvaliacao = findViewById(R.id.rating_nota_livro);
        btnSalvar = findViewById(R.id.btn_salvar_avaliacao);
        myCalendar = Calendar.getInstance();

        Intent getDados = getIntent();
        idLivro = getDados.getIntExtra("idLivro",0);
        idPasta = getDados.getIntExtra("idPasta", 0);
        tipoLista = getDados.getIntExtra("tipoLista",0);



         date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

         if(tipoLista == 0){
             dataAvaliacao.setInputType(InputType.TYPE_NULL);
             txtAvaliacao.setInputType(InputType.TYPE_NULL);
             notaAvaliacao.setIsIndicator(true);
             //notaAvaliacao.setEnabled(false);
             btnSalvar.setVisibility(View.INVISIBLE);
             Avaliacao avaliacao = LivroDAO.buscarAvaliacaoPorIdLivro(this,idLivro);

             notaAvaliacao.setRating(avaliacao.getNota());
             txtAvaliacao.setText(avaliacao.getParecer());
             dataAvaliacao.setText(avaliacao.getData());
             //btnSalvar.setBackgroundResource(R.drawable.);
         }

    }
    private void updateLabel() {

        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt","BR"));

        dataAvaliacao.setText(sdf.format(myCalendar.getTime()));
    }

    public void onClickDataAvaliacao(View view) {
        if(tipoLista != 0){
            new DatePickerDialog(CadastrarAvaliacaoActivity.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }
    }

    public void salvarAvaliacao(View view) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setParecer(txtAvaliacao.getText().toString());
        avaliacao.setData(dataAvaliacao.getText().toString());
        avaliacao.setNota(notaAvaliacao.getRating());

        if(txtAvaliacao.getText().toString().isEmpty()){
            exibirAlertDialogCampoVazio();
            return;
        }
        else{
            //Trocar o campo na base de dados para nota tipo Float e usar a função abaixo
            //avaliacao.setNota(notaAvaliacao.getRating());

            avaliacao.setIdLivro(idLivro);
            LivroDAO.cadastrarAvaliacaoLivro(this,avaliacao);
            exibirAlertDialogSalvoComSucesso();
            Intent intentOrigem = new Intent(CadastrarAvaliacaoActivity.this, HomePastaActivity.class);
            intentOrigem.putExtra("idPasta",idPasta);
            startActivity(intentOrigem);
        }

    }

    private void exibirAlertDialogCampoVazio() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("ATENÇÃO");
        //define a mensagem
        builder.setMessage( "Os Campos (Avaliação e Data não podem estar vazios, preencha e clique em salvar!");
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
        builder.setMessage("Avaliação realizada com sucesso!");
        //define um botão como positivo
        builder.setPositiveButton("Home Page", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CadastrarAvaliacaoActivity.this, "Home Page=" + arg1, Toast.LENGTH_SHORT).show();
                Intent intentOrigem = new Intent(CadastrarAvaliacaoActivity.this, HomePastaActivity.class);
                startActivity(intentOrigem);
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}

package br.edu.ctup.bestreads.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        Toast.makeText(this, String.valueOf(idPasta), Toast.LENGTH_SHORT).show();


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
             dataAvaliacao.setEnabled(false);
             txtAvaliacao.setEnabled(false);
             notaAvaliacao.setEnabled(false);
             btnSalvar.setEnabled(false);
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
        new DatePickerDialog(CadastrarAvaliacaoActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void salvarAvaliacao(View view) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setParecer(txtAvaliacao.getText().toString());
        avaliacao.setData(dataAvaliacao.getText().toString());
        avaliacao.setNota(notaAvaliacao.getRating());

        //Trocar o campo na base de dados para nota tipo Float e usar a função abaixo
        //avaliacao.setNota(notaAvaliacao.getRating());




        avaliacao.setIdLivro(idLivro);
        LivroDAO.cadastrarAvaliacaoLivro(this,avaliacao);

        Intent intentOrigem = new Intent(CadastrarAvaliacaoActivity.this, HomePastaActivity.class);
        intentOrigem.putExtra("idPasta",idPasta);
        startActivity(intentOrigem);
    }
}

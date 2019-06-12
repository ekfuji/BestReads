package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ctup.bestreads.R;

public class CadastrarLivroActivity extends AppCompatActivity {

    private TextView txtNomeLivro, txtGeneroLivro, txtAutorLivro, txtAnoLivro;
    private CheckBox checkboxLido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);
        Intent getDados = getIntent();
        int idPasta = getDados.getIntExtra("idPasta",0);
        txtNomeLivro =  findViewById(R.id.txt_nome_livro);
        txtGeneroLivro = findViewById(R.id.txt_genero_livro);
        txtAnoLivro = findViewById(R.id.txt_ano_publicacao_livro);
        txtAutorLivro = findViewById(R.id.txt_autor_livro);
        checkboxLido = findViewById(R.id.checkbox_lido);
        Toast.makeText(this, String.valueOf(idPasta), Toast.LENGTH_SHORT).show();
    }

    public void salvarLivro(View view) {

    }

    public void anexarImagem(View view) {
    }

    public void checkedLlido(View view) {

    }
}

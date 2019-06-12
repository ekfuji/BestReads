package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import br.edu.ctup.bestreads.R;

public class CadastrarLivroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_livro);
        Intent getDados = getIntent();
        int idPasta = getDados.getIntExtra("idPasta",0);
        Toast.makeText(this, String.valueOf(idPasta), Toast.LENGTH_SHORT).show();
    }
}

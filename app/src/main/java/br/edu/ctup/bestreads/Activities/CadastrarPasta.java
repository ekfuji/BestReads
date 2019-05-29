package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.edu.ctup.bestreads.DAO.PastaDAO;
import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class CadastrarPasta extends AppCompatActivity {
private EditText txtNomePasta;
private Button btnSalvarPasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pasta);

        txtNomePasta = (EditText) findViewById(R.id.txt_nome_pasta);
        btnSalvarPasta = (Button) findViewById(R.id.btn_salvar_pasta);



    }

    public void CadastrarPasta(View view) {
        Pasta pasta = new Pasta();
        pasta.setNomePasta(txtNomePasta.getText().toString());

        long id = PastaDAO.cadastrarPasta(this, pasta);

        Toast.makeText(this, "Id: " +  id, Toast.LENGTH_SHORT).show();

        Intent intentOrigem = new Intent(CadastrarPasta.this, HomePageActivity.class);
        startActivity(intentOrigem);

    }
}

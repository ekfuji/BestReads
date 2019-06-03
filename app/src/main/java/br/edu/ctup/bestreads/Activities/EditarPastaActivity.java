package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ctup.bestreads.DAO.PastaDAO;
import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class EditarPastaActivity extends AppCompatActivity {

    private Button btnSalvar;
    private TextView txtCampo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pasta);
        btnSalvar = findViewById(R.id.btn_salvar_pasta_editada);
        txtCampo = findViewById(R.id.txt_nome_pasta_editada);

    }

    public void EditarPasta(View view) {
        Pasta pasta = new Pasta();
        String nomePasta = txtCampo.getText().toString();
        pasta.setNomePasta(nomePasta);
        PastaDAO.editarPasta(this,pasta);
        Toast.makeText(this, "Sucesso", Toast.LENGTH_SHORT).show();
        Intent intentOrigem = new Intent(EditarPastaActivity.this, HomePageActivity.class);
        startActivity(intentOrigem);

    }
}

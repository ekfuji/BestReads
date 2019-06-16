package br.edu.ctup.bestreads.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ctup.bestreads.DAO.PastaDAO;
import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class EditarPastaActivity extends AppCompatActivity {

    private AlertDialog alerta;
    private Pasta pasta;
    private Button btnSalvar;
    private EditText txtCampo;
    private int idPasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent pegarDado = getIntent();
        pasta = pegarDado.getParcelableExtra("Pasta");

        setContentView(R.layout.activity_editar_pasta);
        btnSalvar = findViewById(R.id.btn_salvar_pasta_editada);
        txtCampo = findViewById(R.id.txt_nome_pasta_editada);

        if(pegarDado.hasExtra("Pasta")) {
            txtCampo.setText(pasta.getNomePasta());
        }
    }

    public void EditarPasta(View view) {
        String nomePasta = txtCampo.getText().toString();
        if(nomePasta.isEmpty()){
            exibirAlertDialogCampoVazio();
        }
        else {
            pasta.setIdPasta(pasta.getIdPasta());
            pasta.setNomePasta(nomePasta);
            PastaDAO.editarPasta(this, pasta);
            exibirAlertDialogSalvoComSucesso();
        }

    }

    private void exibirAlertDialogCampoVazio() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("ATENÇÃO");
        //define a mensagem
        builder.setMessage("O nome da pasta vazio, preencha e clique em salvar!");
        //define um botão como positivo
        builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(EditarPastaActivity.this, "Ok=" + arg1, Toast.LENGTH_SHORT).show();
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
        builder.setMessage("A pasta " + pasta.getNomePasta() + " foi editada com sucesso!");
        //define um botão como positivo
        builder.setPositiveButton("Home Page", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(EditarPastaActivity.this, "Home Page=" + arg1, Toast.LENGTH_SHORT).show();
                Intent intentOrigem = new Intent(EditarPastaActivity.this, HomePageActivity.class);
                startActivity(intentOrigem);
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}

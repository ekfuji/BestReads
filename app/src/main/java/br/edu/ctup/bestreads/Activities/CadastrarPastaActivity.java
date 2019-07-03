package br.edu.ctup.bestreads.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class CadastrarPastaActivity extends AppCompatActivity {
private EditText txtNomePasta;
private Button btnSalvarPasta;
private AlertDialog alerta;
private Pasta pasta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pasta);

        txtNomePasta = (EditText) findViewById(R.id.txt_nome_pasta);
        btnSalvarPasta = (Button) findViewById(R.id.btn_salvar_pasta);



    }

    public void CadastrarPasta(View view) {
        pasta = new Pasta();
        pasta.setNomePasta(txtNomePasta.getText().toString());
        if(txtNomePasta.getText().toString().isEmpty()){
            exibirAlertDialogCampoVazio();
        }
        else {
            long id = PastaDAO.cadastrarPasta(this, pasta);
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
        builder.setMessage("A pasta " + pasta.getNomePasta() + " foi adicionada com sucesso!");
        //define um botão como positivo
        builder.setPositiveButton("Home Page", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                Intent intentOrigem = new Intent(CadastrarPastaActivity.this, HomePageActivity.class);
                startActivity(intentOrigem);
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }
}

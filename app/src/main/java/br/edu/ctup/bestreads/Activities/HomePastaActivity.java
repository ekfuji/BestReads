package br.edu.ctup.bestreads.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import br.edu.ctup.bestreads.Adapter.LivroAdapter;
import br.edu.ctup.bestreads.Adapter.PastaAdapter;
import br.edu.ctup.bestreads.DAO.HelperDAO;
import br.edu.ctup.bestreads.DAO.LivroDAO;
import br.edu.ctup.bestreads.DAO.PastaDAO;
import br.edu.ctup.bestreads.Model.Livro;
import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class HomePastaActivity extends AppCompatActivity {
    private ArrayList<Livro> livrosArrayList;
    private RecyclerView livroRecyclerView;
    private LivroAdapter livroAdapter;
    private AlertDialog alerta;
    private RecyclerView.LayoutManager livroLayoutManager;
    private ImageView btn_excluir_livro,btn_editar_livro;
    private static int idPasta;
    private MaterialSearchBar materialSearchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_editar_livro = findViewById(R.id.btn_editar_livro);
        btn_excluir_livro = findViewById(R.id.btn_excluir_livro);
        loadAdapter();
        loadButtonsAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_livros, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_livro:
                Intent intentOrigem = new Intent(HomePastaActivity.this, CadastrarLivroActivity.class);
                intentOrigem.putExtra("idPasta",idPasta);
                startActivity(intentOrigem);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadButtonsAdapter(){
        livroAdapter.setOnItemClickListner(new LivroAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                ArrayList<Livro> itens = livroAdapter.acervoLivros;
                exibirAlertDialogExclusaoLivro(position, itens);
            }

            @Override
            public void onEditClick(int position) {
                ArrayList<Livro> itens = livroAdapter.acervoLivros;
                editItem(position, itens);

            }
        });

    }
    private void exibirAlertDialogExclusaoLivro(final int position, final ArrayList<Livro> itens) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("ATENÇÃO");
        //define a mensagem
        String nomeDoLivro = itens.get(position).getNome();
        builder.setMessage("Deseja realmente deletar o livro " + nomeDoLivro + "?");
        //define um botão como positivo
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                deleteItem(position, itens);
            }
        });
        //define um botão como negativo.
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alerta.dismiss();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    public void deleteItem(int position, ArrayList<Livro> itens){
        int idLivro = itens.get(position).getIdLivro();
        Livro livro = LivroDAO.BuscarLivroPorId(this, idLivro);
        LivroDAO.excluirLivro(this,livro);
        exibirAlertDialogExclusaoLivroComSucesso();
        loadAdapter();
        loadButtonsAdapter();
        livroAdapter.notifyDataSetChanged();
        livroAdapter.notifyItemChanged(position);

    }

    public void editItem(int position, ArrayList<Livro> itens){
        int idLivro = itens.get(position).getIdLivro();
        Livro livro = LivroDAO.BuscarLivroPorId(this, idLivro);
        Intent intentOrigem = new Intent(HomePastaActivity.this, EditarLivroActivity.class);
        intentOrigem.putExtra("idLivro",livro.getIdLivro());
        intentOrigem.putExtra("idPasta",idPasta);
        startActivity(intentOrigem);
        livroAdapter.notifyItemChanged(position);

    }


    private void exibirAlertDialogExclusaoLivroComSucesso() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Sucesso");
        //define a mensagem
        builder.setMessage("Livro excluído com sucesso!");
        //define um botão como positivo
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                alerta.dismiss();
            }
        });
        //cria o AlertDialog
        alerta = builder.create();
        //Exibe
        alerta.show();
    }

    private void loadAdapter()
    {
        Intent pegarDado = getIntent();
        idPasta = pegarDado.getIntExtra("idPasta",0);
        livrosArrayList = new ArrayList<Livro>();
        livrosArrayList = LivroDAO.listarLivrosPorPasta(this,idPasta);
        //livrosArrayList = LivroDAO.listarLivros(this);
        setContentView(R.layout.activity_home_pasta);

        livroRecyclerView = findViewById(R.id.recyclerViewLivros);
        livroRecyclerView.setHasFixedSize(true);
        livroLayoutManager = new LinearLayoutManager(this);
        livroAdapter = new LivroAdapter(livrosArrayList);
        livroRecyclerView.setLayoutManager(livroLayoutManager);
        livroRecyclerView.setAdapter(livroAdapter);
    }

}
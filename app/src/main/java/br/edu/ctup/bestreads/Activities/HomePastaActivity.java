package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import br.edu.ctup.bestreads.Adapter.LivroAdapter;
import br.edu.ctup.bestreads.Adapter.PastaAdapter;
import br.edu.ctup.bestreads.DAO.HelperDAO;
import br.edu.ctup.bestreads.DAO.LivroDAO;
import br.edu.ctup.bestreads.Model.Livro;
import br.edu.ctup.bestreads.R;

public class HomePastaActivity extends AppCompatActivity {
private ArrayList<Livro> livrosArrayList;
private HelperDAO helper;
private RecyclerView livroRecyclerView;
private LivroAdapter livroAdapter;
private RecyclerView.LayoutManager livroLayoutManager;
    private MaterialSearchBar materialSearchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        livrosArrayList = new ArrayList<Livro>();
        helper = new HelperDAO(this);
        livrosArrayList = LivroDAO.listarLivros(this);
        setContentView(R.layout.activity_home_pasta);

        livroRecyclerView = findViewById(R.id.recyclerViewLivros);
        livroRecyclerView.setHasFixedSize(true);
        livroLayoutManager = new LinearLayoutManager(this);
        livroAdapter = new LivroAdapter(livrosArrayList);
        livroRecyclerView.setLayoutManager(livroLayoutManager);
        livroRecyclerView.setAdapter(livroAdapter);

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
                startActivity(intentOrigem);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

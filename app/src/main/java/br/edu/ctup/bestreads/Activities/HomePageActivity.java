package br.edu.ctup.bestreads.Activities;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import br.edu.ctup.bestreads.Adapter.PastaAdapter;
import br.edu.ctup.bestreads.DAO.HelperDAO;
import br.edu.ctup.bestreads.DAO.PastaDAO;
import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class HomePageActivity extends AppCompatActivity {
    private  ArrayList<Pasta> pastasArrayList;
    private ArrayList<String> suggestList = new ArrayList<>();
    private HelperDAO helper;
    private RecyclerView pastaRecyclerView;
    private MaterialSearchBar materialSearchBar;
    private PastaAdapter pastaAdapter;
    private RecyclerView.LayoutManager pastaLayoutManager;
    private ImageView btn_excluir_pasta;
    private ImageView btn_editar_pasta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pastasArrayList = new ArrayList<Pasta>();
        helper = new HelperDAO(this);
        pastasArrayList =  PastaDAO.listarPastas(this);
        setContentView(R.layout.activity_home_page);

        btn_excluir_pasta = (ImageView) findViewById(R.id.btn_excluir_pasta);
        btn_editar_pasta = (ImageView) findViewById(R.id.btn_editar_pasta);
        materialSearchBar = (MaterialSearchBar) findViewById(R.id.search_bar);

        final String[] pastas = new String[pastasArrayList.size()];


        //Init View
        pastaRecyclerView = findViewById(R.id.recyclerView);
        pastaRecyclerView.setHasFixedSize(true);
        pastaLayoutManager = new LinearLayoutManager(this);
        pastaAdapter = new PastaAdapter(pastasArrayList);
        pastaRecyclerView.setLayoutManager(pastaLayoutManager);
        pastaRecyclerView.setAdapter(pastaAdapter);
        //Setup Search bar
        materialSearchBar.setHint("Qual pasta você procura?");
        materialSearchBar.setCardViewElevation(10);
        loadSugestList();

        pastaAdapter.setOnItemClickListener(new PastaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Rodolfo Lindo");
            }

            @Override
            public void onDeleteClick(int position) {
                deleteItem(position);
            }

            @Override
            public void onEditClick(int position) {
                editItem(position);
            }
        });


        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<String> sugest = new ArrayList<>();
                for (String search: suggestList){
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())){
                        sugest.add(search);
                    }
                }
                materialSearchBar.setLastSuggestions(sugest);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){
                    pastaAdapter = new PastaAdapter(PastaDAO.listarPastas(getBaseContext()));
                    pastaRecyclerView.setAdapter(pastaAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

    }

    private void startSearch(String text) {
        pastaAdapter = new PastaAdapter(PastaDAO.encontrarPastaPorNome(this,text));
        pastaRecyclerView.setAdapter(pastaAdapter);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void OnResume(){

    }

    public void loadSugestList(){
        suggestList = helper.listarPastasPorNome();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    public void changeItem(int position, String text){
        pastasArrayList.get(position).changeNomePasta(text);
        pastaAdapter.notifyItemChanged(position);
    }

    public void deleteItem(int position){
        Pasta pasta = pastasArrayList.get(position);
        PastaDAO.excluirPasta(this,pasta);
        Toast.makeText(this,"Exluído com Sucesso", Toast.LENGTH_SHORT).show();
        pastasArrayList =  PastaDAO.listarPastas(this);
        pastaRecyclerView = findViewById(R.id.recyclerView);
        pastaRecyclerView.setHasFixedSize(true);
        pastaLayoutManager = new LinearLayoutManager(this);
        pastaAdapter = new PastaAdapter(pastasArrayList);
        pastaRecyclerView.setLayoutManager(pastaLayoutManager);
        pastaRecyclerView.setAdapter(pastaAdapter);
        pastaAdapter.notifyDataSetChanged();
        pastaAdapter.notifyItemChanged(position);
    }

    public void editItem(int position){
        int idPasta = pastasArrayList.get(position).getIdPasta();
        Toast.makeText(this, String.valueOf(idPasta), Toast.LENGTH_SHORT).show();
        Intent intentOrigem = new Intent(HomePageActivity.this, EditarPastaActivity.class);
        intentOrigem.putExtra("idPasta",idPasta);
        startActivity(intentOrigem);
        pastaAdapter.notifyItemChanged(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_pastas, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_pasta:
                Intent intentOrigem = new Intent(HomePageActivity.this, CadastrarPasta.class);
                startActivity(intentOrigem);
                return true;
            case R.id.buscar_pasta:
                Toast.makeText(this, "Buscando Pasta", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}


package br.edu.ctup.bestreads.Activities;

import android.app.AlertDialog;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;

import br.edu.ctup.bestreads.Adapter.PastaAdapter;
import br.edu.ctup.bestreads.DAO.HelperDAO;
import br.edu.ctup.bestreads.DAO.PastaDAO;
import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class HomePageActivity extends AppCompatActivity {
    private  ArrayList<Pasta> pastasArrayList;
    private ArrayList<String> suggestList = new ArrayList<>();
    private HelperDAO helper;
    private AlertDialog alerta;
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


        materialSearchBar.setHint("Qual pasta você procura?");
        materialSearchBar.setCardViewElevation(10);

        //Carregamento do Adapter
        loadAdapter();

        //Carregamento dos botãos do adapter
       loadBottonsAdapter();

        loadSugestList();

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
                    loadBottonsAdapter();
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

    private void loadAdapter()
    {
        pastasArrayList = new ArrayList<Pasta>();
        pastasArrayList =  PastaDAO.listarPastas(this);
        pastaRecyclerView = findViewById(R.id.recyclerView);
        pastaRecyclerView.setHasFixedSize(true);
        pastaLayoutManager = new LinearLayoutManager(this);
        pastaAdapter = new PastaAdapter(pastasArrayList);
        pastaRecyclerView.setLayoutManager(pastaLayoutManager);
        pastaRecyclerView.setAdapter(pastaAdapter);
    }

    private void loadBottonsAdapter()
    {
        pastaAdapter.setOnItemClickListener(new PastaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                openFolder(position);
            }

            @Override
            public void onDeleteClick(int position) {
                ArrayList<Pasta> itens = pastaAdapter.acervoPastas;
                exibirAlertDialogExclusaoPasta(position, itens);
            }

            @Override
            public void onEditClick(int position) {
                ArrayList<Pasta> itens = pastaAdapter.acervoPastas;
                editItem(position, itens);
            }
        });
    }


    private void startSearch(String text) {
        pastaAdapter = new PastaAdapter(PastaDAO.encontrarPastaPorNome(this,text));
        pastaRecyclerView.setAdapter(pastaAdapter);
        loadBottonsAdapter();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void OnResume(){

    }

    public void loadSugestList(){
        suggestList = helper.listarPastasPorNome();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    public void openFolder(int position){
        Pasta pasta = pastasArrayList.get(position);
        Intent intentOrigem = new Intent(HomePageActivity.this, HomePastaActivity.class);
        intentOrigem.putExtra("idPasta",pasta.getIdPasta());
        startActivity(intentOrigem);
    }

    public void deleteItem(int position, ArrayList<Pasta> itens){
        int idPasta = itens.get(position).getIdPasta();
        Pasta pasta = PastaDAO.BuscarPastaPorId(this, idPasta);
        PastaDAO.excluirPasta(this,pasta);
        exibirAlertDialogExclusaoPastaComSucesso();
        loadAdapter();
        loadBottonsAdapter();
        pastaAdapter.notifyDataSetChanged();
        pastaAdapter.notifyItemChanged(position);

    }

    public void editItem(int position, ArrayList<Pasta> itens){
        int idPasta = itens.get(position).getIdPasta();
        Pasta pasta = PastaDAO.BuscarPastaPorId(this, idPasta);
        Toast.makeText(this, String.valueOf(idPasta), Toast.LENGTH_SHORT).show();
        Intent intentOrigem = new Intent(HomePageActivity.this, EditarPastaActivity.class);
        intentOrigem.putExtra("Pasta",pasta);
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
                Intent intentOrigem = new Intent(HomePageActivity.this, CadastrarPastaActivity.class);
                startActivity(intentOrigem);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void exibirAlertDialogExclusaoPasta(final int position, final ArrayList<Pasta> itens) {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("ATENÇÃO");
        //define a mensagem
        String nomeDaPasta = itens.get(position).getNomePasta();
        builder.setMessage("Deseja realmente deletar a pasta " + nomeDaPasta + "?");
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

    private void exibirAlertDialogExclusaoPastaComSucesso() {
        //Cria o gerador do AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //define o titulo
        builder.setTitle("Sucesso");
        //define a mensagem
        builder.setMessage("Pasta excluída com sucesso!");
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

}


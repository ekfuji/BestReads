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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import br.edu.ctup.bestreads.Adapter.PastaAdapter;
import br.edu.ctup.bestreads.DAO.HelperDAO;
import br.edu.ctup.bestreads.DAO.PastaDAO;
import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class HomePageActivity extends AppCompatActivity {

    private RecyclerView pastaRecyclerView;
    private RecyclerView.Adapter pastaAdapter;
    private RecyclerView.LayoutManager pastaLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);

        final ArrayList<Pasta> pastasArrayList = PastaDAO.listarPastas(this);
        String[] pastas = new String[pastasArrayList.size()];


        pastaRecyclerView = findViewById(R.id.recyclerView);
        pastaRecyclerView.setHasFixedSize(true);
        pastaLayoutManager = new LinearLayoutManager(this);
        pastaAdapter = new PastaAdapter(pastasArrayList);


        pastaRecyclerView.setLayoutManager(pastaLayoutManager);
        pastaRecyclerView.setAdapter(pastaAdapter);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void OnResume(){
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


package br.edu.ctup.bestreads.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.edu.ctup.bestreads.R;

public class MainActivity extends AppCompatActivity {
    private Button btnProsseguir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProsseguir = (Button) findViewById(R.id.btn_prosseguir);
    }

    public void OnClickIrParaHome(View view) {
        Intent intentOrigem = new Intent(MainActivity.this, HomePageActivity.class);
        startActivity(intentOrigem);
    }
}

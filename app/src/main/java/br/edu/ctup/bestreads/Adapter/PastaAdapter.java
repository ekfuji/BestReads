package br.edu.ctup.bestreads.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.edu.ctup.bestreads.Model.Pasta;
import br.edu.ctup.bestreads.R;

public class PastaAdapter extends RecyclerView.Adapter<PastaAdapter.HomePageViewHolder> {
    private ArrayList<Pasta> acervoPastas;

    public static class HomePageViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewPasta;
        public TextView textViewNomePasta;

        public HomePageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomePasta = itemView.findViewById(R.id.nome_pasta);
        }


    }
    public PastaAdapter(ArrayList<Pasta> pastas){
        acervoPastas = pastas;
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_pasta, viewGroup, false);
        HomePageViewHolder homePageViewHolder = new HomePageViewHolder(view);


        return  homePageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomePageViewHolder homePageViewHolder, int posicao) {
        Pasta pastaAtual = acervoPastas.get(posicao);
        homePageViewHolder.textViewNomePasta.setText(pastaAtual.getNomePasta());

    }

    @Override
    public int getItemCount() {
        return acervoPastas.size();
    }

}

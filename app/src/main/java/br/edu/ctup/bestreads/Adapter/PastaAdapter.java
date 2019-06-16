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
    public ArrayList<Pasta> acervoPastas;
    private OnItemClickListener itemListener;


    public interface  OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }

    public  void setOnItemClickListener(OnItemClickListener listener){
        itemListener = listener;
    }

    public static class HomePageViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageViewPasta;
        public TextView textViewNomePasta;
        public ImageView btnExcluirPasta, btnEditarPasta;

        public HomePageViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewNomePasta = itemView.findViewById(R.id.nome_pasta);
            btnExcluirPasta = itemView.findViewById(R.id.btn_excluir_pasta);
            btnEditarPasta = itemView.findViewById(R.id.btn_editar_pasta);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            btnExcluirPasta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
            btnEditarPasta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }


    }
    public PastaAdapter(ArrayList<Pasta> pastas){
        acervoPastas = pastas;
    }

    @NonNull
    @Override
    public HomePageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_pasta, viewGroup, false);
        HomePageViewHolder homePageViewHolder = new HomePageViewHolder(view, itemListener);


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

package br.edu.ctup.bestreads.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import br.edu.ctup.bestreads.Model.Livro;
import br.edu.ctup.bestreads.R;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.LivroViewHolder> {
    public ArrayList<Livro> acervoLivros;
    private OnItemClickListener itemListener;

    public interface  OnItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
        void onEditClick(int position);
    }


    public void setOnItemClickListner(OnItemClickListener listner){ itemListener = listner;}

    public static class LivroViewHolder extends RecyclerView.ViewHolder{
        public ImageView fotoLivro, btnExcluirLivro, btnEditarLivro;
        public TextView nomeLivro, autorLivro;

        public LivroViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            fotoLivro = itemView.findViewById(R.id.foto_livro);
            nomeLivro = itemView.findViewById(R.id.nome_livro);
            autorLivro = itemView.findViewById(R.id.nome_autor_livro);
            btnEditarLivro = itemView.findViewById(R.id.btn_editar_livro);
            btnExcluirLivro = itemView.findViewById(R.id.btn_excluir_livro);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
            btnEditarLivro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onEditClick(position);
                        }
                    }
                }
            });

            btnExcluirLivro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public LivroAdapter(ArrayList<Livro> livros){
        acervoLivros = livros;
    }

    @NonNull
    @Override
    public LivroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_livro, viewGroup, false);
        LivroViewHolder livroViewHolder = new LivroViewHolder(view,itemListener);
        return  livroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LivroViewHolder livroViewHolder, int position) {
        Livro livroAtual = acervoLivros.get(position);
        //Pede um int mas como faremos para salvar no banco dps carregar?
        livroViewHolder.nomeLivro.setText(livroAtual.getNome());
        livroViewHolder.autorLivro.setText(String.valueOf(livroAtual.getNomeAutor()));
        //livroViewHolder.fotoLivro.setImageResource(livroAtual.getFotoLivro().indexOf(position));
        try{
            /*ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(livroAtual.getFotoLivro());
            Bitmap bitmap = BitmapFactory.decodeStream(arrayInputStream);*/
            Bitmap bitmap = BitmapFactory.decodeByteArray(livroAtual.getFotoLivro(),0,livroAtual.getFotoLivro().length);
            ByteArrayOutputStream stream = new ByteArrayOutputStream(bitmap.getWidth() * bitmap.getHeight());
            if(bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)){
                livroViewHolder.fotoLivro.setImageBitmap(bitmap);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        //Verificar como faz para pegar nome do Autor se não conseguirmos retirar no card view
    }

    @Override
    public int getItemCount() {
        return acervoLivros.size();
    }


}
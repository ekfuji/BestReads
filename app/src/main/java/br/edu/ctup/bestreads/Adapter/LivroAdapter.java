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

    public static class LivroViewHolder extends RecyclerView.ViewHolder{
        public ImageView fotoLivro;
        public TextView nomeLivro, autorLivro;

        public LivroViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoLivro = itemView.findViewById(R.id.foto_livro);
            nomeLivro = itemView.findViewById(R.id.nome_livro);
            autorLivro = itemView.findViewById(R.id.nome_autor_livro);
        }
    }

    public LivroAdapter(ArrayList<Livro> livros){
        acervoLivros = livros;
    }

    @NonNull
    @Override
    public LivroViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_livro, viewGroup, false);
        LivroViewHolder livroViewHolder = new LivroViewHolder(view);
        return  livroViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LivroViewHolder livroViewHolder, int position) {
        Livro livroAtual = acervoLivros.get(position);
        //Pede um int mas como faremos para salvar no banco dps carregar?
        livroViewHolder.nomeLivro.setText(livroAtual.getNome());
        livroViewHolder.autorLivro.setText(livroAtual.getNomeAutor());
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
    }

    @Override
    public int getItemCount() {
        return acervoLivros.size();
    }


}

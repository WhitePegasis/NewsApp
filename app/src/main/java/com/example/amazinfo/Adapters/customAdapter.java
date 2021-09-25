package com.example.amazinfo.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.amazinfo.Interfaces.OnArticleClickedListener;
import com.example.amazinfo.Modals.ArticleModal;
import com.example.amazinfo.R;
import com.example.amazinfo.RecyclerviewViewholder.customViewHolder;

import java.util.List;

public class customAdapter extends RecyclerView.Adapter<customViewHolder> {

    private final Context context;
    private final List<ArticleModal> news;
    private final OnArticleClickedListener listener;

    public customAdapter(Context context, List<ArticleModal> headlines,OnArticleClickedListener listener) {
        this.context = context;
        this.news = headlines;
        this.listener=listener;
    }


    @NonNull
    @Override
    public customViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new customViewHolder(LayoutInflater.from(context).inflate(R.layout.sample_headline_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull customViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.headline.setText(news.get(position).getTitle());
        holder.source.setText(news.get(position).getSource().getName());
        if(news.get(position).getUrlToImage()!=null)
        {
            Glide.with(context).load(news.get(position).getUrlToImage())
                    .into(holder.image);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnArticleClicked(news.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }
}

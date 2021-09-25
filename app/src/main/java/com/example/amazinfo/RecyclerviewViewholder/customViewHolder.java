package com.example.amazinfo.RecyclerviewViewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.amazinfo.R;

public class customViewHolder extends RecyclerView.ViewHolder {
    public TextView headline;
    public TextView source;
    public ImageView image;
    public CardView cardView;
    public customViewHolder(@NonNull View itemView) {
        super(itemView);
        headline=itemView.findViewById(R.id.headline);
        source =itemView.findViewById(R.id.source);
        image=itemView.findViewById(R.id.image);
        cardView=itemView.findViewById(R.id.cardView);
    }
}

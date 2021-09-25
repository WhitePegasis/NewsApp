package com.example.amazinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.amazinfo.Modals.ArticleModal;
import com.example.amazinfo.databinding.ActivityDetailNewsBinding;

import java.util.Objects;

public class DetailNewsActivity extends AppCompatActivity {
    ActivityDetailNewsBinding binding;
    ArticleModal article;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding=ActivityDetailNewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        article=(ArticleModal) getIntent().getSerializableExtra("data");

        binding.newsHeadline.setText(article.getTitle());
        binding.newsAuthor.setText(article.getAuthor());
        binding.newsTime.setText(article.getPublishedAt());
        binding.newsDescription.setText(article.getDescription());
        binding.newsContent.setText(article.getContent());
        Glide.with(this).load(article.getUrlToImage()).into(binding.newsImage);
    }
}
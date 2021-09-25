package com.example.amazinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.amazinfo.Adapters.customAdapter;
import com.example.amazinfo.Interfaces.OnArticleClickedListener;
import com.example.amazinfo.Modals.ArticleModal;
import com.example.amazinfo.Modals.NewsApiResponse;
import com.example.amazinfo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnArticleClickedListener{
    ActivityMainBinding binding;
    //RecyclerView recyclerView;
    customAdapter adapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching new headlines...");
        dialog.show();
        apiRequestManager manager=new apiRequestManager(this);
        manager.getNews(listener,"sports",null);
    }

    private final OnFetchDataCustomListener<NewsApiResponse> listener= new OnFetchDataCustomListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<ArticleModal> list, String message) {
            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<ArticleModal> list) {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new customAdapter(this,list,this);
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnArticleClicked(ArticleModal headlines) {
        startActivity(new Intent(this,DetailNewsActivity.class).putExtra("data",headlines));
    }
}
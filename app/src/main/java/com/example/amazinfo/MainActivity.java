package com.example.amazinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.amazinfo.Adapters.customAdapter;
import com.example.amazinfo.Interfaces.OnArticleClickedListener;
import com.example.amazinfo.Modals.ArticleModal;
import com.example.amazinfo.Modals.NewsApiResponse;
import com.example.amazinfo.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnArticleClickedListener, View.OnClickListener{
    ActivityMainBinding binding;
    //RecyclerView recyclerView;
    customAdapter adapter;
    ProgressDialog dialog;
    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog=new ProgressDialog(this);
        dialog.setTitle("Fetching news headlines...");
        dialog.show();
        selectedCategory="sports";
        apiRequestManager manager=new apiRequestManager(this);
        manager.getNews(listener,"sports",null);


        binding.businessBtn.setOnClickListener(this);
        binding.technologyBtn.setOnClickListener(this);
        binding.scienceBtn.setOnClickListener(this);
        binding.generalBtn.setOnClickListener(this);
        binding.healthBtn.setOnClickListener(this);
        binding.sportsBtn.setOnClickListener(this);
        binding.entertainmentBtn.setOnClickListener(this);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles for "+query);
                dialog.setCancelable(false);
                dialog.show();
                apiRequestManager manager=new apiRequestManager(MainActivity.this);
                manager.getNews(listener,selectedCategory,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private final OnFetchDataCustomListener<NewsApiResponse> listener= new OnFetchDataCustomListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<ArticleModal> list, String message) {
            if(list.isEmpty())
            {
                Toast.makeText(MainActivity.this, "No news article found!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
            else {
                showNews(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Some error occured!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        Button btn= (Button) view;
        String category=btn.getText().toString();
        selectedCategory=category;
        dialog.setTitle("Fetching news of "+category+" category");
        dialog.setCancelable(false);
        dialog.show();
        apiRequestManager manager=new apiRequestManager(this);
        manager.getNews(listener,category,null);
    }
}
package com.example.amazinfo;

import com.example.amazinfo.Modals.ArticleModal;

import java.util.List;

public interface OnFetchDataCustomListener<NewsApiRespose> {
    void onFetchData(List<ArticleModal> list, String message);
    void onError(String message);
}

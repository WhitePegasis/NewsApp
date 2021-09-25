package com.example.amazinfo.Modals;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status="";
    int totalResults=0;
    List<ArticleModal> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticleModal> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticleModal> articles) {
        this.articles = articles;
    }
}

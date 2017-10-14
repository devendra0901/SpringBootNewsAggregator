package com.stackroute.newsaggregator.service;

import java.util.List;

import com.stackroute.newsaggregator.exceptions.ArticleAlreadyExistsException;
import com.stackroute.newsaggregator.exceptions.ArticleNotFoundException;
import com.stackroute.newsaggregator.model.Article;

public interface NewsService {

	void addArticle(Article article) throws ArticleAlreadyExistsException;
	void updateArticle(Article article) throws ArticleNotFoundException;
    List<Article> getAllArticles();
    void deleteArticle(int articleId) throws ArticleNotFoundException;
    Article getArticleById(int articleId)throws ArticleNotFoundException;

}

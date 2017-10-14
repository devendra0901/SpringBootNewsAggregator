package com.stackroute.newsaggregator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsaggregator.dao.NewsRepository;
import com.stackroute.newsaggregator.exceptions.ArticleAlreadyExistsException;
import com.stackroute.newsaggregator.exceptions.ArticleNotFoundException;
import com.stackroute.newsaggregator.model.Article;

@Service
public class NewsServiceRdbmsImpl implements NewsService {

	@Autowired
	private NewsRepository newsRepo;
	
	@Override
	public void addArticle(Article article) throws ArticleAlreadyExistsException{
		
		if(newsRepo.exists(article.getId())) {
			throw new ArticleAlreadyExistsException("Article with Id "+article.getId() +" already exist");
		}else {
			newsRepo.save(article);
		}
	}

	@Override
	public List<Article> getAllArticles() {
		List<Article> articles = new ArrayList<>();	
		newsRepo.findAll().forEach(articles::add);
		return articles;
	}

	@Override
	public void deleteArticle(int articleId) throws ArticleNotFoundException {
		newsRepo.delete(articleId);
		
	}

	@Override
	public void updateArticle(Article article) throws ArticleNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article getArticleById(int articleId) throws ArticleNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}

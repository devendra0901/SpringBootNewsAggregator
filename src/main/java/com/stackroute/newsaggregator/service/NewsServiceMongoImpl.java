package com.stackroute.newsaggregator.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.stackroute.newsaggregator.dao.NewsRepoMongoDb;
import com.stackroute.newsaggregator.exceptions.ArticleAlreadyExistsException;
import com.stackroute.newsaggregator.exceptions.ArticleNotFoundException;
import com.stackroute.newsaggregator.model.Article;

@Service
@Qualifier("newsServiceMongoImpl")
public class NewsServiceMongoImpl implements NewsService {

	@Autowired
	private NewsRepoMongoDb newsRepo;

	@Override
	public void addArticle(Article article) throws ArticleAlreadyExistsException {
		if (newsRepo.exists(article.getId())) {
			throw new ArticleAlreadyExistsException("Article with Id " + article.getId() + " already exist");
		} else {
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
		if (newsRepo.exists(articleId)) {
		newsRepo.delete(articleId);
		}else {
			throw new ArticleNotFoundException("Article with Id " + articleId + " does not exist");
		}


	}

	@Override
	public void updateArticle(Article article) throws ArticleNotFoundException {
		if (newsRepo.exists(article.getId())) {
			newsRepo.save(article);
		} else {
			throw new ArticleNotFoundException("Article with Id " + article.getId() + " does not exist");
		}

	}

	@Override
	public Article getArticleById(int articleId) throws ArticleNotFoundException {
		if (newsRepo.exists(articleId)) {
			return newsRepo.findOne(articleId);
		} else {
			throw new ArticleNotFoundException("Article with Id " + articleId + " does not exist");
		}
	}

}

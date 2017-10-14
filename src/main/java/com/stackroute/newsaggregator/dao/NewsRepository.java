package com.stackroute.newsaggregator.dao;

import org.springframework.data.repository.CrudRepository;

import com.stackroute.newsaggregator.model.Article;

public interface NewsRepository extends CrudRepository<Article, Integer> {

}

package com.stackroute.newsaggregator.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stackroute.newsaggregator.model.Article;

public interface NewsRepoMongoDb extends MongoRepository<Article, Integer>{

}

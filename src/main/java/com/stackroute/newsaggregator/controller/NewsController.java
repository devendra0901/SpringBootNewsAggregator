package com.stackroute.newsaggregator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newsaggregator.exceptions.ArticleAlreadyExistsException;
import com.stackroute.newsaggregator.exceptions.ArticleNotFoundException;
import com.stackroute.newsaggregator.model.Article;
import com.stackroute.newsaggregator.service.NewsService;

@RestController
@RequestMapping("/v1/news")
public class NewsController {

	@Autowired
	@Qualifier("newsServiceMongoImpl")
	private NewsService newsService;

	/******************* All PUT Request Mapping **********************/

	@RequestMapping(value = "/article", method = RequestMethod.PUT)
	public ResponseEntity<String> postAllArticles(RequestEntity<Article> requestEntityArticle) {

		/*
		 * System.out.println("request body: " + requestEntity.getBody().getAuthor());
		 * System.out.println("request headers " + requestEntity.getHeaders());
		 * System.out.println("request method : " + requestEntity.getMethod());
		 */

		try {
			newsService.updateArticle(requestEntityArticle.getBody());
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		} catch (ArticleNotFoundException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.NOT_FOUND);

		}
	}

	/******************* All GET Request Mapping **************************/

	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ResponseEntity<List<Article>> getAllArticles() {

		List<Article> articles = newsService.getAllArticles();

		return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);

	}

	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public ResponseEntity getArticleById(@PathVariable int id) {

		try {
			Article article = newsService.getArticleById(id);
			return new ResponseEntity<Article>(article, HttpStatus.OK);
		} catch (ArticleNotFoundException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/******************
	 * All DELETE Request Mapping
	 *********************************/

	@RequestMapping(value = "/article", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteArticle(RequestEntity<Integer> requestEntityId) {

		// logic to be written to delete that particular data from database
		try {
			newsService.deleteArticle(requestEntityId.getBody());
			return new ResponseEntity<String>("Deleted Succesfully" + requestEntityId.getBody(), HttpStatus.OK);
		} catch (ArticleNotFoundException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	@RequestMapping(value = "/article/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteArticle(@PathVariable("id") int id) {

		// logic to be written to delete that particular data from database
		try {
			newsService.deleteArticle(id);
			return new ResponseEntity<String>("Deleted Succesfully" + id, HttpStatus.OK);
		} catch (ArticleNotFoundException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.NOT_FOUND);
		}

	}

	/********************* All POST Request Mapping *******************************/

	@RequestMapping(value = "/article", method = RequestMethod.POST)
	public ResponseEntity<String> saveArticle(RequestEntity<Article> requestEntityArticle) {

		/*
		 * System.out.println("request body: " +
		 * requestEntityArticle.getBody().getAuthor());
		 * System.out.println("request headers " + requestEntityArticle.getHeaders());
		 * System.out.println("request method : " + requestEntityArticle.getMethod());
		 */

		try {
			newsService.addArticle(requestEntityArticle.getBody());
			return new ResponseEntity<String>("Added Successfully", HttpStatus.OK);
		} catch (ArticleAlreadyExistsException exp) {
			return new ResponseEntity<String>(exp.getMessage(), HttpStatus.CONFLICT);

		}

	}

	/******************* Default Request Mapping *****************************/

	@RequestMapping()
	public ResponseEntity<String> defaultMap() {
		return new ResponseEntity<String>("Request Not Found, Please Enter Proper Url", HttpStatus.OK);
	}

}

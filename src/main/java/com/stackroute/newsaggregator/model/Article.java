package com.stackroute.newsaggregator.model;

import javax.persistence.Column;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="articles")
public class Article {

	@Id
	private int id;	
	private String author;
	private String title;
	private String description;
	private String url;
	@Column(name="imageurl")
	private String urlToImage;

	
	public int getId() {
		return id;
	}

	public void setId(int articleId) {
		this.id = articleId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlToImage() {
		return urlToImage;
	}

	public void setUrlToImage(String urlToImage) {
		this.urlToImage = urlToImage;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Article[id=%d, author='%s', title='%s']",
                id, author, title);
    }

}

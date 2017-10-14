package com.stackroute.newsaggregator.exceptions;

public class ArticleAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArticleAlreadyExistsException(String message) {
		super(message);
	}

}

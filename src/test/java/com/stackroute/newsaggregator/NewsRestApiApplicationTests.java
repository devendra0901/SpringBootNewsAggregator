package com.stackroute.newsaggregator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.newsaggregator.NewsRestApiApplication;
import com.stackroute.newsaggregator.model.Article;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NewsRestApiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NewsRestApiApplicationTests {

	@LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    Article article;

    @Before
    public void setUp() throws Exception {
      article = new Article();
      article.setAuthor("Andrew MarchandESPN Senior Writer");

    }
    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPostArticle() throws Exception {

        HttpEntity<Article> entity = new HttpEntity<Article>(article, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/v1/news/article"),
                HttpMethod.POST, entity, String.class);
        assertNotNull(response);
        String actual = response.getBody();
        System.out.println(actual);
        assertNotNull(actual);
    }

//    @Test
//    public void testList() throws Exception {
//        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//        ResponseEntity<String> response = restTemplate.exchange(
//                createURLWithPort("/user/list"),
//                HttpMethod.GET, entity, String.class);
//        assertNotNull(response);
//
//    }
//    @Test
//    public void testGetUser() throws Exception {
//
//    }
//    @Test
//    public void testUpdateUser() throws Exception {
//
//    }
//
//    @Test
//    public void testDeleteUser() throws Exception {
//
//    }


}

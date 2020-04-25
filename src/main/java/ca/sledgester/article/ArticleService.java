package ca.sledgester.article;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ArticleService {

    String url = "";

    public void saveArticle(Article article) {

        url = "http://localhost:8044/articles/";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Article> requestEntity = new HttpEntity<>(article);
        ResponseEntity<Article> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Article.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //map.addAttribute(article);
        } else {
            //TODO figure out how to localize this.
            String message = "Article not created!";
            throw new RuntimeException(message);
        }

    }

    public Article searchArticle(ArticleForm articleForm) {

        url = "http://localhost:8044/articles/search/findByNameIgnoreCase?name={name}";

        Article article = new Article();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Article> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Article.class, articleForm.getName());
            article = responseEntity.getBody();
        } catch(HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                article = new Article();
            }
        }

        return article;

    }

    public Article populateObject(ArticleForm articleForm) {

        Article article = new Article();

        article.setId(articleForm.getId());
        article.setName(articleForm.getName());
        article.setValue(articleForm.getValue());
        article.setPictureString(articleForm.getPictureString());

        return article;

    }

    public ArticleForm populateForm(Article article) {

        ArticleForm articleForm = new ArticleForm();

        articleForm.setId(article.getId());
        articleForm.setValue(article.getValue());
        articleForm.setName(article.getName());
        articleForm.setPictureString(article.getPictureString());

        return articleForm;

    }

}

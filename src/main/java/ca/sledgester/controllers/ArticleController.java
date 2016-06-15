package ca.sledgester.controllers;

import ca.sledgester.objects.Article;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Controller
public class ArticleController {
    @RequestMapping("/article")
    public String article(ModelMap map) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Article> responseEntity = restTemplate.exchange("http://localhost:8044/articles/1", HttpMethod.GET, null, Article.class);

        Article article = responseEntity.getBody();
        map.addAttribute(article);
        return "article";
    }
}

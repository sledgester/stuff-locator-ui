package ca.sledgester.article;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Controller
public class ArticleController {
    @RequestMapping(value="/article", method= RequestMethod.GET)
    public String article(@ModelAttribute Article article) {

        return "article";
    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "save")
    public String articleCreatePost(@ModelAttribute Article article) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Article> requestEntity = new HttpEntity<>(article);
        ResponseEntity<Article> responseEntity = restTemplate.exchange("http://localhost:8044/articles/", HttpMethod.POST, requestEntity, Article.class);

        if (responseEntity.getStatusCode()== HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //map.addAttribute(article);
        } else {
            //TODO figure out how to localize this.
            String message = "Article not created!";
            throw new RuntimeException(message);
        }

        return "article";
    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "search")
    public String articleSearchPost(@ModelAttribute Article article, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Article> responseEntity = restTemplate.exchange("http://localhost:8044/articles/search/findByNameIgnoreCase?name={name}", HttpMethod.GET, null, Article.class, article.getName());
            article = responseEntity.getBody();
        } catch(HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                article = new Article();
            }
        }

        model.addAttribute(article);
        return "article";
    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "back")
    public String articleBackPost() {
        return "mainMenu";
    }
}

package ca.sledgester.article;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        ArticleService articleService = new ArticleService();

        articleService.createArticle(article);

        return "article";

    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "search")
    public String articleSearchPost(@ModelAttribute Article article, Model model) {

        ArticleService articleService = new ArticleService();

        article = articleService.searchArticle(article);

        model.addAttribute(article);

        return "article";

    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "back")
    public String articleBackPost() {

        return "mainMenu";

    }
}

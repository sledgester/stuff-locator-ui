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
    public String article(@ModelAttribute ArticleForm articleForm) {

        return "article";

    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "save")
    public String articleCreatePost(@ModelAttribute ArticleForm articleForm) {

        Article article = new Article();
        ArticleService articleService = new ArticleService();

        article = articleService.populateObject(articleForm);

        articleService.createArticle(article);

        return "article";

    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "search")
    public String articleSearchPost(@ModelAttribute ArticleForm articleForm, Model model) {

        Article article = new Article();
        ArticleService articleService = new ArticleService();

        article = articleService.searchArticle(articleForm);

        articleForm = articleService.populateForm(article);

        model.addAttribute(articleForm);

        return "article";

    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "back")
    public String articleBackPost() {

        return "mainMenu";

    }
}

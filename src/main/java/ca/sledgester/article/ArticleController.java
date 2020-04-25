package ca.sledgester.article;

import ca.sledgester.container.Container;
import ca.sledgester.container.ContainerService;
import ca.sledgester.person.Person;
import ca.sledgester.person.PersonService;
import ca.sledgester.utils.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Controller
public class ArticleController {

    @RequestMapping(value="/article", method= RequestMethod.GET)
    public String article(@ModelAttribute ArticleForm articleForm, Model model) {

        ContainerService containerService = new ContainerService();
        PersonService personService = new PersonService();

        List<Container> containerList = containerService.getAllContainersFaked();
        List<Person> personList = personService.getAllPeopleFaked();

        model.addAttribute(containerList);
        model.addAttribute(personList);

        return "article";

    }

    @RequestMapping(value="/article", method= RequestMethod.POST, params = "save")
    public String articleSavePost(@ModelAttribute ArticleForm articleForm) {

        Article article = new Article();
        ArticleService articleService = new ArticleService();
        Utils utils = new Utils();

        article = articleService.populateObject(articleForm);

        if (!articleForm.getPictureImage().getOriginalFilename().equals("")) {
            article.setPictureString(utils.imageToBase64String(articleForm.getPictureImage()));
        }
        articleService.saveArticle(article);

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

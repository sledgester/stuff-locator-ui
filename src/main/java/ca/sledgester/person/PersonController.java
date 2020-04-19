package ca.sledgester.person;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Controller
public class PersonController {

    @RequestMapping(value="/person", method= RequestMethod.GET)
    public String person(@ModelAttribute PersonForm personForm) {

        return "person";

    }

    @RequestMapping(value="/person", method= RequestMethod.POST, params = "save")
    public String personCreatePost(@ModelAttribute PersonForm personForm) {

        Person person = new Person();
        PersonService personService = new PersonService();

        person = personService.populateObject(personForm);

        personService.createPerson(person);

        return "person";

    }

    @RequestMapping(value="/person", method= RequestMethod.POST, params = "search")
    public String personSearchPost(@ModelAttribute PersonForm personForm, Model model) {

        Person person = new Person();
        PersonService personService = new PersonService();

        person = personService.searchPerson(personForm);

        personForm = personService.populateForm(person);

        model.addAttribute(personForm);

        return "person";

    }

    @RequestMapping(value="/person", method= RequestMethod.POST, params = "back")
    public String personBackPost() {

        return "mainMenu";

    }
}

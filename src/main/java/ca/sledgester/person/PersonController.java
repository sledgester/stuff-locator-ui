package ca.sledgester.person;

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
public class PersonController {

    @RequestMapping(value="/person", method= RequestMethod.GET)
    public String person(@ModelAttribute Person person) {

        return "person";
    }

    @RequestMapping(value="/person", method= RequestMethod.POST, params = "save")
    public String personCreatePost(@ModelAttribute Person person) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Person> requestEntity = new HttpEntity<>(person);
        ResponseEntity<Person> responseEntity = restTemplate.exchange("http://localhost:8044/people/", HttpMethod.POST, requestEntity, Person.class);

        if (responseEntity.getStatusCode()== HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //map.addAttribute(person);
        } else {
            //TODO figure out how to localize this.
            String message = "Person not created!";
            throw new RuntimeException(message);
        }

        return "person";
    }

    @RequestMapping(value="/person", method= RequestMethod.POST, params = "search")
    public String personSearchPost(@ModelAttribute Person person, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Person> responseEntity = restTemplate.exchange("http://localhost:8044/people/search/findByLastNameIgnoreCaseOrFirstNameIgnoreCase?lastName={lastName}&firstName={firsttName}", HttpMethod.GET, null, Person.class, person.getLastName(), person.getFirstName());
            person = responseEntity.getBody();
        }catch(HttpClientErrorException e) {
        if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
            person = new Person();
        }
    }

        model.addAttribute(person);

        return "person";
    }

    @RequestMapping(value="/person", method= RequestMethod.POST, params = "back")
    public String personBackPost() {
        return "mainMenu";
    }
}

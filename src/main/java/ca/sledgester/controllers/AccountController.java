package ca.sledgester.controllers;

import ca.sledgester.objects.Person;
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
public class AccountController {

    @RequestMapping("/account")
    public String account(ModelMap map) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Person> responseEntity = restTemplate.exchange("http://localhost:8044/persons/1", HttpMethod.GET, null, Person.class);

        Person person = responseEntity.getBody();
        map.addAttribute(person);
        return "account";
    }
}

package ca.sledgester.container;

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
public class ContainerController {
    @RequestMapping(value="/container", method= RequestMethod.GET)
    public String container(@ModelAttribute Container container) {

        return "container";
    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "save")
    public String containerCreatePost(@ModelAttribute Container container) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Container> requestEntity = new HttpEntity<>(container);
        ResponseEntity<Container> responseEntity = restTemplate.exchange("http://localhost:8044/containers/", HttpMethod.POST, requestEntity, Container.class);

        if (responseEntity.getStatusCode()== HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //model.addAttribute(container);
        } else {
            //TODO figure out how to localize this.
            String message = "Container not created!";
            throw new RuntimeException(message);
        }

        return "container";
    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "search")
    public String containerSearchPost(@ModelAttribute Container container, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Container> responseEntity = restTemplate.exchange("http://localhost:8044/containers/search/findByDescriptionIgnoreCase?description={description}", HttpMethod.GET, null, Container.class, container.getDescription());
            container = responseEntity.getBody();
        } catch(HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                container = new Container();
            }
        }

        model.addAttribute(container);
        return "container";
    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "back")
    public String containerBackPost() {
        return "mainMenu";
    }
}
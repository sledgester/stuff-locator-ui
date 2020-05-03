package ca.sledgester.person;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    String url = "";

    public void savePerson(Person person) {

        url = "http://localhost:8044/people/";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Person> requestEntity = new HttpEntity<>(person);
        ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Person.class);

        if (responseEntity.getStatusCode()== HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //map.addAttribute(person);
        } else {
            //TODO figure out how to localize this.
            String message = "Person not created!";
            throw new RuntimeException(message);
        }

    }

    public Person searchPerson(PersonForm personForm) {

        url = "http://localhost:8044/controllers/searchPeople?lastName={lastName}&firstName={firsttName}&age={age}";

        List<Person> personList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<Person>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
            }, personForm.getLastName(), personForm.getFirstName(), personForm.getAge());
            personList = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

                System.out.println("Problem!");
            }
        }

        return personList.get(0);

    }

    public Person populateObject(PersonForm personForm) {

        Person person = new Person();

        person.setId(personForm.getId());
        person.setAge(personForm.getAge());
        person.setFirstName(personForm.getFirstName());
        person.setLastName(personForm.getLastName());

        return person;

    }

    public PersonForm populateForm(Person person) {

        PersonForm personForm = new PersonForm();

        personForm.setId(person.getId());
        personForm.setAge(person.getAge());
        personForm.setFirstName(person.getFirstName());
        personForm.setLastName(person.getLastName());

        return personForm;

    }

    public List<Person> getAllPeople() {

        url = "http://localhost:8044/controllers/allPeople";

        List<Person> personList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<Person>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
            });
            personList = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

                System.out.println("Problem!");
            }
        }

        return personList;

    }
}

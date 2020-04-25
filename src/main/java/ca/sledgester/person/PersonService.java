package ca.sledgester.person;

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

        url = "http://localhost:8044/people/search/findByLastNameIgnoreCaseOrFirstNameIgnoreCase?lastName={lastName}&firstName={firsttName}";

        Person person = new Person();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Person> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Person.class, personForm.getLastName(), personForm.getFirstName());
            person = responseEntity.getBody();
        }catch(HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                person = new Person();
            }
        }

        return person;

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

    public List<Person> getAllPeopleFaked() {

        List<Person> personList = new ArrayList<>();

        Person person = new Person();
        person.setId(1L);
        person.setFirstName("Un");
        person.setLastName("Homme");
        person.setAge(40);
        personList.add(person);

        person = new Person();
        person.setId(2L);
        person.setFirstName("Une");
        person.setLastName("Femme");
        person.setAge(37);
        personList.add(person);

        person = new Person();
        person.setId(3L);
        person.setFirstName("Un");
        person.setLastName("Enfant");
        person.setAge(12);
        personList.add(person);

        person = new Person();
        person.setId(4L);
        person.setFirstName("Autre");
        person.setLastName("Enfant");
        person.setAge(10);
        personList.add(person);

        return personList;

    }
}

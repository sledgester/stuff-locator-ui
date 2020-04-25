package ca.sledgester.container;

import ca.sledgester.room.Room;
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
public class ContainerService {

    String url = "";

    public void saveContainer(Container container) {

        url = "http://localhost:8044/containers/";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Container> requestEntity = new HttpEntity<>(container);
        ResponseEntity<Container> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Container.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //model.addAttribute(container);
        } else {
            //TODO figure out how to localize this.
            String message = "Container not created!";
            throw new RuntimeException(message);
        }

    }

    public Container searchContainer(ContainerForm containerForm) {

        url = "http://localhost:8044/containers/search/findByDescriptionIgnoreCase?description={description}";

        Container container = new Container();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Container> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Container.class, containerForm.getDescription());
            container = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                container = new Container();
            }
        }

        return container;

    }

    public Container populateObject(ContainerForm containerForm) {

        Container container = new Container();
        Room room = new Room();

        room.setId(containerForm.getRoomId());

        container.setId(containerForm.getId());
        container.setDescription(containerForm.getDescription());
        container.setType(containerForm.getType());
        container.setShelfNumber(containerForm.getShelfNumber());
        container.setNumber(containerForm.getNumber());
        container.setRoom(room);

        return container;

    }

    public ContainerForm populateForm(Container container) {
        ContainerForm containerForm = new ContainerForm();

        containerForm.setId(container.getId());
        containerForm.setDescription(container.getDescription());
        containerForm.setNumber(container.getNumber());
        containerForm.setShelfNumber(container.getShelfNumber());
        containerForm.setType(container.getType());

        return containerForm;

    }

    public List<Container> getAllContainersFaked() {

        List<Container> containerList = new ArrayList<>();

        Container container = new Container();
        container.setId(1L);
        container.setDescription("Armoire à gauche de la fenêtre");
        container.setNumber(1);
        container.setShelfNumber(4);
        container.setType(1);
        containerList.add(container);

        container = new Container();
        container.setId(2L);
        container.setDescription("Armoire à droite de la fenêtre");
        container.setNumber(3);
        container.setShelfNumber(3);
        container.setType(1);
        containerList.add(container);

        container = new Container();
        container.setId(3L);
        container.setDescription("Armoire à gauche du micro-ondes");
        container.setNumber(5);
        container.setShelfNumber(3);
        container.setType(1);
        containerList.add(container);

        container = new Container();
        container.setId(4L);
        container.setDescription("Armoire au dessus du micro-ondes");
        container.setNumber(7);
        container.setShelfNumber(2);
        container.setType(1);
        containerList.add(container);

        return containerList;

    }

}

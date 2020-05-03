package ca.sledgester.codetables;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContainerTypeService {

    String url = "";

    public List<ContainerType> getAllContainerTypes() {

        url = "http://localhost:8044/controllers/allContainerTypes";

        List<ContainerType> containerTypeList = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<List<ContainerType>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ContainerType>>() {
            });
            containerTypeList = responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

                System.out.println("Problem!");
            }
        }

        return containerTypeList;

    }


}

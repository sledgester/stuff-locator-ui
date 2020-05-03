package ca.sledgester.room;

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
public class RoomService {

    String url = "";

    public void saveRoom(Room room) {

        url = "http://localhost:8044/rooms/";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Room> requestEntity = new HttpEntity<>(room);
        ResponseEntity<Room> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Room.class);

        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //map.addAttribute(room);
        } else {
            //TODO figure out how to localize this.
            String message = "Room not created!";
            throw new RuntimeException(message);
        }

    }

    public Room searchRoom (RoomForm roomForm) {

        url = "http://localhost:8044/rooms/search/findByNameIgnoreCase?name={name}";

        Room room = new Room();
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Room> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, Room.class, roomForm.getName());
            room = responseEntity.getBody();
        } catch(HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                room = new Room();
            }
        }

        return room;

    }

    public Room populateObject(RoomForm roomForm) {

        Room room = new Room();

        room.setId(roomForm.getId());
        room.setName(roomForm.getName());
        room.setPictureString(roomForm.getPictureString());

        return room;

    }

    public RoomForm populateForm(Room room) {

        RoomForm roomForm = new RoomForm();

        roomForm.setId(room.getId());
        roomForm.setName(room.getName());
        roomForm.setPictureString(room.getPictureString());

        return roomForm;

    }

    public List<Room> getAllRooms() {

        url = "http://localhost:8044/controllers/allRooms/";

        RestTemplate restTemplate = new RestTemplate();
        List<Room> roomList = new ArrayList<>();

        try {
            ResponseEntity<List<Room>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
            });
            roomList = responseEntity.getBody();
        }
        catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {

                System.out.println("Problem!");
            }
        }

        return roomList;

    }

}

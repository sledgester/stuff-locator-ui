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

    public void createRoom(Room room) {

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
        room.setName(roomForm.getName());

        return room;

    }

    public RoomForm populateForm(Room room) {

        RoomForm roomForm = new RoomForm();

        roomForm.setName(room.getName());

        return roomForm;

    }

    public List<Room> getAllRooms() {

        url = "http://localhost:8044/rooms/";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Room>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
        });
        List<Room> roomList = responseEntity.getBody();

        return roomList;

    }

    public List<Room> getAllRoomsFaked() {

        List<Room> roomList = new ArrayList<>();

        Room room = new Room();
        room.setId(1L);
        room.setName("cuisine");
        roomList.add(room);

        room = new Room();
        room.setId(2L);
        room.setName("salon");
        roomList.add(room);

        room = new Room();
        room.setId(3L);
        room.setName("salle de bain");
        roomList.add(room);

        return roomList;

    }

}
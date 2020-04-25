package ca.sledgester.room;

import com.google.common.io.ByteStreams;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
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
        room.setPlanString(roomForm.getPlanString());

        return room;

    }

    public RoomForm populateForm(Room room) {

        RoomForm roomForm = new RoomForm();

        roomForm.setId(room.getId());
        roomForm.setName(room.getName());
        roomForm.setPlanString(room.getPlanString());

        return roomForm;

    }

    public String planImageToBase64String(RoomForm roomForm) {

        byte[] filecontent = null;
        String planString = "";

        try
        {
            InputStream inputStream = roomForm.getPlanImage().getInputStream();

            if(inputStream == null) {

                System.out.println("Null inputstream");

            } else {

                filecontent = ByteStreams.toByteArray(inputStream);

            }

            planString =  Base64.encodeBase64String(filecontent);

        } catch(IOException ex) {

            System.out.println("IOException");

        }

        return planString;

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

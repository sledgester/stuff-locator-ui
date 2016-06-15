package ca.sledgester.controllers;

import ca.sledgester.objects.Room;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Sledgester on 2016-05-27.
 */
@Controller
public class RoomController {
    @RequestMapping(value="/room", method= RequestMethod.GET)
    public String room(ModelMap map) {

        Room room = new Room();
        map.addAttribute(room);

        return "room";
    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "save")
    public String roomCreatePost(ModelMap map) {
        RestTemplate restTemplate = new RestTemplate();

        Room room = new Room();

        room.setName("Cuisine");

        HttpEntity<Room> requestEntity = new HttpEntity<>(room);
        ResponseEntity<Room> responseEntity = restTemplate.exchange("http://localhost:8044/rooms/", HttpMethod.POST, requestEntity, Room.class);

        if (responseEntity.getStatusCode()== HttpStatus.CREATED) {
            map.addAttribute(room);
        } else {
            //TODO figure out how to localize this.
            String message = "Room not created!";
            throw new RuntimeException(message);
        }

        return "room";
    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "search")
    public String roomSearchPost(ModelMap map) {
        RestTemplate restTemplate = new RestTemplate();

        //TODO Make the search use the criteria from the page.
        ResponseEntity<Room> responseEntity = restTemplate.exchange("http://localhost:8044/rooms/1", HttpMethod.GET, null, Room.class);

        Room room = responseEntity.getBody();
        map.addAttribute(room);

        return "room";
    }

}

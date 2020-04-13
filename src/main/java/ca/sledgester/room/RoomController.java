package ca.sledgester.room;

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
 * Created by Sledgester on 2016-05-27.
 */
@Controller
public class RoomController {
    @RequestMapping(value="/room", method= RequestMethod.GET)
    public String room(@ModelAttribute Room room) {

        return "room";
    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "save")
    public String roomCreatePost(@ModelAttribute Room room) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Room> requestEntity = new HttpEntity<>(room);
        ResponseEntity<Room> responseEntity = restTemplate.exchange("http://localhost:8044/rooms/", HttpMethod.POST, requestEntity, Room.class);

        if (responseEntity.getStatusCode()== HttpStatus.CREATED) {
            //TODO figure out how to show success message
            //map.addAttribute(room);
        } else {
            //TODO figure out how to localize this.
            String message = "Room not created!";
            throw new RuntimeException(message);
        }

        return "room";
    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "search")
    public String roomSearchPost(@ModelAttribute Room room, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<Room> responseEntity = restTemplate.exchange("http://localhost:8044/rooms/search/findByNameIgnoreCase?name={name}", HttpMethod.GET, null, Room.class, room.getName());
            room = responseEntity.getBody();
        } catch(HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                room = new Room();
            }
        }
        model.addAttribute(room);
        return "room";
    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "back")
    public String roomBackPost() {
        return "mainMenu";
    }
}

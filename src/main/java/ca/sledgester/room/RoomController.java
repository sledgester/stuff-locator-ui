package ca.sledgester.room;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        RoomService roomService = new RoomService();

        roomService.createRoom(room);

        return "room";

    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "search")
    public String roomSearchPost(@ModelAttribute Room room, Model model) {

        RoomService roomService = new RoomService();

        room = roomService.searchRoom(room);

        model.addAttribute(room);

        return "room";

    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "back")
    public String roomBackPost() {

        return "mainMenu";

    }
}

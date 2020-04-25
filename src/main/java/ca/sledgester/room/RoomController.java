package ca.sledgester.room;

import ca.sledgester.utils.Utils;
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
    public String room(@ModelAttribute RoomForm roomForm) {

        return "room";

    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "save")
    public String roomSavePost(@ModelAttribute RoomForm roomForm) {

        Room room = new Room();
        RoomService roomService = new RoomService();
        Utils utils = new Utils();

        room = roomService.populateObject(roomForm);

        if (!roomForm.getPictureImage().getOriginalFilename().equals("")) {
            room.setPictureString(utils.imageToBase64String(roomForm.getPictureImage()));
        }

        roomService.saveRoom(room);


        return "room";

    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "search")
    public String roomSearchPost(@ModelAttribute RoomForm roomForm, Model model) {

        Room room = new Room();
        RoomService roomService = new RoomService();

        room = roomService.searchRoom(roomForm);

        roomForm = roomService.populateForm(room);

        model.addAttribute(roomForm);

        return "room";

    }

    @RequestMapping(value="/room", method= RequestMethod.POST, params = "back")
    public String roomBackPost() {

        return "mainMenu";

    }

}

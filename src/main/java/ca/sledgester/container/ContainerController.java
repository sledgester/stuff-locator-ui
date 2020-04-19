package ca.sledgester.container;

import ca.sledgester.room.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Controller
public class ContainerController {

    @RequestMapping(value="/container", method= RequestMethod.GET)
    public String container(@ModelAttribute Container container) {

        RoomService roomService = new RoomService();

        //List<Room> roomList = roomService.getAllRooms();

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "save")
    public String containerCreatePost(@ModelAttribute Container container) {

        ContainerService containerService = new ContainerService();

        containerService.createContainer(container);

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "search")
    public String containerSearchPost(@ModelAttribute Container container, Model model) {

        ContainerService containerService = new ContainerService();

        container = containerService.searchContainer(container);

        model.addAttribute(container);

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "back")
    public String containerBackPost() {

        return "mainMenu";

    }
}
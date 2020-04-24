package ca.sledgester.container;

import ca.sledgester.room.Room;
import ca.sledgester.room.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Controller
public class ContainerController {

    @RequestMapping(value="/container", method= RequestMethod.GET)
    public String container(@ModelAttribute ContainerForm containerForm, Model model) {

        model.addAttribute(loadCodeTables());

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "save")
    public String containerCreatePost(@ModelAttribute ContainerForm containerForm, Model model) {

        Container container = new Container();
        ContainerService containerService = new ContainerService();

        model.addAttribute(loadCodeTables());

        container = containerService.populateObject(containerForm);

        containerService.createContainer(container);

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "search")
    public String containerSearchPost(@ModelAttribute ContainerForm containerForm, Model model) {
        List<Room> roomList = new ArrayList<>();
        Container container = new Container();
        ContainerService containerService = new ContainerService();

        model.addAttribute(loadCodeTables());

        container = containerService.searchContainer(containerForm);

        containerForm = containerService.populateForm(container);

        model.addAttribute(containerForm);

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "back")
    public String containerBackPost() {

        return "mainMenu";

    }

    private List<Room> loadCodeTables() {
        RoomService roomService = new RoomService();

        List<Room> roomList = roomService.getAllRoomsFaked();

        return roomList;

    }

}
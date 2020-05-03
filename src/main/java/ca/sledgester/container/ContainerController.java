package ca.sledgester.container;

import ca.sledgester.codetables.ContainerType;
import ca.sledgester.codetables.ContainerTypeService;
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

        model.addAttribute(loadContainerTypes());
        model.addAttribute(loadRooms());

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "save")
    public String containerSavePost(@ModelAttribute ContainerForm containerForm, Model model) {

        Container container = new Container();
        ContainerService containerService = new ContainerService();

        model.addAttribute(loadRooms());

        container = containerService.populateObject(containerForm);

        containerService.saveContainer(container);

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "search")
    public String containerSearchPost(@ModelAttribute ContainerForm containerForm, Model model) {
        List<Room> roomList = new ArrayList<>();
        Container container = new Container();
        ContainerService containerService = new ContainerService();

        model.addAttribute(loadRooms());

        container = containerService.searchContainer(containerForm);

        containerForm = containerService.populateForm(container);

        model.addAttribute(containerForm);

        return "container";

    }

    @RequestMapping(value="/container", method= RequestMethod.POST, params = "back")
    public String containerBackPost() {

        return "mainMenu";

    }

    private List<Room> loadRooms() {
        RoomService roomService = new RoomService();

        List<Room> roomList = roomService.getAllRooms();

        return roomList;

    }

    private List<ContainerType> loadContainerTypes() {
        ContainerTypeService containerTypeService = new ContainerTypeService();

        List<ContainerType> containerTypeList = containerTypeService.getAllContainerTypes();

        return containerTypeList;

    }

}
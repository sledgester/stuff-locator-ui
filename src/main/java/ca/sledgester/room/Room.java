package ca.sledgester.room;

import ca.sledgester.container.Container;
import lombok.Data;

import java.util.List;

/**
 * Created by Sledgester on 2016-05-27.
 */
@Data
public class Room {

    private String name;
    private List<Container> containers;

}

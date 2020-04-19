package ca.sledgester.container;

import ca.sledgester.room.Room;
import lombok.Data;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Data
public class Container {

    private Long id;
    private int number;
    private String description;
    private int type;
    private int shelfNumber;
    private Room room;

}

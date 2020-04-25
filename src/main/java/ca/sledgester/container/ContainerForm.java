package ca.sledgester.container;

import lombok.Data;

@Data
public class ContainerForm {

    private Long id;
    private int number;
    private String description;
    private int type;
    private int shelfNumber;
    private Long roomId;

}

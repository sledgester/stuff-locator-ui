package ca.sledgester.room;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RoomForm {

    private Long id;
    private String name;
    private MultipartFile planImage;
    private String planString;
}

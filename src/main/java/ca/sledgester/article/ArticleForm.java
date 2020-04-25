package ca.sledgester.article;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class ArticleForm {

    private Long Id;
    private String name;
    private BigDecimal value;
    private MultipartFile pictureImage;
    private String pictureString;
    private Long containerId;
    private Long personId;

}

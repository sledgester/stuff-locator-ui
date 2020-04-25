package ca.sledgester.article;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleForm {

    private Long Id;
    private String name;
    private BigDecimal value;
    private Long containerId;
    private Long personId;

}

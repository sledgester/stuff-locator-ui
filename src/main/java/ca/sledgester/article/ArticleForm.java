package ca.sledgester.article;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleForm {

    private String name;
    private BigDecimal value;

}

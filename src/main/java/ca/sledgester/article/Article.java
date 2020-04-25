package ca.sledgester.article;

import ca.sledgester.container.Container;
import ca.sledgester.person.Person;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Data
public class Article {

    private Long id;
    private String name;
    private BigDecimal value;
    private String pictureString;
    private Person person;
    private Container container;

}

package ca.sledgester.person;

import ca.sledgester.article.Article;
import lombok.Data;

import java.util.List;

/**
 * Created by Sledgester on 2016-06-06.
 */
@Data
public class Person {

    private String lastName;
    private String firstName;
    private int age;
    private List<Article> articles;

}

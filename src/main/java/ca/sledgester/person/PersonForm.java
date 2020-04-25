package ca.sledgester.person;

import lombok.Data;

@Data
public class PersonForm {

    private Long id;
    private String lastName;
    private String firstName;
    private int age;

}

package ca.sledgester.objects;

import java.math.BigDecimal;

/**
 * Created by Sledgester on 2016-06-06.
 */
public class Article {

    private String name;
    private BigDecimal value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}

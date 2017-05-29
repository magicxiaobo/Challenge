package challenge.domain;

import lombok.Data;
import lombok.Generated;

/**
 * Created by xiaoboyu on 5/18/17.
 */


@Data
public class Person {
    @Generated
    private int id;
    String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

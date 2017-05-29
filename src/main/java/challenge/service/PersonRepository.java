package challenge.service;

import challenge.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoboyu on 5/21/17.
 */
@Repository
public interface PersonRepository {

    public List<Person> getAllPersons();

    public Person getPerson(int id);




    // add a  new person
    public int addNewPerson(String name);

}

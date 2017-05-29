package challenge.service.Impl;

import challenge.domain.Person;
import challenge.service.PersonRepository;
import challenge.support.PersonRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiaoboyu on 5/21/17.
 */
@Service
public class PersonRepositoryImpl implements PersonRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public List<Person> getAllPersons() {
        List<Person> result = this.jdbcTemplate.query(GET_ALL_PERSONS, new PersonRowMapper());

        return result;
    }


    @Override
    public Person getPerson(int id) {
        SqlParameterSource nameParameters = new MapSqlParameterSource("id", Integer.valueOf(id));
        Person person= (Person) this.namedParameterJdbcTemplate.queryForObject(GET_PERSON, nameParameters, new PersonRowMapper());
        return person;
    }


    @Override
    public int addNewPerson(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", String.valueOf(name));
        namedParameterJdbcTemplate.update(NEW_PERSON, sqlParameterSource, keyHolder);
        return keyHolder.getKey().intValue();
    }


    private final String GET_ALL_PERSONS = "SELECT ID, NAME FROM PERSON";
    private final String GET_PERSON = "SELECT * FROM PERSON WHERE id = :id";
    private final String NEW_PERSON = "INSERT INTO person (name) VALUES (:name)";


}

package challenge.service.Impl;

import challenge.domain.Follower;
import challenge.domain.Person;
import challenge.service.FollowerRepository;
import challenge.service.PersonRepository;
import challenge.support.FollowerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoboyu on 5/21/17.
 */
@Service
public class FollowerRepositoryImpl implements FollowerRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Get all persons the follower_person_id is following
     * @param follower_person_id
     * @return
     */
    @Override
    public List<Person> getAllFollowing(int follower_person_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("follower_person_id", follower_person_id);
        List<Follower> followings = this.namedParameterJdbcTemplate.query(GET_ALL_FOLLOWINGS, sqlParameterSource, new FollowerRowMapper());

        List<Person> result = new ArrayList<>();
        for (Follower f: followings) {
            result.add(personRepository.getPerson(f.getPerson_id()));
        }
        return result;
    }

    /**
     * Get all persons who are following the person_id
     * @param person_id
     * @return
     */
    @Override
    public List<Person> getAllFollowers(int person_id) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("person_id", person_id);
        List<Follower> followers = this.namedParameterJdbcTemplate.query(GET_ALL_FOLLOWERS, sqlParameterSource, new FollowerRowMapper());

        List<Person> result = new ArrayList<>();
        for (Follower f: followers) {
            result.add(personRepository.getPerson(f.getFollower_person_id()));
        }
        return result;
    }

    /**
     * follower_person_id starts follow person_id
     * @param person_id
     * @param follower_person_id
     * @return
     */
    @Override
    public boolean follow(int follower_person_id, int person_id) {

        try {
            // if found already followed, return false
            if (findFollowerEntry(person_id, follower_person_id) != null)
                return false;
        } catch (EmptyResultDataAccessException e) {
            //e.printStackTrace();
        }

        Map<String, Integer> namedParameters = new HashMap<>();
        namedParameters.put("person_id", person_id);
        namedParameters.put("follower_person_id", follower_person_id);
        namedParameterJdbcTemplate.update(FOLLOW, namedParameters);
        return true;
    }

    /**
     * follower unfollow the user
     *
     * @param follower_person_id
     * @param person_id
     * @return
     */
    @Override
    public boolean unfollow(int follower_person_id, int person_id) {        // follower_person_id unfollow person_id

        Map<String, Integer> namedParameters = new HashMap<>();
        namedParameters.put("follower_person_id", follower_person_id);
        namedParameters.put("person_id", person_id);
        int res = namedParameterJdbcTemplate.update(UNFOLLOW, namedParameters);
        return res == 1;
    }

    /**
     * Search the follower table to find the the row with given person_id and follower_person_id
     * @param person_id
     * @param follower_person_id
     * @return  the Follower object if found, otherwise throw Exception
     */
    @Override
    public Follower findFollowerEntry(int person_id, int follower_person_id) {
        Map<String, Integer> namedParameters = new HashMap<>();
        namedParameters.put("person_id", person_id);
        namedParameters.put("follower_person_id", person_id);
        Follower followerEntry = (Follower) namedParameterJdbcTemplate.queryForObject(FIND_FOLLOW_ENTRY, namedParameters, new FollowerRowMapper());
        return followerEntry;
    }

    /**
     * Find all the people and the pairs of everyone with his/her most popular follower
     * @return
     */
    @Override
    public Map<Person, Person> showAllPopularPairs() {

        Map<Integer, Integer> popularDegree = new HashMap<>();      // popularDegree: key: person_id, value: count being followed
        List<Person> allPersons = personRepository.getAllPersons();
        for (Person person : allPersons) {
            popularDegree.put(person.getId(), getAllFollowers(person.getId()).size());
        }
        Map<Person, Person> result = new HashMap<>();
        for (Person person : allPersons) {
            int amount = 0;
            Person mostPopularPerson = null;
            for (Person follower : getAllFollowers(person.getId())) {
                if (amount < popularDegree.get(follower.getId())) {
                    mostPopularPerson = follower;
                    amount = popularDegree.get(follower.getId());
                }
            }
            result.put(person, mostPopularPerson);
        }
        return result;
    }


    private final String FOLLOW = "INSERT INTO followers (follower_person_id, person_id) VALUES (:follower_person_id, :person_id)";
    private final String UNFOLLOW = "DELETE FROM followers WHERE person_id = :person_id AND follower_person_id = :follower_person_id";
    private final String FIND_FOLLOW_ENTRY = "SELECT * FROM followers WHERE person_id = :person_id AND follower_person_id = :follower_person_id";
    private final String GET_ALL_FOLLOWERS = "SELECT * FROM followers WHERE person_id = :person_id";
    private final String GET_ALL_FOLLOWINGS = "SELECT * FROM followers WHERE follower_person_id = :follower_person_id";

}

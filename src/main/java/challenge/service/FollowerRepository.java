package challenge.service;

import challenge.domain.Follower;
import challenge.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoboyu on 5/21/17.
 */
@Repository
public interface FollowerRepository {

    // get all followers
    public List<Person> getAllFollowers(int person_id);

    // get all Persons he is following
    public List<Person> getAllFollowing(int follower_person_id);

    public boolean follow(int user1, int user2);    // user1 follow user2

    public boolean unfollow(int follower_person_id, int person_id);  // user1 unfollow user2

    public Follower findFollowerEntry(int person_id, int follower_person_id);


    public Map<Person, Person> showAllPopularPairs();
}

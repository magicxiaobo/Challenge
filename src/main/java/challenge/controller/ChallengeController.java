package challenge.controller;


import challenge.domain.Person;
import challenge.domain.Tweet;
import challenge.service.FollowerRepository;
import challenge.service.PersonRepository;
import challenge.service.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoboyu on 5/16/17.
 */
@RestController
public class ChallengeController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private FollowerRepository followerRepository;



    @RequestMapping(value = "/challenge/getAllTweets", method = RequestMethod.GET)
    public List<Tweet> getAllTweets() {
        return this.tweetRepository.getAllTweets();
    }


    /**
     * Get news feed of the user
     * @param person_id
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/challenge/news", method = RequestMethod.GET)
    public List<Tweet> getNewsFeed(@RequestParam int person_id, @RequestParam(value="keyword", required = false) String keyword) {
        return this.tweetRepository.findAllNews(person_id, keyword);
    }


    /**
     *  Add new person to person table; Debugging purpose only
     * @param name
     * @return
     */
    @RequestMapping(value = "/challenge/new_person", method = RequestMethod.GET)
    public int addNewPerson(@RequestParam String name) {

        return this.personRepository.addNewPerson(name);
    }


    /**
     * User starts follow somebody
     * @param person_id
     * @param follower_person_id
     * @return
     */
    @RequestMapping(value = "/challenge/follow", method = RequestMethod.GET)
    public boolean follow(@RequestParam int person_id, @RequestParam int follower_person_id) {
        return this.followerRepository.follow(follower_person_id, person_id);
    }

    /**
     * User start unfollow somebody
     * @param follower_person_id
     * @param person_id
     * @return
     */
    @RequestMapping(value="/challenge/unfollow", method = RequestMethod.GET)
    public boolean unfollow(@RequestParam int follower_person_id, @RequestParam int person_id) {
        return this.followerRepository.unfollow(follower_person_id, person_id);
    }

    /**
     * Get all the followers of the user
     * @param person_id
     * @return
     */
    @RequestMapping(value = "/challenge/getAllFollowers", method = RequestMethod.GET)
    public List<Person> getAllFollowers(@RequestParam int person_id) {
        return this.followerRepository.getAllFollowers(person_id);
    }

    /**
     * Get all the persons the use is following
     * @param follower_person_id
     * @return
     */
    @RequestMapping(value = "/challenge/getAllFollowing", method = RequestMethod.GET)
    public List<Person> getAllFollowings(@RequestParam int follower_person_id) {
        return this.followerRepository.getAllFollowing(follower_person_id);
    }


    /**
     *  Show all pairs [the user, his/her most popular follower]
     * @return
     */
    @RequestMapping(value = "/challenge/showAllPopularPairs", method = RequestMethod.GET)
    public Map<Person, Person> getCount() {
        return this.followerRepository.showAllPopularPairs();
    }

}

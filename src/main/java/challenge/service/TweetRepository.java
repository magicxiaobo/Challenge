package challenge.service;

import challenge.domain.Tweet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiaoboyu on 5/21/17.
 */
@Repository
public interface TweetRepository {

    public List<Tweet> getAllTweets();

    public List<Tweet> findNewsFromUser(int person_id, String keyword); // find all the user's news

    public List<Tweet> findAllNews(int person_id, String keyword);      // find all news including the user's and the user's followings
}

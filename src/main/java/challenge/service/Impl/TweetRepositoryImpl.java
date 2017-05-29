package challenge.service.Impl;

import challenge.domain.Person;
import challenge.domain.Tweet;
import challenge.service.FollowerRepository;
import challenge.service.TweetRepository;
import challenge.support.TweetRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaoboyu on 5/21/17.
 */
@Service
public class TweetRepositoryImpl implements TweetRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private FollowerRepository followerRepository;



    @Override
    public List<Tweet> getAllTweets() {
        List<Tweet> result = this.jdbcTemplate.query(GET_ALL_TWEETS, new TweetRowMapper());
        return result;
    }

    @Override
    public List<Tweet> findNewsFromUser(int person_id, String keyword) {
        //1. Get all the tweets from the user
        //SqlParameterSource sqlParameterSource = new MapSqlParameterSource("person_id", Integer.valueOf(person_id));
        Map<String, String> namedParamters = new HashMap<>();
        namedParamters.put("person_id", String.valueOf(person_id));
        if (keyword == null || keyword.equals("")) {
            namedParamters.put("keyword", ".*");
        } else {
            namedParamters.put("keyword", ".*" + keyword + ".*");
        }
        List<Tweet> result = namedParameterJdbcTemplate.query(NEWS_FEED, namedParamters, new TweetRowMapper());
        return result;
    }

    @Override
    public List<Tweet> findAllNews(int person_id, String keyword) {

        List<Tweet> result = new ArrayList<>();
        result.addAll(findNewsFromUser(person_id, keyword));         // 1. add all the user's tweets
                                                            // 2. add all the persons' (the user is following) tweets
        List<Person> followings = followerRepository.getAllFollowing(person_id);
        for (Person p: followings) {
            result.addAll(findNewsFromUser(p.getId(), keyword));
        }
        return result;
    }

    private final String GET_ALL_TWEETS = "SELECT * FROM tweet";
    private final String NEWS_FEED = "SELECT * FROM tweet WHERE person_id = :person_id AND content REGEXP :keyword";
}

package challenge.support;

import challenge.domain.Tweet;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by xiaoboyu on 5/21/17.
 */

public class TweetRowMapper implements RowMapper {


    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tweet tweet = new Tweet();
        tweet.setPersonId(rs.getInt("person_id"));
        tweet.setContent(rs.getString("content"));
        return tweet;
    }
}

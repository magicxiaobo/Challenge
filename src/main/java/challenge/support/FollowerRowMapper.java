package challenge.support;

import challenge.domain.Follower;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FollowerRowMapper implements RowMapper{

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Follower follower = new Follower();
        follower.setId(rs.getInt("id"));
        follower.setPerson_id(rs.getInt("person_id"));
        follower.setFollower_person_id(rs.getInt("follower_person_id"));
        return follower;
    }
}

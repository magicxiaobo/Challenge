package challenge.domain;

/**
 * Created by xiaoboyu on 5/21/17.
 */
public class Follower {

    int id;
    int person_id;
    int follower_person_id;

    public Follower(int id, int person_id, int follower_person_id) {
        this.id = id;
        this.person_id = person_id;
        this.follower_person_id = follower_person_id;
    }

    public Follower() {
    }

    public int getId() {
        return id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public int getFollower_person_id() {
        return follower_person_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public void setFollower_person_id(int follower_person_id) {
        this.follower_person_id = follower_person_id;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "id=" + id +
                ", person_id=" + person_id +
                ", follower_person_id=" + follower_person_id +
                '}';
    }
}

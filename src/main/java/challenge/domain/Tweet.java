package challenge.domain;

/**
 * Created by xiaoboyu on 5/18/17.
 */
public class Tweet {
    int id;
    int personId;
    String content;

    public Tweet(){}

    public Tweet(int personId, String content) {
        this.personId = personId;
        this.content = content;
    }

    public int getPersonId() {
        return personId;
    }

    public String getContent() {
        return content;
    }

    public void setPersonId(int personId) {

        this.personId = personId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", personId=" + personId +
                ", content='" + content + '\'' +
                '}';
    }
}

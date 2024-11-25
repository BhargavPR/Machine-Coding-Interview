package model;

public class Follower {

    private String id;
    private String userId;
    private String followerId;

    public Follower(String id, String userId, String followerId) {
        this.id = id;
        this.userId = userId;
        this.followerId = followerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }
}

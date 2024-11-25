package database;

import model.Tweet;
import model.User;

import java.util.*;

public class Database {

    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database() {
    }

    private HashMap<String, User> userHashMap = new HashMap<>();
    private HashMap<String, List<Tweet>> userTweetsMap = new HashMap<>();
    private HashMap<String, Set<String>> followerMap = new HashMap<>();
    private HashMap<String, Set<String>> followeeMap = new HashMap<>();

    public void addUser(User user) {
        userHashMap.put(user.getId(), user);
    }

    public void addTweet(Tweet tweet) {
        if (!userTweetsMap.containsKey(tweet.getUserId())) {
            userTweetsMap.put(tweet.getUserId(), new ArrayList<>());
        }
        userTweetsMap.get(tweet.getUserId()).add(tweet);
    }

    public List<Tweet> getTweetsByUserId(String userId) {
        if (!userTweetsMap.containsKey(userId)) {
            return new ArrayList<>();
        }
        return userTweetsMap.get(userId);
    }

    public void addFollower(String userId, String followerId) {
        if (!followerMap.containsKey(userId)) {
            followerMap.put(userId, new HashSet<>());
        }
        followerMap.get(userId).add(followerId);

        if (!followeeMap.containsKey(followerId)) {
            followeeMap.put(followerId, new HashSet<>());
        }
        followeeMap.get(followerId).add(userId);
    }

    public void deleteFollower(String userId, String followerId) {
        if (followerMap.containsKey(userId)) {
            Set<String> followers = followerMap.get(userId);
            followers.remove(followerId);
        }


        if (followeeMap.containsKey(followerId)) {
            followeeMap.get(followerId).remove(userId);
        }
    }

    public Set<String> getFolloweeIds(String userId) {
        if (!followeeMap.containsKey(userId)) {
            return new HashSet<>();
        }
        return followeeMap.get(userId);
    }


}

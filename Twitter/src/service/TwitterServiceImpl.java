package service;

import database.Database;
import model.Tweet;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TwitterServiceImpl implements TwitterService {

    private final Database database;

    private Long counter = 0L;

    public TwitterServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public User crateUser(String name, String email) {
        User user = new User(UUID.randomUUID().toString(), name, email);
        database.addUser(user);
        return user;
    }

    @Override
    public Tweet postTweet(String userId, String message) {
        Tweet tweet = new Tweet(UUID.randomUUID().toString(), userId, message, counter);
        database.addTweet(tweet);
        counter++;
        return tweet;
    }

    @Override
    public List<Tweet> getNewsFeed(String userId) {
        Set<String> followeeIds = database.getFolloweeIds(userId);
        List<Tweet> tweets = new ArrayList<>(database.getTweetsByUserId(userId));
        for (String followeeId : followeeIds) {
            tweets.addAll(database.getTweetsByUserId(followeeId));
        }
        tweets.sort((tweet1, tweet2) -> Long.compare(tweet2.getCreatedAt(), tweet1.getCreatedAt()));
        return tweets;
    }

    @Override
    public void follow(String userId, String followerId) {
        database.addFollower(userId, followerId);
    }

    @Override
    public void unfollow(String userId, String followerId) {
        database.deleteFollower(userId, followerId);
    }
}

package service;

import model.Tweet;
import model.User;

import java.util.List;

public interface TwitterService {

    User crateUser(String name, String email);

    Tweet postTweet(String userId, String message);

    List<Tweet> getNewsFeed(String userId);

    void follow(String userId, String followerId);

    void unfollow(String userId, String followerId);
}

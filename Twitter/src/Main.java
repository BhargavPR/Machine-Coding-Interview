import database.Database;
import model.Tweet;
import model.User;
import service.TwitterService;
import service.TwitterServiceImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Database database = Database.getInstance();
        TwitterService twitterService = new TwitterServiceImpl(database);

        User userA = twitterService.crateUser("userA", "userA");
        User userB = twitterService.crateUser("userB", "userB");
        User userC = twitterService.crateUser("userC", "userC");

        twitterService.postTweet(userA.getId(), "userA post 1");
        twitterService.postTweet(userA.getId(), "userA post 2");
        twitterService.postTweet(userA.getId(), "userA post 3");

        twitterService.postTweet(userB.getId(), "userB post 1");
        twitterService.postTweet(userB.getId(), "userB post 2");

        twitterService.postTweet(userC.getId(), "userC post 1");
        twitterService.postTweet(userC.getId(), "userC post 2");
        twitterService.postTweet(userC.getId(), "userC post 3");
        twitterService.postTweet(userC.getId(), "userC post 4");

        twitterService.follow(userA.getId(), userB.getId());
        twitterService.follow(userC.getId(), userB.getId());
        twitterService.unfollow(userC.getId(), userB.getId());

        printTweets(twitterService.getNewsFeed(userA.getId()));

        printTweets(twitterService.getNewsFeed(userB.getId()));

    }

    private static void printTweets(List<Tweet> tweetList) {
        for (Tweet tweet : tweetList) {
            System.out.println(tweet.getUserId() + " and " + tweet.getMessage() + " time " + tweet.getCreatedAt());
        }
        System.out.println("end");
    }
}
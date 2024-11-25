package database;

import model.movie.Movie;
import model.review.Review;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Database {

    private final HashMap<String, Movie> movieMap = new HashMap<>();
    private final HashMap<String, User> userMap = new HashMap<>();
    private final HashMap<String, Review> reviewMap = new HashMap<>();
    private final HashMap<String, List<Review>> userReviewMap = new HashMap<>();
    private final HashMap<String, List<Review>> movieReviewMap = new HashMap<>();

    private static Database INSTANCE = null;

    public static Database getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    private Database() {
    }

    public void addMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
    }

    public Movie getMovie(String name) {
        return movieMap.get(name);
    }

    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>(movieMap.values());
        return !movies.isEmpty() ? List.copyOf(movies) : new ArrayList<>();
    }

    public void addUser(User user) {
        userMap.put(user.getName(), user);
    }

    public User getUser(String name) {
        return userMap.get(name);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>(userMap.values());
        return !users.isEmpty() ? List.copyOf(users) : new ArrayList<>();
    }

    public void addReview(Review review) {
        reviewMap.put(getReviewKey(review.getUserName(), review.getMovieName()), review);
        if (!userReviewMap.containsKey(review.getUserName())) {
            userReviewMap.put(review.getUserName(), new ArrayList<>());
        }
        userReviewMap.get(review.getUserName()).add(review);


        if (!movieReviewMap.containsKey(review.getMovieName())) {
            movieReviewMap.put(review.getMovieName(), new ArrayList<>());
        }
        movieReviewMap.get(review.getMovieName()).add(review);
    }

    public Review getReview(String userName, String movieName) {
        return reviewMap.get(getReviewKey(userName, movieName));
    }

    public List<Review> getReviews(String userName) {
        List<Review> reviews = userReviewMap.get(userName);
        return Objects.nonNull(reviews) && !reviews.isEmpty() ? reviews : new ArrayList<>();
    }

    public List<Review> getReviewsByMovie(String movieName) {
        List<Review> reviews = movieReviewMap.get(movieName);
        return Objects.nonNull(reviews) && !reviews.isEmpty() ? reviews : new ArrayList<>();
    }

    private String getReviewKey(String userName, String movieName) {
        return userName + "_" + movieName;
    }

}

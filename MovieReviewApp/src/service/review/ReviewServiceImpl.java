package service.review;

import comparator.MovieReviewComparator;
import constants.Constants;
import database.Database;
import model.movie.Movie;
import model.review.Review;
import model.review.ReviewStatus;
import model.user.User;
import model.user.UserType;

import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class ReviewServiceImpl implements ReviewService {

    private final Database database;

    public ReviewServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void addReview(String userName, String movieName, Integer rating) {
        if (isReviewExists(userName, movieName)) {
            throw new RuntimeException("Multiple reviews not allowed");
        }
        if (rating < Constants.MIN_RATING || rating > Constants.MAX_RATING) {
            throw new RuntimeException("Invalid rating");
        }
        User user = database.getUser(userName);
        if (Objects.isNull(user)) {
            throw new RuntimeException("User not found");
        }
        Movie movie = database.getMovie(movieName);
        if (Objects.isNull(movie)) {
            throw new RuntimeException("Movie not found");
        }

        Review review = new Review(movieName, userName, rating, ReviewStatus.ACTIVE);
        database.addReview(review);

        List<Review> userReviews = database.getReviews(userName);
        long activeReviewCount = userReviews.stream()
                .filter(userReview -> userReview.getStatus().equals(ReviewStatus.ACTIVE))
                .count();
        if (activeReviewCount >= Constants.CRITIC_THRESHOLD_COUNT) {
            user.setType(UserType.CRITIC);
        }

    }

    @Override
    public void updateReview(String userName, String movieName, Integer rating) {
        if (!isReviewExists(userName, movieName)) {
            throw new RuntimeException("Review not found");
        }
        if (rating < Constants.MIN_RATING || rating > Constants.MAX_RATING) {
            throw new RuntimeException("Invalid rating");
        }
        User user = database.getUser(userName);
        if (Objects.isNull(user)) {
            throw new RuntimeException("User not found");
        }
        Movie movie = database.getMovie(movieName);
        if (Objects.isNull(movie)) {
            throw new RuntimeException("Movie not found");
        }

        Review review = database.getReview(userName, movieName);
        review.setRating(rating);
    }

    @Override
    public void deleteReview(String userName, String movieName) {
        if (!isReviewExists(userName, movieName)) {
            throw new RuntimeException("Review not found");
        }
        User user = database.getUser(userName);
        if (Objects.isNull(user)) {
            throw new RuntimeException("User not found");
        }
        Movie movie = database.getMovie(movieName);
        if (Objects.isNull(movie)) {
            throw new RuntimeException("Movie not found");
        }
        Review review = database.getReview(userName, movieName);
        review.setStatus(ReviewStatus.INACTIVE);

        List<Review> userReviews = database.getReviews(userName);
        long activeReviewCount = userReviews.stream()
                .filter(userReview -> userReview.getStatus().equals(ReviewStatus.ACTIVE))
                .count();
        if (activeReviewCount < Constants.CRITIC_THRESHOLD_COUNT) {
            user.setType(UserType.REGULAR);
        }
    }

    @Override
    public void displayUserViews(String userName) {
        User user = database.getUser(userName);
        if (Objects.isNull(user)) {
            throw new RuntimeException("User not found");
        }

        System.out.println("Reviews of " + userName);
        List<Review> reviews = database.getReviews(userName);
        if (reviews.isEmpty()) {
            System.out.println("No review found");
            return;
        }

        for (Review review : reviews) {
            System.out.println(review.getMovieName() + " " + review.getRating());
        }

    }

    @Override
    public void displayTopMovieByReleaseYear(Integer count, Integer year) {
        List<Movie> movies = database.getMovies().stream()
                .filter(movie -> movie.getReleaseYear().equals(year))
                .toList();
        if (movies.isEmpty()) {
            System.out.println("No movies release in year " + year);
            return;
        }

        displayTopMovies(count, movies);
    }

    @Override
    public void displayTopMovieByGenre(Integer count, String genre) {
        List<Movie> movies = database.getMovies().stream()
                .filter(movie -> !movie.getGenres().isEmpty() &&
                        movie.getGenres().contains(genre))
                .toList();
        if (movies.isEmpty()) {
            System.out.println("No movies found for genre " + genre);
            return;
        }

        displayTopMovies(count, movies);
    }

    private void displayTopMovies(int count, List<Movie> movies) {
        for (Movie movie : movies) {
            List<Review> reviews = database.getReviewsByMovie(movie.getName());
            int rating = 0;
            for (Review review : reviews) {
                if (review.getStatus().equals(ReviewStatus.ACTIVE)) {
                    User user = database.getUser(review.getUserName());
                    rating = rating + ((user.getType().equals(UserType.CRITIC)) ? 2 * review.getRating() : review.getRating());
                }
            }
            movie.setRating(rating);
        }

        PriorityQueue<Movie> moviesQueue = new PriorityQueue<>(new MovieReviewComparator());
        moviesQueue.addAll(movies);

        int index = 0;
        while (!moviesQueue.isEmpty() && index < count) {
            Movie movie = moviesQueue.peek();
            moviesQueue.remove();
            System.out.println(movie.getName() + " " + movie.getRating());
            index++;
        }
    }

    private boolean isReviewExists(String userName, String movieName) {
        return Objects.nonNull(database.getReview(userName, movieName));
    }


}

import constants.Constants;
import database.Database;
import service.movie.MovieService;
import service.movie.MovieServiceImpl;
import service.review.ReviewService;
import service.review.ReviewServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));

        Database database = Database.getInstance();
        UserService userService = new UserServiceImpl(database);
        MovieService movieService = new MovieServiceImpl(database);
        ReviewService reviewService = new ReviewServiceImpl(database);

        while (scanner.hasNextLine()) {
            String[] input = scanner.nextLine().split(" ");
            switch (input[0]) {
                case Constants.ADD_MOVIE -> {
                    List<String> genres = Arrays.asList(input[3].split(","));
                    movieService.addMovie(input[1], Integer.valueOf(input[2]), genres);
                }
                case Constants.ADD_USER -> {
                    userService.addUser(input[1]);
                }
                case Constants.ADD_REVIEW -> {
                    try {
                        reviewService.addReview(input[1], input[2], Integer.valueOf(input[3]));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case Constants.UPDATE_REVIEW -> {
                    try {
                        reviewService.updateReview(input[1], input[2], Integer.valueOf(input[3]));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case Constants.DELETE_REVIEW -> {
                    try {
                        reviewService.deleteReview(input[1], input[2]);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case Constants.DISPLAY_USER_REVIEW -> {
                    try {
                        reviewService.displayUserViews(input[1]);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case Constants.DISPLAY_TOP_MOVIES_BY_YEAR -> {
                    try {
                        reviewService.displayTopMovieByReleaseYear(Integer.valueOf(input[1]), Integer.valueOf(input[2]));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case Constants.DISPLAY_TOP_MOVIES_BY_GENRE -> {
                    try {
                        reviewService.displayTopMovieByGenre(Integer.valueOf(input[1]), input[2]);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}
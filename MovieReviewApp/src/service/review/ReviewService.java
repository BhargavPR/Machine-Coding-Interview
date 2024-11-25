package service.review;

public interface ReviewService {

    void addReview(String userName, String movieName, Integer rating);

    void updateReview(String userName, String movieName, Integer rating);

    void deleteReview(String userName, String movieName);

    void displayUserViews(String userName);

    void displayTopMovieByReleaseYear(Integer count, Integer year);

    void displayTopMovieByGenre(Integer count, String genre);

}

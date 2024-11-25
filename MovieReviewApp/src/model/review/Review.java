package model.review;

public class Review {

    private String movieName;
    private String userName;
    private Integer rating;
    private ReviewStatus status;

    public Review(String movieName, String userName, Integer rating, ReviewStatus status) {
        this.movieName = movieName;
        this.userName = userName;
        this.rating = rating;
        this.status = status;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ReviewStatus getStatus() {
        return status;
    }

    public void setStatus(ReviewStatus status) {
        this.status = status;
    }
}

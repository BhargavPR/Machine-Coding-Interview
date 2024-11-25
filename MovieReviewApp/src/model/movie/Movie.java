package model.movie;

import java.util.List;

public class Movie {

    private String name;
    private Integer releaseYear;
    private List<String> genres;
    private Integer rating;

    public Movie(String name, Integer releaseYear, List<String> genres) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}


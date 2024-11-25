package comparator;

import model.movie.Movie;

import java.util.Comparator;

public class MovieReviewComparator implements Comparator<Movie> {

    @Override
    public int compare(Movie o1, Movie o2) {
        return Integer.compare(o2.getRating(), o1.getRating());
    }

}

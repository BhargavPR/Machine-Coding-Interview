package service.movie;

import database.Database;
import model.movie.Movie;

import java.util.List;

public class MovieServiceImpl implements MovieService {

    private final Database database;

    public MovieServiceImpl(Database database) {
        this.database = database;
    }

    @Override
    public void addMovie(String name, Integer releaseYear, List<String> genres) {
        Movie movie = new Movie(name, releaseYear, genres);
        database.addMovie(movie);
    }

}

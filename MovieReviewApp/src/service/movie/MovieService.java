package service.movie;

import java.util.List;

public interface MovieService {

    void addMovie(String name, Integer releaseYear, List<String> genres);

}

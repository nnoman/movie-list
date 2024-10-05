package test;

import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.MovieService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {

    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        movieService = new MovieService();
        movieService.fetchMovieList(); // Fetch movies before each test
    }

    @Test
    public void testMovieListNotEmpty() {
        List<Movie> movieList = movieService.getMovieList();
        assertNotNull(movieList);
        assertFalse(movieList.isEmpty());
    }

    @Test
    public void testMovieListContainsInception() {
        List<Movie> movieList = movieService.getMovieList();
        boolean hasInception = movieList.stream().anyMatch(movie -> movie.getTitle().equals("Inception"));
        assertTrue(hasInception);
    }

    @Test
    public void testSearchMovieByKeyword() {
        List<Movie> movieList = movieService.getMovieList();
        List<Movie> result = movieService.searchMovies("Action");
        for (Movie movie : result) {
            System.out.println(movie.toString());
        }
        assertNotNull(result);
    }

}

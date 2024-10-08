package test;

import main.java.model.Movie;
import main.java.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.service.MovieService;

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
    public void testGetMovieList() {
        List<String> result = movieService.getMovies(movieService.getMovieList());
        for (String movieName : result) {
            System.out.println(movieName);
        }
        assertNotNull(result);
    }

    @Test
    public void testMovieListContainsInception() {
        List<Movie> movieList = movieService.getMovieList();
        boolean hasInception = movieList.stream().anyMatch(movie -> movie.getTitle().equals("Inception"));
        assertTrue(hasInception);
    }

    @Test
    public void testSearchMovieByKeyword() {
        List<String> result = movieService.searchMovies(movieService.getMovieList(), "Action");
        for (String movieName : result) {
            System.out.println(movieName);
        }
        assertNotNull(result);
    }

    @Test
    public void testAddRemoveMovieToUserFavourites() {
        User user = new User();
        user.setEmail("test@test.com");
        assertTrue(movieService.addMovieToUserFavourites(movieService.getMovieByTitle("Inception").getTitle(), user));
        assertTrue(movieService.removeMovieToUserFavourites(movieService.getMovieByTitle("Inception").getTitle(), user));
    }

}

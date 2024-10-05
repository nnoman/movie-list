package main.java.service;

import lombok.Getter;
import lombok.Setter;
import main.java.model.Movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import main.java.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

@Getter
@Setter
public class MovieService {
    private List<Movie> movieList;


    public void fetchMovieList() {
        this.movieList = new ArrayList<>();
        try {
            String apiUrl = "https://freetestapi.com/api/v1/movies";
            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                String jsonResponse = response.toString();
                JSONArray jsonArray = new JSONArray(jsonResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    Movie movie = new Movie();
                    movie.setTitle(jsonObject.getString("title"));
                    List<String> actors = new ArrayList<>();
                    JSONArray actorsArray = jsonObject.getJSONArray("actors");
                    for (int j = 0; j < actorsArray.length(); j++) {
                        actors.add(actorsArray.getString(j));
                    }
                    movie.setCasts(actors);
                    Random random = new Random();
                    movie.setBudget(100.00 + random.nextDouble(401));
                    JSONArray genresArray = jsonObject.getJSONArray("genre");
                    movie.setCategory(genresArray.getString(0));
                    movie.setReleaseDate(jsonObject.getInt("year"));
                    movieList.add(movie);
                }

            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<String> getMovies(List<Movie> movieList) {
        return movieList.stream()
                .sorted((m1, m2) -> m1.getTitle().toLowerCase().compareTo(m2.getTitle().toLowerCase()))
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }

    public List<String> getMoviesByTitles(List<String> titles) {
        List<String> foundMovies = new ArrayList<>();

        for (String title : titles) {
            boolean found = false;
            for (Movie movie : movieList) {
                if (movie.getTitle().equalsIgnoreCase(title)) {
                    foundMovies.add(movie.getTitle());
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Movie not found for title: " + title);
            }
        }

        return foundMovies;
    }


    public List<String> searchMovies(List<Movie> movieList, String keyword) {
        return movieList.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(keyword.toLowerCase())
                        || movie.getCategory().toLowerCase().contains(keyword.toLowerCase()) ||
                        movie.getCasts().stream()
                                .anyMatch(cast -> cast.toLowerCase().contains(keyword.toLowerCase())))
                .sorted((m1, m2) -> m1.getTitle().toLowerCase().compareTo(m2.getTitle().toLowerCase()))
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }

    public Movie getMovieByTitle(String title) {
        for (Movie movie : movieList) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        System.out.println("Movie not found");
        return null;
    }

    public Boolean addMovieToUserFavourites(String title, User user) {
        List<String> favouriteMovies = user.getFavouriteMovies();
        if(favouriteMovies.contains(title)) {
            System.out.println(title + " is already in the favourite list");
            return false;
        }
        favouriteMovies.add(title);
        System.out.println(title + " was added to the favourite list");
        return true;
    }

    public Boolean removeMovieToUserFavourites(String title, User user) {
        List<String> favouriteMovies = user.getFavouriteMovies();
        if(!favouriteMovies.contains(title)) {
            System.out.println(title + " is not in the favourite list");
            return false;
        }
        favouriteMovies.remove(title);
        System.out.println(title + " was removed to the favourite list");
        return true;
    }

}

package model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private String email;
    private List<Movie> favouriteMovies = new ArrayList<>();
}

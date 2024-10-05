package main.java.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private String email;
    private List<String> favouriteMovies = new ArrayList<>();

    @Override
    public String toString() {
        return "email(username)='" + email + "'\n" +
                "favouriteMovies:\n" + String.join("\n", favouriteMovies);
    }
}

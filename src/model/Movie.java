package model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Movie {
    private int id;
    private String title;
    private List<String> casts;
    private String category;
    private Integer releaseDate;
    private Double budget;

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", casts=" + casts +
                ", category='" + category + '\'' +
                ", releaseDate=" + releaseDate +
                ", budget=" + budget +
                '}';
    }
}
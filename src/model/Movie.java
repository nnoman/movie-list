package model;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;
import java.util.List;

@Getter
@Setter
public class Movie {
    private String title;
    private List<String> casts;
    private String category;
    private Integer releaseDate;
    private Double budget;

    @Override
    public String toString() {
        return  "title='" + title + "'\n" +
                "casts=" + casts.toString() + "'\n" +
                "category='" + category + "'\n" +
                "releaseDate=" + releaseDate + "'\n" +
                "budget=" + new DecimalFormat("##.##").format(budget) +"m";
    }
}
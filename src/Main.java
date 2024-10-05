import main.java.model.Movie;
import main.java.model.User;
import main.java.service.MovieService;
import main.java.service.UserService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static User loggedInUser = null;
    private static final Scanner scanner = new Scanner(System.in);
    private static final MovieService movieService = new MovieService();
    private static final UserService userService = new UserService();
    public static void main(String[] args) {
        movieService.fetchMovieList();
        while (true) {
           if(loggedInUser == null) {
               System.out.println("\n--- Movie Listing Application ---");
               System.out.println("1. Sign Up");
               System.out.println("2. Log In");
               System.out.println("3. Exit");
               System.out.print("Choose an option: ");

               int choice = scanner.nextInt();
               scanner.nextLine(); // Consume newline

               switch (choice) {
                   case 1 -> signUp();
                   case 2 -> logIn();
                   case 3 -> {
                       System.out.println("Exiting the application.");
                       return;
                   }
                   default -> System.out.println("Invalid choice! Please try again.");
               }
           } else {
               System.out.println("\n--- Movie Listing Application ---");
               System.out.println("1. All Movies");
               System.out.println("2. Favourite Movies");
               System.out.println("3. Search from All Movies");
               System.out.println("4. Search from favourites Movies");
               System.out.println("5. See details of a Movie");
               System.out.println("6. Add movies to favourites");
               System.out.println("7. remove movies to favourites");
               System.out.println("8. See personal details");
               System.out.println("9. Log out");
               System.out.println("10. Exit");
               System.out.print("Choose an option: ");

               int choice = scanner.nextInt();
               scanner.nextLine(); // Consume newline

               switch (choice) {
                   case 1 -> showMovieList();
                   case 2 -> showFavMovieList();
                   case 3 -> searchFromAllMovies();
                   case 4 -> searchFromFavMovies();
                   case 5 -> detailsOfaMovie();
                   case 6 -> addMovieToFavourites();
                   case 7 -> removeMovieToFavourites();
                   case 8 -> personalDetails();
                   case 9 -> logout();
                   case 10 -> {
                       System.out.println("Exiting the application.");
                       return;
                   }
                   default -> System.out.println("Invalid choice! Please try again.");
               }
           }
        }
    }

    public static void signUp() {
        System.out.print("Enter your email: ");
        String username = scanner.nextLine();
        loggedInUser = userService.registerUser(username);
    }

    public static void logIn() {
        System.out.print("Enter your email: ");
        String username = scanner.nextLine();
        loggedInUser = userService.loginUser(username);
    }



    public static void logout() {
        loggedInUser = null;
    }

    public static void showMovieList() {
        System.out.println("\n--- This is all Movie in our system ---");
        List<String> result = movieService.getMovies(movieService.getMovieList());
        for (String movieName : result) {
            System.out.println(movieName);
        }
        scanner.nextLine();
    }

    public static void showFavMovieList() {
        System.out.println("\n--- This is all your favourite movies  ---");
        List<String> result = movieService.getMoviesByTitles(loggedInUser.getFavouriteMovies());
        for (String movieName : result) {
            System.out.println(movieName);
        }
        scanner.nextLine();
    }

    public static void searchFromAllMovies() {
        System.out.println("--- Search from all movies  ---");
        System.out.print("Enter your Keyword: ");
        String keyword = scanner.nextLine();
        List<String> result = movieService.searchMovies(movieService.getMovieList(), keyword);
        for (String movieName : result) {
            System.out.println(movieName);
        }
        scanner.nextLine();
    }

    public static void searchFromFavMovies() {
        System.out.println("--- Search from favourite movies  ---");
        System.out.print("Enter your Keyword: ");
        String keyword = scanner.nextLine();
        List<String> result = movieService.searchMovies(movieService.getMovieList(), keyword);
        for (String movieName : result) {
            System.out.println(movieName);
        }
        scanner.nextLine();
    }

    public static void detailsOfaMovie() {
        System.out.print("Enter your movie title: ");
        String title = scanner.nextLine();
        Movie movie = movieService.getMovieByTitle(title);
        if(Objects.nonNull(movie)) {
            System.out.println(movie);
        }
        scanner.nextLine();
    }

    public static void addMovieToFavourites() {
        System.out.print("Enter your movie title to add your favourites: ");
        String title = scanner.nextLine();
        Movie movie = movieService.getMovieByTitle(title);
        if(Objects.nonNull(movie)) {
            movieService.addMovieToUserFavourites(movie.getTitle(), loggedInUser);
        }
        scanner.nextLine();
    }

    public static void removeMovieToFavourites() {
        System.out.print("Enter your movie title to remove from your favourites: ");
        String title = scanner.nextLine();
        Movie movie = movieService.getMovieByTitle(title);
        if(Objects.nonNull(movie)) {
            movieService.removeMovieToUserFavourites(movie.getTitle(), loggedInUser);
        }
        scanner.nextLine();
    }

    public static void personalDetails() {
        System.out.println(loggedInUser.toString());
        scanner.nextLine();
    }
}
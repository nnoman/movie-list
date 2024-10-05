package main.java.service;

import main.java.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserService {
    List<User> users;
    public UserService() {
        users = new ArrayList<>();
    }

    public User registerUser(String email) {
        if(!validateEmail(email)) {
            System.out.println("email is not valid");
            return null;
        }
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Username already exists! Try another.");
                return null;
            }
        }
        User user = new User();
        user.setEmail(email);
        users.add(user);
        return user;
    }

    private Boolean validateEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public User loginUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Login successful");
                return user;
            }
        }
        System.out.println("No user exist!");
        return null;
    }

}

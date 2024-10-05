package test;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceTest {
    private UserService userService;
    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }
    @Test
    public void testRegisterUser() {
        assertNotNull(userService.registerUser("abcd@gmail.com"));
        assertNotNull(userService.loginUser("abcd@gmail.com"));
    }
}

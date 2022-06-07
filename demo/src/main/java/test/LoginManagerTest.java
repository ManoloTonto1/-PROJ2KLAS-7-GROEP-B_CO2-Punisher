package test;

import static org.junit.Assert.*;
import org.junit.Test;

import models.LoginManager;

public class LoginManagerTest {

    // public static void main(String[] args) {}

    @Test
    public void GetUser_UserLoggedIn_ExpectTrue() {

        LoginManager session = new LoginManager();
        session.Login("email", "1234");
        // System.out.println(session.GetUser() != null);

        assertTrue(session.GetUser() != null);
    }

    @Test
    public void GetUser_UserNotLoggedIn_ExpectFalse() {

        LoginManager session = new LoginManager();
        // System.out.println(session.GetUser() != null);

        assertFalse(session.GetUser() != null);
    }
}

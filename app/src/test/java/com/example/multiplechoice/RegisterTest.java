package com.example.multiplechoice;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void canRegister() {
        User user = new User();
        String username = "geralt";
        String email = "geralt@rivia.com";
        String password = "roach";
        int expected = 200;
        int output =  user.register(username, email,password);
        assertEquals(expected, output);

    }
    @Test
    public void userExistsButNotEmail() {
        User user = new User();
        String username = "geralt";
        String email = "geralt@novigrad.com";
        String password = "roach";
        int expected = 403;
        int output =  user.register(username, email,password);
        assertEquals(expected, output);
    }
    @Test
    public void emailExistsButNotUser() {
        User user = new User();
        String username = "geraltOfRivia2009";
        String email = "geralt@rivia.com";
        String password = "roach";
        int expected = 403;
        int output =  user.register(username, email,password);
        assertEquals(expected, output);
    }
}
package com.example.multiplechoice;

import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void isValid(){
        User user = new User();
        String username = "volpym";
        String password = "ac0b3pass";
        int expected = 200;
        int output =  user.login(username,password);
        assertEquals(expected,output);
    }
    @Test
    public void isWrongPassword(){
        User user = new User();
        String username = "volpym";
        String password = "ac0b3pas2";
        int expected = 401;
        int output =  user.login(username,password);

    }
    @Test
    public void userDoesNotExist(){
        User user = new User();
        String username = "volpym234";
        String password = "ac0b3pass";
        int expected = 404;
        int output =  user.login(username,password);

    }



}
package com.example.multiplechoice;

import java.io.IOException;
import java.net.MalformedURLException;

public interface Users {
        /**
         * <p>
         *     This method will let the user login by passing the strings from <b>editUsernameText</b> and <b>editPasswordText</b>
         *     to the server. This is achieved by using <b><a href='https://docs.oracle.com/javase/8/docs/api/java/net/HttpURLConnection.html'>HttpURLConnection()</a></b>
         *     and a <b>register.php</b> script which is stored on a remote server.
         * </p>
         * @param username user's username
         * @param password user's password
         * @return returns the integer from getResponseCode()
         */
        int login(String username, String password);
        int register(String username, String email, String password);
        boolean isTheSame(String password, String confirmation);
        int changePassword(String username, String email, String password);



}

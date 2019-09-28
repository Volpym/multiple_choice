package com.example.multiplechoice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User implements Users {



    @Override
    public int login(String username,String password) {
        int response = 0;

        try {
            URL url= new URL("http://localhost/icd_tei_ser/script/user/log_in.php?username="+username+"&password="+password);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            response = connection.getResponseCode();
            return response;

        }catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

        return response;
    }

    @Override
    public int register(String username, String email, String password) {
        int response = 0;
        try {
            URL url= new URL("http://localhost/icd_tei_ser/script/user/register.php?username="+username+"&email="+email+"&password="+password);
            System.out.println(url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            response = connection.getResponseCode();
        } catch (MalformedURLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public boolean isTheSame(String password, String confirmation) {
        return false;
    }

    @Override
    public int changePassword(String username, String email, String password) {
        return 0;
    }
}

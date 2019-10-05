package com.example.multiplechoice;



import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class Question{
     int question_id;
     String question_txt ;
     JSONArray json ;


    public int setQuestion_id(int i) {
        try {
            this.question_id = this.json.getJSONObject(i).getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return question_id;
    }

    public String setQuestion_txt(int i) {
        try {
            this.question_txt = this.json.getJSONObject(i).getString("Question");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return question_txt;
    }

    public JSONArray setJson()throws JSONException, IOException {
        java.net.HttpURLConnection connection;
        URL url = new URL("http://192.168.1.5/icd_tei_ser/script/user/get_questions.php");
        connection = (java.net.HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-length", "0");
        connection.setUseCaches(false);
        connection.setConnectTimeout(0);
        connection.setReadTimeout(0);
        connection.connect();
        int status = connection.getResponseCode();
        JSONArray json = null;


        switch (status) {
            case 200:
            case 201:

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                this.json = new JSONArray(sb.toString());
                br.close();
        }
        return this.json;

    }
}



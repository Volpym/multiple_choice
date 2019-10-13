package com.example.multiplechoice;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Question{
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctAnswer;




    public Question (int i, String q, String o1, String o2, String o3, String o4, String cA) {
        this.id = i;
        this.question = q;
        this.option1 = o1;
        this.option2 = o2;
        this.option3 = o3;
        this.option4 = o4;
        this.correctAnswer = cA;
    }

    public String getQuestion() {
        return question;
    }

    public int getId() {
        return id;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isCorrect(String answer,String correctAnswer){
        boolean value;
        if (answer.equals(correctAnswer)){
            value = true;
        }else{
            value = false;
        }
        return value;
    }



    public static List<Question> fetchQuestions () throws JSONException, IOException {

        HttpURLConnection connection = (HttpURLConnection) new URL("http://192.168.1.6/icd_tei_ser/script/question/get_questions.php").openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-length", "0");
        connection.setUseCaches(false);
        connection.setConnectTimeout(0);
        connection.setReadTimeout(0);
        connection.connect();
        int status = connection.getResponseCode();
        if (!(status == 200 || status == 201)) {
            throw new IOException("Bad status code: " + status);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        String response = sb.toString();


        List<Question> questions = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(response);
        JSONObject questionAsJSON;
        for (int i = 0; i< jsonArray.length();i++) {
            questionAsJSON = jsonArray.getJSONObject(i); // throws on unexpected response format
            questions.add(new Question(questionAsJSON.getInt("id"),
                                       questionAsJSON.getString("Question"),
                                       questionAsJSON.getString("Option1"),
                                       questionAsJSON.getString("Option2"),
                                       questionAsJSON.getString("Option3"),
                                       questionAsJSON.getString("Option4"),
                                       questionAsJSON.getString("Correct Answer")
                                       ));
        }

        return questions;

    }



}



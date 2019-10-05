package com.example.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




       getQuestionFromDB.execute();


    }


    private  class getQuestionFromDB extends AsyncTask<Void, Void, ArrayList<Question>> {
        @Override
        protected ArrayList<Question> doInBackground(Void... voids) {
            ArrayList<Question> questionnaire = new ArrayList<>();
            Question q = new Question();
            try {
                for (int i = 0; i < q.setJson().length(); i++) {
                    Question newQuestion = new Question();
                    newQuestion.json = q.json;
                    newQuestion.setQuestion_id(i);
                    newQuestion.setQuestion_txt(i);
                    questionnaire.add(newQuestion);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<Question> questionnaire) {
            super.onPostExecute(questionnaire);
    }
    }
}





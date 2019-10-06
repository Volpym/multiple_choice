package com.example.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    FetchQ fetchq = new FetchQ();
    List<Question> questions = null;
    String correctAnswer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tQuestion = findViewById(R.id.textViewQustion);
        Button option1 = findViewById(R.id.bOption1);
        Button option2 = findViewById(R.id.bOption2);
        Button option3 = findViewById(R.id.bOption3);
        Button option4 = findViewById(R.id.bOption4);

        try {
            questions = fetchq.execute().get();
            System.out.println(questions);
            Collections.shuffle(questions);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }






    private class FetchQ extends AsyncTask<Void, Void, List<Question>> {

        List<Question> questions;
        private MainActivity activity;

        @Override
        protected List<Question> doInBackground(Void... voids) {
            try {
                this.questions =  Question.fetchQuestions();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  questions;
        }
    }

    public void setList(List<Question> questions){
        this.questions=questions;
    }


}





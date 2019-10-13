package com.example.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FetchQ fetchq = new FetchQ();
    List<Question> questions = null;
    String correctAnswer;
    private Bundle savedInstanceState;
    int iterator = 0;
    Question question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView tQuestion = findViewById(R.id.textViewQuestion);
        Button answer1 = findViewById(R.id.bAnswer1);
        Button answer2 = findViewById(R.id.bAnswer2);
        Button answer3 = findViewById(R.id.bAnswer3);
        Button answer4 = findViewById(R.id.bAnswer4);

        answer1.setOnClickListener(this);
        answer2.setOnClickListener(this);
        answer3.setOnClickListener(this);
        answer4.setOnClickListener(this);



        //Retrieves all the questions from the database and shuffles it to create a random order of presentation for the questions
        try {
            questions = fetchq.execute().get();
            Collections.shuffle(questions);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        this.question = loadQuestionElements(questions, iterator, tQuestion, answer1, answer2, answer3, answer4);
        this.correctAnswer = question.getCorrectAnswer();

    }


    public Question loadQuestionElements(List<Question> questions, int iterator, TextView question, Button option1, Button option2, Button option3, Button option4){
        Question q = questions.get(iterator);
        question.setText(q.getQuestion());
        option1.setText(q.getOption1());
        option2.setText(q.getOption2());
        option3.setText(q.getOption3());
        option4.setText(q.getOption4());
        this.correctAnswer = q.getCorrectAnswer();
        this.iterator++;
        return q;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;

        switch(v.getId()){
            case R.id.bAnswer1:
                if (this.question.isCorrect(button.getText().toString(),this.correctAnswer)){
                    button.setBackgroundColor(0xFF4CAF50);//Set button background color to green
                }else{
                    button.setBackgroundColor(0xFFF44336);//Set button background color to red
                }




            case R.id.bAnswer2:
                if (this.question.isCorrect(button.getText().toString(),this.correctAnswer)){
                    button.setBackgroundColor(0xFF4CAF50);//Set button background color to green
                }else{
                    button.setBackgroundColor(0xFFF44336);//Set button background color to red
                }




            case R.id.bAnswer3:
                if (this.question.isCorrect(button.getText().toString(),this.correctAnswer)){
                    button.setBackgroundColor(0xFF4CAF50);//Set button background color to green
                }else{
                    button.setBackgroundColor(0xFFF44336);//Set button background color to red
                }



            case R.id.bAnswer4:
                if (this.question.isCorrect(button.getText().toString(),this.correctAnswer)){
                    button.setBackgroundColor(0xFF4CAF50);//Set button background color to green
                }else{
                    button.setBackgroundColor(0xFFF44336);//Set button background color to red
                }







        }

    }


    private class FetchQ extends AsyncTask<Void, Void, List<Question>> {

        List<Question> questions;
        private MainActivity activity;

        @Override
        protected List<Question> doInBackground(Void... voids) {
            try {
                this.questions = Question.fetchQuestions();
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
            return questions;
        }

    }




    


}






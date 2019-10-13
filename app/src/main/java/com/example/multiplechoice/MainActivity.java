package com.example.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FetchQ fetchq = new FetchQ();
    List<Question> questions = null;
    String correctAnswer;
    private Bundle savedInstanceState;
    PointSystem pointSystem = new PointSystem();
    int iterator = 0;
    private CountDownTimer countDownTimer;
    private long timeLeft;




    private TextView tQuestion;
    private TextView questionNumber;
    private TextView score;
    private TextView timer;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;
    Question question;

    private Toast correct_message;
    private Toast wrong_message;
    private Toast out_of_time;
    final Handler handler = new Handler();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

        //Sets the messages that are going to be displayed when the user selects and answer.
        correct_message = Toast.makeText(getApplicationContext(),"Correct!", Toast.LENGTH_SHORT);
        wrong_message = Toast.makeText(getApplicationContext(),"Wrong!", Toast.LENGTH_SHORT);
        out_of_time = Toast.makeText(getApplicationContext(),"Out of Time!", Toast.LENGTH_SHORT);


        setContentView(R.layout.activity_main);

        questionNumber=findViewById(R.id.eNumberOfQuestions);
        tQuestion = findViewById(R.id.textViewQuestion);
        score = findViewById(R.id.eScore);
        timer = findViewById(R.id.timer);
        answer1 = findViewById(R.id.bAnswer1);
        answer2 = findViewById(R.id.bAnswer2);
        answer3 = findViewById(R.id.bAnswer3);
        answer4 = findViewById(R.id.bAnswer4);


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

        question = getNewQuestion();
        try {
            loadQuestionElements(question);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    private Question getNewQuestion() {
        Question q = questions.get(iterator);
        iterator++;
        return q;

    }

    public void loadQuestionElements(Question question) throws InterruptedException {


        this.tQuestion.setText(question.getQuestion());
        this.questionNumber.setText(showQuestionNumber(iterator));
        this.score.setText("Score: "+String.valueOf(pointSystem.getCurrentPoints()));

        //resets Buttons' background color
        this.answer1.setBackgroundResource(R.color.default_button);
        this.answer2.setBackgroundResource(R.color.default_button);
        this.answer3.setBackgroundResource(R.color.default_button);
        this.answer4.setBackgroundResource(R.color.default_button);

        //Sets Buttons' text
        this.answer1.setText(question.getOption1());
        this.answer2.setText(question.getOption2());
        this.answer3.setText(question.getOption3());
        this.answer4.setText(question.getOption4());


        this.correctAnswer = question.getCorrectAnswer();
        startCountdown();
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        switch(v.getId()) {
            case R.id.bAnswer1:
                if (this.question.isCorrect(button.getText().toString(), this.correctAnswer)) {
                    pointSystem.increasePoints();
                    this.correct_message.show();
                    countDownTimer.cancel();
                } else {
                    this.wrong_message.show();
                    countDownTimer.cancel();
                }

                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            loadQuestionElements(getNewQuestion());

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },1000);
                break;


            case R.id.bAnswer2:
                if (this.question.isCorrect(button.getText().toString(), this.correctAnswer)) {
                    pointSystem.increasePoints();
                    this.correct_message.show();
                    countDownTimer.cancel();
                } else {
                    this.wrong_message.show();
                    countDownTimer.cancel();
                }
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            loadQuestionElements(getNewQuestion());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },1500);
                break;
            case R.id.bAnswer3:
                if (this.question.isCorrect(button.getText().toString(), this.correctAnswer)) {
                    pointSystem.increasePoints();
                    this.correct_message.show();
                    countDownTimer.cancel();
                } else {
                    this.wrong_message.show();
                    countDownTimer.cancel();
                }
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            loadQuestionElements(getNewQuestion());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },1500);
                break;

            case R.id.bAnswer4:
                if (this.question.isCorrect(button.getText().toString(), this.correctAnswer)) {
                    pointSystem.increasePoints();
                    this.correct_message.show();
                    countDownTimer.cancel();
                } else {
                    this.wrong_message.show();
                    countDownTimer.cancel();
                }
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            loadQuestionElements(getNewQuestion());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },1500);
                break;

        }
    }

    private class FetchQ extends AsyncTask<Void, Void, List<Question>> {

        List<Question> questions;


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

    public String showQuestionNumber(int iterator){
        String qNumber = iterator+"/100";
        return qNumber;
    }

    private void startCountdown(){
        countDownTimer = new CountDownTimer(31000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountdownText();
            }

            @Override
            public void onFinish() {
                out_of_time.show();
                countDownTimer.cancel();
                updateCountdownText();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        try {
                            loadQuestionElements(getNewQuestion());

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                },1000);

            }
        }.start();
    }

    private void updateCountdownText(){

        int seconds = (int) (timeLeft / 1000) % 60;

        String timeFormatted = String.valueOf(seconds);

        timer.setText(timeFormatted);

        if (timeLeft < 10000) {
            timer.setTextColor(Color.RED);
        }else{
            timer.setTextColor(Color.BLACK);
        }
    }


}






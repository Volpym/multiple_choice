package com.example.multiplechoice;

public class Answer {
    private static String answer1;
    private static String answer2;
    private static String answer3;
    private static String answer4;
    private static String correctAnswer;


    public Answer(String answer1, String answer2, String answer3, String answer4, String correctAnswer) {
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }

    public static String getAnswer1() {
        return answer1;
    }

    public static String getAnswer2() {
        return answer2;
    }

    public static String getAnswer3() {
        return answer3;
    }

    public static String getAnswer4() {
        return answer4;
    }

    public static String getCorrectAnswer() {
        return correctAnswer;
    }


    public boolean isNull(String answer4){
        boolean check = false;
        if(answer4 == null){
            check = true;
        }
        return check;
    }

}

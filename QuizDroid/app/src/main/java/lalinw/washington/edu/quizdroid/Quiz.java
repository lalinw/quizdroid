package lalinw.washington.edu.quizdroid;

import java.util.List;

/**
 * Created by IreneW on 2017-02-14.
 */

public class Quiz {
    private String question;
    private List<String> answers;
    private int correctAnswer;

    public String getQuestion() {
        return question;
    }
    public List<String> getAnswers() {
        return answers;
    }
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setQuestion(String q) {}

    public void setAnswers(String a, String b, String c, String d){}

    public void setCorrectAnswer(int ans) {}

}

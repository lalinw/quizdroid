package lalinw.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IreneW on 2017-02-14.
 */

public class Quiz {
    private String question;
    private List<String> answers = new ArrayList<String>();
    private int correctAnswer;

    //temporary constructor for hard code data
    public Quiz() {
        setQuestion("How many days are in February this year?");
        setAnswers("28", "29", "30", "31");
        setCorrectAnswer(1);
    }

    //getters
    public String getQuestion() {
        return question;
    }
    public List<String> getAnswers() {
        return answers;
    }
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    //setters
    public void setQuestion(String q) {
        question = q;
    }

    public void setAnswers(String a, String b, String c, String d){
        answers.add(a);
        answers.add(b);
        answers.add(c);
        answers.add(d);
    }

    public void setCorrectAnswer(int ans) {
        correctAnswer = ans;
    }

}

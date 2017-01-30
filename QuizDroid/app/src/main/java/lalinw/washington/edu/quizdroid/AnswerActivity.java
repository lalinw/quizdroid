package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        //change id according to answer key
        //highlights the correct answer
        //change the box color and display some text "Your answer"
        //tags the answer given by the user

        //correct answer
        TextView correctAnswer = (TextView) findViewById(R.id.ans1);
        correctAnswer.setBackgroundColor(Color.parseColor("#d2ff74"));
        //TextView correctAnswerText = (TextView) findViewById(R.id.ans1text);
        //correctAnswerText.setText("Correct Answer");
        //correctAnswerText.setTextColor(Color.parseColor("#4f7400"));

        //wrong answer
        TextView yourWrongAnswer = (TextView) findViewById(R.id.ans2);
        yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
        //TextView yourWrongAnswerText = (TextView) findViewById(R.id.ans2text);
        //correctAnswerText.setText("Your Answer");
        //correctAnswerText.setTextColor(Color.parseColor("#e91a00"));

    }



    //BUTTON
    //text=next if have more questions
    //text=finish if on last question
    public void continueQuiz(View view) {
        //displays questions and answers
        //keeps track of previous answers
        boolean lastQuestion = true;
        Button cont = (Button) findViewById(R.id.quiz_continue);
        if (!lastQuestion) {
            cont.setText("Next Question");
            Intent intent = new Intent(this, QuestionActivity.class);
            startActivity(intent);
        } else {
            //ends quiz
            cont.setText("Finish");
            Intent intent = new Intent(this, QuizTopicActivity.class);
            startActivity(intent);
        }

    }
}

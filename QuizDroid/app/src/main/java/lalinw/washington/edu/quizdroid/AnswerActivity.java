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

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        //receive
        extras = getIntent().getExtras();
        if (extras != null) {

            //display question + choices
            String ques = extras.getString("ques");
            TextView question = (TextView) findViewById(R.id.question);
            question.setText(ques);
            TextView ans1 = (TextView) findViewById(R.id.ans1);
            ans1.setText("right answer");
            TextView ans2 = (TextView) findViewById(R.id.ans2);
            ans2.setText("wrong answer");
            TextView ans3 = (TextView) findViewById(R.id.ans3);
            ans3.setText("wrong answer");
            TextView ans4 = (TextView) findViewById(R.id.ans4);
            ans4.setText("wrong answer");

            //displays score
            String currentScore = extras.getString("score");
            TextView score = (TextView) findViewById(R.id.score);
            score.setText("You have " + currentScore + " out of 2 correct");

            //customizes the button
            String progress = extras.getString("qnum");
            Button cont = (Button) findViewById(R.id.quiz_continue);
            if (!progress.equalsIgnoreCase("last")) {
                cont.setText("Next Question");
            }

            //textview predefined according to the answer key
            //is ans1 in this case
            String yourAnswer = extras.getString("input");
            TextView correctAnswer = (TextView) findViewById(R.id.ans1);
            correctAnswer.setBackgroundColor(Color.parseColor("#d2ff74"));
            if (yourAnswer.equalsIgnoreCase("a")) {
                //correct answer
            } else if (yourAnswer.equalsIgnoreCase("b")) {
                TextView yourWrongAnswer = (TextView) findViewById(R.id.ans2);
                yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
            } else if (yourAnswer.equalsIgnoreCase("c")) {
                TextView yourWrongAnswer = (TextView) findViewById(R.id.ans3);
                yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
            } else {
                //choice d
                TextView yourWrongAnswer = (TextView) findViewById(R.id.ans4);
                yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
            }

        }

        //change id according to answer key
        //highlights the correct answer
        //change the box color and display some text "Your answer"
        //tags the answer given by the user

//        //correct answer
//        TextView correctAnswer = (TextView) findViewById(R.id.ans1);
//        correctAnswer.setBackgroundColor(Color.parseColor("#d2ff74"));
//        TextView correctAnswerText = (TextView) findViewById(R.id.ans1text);
//        correctAnswerText.setText("Correct Answer");
//        correctAnswerText.setTextColor(Color.parseColor("#4f7400"));
//
//        //wrong answer
//        TextView yourWrongAnswer = (TextView) findViewById(R.id.ans2);
//        yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
//        TextView yourWrongAnswerText = (TextView) findViewById(R.id.ans2text);
//        correctAnswerText.setText("Your Answer");
//        correctAnswerText.setTextColor(Color.parseColor("#e91a00"));

    }


    //BUTTON
    //text=next if have more questions
    //text=finish if on last question
    public void continueQuiz(View view) {
        //displays questions and answers
        //keeps track of previous answers

        String progress = extras.getString("qnum");
        if (!progress.equalsIgnoreCase("last")) {
            Intent intent = new Intent(this, Question2Activity.class);
            intent.putExtra("score", extras.getString("score"));
            startActivity(intent);
        } else {
            //ends quiz
            Intent intent = new Intent(this, QuizTopicActivity.class);
            startActivity(intent);
        }

    }
}

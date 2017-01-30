package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class AnswerActivity extends AppCompatActivity {

    String newScore;
    boolean lastQ = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        //receive
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            //progress
            String lastOrNo = extras.getString("qnum");
            lastQ = lastOrNo.equalsIgnoreCase("last");

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
            newScore = extras.getString("score");
            TextView score = (TextView) findViewById(R.id.score);
            score.setText("You have " + newScore + " out of 2 correct");

            //customizes the button
            String progress = extras.getString("qnum");
            Button cont = (Button) findViewById(R.id.quiz_continue);
            if (!lastQ) {
                cont.setText("Next Question");
            }

            //text predefined according to the answer key
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
    public void continueQuiz(View view) {
        Log.i("ANSWER_Activity", "button clicked");
        if (lastQ) {
            //ends quiz
            Log.i("ANSWER_Activity", "is last q");
            Intent intent = new Intent(this, QuizTopicActivity.class);
            startActivity(intent);
        } else {
            Log.i("ANSWER_Activity", "is NOT last q");
            Intent intent = new Intent(this, Question2Activity.class);
            Log.i("ANSWER_Activity", "created intent");
            intent.putExtra("score", newScore);
            Log.i("ANSWER_Activity", "set score");
            startActivity(intent);
        }

    }
}

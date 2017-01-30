package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

//temporary
public class Question2Activity extends AppCompatActivity {

    String input;
    String score;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //same layout as question
        setContentView(R.layout.activity_question);

        //dummy question 2
        TextView question = (TextView) findViewById(R.id.question);
        question.setText("This is question two.");
        RadioButton ans1 = (RadioButton) findViewById(R.id.ans1);
        ans1.setText("right answer");
        RadioButton ans2 = (RadioButton) findViewById(R.id.ans2);
        ans1.setText("wrong answer");
        RadioButton ans3 = (RadioButton) findViewById(R.id.ans3);
        ans1.setText("wrong answer");
        RadioButton ans4 = (RadioButton) findViewById(R.id.ans4);
        ans1.setText("wrong answer");

        extras = getIntent().getExtras();
        if (extras != null) {
            score = extras.getString("score");
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            Button submit = (Button) findViewById(R.id.answer_submit);
            submit.setEnabled(true);
            submit.setBackgroundColor(Color.parseColor("#FF9900"));
        }

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ans1:
                if (checked)
                    input = "a";
                    int scoreInt = Integer.parseInt(score);
                    scoreInt++;
                    score = "" + scoreInt;
                    break;
            case R.id.ans2:
                if (checked)
                    input = "b";
                    break;
            case R.id.ans3:
                if (checked)
                    input = "c";
                    break;
            case R.id.ans4:
                if (checked)
                    input = "d";
                    break;
        }
    }

    public void submitAnswer(View view) {
        //displays questions and answers
        //keeps track of previous answers
        Intent intent = new Intent(this, AnswerActivity.class);
        intent.putExtra("input", input);
        intent.putExtra("qnum", "last");
        intent.putExtra("score", score);
        intent.putExtra("ques", "This is question two.");
        startActivity(intent);
    }
}

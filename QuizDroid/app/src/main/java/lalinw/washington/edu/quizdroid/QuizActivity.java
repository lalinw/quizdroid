package lalinw.washington.edu.quizdroid;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity
        implements
            TopicOverviewFragment.OnFragmentInteractionListener,
            QuestionFragment.OnFragmentInteractionListener,
            AnswerFragment.OnFragmentInteractionListener{

    //from last activity
    String input;
    String score;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction frag = fragmentManager.beginTransaction();
        frag.replace(R.id.placeholder, new TopicOverviewFragment());
        frag.commit();

        Log.i("QUIZ ACTIVITY", "replaced fragment");

//        extras = getIntent().getExtras();
//        if (extras != null) {
//            String message = extras.getString("topic");
//            //displays description, # of questions
//            TextView quizTopic = (TextView) findViewById(R.id.topic);
//            quizTopic.setText(message);
//            TextView qs = (TextView) findViewById(R.id.q_amnt);
//            qs.setText("Questions in this topic: 2");
//        }

        //Log.i("QUIZ ACTIVITY", "extras stuff happened");



    }

        public void onRadioButtonClicked(View view) {

        Log.i("QUESTION_ACTIVITY", "clicked radio button");
        // Is the button now checked?
        RadioGroup g = (RadioGroup) findViewById(R.id.answer_choice);
            boolean checked = ((RadioButton) view).isChecked();
            if (checked) {
                Button submit = (Button) findViewById(R.id.answer_submit);
                submit.setEnabled(true);
                submit.setBackgroundColor(Color.parseColor("#FF9900"));
            }


            // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.ans1:
//                if (checked)
//                    input = "a";
//                int scoreInt = Integer.parseInt(score);
//                scoreInt++;
//                score = "" + scoreInt;
//                break;
//            case R.id.ans2:
//                if (checked)
//                    input = "b";
//                break;
//            case R.id.ans3:
//                if (checked)
//                    input = "c";
//                break;
//            case R.id.ans4:
//                if (checked)
//                    input = "d";
//                break;
//        }
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

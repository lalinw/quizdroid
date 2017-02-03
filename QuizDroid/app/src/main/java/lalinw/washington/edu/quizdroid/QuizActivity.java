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
    int yourAnswer;
    String topic;
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
//        };



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

             //Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ans1:
                if (checked)
                    recordAnswer(1);
                break;
            case R.id.ans2:
                if (checked)
                    recordAnswer(2);
                break;
            case R.id.ans3:
                if (checked)
                    recordAnswer(3);
                break;
            case R.id.ans4:
                if (checked )
                    recordAnswer(4);
                break;
        }

    }

    public int yourAnswer() {
        return yourAnswer;
    }

    public void recordAnswer(int set) {
        yourAnswer = set;
    }

    public String currentTopic() {
        return topic;
    }
    public void chosenTopic(String t) {
        topic = t;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}

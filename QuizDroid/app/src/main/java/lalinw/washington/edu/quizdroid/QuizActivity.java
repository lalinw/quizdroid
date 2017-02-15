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

import java.util.List;

public class QuizActivity extends AppCompatActivity
        implements
            TopicOverviewFragment.OnFragmentInteractionListener,
            QuestionFragment.OnFragmentInteractionListener,
            AnswerFragment.OnFragmentInteractionListener{

    private int chosenTopicIndex;
    private int score;
    private int progress;
    private int yourAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //set the topic chosen for other fragments
        String chosenTopic = getIntent().getStringExtra("topic");
        Log.i("QUIZ ACTIVITY", chosenTopic);
        setTopicIndex(Integer.parseInt(chosenTopic));
        List<Topic> data = getData();
//        for (int i = 0; i < data.size(); i++) {
//            if (chosenTopic.equalsIgnoreCase(data.get(i).getTopic())) {
//                setTopicIndex(i);
//            }
//        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction frag = fragmentManager.beginTransaction();
        frag.replace(R.id.placeholder, new TopicOverviewFragment());
        frag.commit();

        Log.i("QUIZ ACTIVITY", "replaced fragment");

    }

    public void updateScore(int i) {
        score += i;
    }
    public int getScore() {
        return score;
    }
    public void updateProgress(int i) {
        progress+= i;
    }
    public int getProgress() {
        return progress;
    }
    public void setTopicIndex(int i) {
        chosenTopicIndex = i;
    }
    public int getTopicIndex() {
        return chosenTopicIndex;
    }

    public int yourAnswer() {
        return yourAnswer;
    }

    public void recordAnswer(int set) {
        yourAnswer = set;
    }

    public List<Topic> getData(){
        QuizApp app = (QuizApp)this.getApplication();
        List<Topic> data = app.getRepository().getListOfTopics();
        return data;
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}

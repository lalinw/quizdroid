package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TopicOverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);

        //displays description, # of questions
    }

    public void beginQuiz(View view) {
        //pass the topic and question info
        Intent intent = new Intent(this, QuestionActivity.class);
        startActivity(intent);
    }
}

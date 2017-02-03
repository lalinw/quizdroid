package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TopicOverviewActivity extends AppCompatActivity {

    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_overview);

        //receive extras
        extras = getIntent().getExtras();
        if (extras != null) {
            String message = extras.getString("topic");
            //displays description, # of questions
            TextView quizTopic = (TextView) findViewById(R.id.topic);
            quizTopic.setText(message);
        }

    }

    public void beginQuiz(View view) {
        //pass the topic and question info
        Intent intent = new Intent(this, QuizActivity.class);
        intent.putExtra("score", "0");
        startActivity(intent);
    }
}

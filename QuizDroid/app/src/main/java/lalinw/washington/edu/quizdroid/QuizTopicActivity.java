package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class QuizTopicActivity extends AppCompatActivity {

    private String[] topics = new String[]{
            "Math", "Physics", "Marvel Super Heroes", "Other stuff"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

            //ListView example code
            ListView listView = (ListView)findViewById(R.id.quizlist);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    topics);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new Intent(QuizTopicActivity.this, TopicOverviewActivity.class);
                startActivity(intent);
            }
        });

    }

    public void toQuizPage(View view) {
        Intent intent = new Intent(this, TopicOverviewActivity.class);
        startActivity(intent);
    }
}

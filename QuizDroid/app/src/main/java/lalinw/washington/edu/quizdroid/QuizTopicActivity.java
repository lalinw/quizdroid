package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class QuizTopicActivity extends AppCompatActivity {

    private List<String> topics = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        QuizApp app = (QuizApp)this.getApplication();
        List<Topic> data = app.getRepository().getListOfTopics();

        for (int i = 0; i < data.size(); i++) {
            topics.add(data.get(i).getTopic());
        }

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

                String entry = position + 1 + "";
                Log.i("TOPIC CHOSEN", entry);
                //pass in info about topic overview, amount of questions, questions, correct answers
                Intent intent = new Intent(QuizTopicActivity.this, QuizActivity.class);
                intent.putExtra("topic", entry);
                startActivity(intent);
                Log.i("MAIN ACTIVITY", "open quiz activity");
            }
        });



    }

}

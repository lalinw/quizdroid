package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class QuizTopicActivity extends AppCompatActivity {

    private String[] dwarves = new String[]{
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
                    dwarves);
            listView.setAdapter(adapter);
    }

    public void toQuizPage(View view) {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }
}

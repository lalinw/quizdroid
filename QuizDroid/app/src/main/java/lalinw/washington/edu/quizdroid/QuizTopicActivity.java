package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class QuizTopicActivity extends AppCompatActivity {

    private List<String> topics = new ArrayList<String>();
    private List<Integer> icons = new ArrayList<Integer>();
    private List<String> sDescr = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        QuizApp app = (QuizApp)this.getApplication();
        List<Topic> data = app.getRepository().getListOfTopics();

        for (int i = 0; i < data.size(); i++) {
            topics.add(data.get(i).getTopic());
            sDescr.add(data.get(i).getShortDescr());
            icons.add(R.drawable.default_icon);

        }

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i = 0; i < topics.size(); i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("topic", topics.get(i));
            hm.put("descr", sDescr.get(i) );
            hm.put("icon", Integer.toString(icons.get(i)));
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = { "topic","descr","icon" };

        // Ids of views in listview_layout
        int[] to = { R.id.topic ,R.id.descr, R.id.icon};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.template_list_topic, from, to);

        // Getting a reference to listview of main.xml layout file
        ListView listView = (ListView)findViewById(R.id.quizlist);

        // Setting the adapter to the listView
        listView.setAdapter(adapter);
//http://wptrafficanalyzer.in/blog/listview-with-images-and-text-using-simple-adapter-in-android/





            //ListView example code
//            ListView listView = (ListView)findViewById(R.id.quizlist);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                    android.R.layout.simple_list_item_1,
//                    android.R.id.text1,
//                    topics);
//            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String entry = position + "";
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

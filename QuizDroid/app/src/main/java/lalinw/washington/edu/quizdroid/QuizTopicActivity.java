package lalinw.washington.edu.quizdroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class QuizTopicActivity extends AppCompatActivity {

    private List<String> topics = new ArrayList<String>();
    private List<Integer> icons = new ArrayList<Integer>();
    private List<String> sDescr = new ArrayList<String>();
    private QuizApp app;
    private List<Topic> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        Log.e("STREAM", "quiztopicactivity RAN?");

//        http://tednewardsandbox.site44.com/questions.json

        app = (QuizApp) this.getApplication();
        data = app.getRepository().getListOfTopics();
        Log.e("current repo", data.toString());

        //dummy activity reload
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (data != app.getRepository().getListOfTopics()) {
                    recreate();
                }
            }
        }, 1500);

        for (int i = 0; i < data.size(); i++) {
            topics.add(data.get(i).getTopic());
            sDescr.add(data.get(i).getShortDescr());
            icons.add(R.drawable.default_icon);
        }

        // Each row in the list stores the topic, descr, icon
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < topics.size(); i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("topic", topics.get(i));
            hm.put("descr", sDescr.get(i));
            hm.put("icon", Integer.toString(icons.get(i)));
            aList.add(hm);
        }

        // Keys used in Hashmap
        String[] from = {"topic", "descr", "icon"};
        int[] to = {R.id.topic, R.id.descr, R.id.icon};
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.template_list_topic, from, to);
        ListView listView = (ListView) findViewById(R.id.quizlist);
        listView.setAdapter(adapter);
        //http://wptrafficanalyzer.in/blog/listview-with-images-and-text-using-simple-adapter-in-android/

//        //Simple ListView example code
//        ListView listView = (ListView)findViewById(R.id.quizlist);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1,
//                android.R.id.text1,
//                topics);
//        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.edit_url:
                showPreference();
//                AlertDialog.Builder alert = new AlertDialog.Builder(this);
//                LayoutInflater inflater = this.getLayoutInflater();
//                //this is what I did to added the layout to the alert dialog
//                View layout = inflater.inflate(R.layout.dialog_preference,null);
//                alert.setView(layout);
//                final EditText usernameInput=(EditText)layout.findViewById(R.id.edit_url);
//                final EditText passwordInput=(EditText)layout.findViewById(R.id.freq);

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showPreference() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_preference, null);
        dialogBuilder.setView(dialogView);

        final EditText edt_url = (EditText) dialogView.findViewById(R.id.edit_url);

        dialogBuilder.setTitle("Enter your source URL");
        dialogBuilder.setMessage("link to JSON only");
        dialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String newURL = edt_url.getText().toString();
//                TopicRepository tr = new TopicRepository();
//                tr.updateTopicRepository(newURL);
                data = app.getRepository(newURL).getListOfTopics();
                Log.e("current repo2", data.toString());
                recreate();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

}


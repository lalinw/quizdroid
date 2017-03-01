package lalinw.washington.edu.quizdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
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
    private String url;
    public static boolean dlFailed = false;


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static boolean isAirplaneModeOn(Context context) {
        return Settings.System.getInt(context.getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0) != 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);


        Log.e("STREAM", "quiztopicactivity RAN?");

//        http://tednewardsandbox.site44.com/questions.json
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        if(isNetworkAvailable()) {
            app = (QuizApp) this.getApplication();
            data = app.getRepository().getListOfTopics();
            Log.e("current repo", data.toString());

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (data != app.getRepository().getListOfTopics()) {
                        recreate();
                    }
                }
            }, 1500);

            Intent alarmIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
            alarmIntent.putExtra("url", url);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, SystemClock.elapsedRealtime(), 300000, pendingIntent);

            if (dlFailed) {
                builder.setTitle("Download has failed");
                builder.setMessage("Unable to download the source file");

                builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        dlFailed = false;
                        showPreference();
                    }
                }).setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
                android.app.AlertDialog dialog = builder.create();
                dialog.show();
            }
            populateList();
        } else {


            if (isAirplaneModeOn(this)) {
                builder.setTitle("ERROR!");
                builder.setMessage("Seems like you are in Airplane Mode");

                builder.setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
                    }
                }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(QuizTopicActivity.this, "The App cannot download the source JSON file.", Toast.LENGTH_SHORT).show();
                    }
                });
                android.app.AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                builder.setTitle("ERROR!");
                builder.setMessage("Seems like you have no connection.");
                android.app.AlertDialog dialog = builder.create();
                builder.setPositiveButton("Understood", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        populateList();
                    }
                });

                dialog.show();
                Toast.makeText(this, "Network Unavailable!", Toast.LENGTH_LONG).show();
            }
        }

    }

    public void populateList() {

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
                url = newURL;
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


package lalinw.washington.edu.quizdroid;

/**
 * Created by IreneW on 2017-02-14.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.ArrayList;

import static lalinw.washington.edu.quizdroid.QuizTopicActivity.dlFailed;

public class TopicRepository {

    private List<Topic> topicsList = new ArrayList<Topic>();
    private String url;

    //constructor
    public TopicRepository() {
        //topics.add(new Topic());
        url = "http://tednewardsandbox.site44.com/questions.json";
        new GetData().execute();
    }
    public TopicRepository(String userUrl) {
        //topics.add(new Topic());
        url = userUrl;
        new GetData().execute();
    }

    public List<Topic> getListOfTopics() {
        return topicsList;
    }

    public void setUrl(String newUrl) {
        url = newUrl;
    }




    private class GetData extends AsyncTask<Void, Void, Void> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
//            pDialog = new ProgressDialog(QuizTopicActivity.this);
//            pDialog.setMessage("Please wait...");
//            pDialog.setCancelable(false);
//            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(url);
            Log.e("JSON STUFF", "Response from url: " + jsonStr);


            if (jsonStr != null) {
                try {
                    // Getting JSON Array node
                    JSONArray quizSet = new JSONArray(jsonStr);
                    List<Topic> repo = new ArrayList<Topic>();

                    // looping through All topics
                    for (int i = 0; i < quizSet.length(); i++) {
                        JSONObject topic = quizSet.getJSONObject(i);
                        String topicText = topic.getString("title");
                        String descr = topic.getString("desc");

                        // Phone node is JSON Object
                        JSONArray q = topic.getJSONArray("questions");

                        List<Quiz> qs = new ArrayList<Quiz>();

                        for (int j = 0; j < q.length(); j++) {
                            JSONObject eachQ = q.getJSONObject(j);
                            String qText = eachQ.getString("text");
                            String correctAnswer = eachQ.getString("answer");
                            JSONArray choices = eachQ.getJSONArray("answers");
                            List<String> answersList = new ArrayList<String>();
                            answersList.add(choices.getString(0));
                            answersList.add(choices.getString(1));
                            answersList.add(choices.getString(2));
                            answersList.add(choices.getString(3));

                            Quiz newQuestion = new Quiz(qText, answersList, Integer.parseInt(correctAnswer)-1);
                            qs.add(newQuestion);
                        }

                        Topic newTopic = new Topic(topicText, descr, descr, qs);
                        repo.add(newTopic);
                        Log.v("STREAM", "do in background RAN?");
                    }
                    //set the repo to the topic repository
                    topicsList = repo;

                } catch (final JSONException e) {
                    Log.e("JSON", "Json parsing error: " + e.getMessage());


                }
            } else {
                Log.e("JSON", "Couldn't get json from server.");
                dlFailed = true;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            Log.e("POST EXECUTION", "BAAAM");
        }

    }

}

package lalinw.washington.edu.quizdroid;

/**
 * Created by IreneW on 2017-02-14.
 */
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class QuizApp extends android.app.Application implements java.io.Serializable {
    private TopicRepository instance = new TopicRepository();


    public QuizApp() {
        Log.i("SINGLETON", "onCreate()");

        // /initialize the topic repo
        List<Topic> data = instance.getListOfTopics();
        List<Quiz> qsqs = new ArrayList<Quiz>();
        List<String> choices = new ArrayList<String>();
        choices.add("aa");
        choices.add("bb");
        choices.add("cc");
        choices.add("dd");
        Quiz qs = new Quiz("what is?", choices, 2);
        qsqs.add(0, qs);
        data.add(0, new Topic("Math", "sdsdsdsd", "ldldldld", qsqs));
        data.add(1, new Topic("Physics", "sdsdsdsd", "ldldldld", qsqs));

    }

    public TopicRepository getRepository() {
        return instance;
        //http://munchpress.com/singleton-vs-applicationcontext/
    }
}

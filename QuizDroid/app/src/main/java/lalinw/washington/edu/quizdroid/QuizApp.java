package lalinw.washington.edu.quizdroid;

/**
 * Created by IreneW on 2017-02-14.
 */
import android.util.Log;
import java.util.List;


public class QuizApp extends android.app.Application implements java.io.Serializable {
    private TopicRepository instance = new TopicRepository();
//    private ProgressDialog pDialog;
//    private static String url = "http://tednewardsandbox.site44.com/questions.json";

    public QuizApp() {
        Log.i("SINGLETON", "onCreate()");
        // /initialize the topic repo
        List<Topic> data = instance.getListOfTopics();
    }

    public TopicRepository getRepository() {
        return instance;
        //http://munchpress.com/singleton-vs-applicationcontext/
    }

    public TopicRepository getRepository(String url) {
        instance = new TopicRepository(url);
        return instance;
        //http://munchpress.com/singleton-vs-applicationcontext/
    }



}

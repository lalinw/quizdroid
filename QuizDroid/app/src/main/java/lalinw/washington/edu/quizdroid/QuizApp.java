package lalinw.washington.edu.quizdroid;

/**
 * Created by IreneW on 2017-02-14.
 */
import android.util.Log;


public class QuizApp extends android.app.Application implements java.io.Serializable {
    private TopicRepository instance = new TopicRepository();

    public TopicRepository getInstance() {
        return instance;
    }

    public QuizApp() {
        Log.i("SINGLETON", "onCreate()");
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }

    public TopicRepository getRepository() {
        return instance;
        //http://munchpress.com/singleton-vs-applicationcontext/
    }
}

package lalinw.washington.edu.quizdroid;

/**
 * Created by IreneW on 2017-02-14.
 */
import android.util.Log;


public class QuizApp extends android.app.Application implements java.io.Serializable {
    private MyRepository instance = new MyRepository();

    public MyRepository getInstance() {
        return instance;
    }

    public QuizApp() {
        Log.i("SINGLETON", "onCreate()");
    }

//    @Override
//    public void onCreate() {
//        super.onCreate();
//    }

    public MyRepository getRepository() {
        return instance;
        //http://munchpress.com/singleton-vs-applicationcontext/
    }
}

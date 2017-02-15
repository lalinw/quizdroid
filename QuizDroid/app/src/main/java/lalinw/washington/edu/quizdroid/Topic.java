package lalinw.washington.edu.quizdroid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by IreneW on 2017-02-14.
 */

public class Topic {
    private String topic;
    private String shortDescr;
    private String longDescr;
    private List<Quiz> questions = new ArrayList<Quiz>();

    //temp constructor for hard code stuff
    public Topic(String t, String sd, String ld, List<Quiz> qs) {
//        List<Quiz> qs = new ArrayList<Quiz>();
//        qs.add(new Quiz());

        setTopic(t);
        setShortDescr(sd);
        setLongDescr(ld);
        setQuestions(qs);

//        setTopic("Other stuff");
//        setShortDescr("this is short descr");
//        setLongDescr("this is looooooooonger descr");
//        setQuestions(qs);
    }


    public String getTopic() {
        return topic;
    }
    public String getShortDescr() {
        return shortDescr;
    }
    public String getLongDescr() {
        return longDescr;
    }

    public List<Quiz> getQuestions() {
        return questions;
    }

    public void setTopic(String t) {
        topic = t;
    }
    public void setShortDescr(String sd) {
        shortDescr = sd;
    }
    public void setLongDescr(String ld) {
        longDescr = ld;
    }
    public void setQuestions(List<Quiz> quiz) {
        for (int i = 0; i < quiz.size(); i++) {
            questions.add(i, quiz.get(i));
        }
        //in case the implementation is different from what is passed in
    }

}

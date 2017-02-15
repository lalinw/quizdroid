package lalinw.washington.edu.quizdroid;

import java.util.Collection;
import java.util.List;

/**
 * Created by IreneW on 2017-02-14.
 */

public class Topic {
    private String topic;
    private String shortDescr;
    private String longDescr;
    private List<Quiz> questions;

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

    }
    public void setShortDescr(String sd) {

    }
    public void setLongDescr(String ld) {

    }
    public void setQuestions(List<Quiz> quiz) {

    }

}

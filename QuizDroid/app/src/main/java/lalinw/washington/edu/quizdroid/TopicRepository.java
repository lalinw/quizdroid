package lalinw.washington.edu.quizdroid;

/**
 * Created by IreneW on 2017-02-14.
 */

import java.util.List;
import java.util.ArrayList;

public class TopicRepository {

    private List<Topic> topicsList = new ArrayList<Topic>();

    //constructor
    public TopicRepository() {
        //topics.add(new Topic());
        //read file or sth and set the topic here???
    }

    public List<Topic> getListOfTopics() {
        return topicsList;
    }

    public void setAllTopics() {
        //re construct the repository
    }


}
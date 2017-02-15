package lalinw.washington.edu.quizdroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AnswerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AnswerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnswerFragment extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_answer, container, false);

        Button cont = (Button) v.findViewById(R.id.quiz_continue);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("FRAGMENT", "continue quiz");
//                //pass the topic and question info
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction frag = fragmentManager.beginTransaction();
//                frag.replace(R.id.placeholder, new AnswerFragment());
//                frag.commit();
                Log.i("ANSWER_Activity", "is last q");
                Intent intent = new Intent(getActivity(), QuizTopicActivity.class);
                startActivity(intent);
            }
        });


        //text predefined according to the answer key
        //is ans1 in this case

//        TextView correctAnswer = (TextView) v.findViewById(R.id.ans1);
//        correctAnswer.setBackgroundColor(Color.parseColor("#d2ff74"));
//        int scr = 0;
//        int yourA = ((QuizActivity)getActivity()).yourAnswer();
//        if (yourA == 1) {
//            //correct answer
//            scr = 1;
//        } else if (yourA == 2) {
//            TextView yourWrongAnswer = (TextView) v.findViewById(R.id.ans2);
//            yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
//        } else if (yourA == 3) {
//            TextView yourWrongAnswer = (TextView) v.findViewById(R.id.ans3);
//            yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
//        } else if (yourA == 4) {
//            //choice d
//            TextView yourWrongAnswer = (TextView) v.findViewById(R.id.ans4);
//            yourWrongAnswer.setBackgroundColor(Color.parseColor("#ffa69b"));
//        }


        List<Topic> data = ((QuizActivity)getActivity()).getData();
        int topicIndex = ((QuizActivity)getActivity()).getTopicIndex();
        Topic thisTopic = data.get(topicIndex);
        int qNum = ((QuizActivity)getActivity()).getProgress();
        Quiz thisQuestion = thisTopic.getQuestions().get(qNum);

        //set the view

        TextView question = (TextView) v.findViewById(R.id.question);
        question.setText("(" + thisTopic.getQuestions().get(qNum).getQuestion() + ")");
        TextView choiceA = (TextView) v.findViewById(R.id.ans1);
        choiceA.setText(thisQuestion.getAnswers().get(0));
        TextView choiceB = (TextView) v.findViewById(R.id.ans2);
        choiceB.setText(thisQuestion.getAnswers().get(1));
        TextView choiceC = (TextView) v.findViewById(R.id.ans3);
        choiceC.setText(thisQuestion.getAnswers().get(2));
        TextView choiceD = (TextView) v.findViewById(R.id.ans4);
        choiceD.setText(thisQuestion.getAnswers().get(3));

        int yourAnswer = ((QuizActivity)getActivity()).yourAnswer();
        int correctAnswer = thisQuestion.getCorrectAnswer();
        if(yourAnswer == correctAnswer) {
            ((QuizActivity)getActivity()).updateScore(1);
        }

        TextView score = (TextView) v.findViewById(R.id.score);
        score.setText("You have " + ((QuizActivity)getActivity()).getScore() + " out of " + thisTopic.getQuestions().size() + " correct.");

        return v;
    }

    //////////////////////////////////////////////////////////

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AnswerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnswerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnswerFragment newInstance(String param1, String param2) {
        AnswerFragment fragment = new AnswerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package lalinw.washington.edu.quizdroid;

import android.content.Context;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    View v;
    String input;
    String score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        v = inflater.inflate(R.layout.fragment_question, container, false);

        Button submit = (Button) v.findViewById(R.id.answer_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("FRAGMENT", "submit clicked");
                //pass the topic and question info
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction frag = fragmentManager.beginTransaction();
                frag.replace(R.id.placeholder, new AnswerFragment());
                frag.commit();
            }
        });


//        g.isSelected();
//        int selected = g.getCheckedRadioButtonId();
//        RadioButton b = (RadioButton) v.findViewById(selected);
//        b.getText();
//

        //boolean checked = ((RadioButton) v).isSelected();;
        Log.i("QUESTION_ACTIVITY", "check button");

        return v;
    }

//    public void onRadioButtonClicked(View view) {
//
//        Log.i("QUESTION_ACTIVITY", "clicked radio button");
//        // Is the button now checked?
//        RadioGroup g = (RadioGroup) v.findViewById(R.id.answer_choice);
//        int checked = g.getCheckedRadioButtonId();
//        if (checked == -1) {
//            Button submit = (Button) v.findViewById(R.id.answer_submit);
//            submit.setEnabled(true);
//            submit.setBackgroundColor(Color.parseColor("#FF9900"));
//        }
//
//        // Check which radio button was clicked
////        switch(view.getId()) {
////            case R.id.ans1:
////                if (checked)
////                    input = "a";
////                int scoreInt = Integer.parseInt(score);
////                scoreInt++;
////                score = "" + scoreInt;
////                break;
////            case R.id.ans2:
////                if (checked)
////                    input = "b";
////                break;
////            case R.id.ans3:
////                if (checked)
////                    input = "c";
////                break;
////            case R.id.ans4:
////                if (checked)
////                    input = "d";
////                break;
////        }
//    }
    ///////////////////////////////////////////////////////////

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(String param1, String param2) {
        QuestionFragment fragment = new QuestionFragment();
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

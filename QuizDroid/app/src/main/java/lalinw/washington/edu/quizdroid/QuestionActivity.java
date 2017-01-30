package lalinw.washington.edu.quizdroid;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }

    //https://developer.android.com/guide/topics/ui/controls/radiobutton.html

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        if (checked) {
            Button submit = (Button) findViewById(R.id.answer_submit);
            submit.setEnabled(true);
            submit.setBackgroundColor(Color.parseColor("#FF9900"));
        }

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.ans1:
                if (checked)
                    //choice 1
                    break;
            case R.id.ans2:
                if (checked)
                    // choice 2
                    break;
            case R.id.ans3:
                if (checked)
                    // choice 3
                    break;
            case R.id.ans4:
                if (checked)
                    // choice 4
                    break;
        }
    }

    public void submitAnswer(View view) {
        //displays questions and answers
        //keeps track of previous answers
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
    }
}

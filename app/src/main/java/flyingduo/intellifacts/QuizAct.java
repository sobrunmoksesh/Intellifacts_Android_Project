package flyingduo.intellifacts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizAct extends AppCompatActivity {

    private QuizDetails QD = new QuizDetails();

    private TextView display;
    private TextView scView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private String theAns;
    private int qnum = 0;
    private int counter = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        display = findViewById(R.id.textView1);
        scView = findViewById(R.id.scoreView);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);

        updateQuestion();


        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (btn1.getText() == theAns) {
                    score = score + 1;
                    updateScore(score);
                    updateQuestion();
                    Toast.makeText(QuizAct.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(QuizAct.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }

            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (btn2.getText() == theAns) {
                    score = score + 1;
                    updateScore(score);
                    updateQuestion();
                    Toast.makeText(QuizAct.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizAct.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }

            }

        });
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (btn3.getText() == theAns) {
                    score = score + 1;
                    updateScore(score);
                    updateQuestion();
                    Toast.makeText(QuizAct.this, "Correct", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QuizAct.this, "Wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }

            }

        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(startIntent);
                overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            }
        });

    }
    private void updateQuestion(){
        display.setText(QD.getQuestion(qnum));
        btn1.setText(QD.getChoice(qnum));
        btn2.setText(QD.getChoice1(qnum));
        btn3.setText(QD.getChoice2(qnum));
        theAns = QD.getAns(qnum);
        qnum++;
        counter++;
        if(counter==5){
            btn1.setEnabled(false);
            btn2.setEnabled(false);
            btn3.setEnabled(false);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
        }

    }

    private void updateScore(int points){
        scView.setText("Score: " + score + " /4");
    }

}

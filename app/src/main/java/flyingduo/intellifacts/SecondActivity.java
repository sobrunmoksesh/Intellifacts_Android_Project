package flyingduo.intellifacts;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity{

    //voice over
    private TextToSpeech myTTS;
    //Speech to text
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(startIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second);

        Button topic1 = findViewById(R.id.btn1);
        topic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent1 = new Intent(getApplicationContext(),Topic1Act.class);
                startActivity(startIntent1);
            }
        });

        Button topic2 = findViewById(R.id.btn2);
        topic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent2 = new Intent(getApplicationContext(),Topic2Act.class);
                startActivity(startIntent2);
            }
        });

        Button topic3 = findViewById(R.id.btn4);
        topic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent3 = new Intent(getApplicationContext(),Topic3Act.class);
                startActivity(startIntent3);
            }
        });

        Button topic4 = findViewById(R.id.btn3);
        topic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent4 = new Intent(getApplicationContext(),Topic4Act.class);
                startActivity(startIntent4);
            }
        });

        Button quizBtn = findViewById(R.id.btn5);
        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent5 = new Intent(getApplicationContext(),QuizAct.class);
                startActivity(startIntent5);
            }
        });

        Button voice1 = findViewById(R.id.voice1);
        voice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Your device does not support this functionality!",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });
        //voice over
        initializeTextToSpeech();

    }


    //voice over
    private void initializeTextToSpeech() {
        myTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (myTTS.getEngines().size() == 0) {
                    Toast.makeText(SecondActivity.this, "There is no TTS engine in your device", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    myTTS.setLanguage(Locale.US);
                    speak("Choose between art, cuisine, place, history and quiz");

                }
            }
        });
    }

    private void speak(String message) {
        if (Build.VERSION.SDK_INT >= 21) {
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            myTTS.speak(message, TextToSpeech.QUEUE_FLUSH, null);

        }
    }
    //eof voice over

    public void onClick(View v) {

        switch (v.getId()) {
            //Edited for Speech to text
            //case R.id.PlanetId : i = new Intent(this,Planet.class);startActivity(i); break;
            case R.id.btn1:
                open(1);
                break;
            case R.id.btn2:
                open(3);
                break;
            case R.id.btn4:
                open(4);
                break;
            case R.id.btn3:
                open(5);
                break;
            case R.id.btn5:
                open(5);
                break;
            default:
            break;
        }
    }

    //Speech to text
    private void open(int count) {

        Intent i;

        if (count == 1) {
            i = new Intent(this, Topic1Act.class);
            startActivity(i);
        } else if (count == 2) {
            i = new Intent(this, Topic2Act.class);
            startActivity(i);
        } else if (count == 3) {
            i = new Intent(this, Topic3Act.class);
            startActivity(i);
        } else if (count == 4) {
            i = new Intent(this, Topic4Act.class);
            startActivity(i);
        } else if (count == 5) {
            i = new Intent(this, QuizAct.class);
            startActivity(i);
        }
        else if (count == 6){
            i = new Intent(this, MainActivity.class);
            startActivity(i);
        }

    }

    //voice over
    protected void onPause() {
        super.onPause();
        myTTS.shutdown();
    }

    //Speech to text
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = new ArrayList<String>();
                    result.clear();
                    result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String change = result.get(0).toLowerCase();

                    //change = change.replace("cuisine", "cuisines").toLowerCase();


                    if (change.contains("art")) {
                        open(1);
                    }
                    else if (change.contains("cuisine")) {
                        open(2);
                    } else if (change.contains("place")) {
                        open(3);
                    } else if (change.contains("history")) {
                        open(4);
                    }
                    else if (change.contains("quiz")) {
                        open(5);
                    } else if (change.contains("home")) {
                        open(6);
                    }

                }
                break;
            }
        }

    }
}

package flyingduo.intellifacts;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    //voice over
    private TextToSpeech myTTS;
    //Speech to text
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    public void onBackPressed(){
            moveTaskToBack(true);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        Button discover = findViewById(R.id.btnHome);
        discover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(startIntent);
            }
        });
        Button voice = findViewById(R.id.voice);
        voice.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(MainActivity.this, "There is no TTS engine in your device", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    myTTS.setLanguage(Locale.US);
                    speak("Hello, How may i help you today? Say start now to begin");

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
            case R.id.btnHome:
                open(1);
                break;
            default:
                break;
        }
    }

    //Speech to text
    private void open(int count) {

        Intent i;

        if (count == 1) {
            i = new Intent(this, SecondActivity.class);
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

                    //change = change.replace("start", "starts").toLowerCase();


                    if (change.contains("start now")) {
                        open(1);
                    }

                }
                break;
            }
        }

    }
}

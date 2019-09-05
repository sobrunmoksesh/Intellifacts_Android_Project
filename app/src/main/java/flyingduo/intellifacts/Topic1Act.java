package flyingduo.intellifacts;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import  android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.speech.tts.TextToSpeech;
import  android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Topic1Act extends AppCompatActivity implements View.OnClickListener {

    String text;
    int result;
    TextToSpeech toSpeech;
    TextView editText;

    private Button btn;
    private Button btn2;

    private ViewPager viewpager;
    private LinearLayout liner;
    private SlideAdapter myadapter;

    private TextView[] mdots;
    private Button next,back;

    private int mCureentPage;

    //Speech to text
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_topic1);
        viewpager=(ViewPager)findViewById(R.id.viewpager);
        liner=(LinearLayout)findViewById(R.id.dots);

        next=(Button)findViewById(R.id.nextBtn);
        back=(Button)findViewById(R.id.backBtn);

        myadapter=new SlideAdapter(this);
        viewpager.setAdapter(myadapter);
        adddots(0);

        viewpager.addOnPageChangeListener(viewlistener);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(mCureentPage+1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager.setCurrentItem(mCureentPage-1);
            }
        });

        //TTs
        editText = (TextView) findViewById(R.id.slidedescription);
        btn = (Button) findViewById(R.id.speak);
        btn2= (Button) findViewById(R.id.stop) ;

        toSpeech = new TextToSpeech(Topic1Act.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result = toSpeech.setLanguage(Locale.UK);
                } else {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });//end for speech

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

        Button quizBtn = findViewById(R.id.btn5);
        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent5 = new Intent(getApplicationContext(),QuizAct.class);
                startActivity(startIntent5);
            }
        });

    }



    public void adddots(int i){

        mdots=new TextView[11];
        liner.removeAllViews();

        for (int x=0;x<mdots.length;x++){

            mdots[x]=new TextView(this);
            mdots[x].setText(Html.fromHtml("&#8226;"));
            mdots[x].setTextSize(35);
            mdots[x].setTextColor(getResources().getColor(R.color.qui));

            liner.addView(mdots[x]);
        }
        if (mdots.length>0){

            mdots[i].setTextColor(getResources().getColor(R.color.fact));
            if (toSpeech != null) {
                toSpeech.stop();
            }

        }

    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            adddots(position);
            mCureentPage = position;

            if (position==0){
                next.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);

                next.setText("NEXT");
            }
            else if(position==mdots.length-1){

                next.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);

                next.setText("");
                back.setText("BACK");

            }
            else {
                next.setEnabled(true);
                back.setEnabled(true );
                back.setVisibility(View.VISIBLE);

                next.setText("NEXT");
                back.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toSpeech != null) {
            toSpeech.stop();
            toSpeech.shutdown();
        }
    }

    public int[] list_description={

            R.string.t1intro,
            R.string.fact1detailt1,
            R.string.fact2detailt1,
            R.string.fact3detailt1,
            R.string.fact4detailt1,
            R.string.fact5detailt1,
            R.string.fact6detailt1,
            R.string.fact7detailt1,
            R.string.fact8detailt1,
            R.string.acting_is_an_art_that_has_started_to_bloom_in_mauritius_many_shows_are_being_produced_nowadays_such_as_komiko_which_is_one_of_the_best_shows_made_in_mauritius_many_film_directors_are_choosing_mauritius_for_their_movie_production_and_due_to_this_mauritian_people_are_getting_the_opportunity_to_participate,
            R.string.lastly_painting_is_one_of_mauritius_local_art_speciality_as_many_of_our_ancestors_history_and_even_the_history_of_mauritius_have_been_brought_to_the_world_through_paintings_many_artists_are_still_practising_this_art_as_it_is_something_that_only_the_artist_can_give_form_to_many_tourists_love_to_buy_paintings_drawn_by_mauritians_as_a_souvenir_of_their_visit_to_mauritius_because_of_its_originality_and_more_importantly_because_of_the_beauty_and_historical_backgrounds
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.speak:
        if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
        } else {
            text = getString(list_description[mCureentPage]);
            toSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
        break;
            case R.id.stop:
                if (toSpeech != null) {
                    toSpeech.stop();
                }
                break;

            case R.id.nextBtn:
                if (toSpeech != null) {
                    toSpeech.stop();
                }
                break;

            case R.id.backBtn:
                if (toSpeech != null) {
                    toSpeech.stop();
                }
                break;
         }

    }

}



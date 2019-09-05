package flyingduo.intellifacts;

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
import java.util.Locale;

public class Topic2Act extends AppCompatActivity implements View.OnClickListener{

    String text1;
    int result1;
    TextToSpeech toSpeech1;
    TextView editText1;

    private Button btn1;
    private Button btn21;

    private ViewPager viewpager1;
    private LinearLayout liner1;
    private SlideAdapter1 myadapter1;

    private TextView[] mdots1;
    private Button next1,back1;

    private int mCureentPage1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_topic2);

        viewpager1=(ViewPager)findViewById(R.id.viewpager1);
        liner1=(LinearLayout)findViewById(R.id.dots1);

        next1=(Button)findViewById(R.id.nextBtn);
        back1=(Button)findViewById(R.id.backBtn);

        myadapter1=new SlideAdapter1 (this);
        viewpager1.setAdapter(myadapter1);
        adddots(0);

        viewpager1.addOnPageChangeListener(viewlistener);

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager1.setCurrentItem(mCureentPage1+1);
            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager1.setCurrentItem(mCureentPage1-1);
            }
        });

        editText1 = (TextView) findViewById(R.id.slidedescription1);
        btn1 = (Button) findViewById(R.id.speak1);
        btn21= (Button) findViewById(R.id.stop1) ;

        toSpeech1 = new TextToSpeech(Topic2Act.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result1 = toSpeech1.setLanguage(Locale.UK);
                } else {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });//end for speech

        btn1.setOnClickListener(this);
        btn21.setOnClickListener(this);
    }

    public void adddots(int i){

        mdots1=new TextView[11];
        liner1.removeAllViews();

        for (int x=0;x<mdots1.length;x++){

            mdots1[x]=new TextView(this);
            mdots1[x].setText(Html.fromHtml("&#8226;"));
            mdots1[x].setTextSize(35);
            mdots1[x].setTextColor(getResources().getColor(R.color.qui));

            liner1.addView(mdots1[x]);
        }
        if (mdots1.length>0){

            mdots1[i].setTextColor(getResources().getColor(R.color.fact));
            if (toSpeech1 != null) {
                toSpeech1.stop();
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
            mCureentPage1 = position;

            if (position==0){
                next1.setEnabled(true);
                back1.setEnabled(false);
                back1.setVisibility(View.INVISIBLE);

                next1.setText("NEXT");
            }
            else if(position==mdots1.length-1){

                next1.setEnabled(true);
                back1.setEnabled(true);
                back1.setVisibility(View.VISIBLE);

                next1.setText("");
                back1.setText("BACK");

            }
            else {
                next1.setEnabled(true);
                back1.setEnabled(true );
                back1.setVisibility(View.VISIBLE);

                next1.setText("NEXT");
                back1.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toSpeech1 != null) {
            toSpeech1.stop();
            toSpeech1.shutdown();
        }
    }

    public int[] list_description1={

            R.string.history2,
            R.string.fact1detailt2,
            R.string.fact2detailt2,
            R.string.fact3detailt2,
            R.string.fact4detailt2,
            R.string.fact5detailt2,
            R.string.fact6detailt2,
            R.string.fact7detailt2,
            R.string.fact8detailt2,
            R.string.fact9detailt2,
            R.string.fact10detailt2
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.speak1:
                if (result1 == TextToSpeech.LANG_MISSING_DATA || result1 == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                } else {
                    text1 = getString(list_description1[mCureentPage1]);
                    toSpeech1.speak(text1, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            case R.id.stop1:
                if (toSpeech1 != null) {
                    toSpeech1.stop();
                }
                break;

            case R.id.nextBtn:
                if (toSpeech1 != null) {
                    toSpeech1.stop();
                }
                break;

            case R.id.backBtn:
                if (toSpeech1 != null) {
                    toSpeech1.stop();
                }
                break;
        }
    }
}
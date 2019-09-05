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


public class Topic4Act extends AppCompatActivity implements View.OnClickListener{

    String text3;
    int result3;
    TextToSpeech toSpeech3;
    TextView editText3;

    private Button btn3;
    private Button btn23;

    private ViewPager viewpager3;
    private LinearLayout liner3;
    private SlideAdapter3 myadapter3;

    private TextView[] mdots3;
    private Button next3, back3;

    private int mCureentPage3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_topic4);

        viewpager3 = (ViewPager) findViewById(R.id.viewpager3);
        liner3 = (LinearLayout) findViewById(R.id.dots3);

        next3 = (Button) findViewById(R.id.nextBtn);
        back3 = (Button) findViewById(R.id.backBtn);

        myadapter3 = new SlideAdapter3(this);
        viewpager3.setAdapter(myadapter3);
        adddots(0);

        viewpager3.addOnPageChangeListener(viewlistener);

        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager3.setCurrentItem(mCureentPage3 + 1);
            }
        });

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager3.setCurrentItem(mCureentPage3 - 1);
            }
        });

        editText3 = (TextView) findViewById(R.id.slidedescription3);
        btn3 = (Button) findViewById(R.id.speak3);
        btn23= (Button) findViewById(R.id.stop3) ;

        toSpeech3 = new TextToSpeech(Topic4Act.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result3 = toSpeech3.setLanguage(Locale.UK);
                } else {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });//end for speech

        btn3.setOnClickListener(this);
        btn23.setOnClickListener(this);
    }

    public void adddots(int i) {

        mdots3 = new TextView[11];
        liner3.removeAllViews();

        for (int x = 0; x < mdots3.length; x++) {

            mdots3[x] = new TextView(this);
            mdots3[x].setText(Html.fromHtml("&#8226;"));
            mdots3[x].setTextSize(35);
            mdots3[x].setTextColor(getResources().getColor(R.color.qui));

            liner3.addView(mdots3[x]);
        }
        if (mdots3.length > 0) {

            mdots3[i].setTextColor(getResources().getColor(R.color.fact));
            if (toSpeech3 != null) {
                toSpeech3.stop();
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
            mCureentPage3 = position;

            if (position == 0) {
                next3.setEnabled(true);
                back3.setEnabled(false);
                back3.setVisibility(View.INVISIBLE);

                next3.setText("NEXT");
            } else if (position == mdots3.length - 1) {

                next3.setEnabled(true);
                back3.setEnabled(true);
                back3.setVisibility(View.VISIBLE);

                next3.setText("");
                back3.setText("BACK");

            } else {
                next3.setEnabled(true);
                back3.setEnabled(true);
                back3.setVisibility(View.VISIBLE);

                next3.setText("NEXT");
                back3.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toSpeech3 != null) {
            toSpeech3.stop();
            toSpeech3.shutdown();
        }
    }

    public int[] list_description3 = {

            R.string.introhistory,
            R.string.fact1detail,
            R.string.fact2detail,
            R.string.fact3detail,
            R.string.fact4detail,
            R.string.fact5detail,
            R.string.fact6detail,
            R.string.fact7detail,
            R.string.fact8detail,
            R.string.fact9detail,
            R.string.fact10detail
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.speak3:
                if (result3 == TextToSpeech.LANG_MISSING_DATA || result3 == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                } else {
                    text3 = getString(list_description3[mCureentPage3]);
                    toSpeech3.speak(text3, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            case R.id.stop3:
                if (toSpeech3 != null) {
                    toSpeech3.stop();
                }
                break;

            case R.id.nextBtn:
                if (toSpeech3 != null) {
                    toSpeech3.stop();
                }
                break;

            case R.id.backBtn:
                if (toSpeech3 != null) {
                    toSpeech3.stop();
                }
                break;
        }
    }
}

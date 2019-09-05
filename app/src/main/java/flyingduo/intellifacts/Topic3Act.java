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


public class Topic3Act extends AppCompatActivity implements View.OnClickListener{

    String text2;
    int result2;
    TextToSpeech toSpeech2;
    TextView editText2;

    private Button btn2;
    private Button btn22;

    private ViewPager viewpager2;
    private LinearLayout liner2;
    private SlideAdapter2 myadapter2;

    private TextView[] mdots2;
    private Button next2,back2;

    private int mCureentPage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_topic3);

        viewpager2=(ViewPager)findViewById(R.id.viewpager2);
        liner2=(LinearLayout)findViewById(R.id.dots2);

        next2=(Button)findViewById(R.id.nextBtn);
        back2=(Button)findViewById(R.id.backBtn);

        myadapter2=new SlideAdapter2 (this);
        viewpager2.setAdapter(myadapter2);
        adddots(0);

        viewpager2.addOnPageChangeListener(viewlistener);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager2.setCurrentItem(mCureentPage2+1);
            }
        });

        back2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewpager2.setCurrentItem(mCureentPage2-1);
            }
        });

        editText2 = (TextView) findViewById(R.id.slidedescription2);
        btn2 = (Button) findViewById(R.id.speak2);
        btn22= (Button) findViewById(R.id.stop2) ;

        toSpeech2 = new TextToSpeech(Topic3Act.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    result2 = toSpeech2.setLanguage(Locale.UK);
                } else {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                }
            }
        });//end for speech

        btn2.setOnClickListener(this);
        btn22.setOnClickListener(this);
    }

    public void adddots(int i){

        mdots2=new TextView[11];
        liner2.removeAllViews();

        for (int x=0;x<mdots2.length;x++){

            mdots2[x]=new TextView(this);
            mdots2[x].setText(Html.fromHtml("&#8226;"));
            mdots2[x].setTextSize(35);
            mdots2[x].setTextColor(getResources().getColor(R.color.qui));

            liner2.addView(mdots2[x]);
        }
        if (mdots2.length>0){

            mdots2[i].setTextColor(getResources().getColor(R.color.fact));
            if (toSpeech2 != null) {
                toSpeech2.stop();
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
            mCureentPage2 = position;

            if (position==0){
                next2.setEnabled(true);
                back2.setEnabled(false);
                back2.setVisibility(View.INVISIBLE);

                next2.setText("NEXT");
            }
            else if(position==mdots2.length-1){

                next2.setEnabled(true);
                back2.setEnabled(true);
                back2.setVisibility(View.VISIBLE);

                next2.setText("");
                back2.setText("BACK");

            }
            else {
                next2.setEnabled(true);
                back2.setEnabled(true );
                back2.setVisibility(View.VISIBLE);

                next2.setText("NEXT");
                back2.setText("BACK");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (toSpeech2 != null) {
            toSpeech2.stop();
            toSpeech2.shutdown();
        }
    }

    public int[] list_description2={

            R.string.introt3,
            R.string.fact1detailt3,
            R.string.fact2detailt3,
            R.string.fact3detailt3,
            R.string.fact4detailt3,
            R.string.fact5detailt3,
            R.string.fact6detailt3,
            R.string.fact7detailt3,
            R.string.fact8detailt3,
            R.string.fact9detailt3,
            R.string.fact10detailt3
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.speak2:
                if (result2 == TextToSpeech.LANG_MISSING_DATA || result2 == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(getApplicationContext(), "Feature not supported in your device", Toast.LENGTH_SHORT).show();
                } else {
                    text2 = getString(list_description2[mCureentPage2]);
                    toSpeech2.speak(text2, TextToSpeech.QUEUE_FLUSH, null);
                }
                break;
            case R.id.stop2:
                if (toSpeech2 != null) {
                    toSpeech2.stop();
                }
                break;

            case R.id.nextBtn:
                if (toSpeech2 != null) {
                    toSpeech2.stop();
                }
                break;

            case R.id.backBtn:
                if (toSpeech2 != null) {
                    toSpeech2.stop();
                }
                break;
        }
    }
}

package flyingduo.intellifacts;


import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SlideAdapter3 extends PagerAdapter {

    Context context3;
    LayoutInflater inflater3;

    public SlideAdapter3(Context context3){
        this.context3=context3;
    }

    //Array
    public int[] list_images3={


            R.drawable.mauritius,
            R.drawable.poship,
            R.drawable.vannassau,
            R.drawable.flag,
            R.drawable.mahe,
            R.drawable.war,
            R.drawable.aapravasi_ghat,
            R.drawable.indepen,
            R.drawable.mflagi,
            R.drawable.mflagi,
            R.drawable.dodo
    };

    public int[] list_title3={

            R.string.mauritian_history,
            R.string.poshipn,
            R.string.prince_maurice_van_nassau,
            R.string.stateflag,
            R.string.mahe_de_labourdonnais_governor_of_mauritius_1734_1746,
            R.string.warinfo,
            R.string.fact6i,
            R.string.father_of_the_nation_sir_seewoosagur_ramgoolam,
            R.string.mauritian_flag1,
            R.string.mauritian_flag1,
            R.string.the_dodo
    };

    public int[] list_description3={

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
    public int[] list_color3={

            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY,
            Color.LTGRAY
    };

    @Override
    public int getCount() {
        return list_title3.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view==(LinearLayout)obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container3, int position) {

        inflater3 = (LayoutInflater)context3.getSystemService(context3.LAYOUT_INFLATER_SERVICE);
        View view = inflater3.inflate(R.layout.slide3,container3,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.slidelinearlayout3);
        ImageView img = (ImageView)view.findViewById(R.id.slideimg3);
        TextView txt1 = (TextView) view.findViewById(R.id.slidetitle3);
        TextView txt2 = (TextView) view.findViewById(R.id.slidedescription3);


        img.setImageResource(list_images3[position]);
        txt1.setText(list_title3[position]);
        txt2.setText(list_description3[position]);
        linearLayout.setBackgroundColor(list_color3[position]);

        container3.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container3, int position, Object object3) {
        container3.removeView((LinearLayout)object3);
    }
}
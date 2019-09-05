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


public class SlideAdapter2 extends PagerAdapter {

    Context context2;
    LayoutInflater inflater2;

    public SlideAdapter2(Context context2){
        this.context2=context2;
    }

    //Array
    public int[] list_images2={


            R.drawable.places,
            R.drawable.lemorne,
            R.drawable.blackriver,
            R.drawable.chamarel,
            R.drawable.insectarium,
            R.drawable.pamplemouse,
            R.drawable.chateaumauritius,
            R.drawable.ileauxcerfs,
            R.drawable.leshoffleur,
            R.drawable.bassin,
            R.drawable.bh
    };

    public int[] list_title2={

            R.string.must_visit_places,
            R.string.le_morne_brabant,
            R.string.black_river_gorges_national_park,
            R.string.chamarel,
            R.string.insectarium_crocodile_park,
            R.string.pamplemousses_botanical_garden,
            R.string.ch_teau_de_labourdonnais,
            R.string.ile_aux_cerfs,
            R.string.gris_gris_beach_la_roche_qui_pleure,
            R.string.grand_bassin_sacred_lake,
            R.string.champ_de_mars_racecourse
    };

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
    public int[] list_color2={

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
        return list_title2.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view==(LinearLayout)obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container2, int position) {

        inflater2 = (LayoutInflater)context2.getSystemService(context2.LAYOUT_INFLATER_SERVICE);
        View view = inflater2.inflate(R.layout.slide2,container2,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.slidelinearlayout2);
        ImageView img = (ImageView)view.findViewById(R.id.slideimg2);
        TextView txt1 = (TextView) view.findViewById(R.id.slidetitle2);
        TextView txt2 = (TextView) view.findViewById(R.id.slidedescription2);


        img.setImageResource(list_images2[position]);
        txt1.setText(list_title2[position]);
        txt2.setText(list_description2[position]);
        linearLayout.setBackgroundColor(list_color2[position]);

        container2.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container2, int position, Object object2) {
        container2.removeView((LinearLayout)object2);
    }
}
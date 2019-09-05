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


public class SlideAdapter1 extends PagerAdapter {

    Context context1;
    LayoutInflater inflater1;

    public SlideAdapter1 (Context context1){
        this.context1=context1;
    }

    //Array
    public int[] list_images1={


            R.drawable.food,
            R.drawable.dhollpuri,
            R.drawable.seafood,
            R.drawable.alouda,
            R.drawable.minefrit,
            R.drawable.phoenix,
            R.drawable.coconut,
            R.drawable.poisson,
            R.drawable.boischeri,
            R.drawable.patate,
            R.drawable.palmheart
    };

    public int[] list_title1={

            R.string.mauritian_cuisines,
            R.string.dhall_puri,
            R.string.lobster_gram_seafood_baked,
            R.string.alouda,
            R.string.mine_frite,
            R.string.pheonix_beer,
            R.string.green_creamy_coconuts,
            R.string.vindaye_poisson,
            R.string.bois_cheri_tea_plantations,
            R.string.gateau_de_patate_douce_sweet_potato_cakes,
            R.string.palm_heart_salad
    };

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
    public int[] list_color1={

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
        return list_title1.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view==(LinearLayout)obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container1, int position) {

        inflater1 = (LayoutInflater)context1.getSystemService(context1.LAYOUT_INFLATER_SERVICE);
        View view = inflater1.inflate(R.layout.slide1,container1,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.slidelinearlayout1);
        ImageView img = (ImageView)view.findViewById(R.id.slideimg1);
        TextView txt1 = (TextView) view.findViewById(R.id.slidetitle1);
        TextView txt2 = (TextView) view.findViewById(R.id.slidedescription1);


        img.setImageResource(list_images1[position]);
        txt1.setText(list_title1[position]);
        txt2.setText(list_description1[position]);
        linearLayout.setBackgroundColor(list_color1[position]);

        container1.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container1, int position, Object object1) {
        container1.removeView((LinearLayout)object1);
    }
}
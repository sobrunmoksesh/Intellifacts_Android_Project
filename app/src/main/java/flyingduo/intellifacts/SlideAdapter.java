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




public class SlideAdapter extends PagerAdapter {

    Context context;
    LayoutInflater inflater;

    public SlideAdapter(Context context){
        this.context=context;
    }

    //Array
    public int[] list_images={


            R.drawable.culture,
            R.drawable.sega,
            R.drawable.tante,
            R.drawable.ship,
            R.drawable.rum,
            R.drawable.ravanne,
            R.drawable.kaya,
            R.drawable.sculpture,
            R.drawable.amazing,
            R.drawable.drama,
            R.drawable.paint
    };

    public int[] list_title={

            R.string.local_culture,
            R.string.sega,
            R.string.basketwork_tente_vacoas,
            R.string.handmade_warship_model,
            R.string.rhum_made_in_chamarel_distillery,
            R.string.ravanne,
            R.string.joseph_reginald_topize_aka_kaya,
            R.string.wood_sculptured_at_le_caudan_waterfront,
            R.string.briyani,
            R.string.a_comedy_show_called_komiko,
            R.string.a_painting_of_le_morne_brabant
    };

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
    public int[] list_color={

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
        return list_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view==(LinearLayout)obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);

        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.slidelinearlayout);
        ImageView img = (ImageView)view.findViewById(R.id.slideimg);
        TextView txt1 = (TextView) view.findViewById(R.id.slidetitle);
        TextView txt2 = (TextView) view.findViewById(R.id.slidedescription);


        img.setImageResource(list_images[position]);
        txt1.setText(list_title[position]);
        txt2.setText(list_description[position]);
        linearLayout.setBackgroundColor(list_color[position]);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}
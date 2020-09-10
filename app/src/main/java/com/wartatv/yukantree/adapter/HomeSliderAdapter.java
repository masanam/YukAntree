package com.wartatv.yukantree.adapter;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.model.Slider;

import java.util.List;

/**
 * Created by .
 * www.wartatv.com
 */
public class HomeSliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] images;
    private List<Slider> sliderImg;

    public HomeSliderAdapter(Context context, List<Slider> sliderImg) {
        this.sliderImg = sliderImg;
        this.context = context;
    }

    public HomeSliderAdapter(Context context, Integer[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
//        return images.length;
        return sliderImg.size();

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final Slider slider = sliderImg.get(position);

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_home_slider, null);
        ImageView imageView = view.findViewById(R.id.imageView);
//        imageView.setImageResource(images[position]);

        Picasso.get().load(slider.getImage()).error(R.drawable.no_image).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
//                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                Log.d("Error : ", e.getMessage());
            }
        });

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}
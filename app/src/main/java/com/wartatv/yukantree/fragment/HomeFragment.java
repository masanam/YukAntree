package com.wartatv.yukantree.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.wartatv.yukantree.R;
import com.wartatv.yukantree.activity.MainActivity;
import com.wartatv.yukantree.adapter.CategoryAdapter;
import com.wartatv.yukantree.adapter.HomeSliderAdapter;
import com.wartatv.yukantree.adapter.NewProductAdapter;
import com.wartatv.yukantree.adapter.PopularProductAdapter;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.helper.Data;
import com.wartatv.yukantree.model.Category;
import com.wartatv.yukantree.model.Loket;
import com.wartatv.yukantree.model.ModelCategory;
import com.wartatv.yukantree.model.ModelLoket;
import com.wartatv.yukantree.model.ModelProduct;
import com.wartatv.yukantree.model.ModelSlider;
import com.wartatv.yukantree.model.Product;
import com.wartatv.yukantree.model.Slider;
import com.wartatv.yukantree.util.Preferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by .
 * www.wartatv.com
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int page_position = 0;
    Data data;
    private int dotscount;
    private ImageView[] dots;
    private RecyclerView recyclerView, nRecyclerView, pRecyclerView;
    private CategoryAdapter mAdapter;
    private NewProductAdapter nAdapter;
    private PopularProductAdapter pAdapter;
    String[] id;
    String[] title;
    String[] image;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.category_rv);
        pRecyclerView = view.findViewById(R.id.popular_product_rv);
        nRecyclerView = view.findViewById(R.id.new_product_rv);

        getCategoryList();
        getNewList();
        getPopularList();
        getSliderList();

        TextView dateTimeDisplay = view.findViewById(R.id.txt_date_display);
        calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMMM yyyy");
        date = df.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        TextView nama = view.findViewById(R.id.fullName);
        nama.setText(Preferences.getLoggedInUser(getActivity()));

        timer = new Timer();
        viewPager = view.findViewById(R.id.viewPager);

        sliderDotspanel = view.findViewById(R.id.SliderDots);

//        HomeSliderAdapter viewPagerAdapter = new HomeSliderAdapter(getContext(), images);
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelSlider> call = apiService.getSlider();

        call.enqueue(new Callback<ModelSlider>() {
            @Override
            public void onResponse(Call<ModelSlider> call, Response<ModelSlider> response) {
                if (response.isSuccessful()){
                    ModelSlider databody = response.body();
                    List<Slider> sliderImg = databody.getResult();
                    Log.i("debug", "onResponse: Slider" + sliderImg.size());

                    HomeSliderAdapter viewPagerAdapter = new HomeSliderAdapter(getContext(), sliderImg);

                    viewPager.setAdapter(viewPagerAdapter);

                    dotscount = viewPagerAdapter.getCount();
                    dots = new ImageView[dotscount];

                    for (int i = 0; i < dotscount; i++) {

                        dots[i] = new ImageView(getContext());
                        dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));

                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                        params.setMargins(8, 0, 8, 0);

                        sliderDotspanel.addView(dots[i], params);

                    }

                    dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                            for (int i = 0; i < dotscount; i++) {
                                dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                            }

                            dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));
                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });
                    scheduleSlider();
                }else{
                    Log.i("debug", "onResponse: Slider-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelSlider> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
            }

        });




        return view;
    }


    public void getCategoryList(){
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelCategory> call = apiService.getCategory();

        call.enqueue(new Callback<ModelCategory>() {
            @Override
            public void onResponse(Call<ModelCategory> call, Response<ModelCategory> response) {
                if (response.isSuccessful()){
                    ModelCategory databody = response.body();
                    List<Category> categoryList = databody.getResult();
                    mAdapter = new CategoryAdapter(categoryList, getContext(), "Home");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);                }else{
                    Log.i("debug", "onResponse: Category-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelCategory> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }

    public void getSliderList(){
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelSlider> call = apiService.getSlider();

        call.enqueue(new Callback<ModelSlider>() {
            @Override
            public void onResponse(Call<ModelSlider> call, Response<ModelSlider> response) {
                if (response.isSuccessful()){
                    ModelSlider databody = response.body();
                    List<Slider> sliderImg = databody.getResult();

                }else{
                    Log.i("debug", "onResponse: Category-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelSlider> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());
            }

        });
    }

    public void getNewList(){
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelProduct> call = apiService.getHost();

        call.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {
                if (response.isSuccessful()){
                    ModelProduct databody = response.body();
                    List<Product> newList = databody.getResult();
                    nAdapter = new NewProductAdapter(newList, getContext(), "Home");
                    RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    nRecyclerView.setLayoutManager(nLayoutManager);
                    nRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    nRecyclerView.setAdapter(nAdapter);
                }else{
                    Log.i("debug", "onResponse: Category-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }


    public void getPopularList(){
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelLoket> call = apiService.getLoket();

        call.enqueue(new Callback<ModelLoket>() {
            @Override
            public void onResponse(Call<ModelLoket> call, Response<ModelLoket> response) {
                if (response.isSuccessful()){
                    ModelLoket databody = response.body();
                    List<Loket> popularList = databody.getResult();
                    pAdapter = new PopularProductAdapter(popularList, getContext(), "Home");
                    RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    pRecyclerView.setLayoutManager(pLayoutManager);
                    pRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    pRecyclerView.setAdapter(pAdapter);
                }else{
                    Log.i("debug", "onResponse: Category-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelLoket> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }


    public void scheduleSlider() {

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == dotscount) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                viewPager.setCurrentItem(page_position, true);
            }
        };

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(update);
            }
        }, 500, 4000);
    }

    @Override
    public void onStop() {
        timer.cancel();
        super.onStop();
    }

    @Override
    public void onPause() {
        timer.cancel();
        super.onPause();
    }

    public void onLetsClicked(View view) {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }
}

package com.wartatv.yukantree.activity;

import android.annotation.SuppressLint;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.ActionBar;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.adapter.HostAdapter;
import com.wartatv.yukantree.adapter.LoketAdapter;
import com.wartatv.yukantree.adapter.ProductAdapter;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.helper.Converter;
import com.wartatv.yukantree.helper.Data;
import com.wartatv.yukantree.model.Loket;
import com.wartatv.yukantree.model.ModelLoket;
import com.wartatv.yukantree.model.ModelLoket;
import com.wartatv.yukantree.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by .
 * www.wartatv.com
 */
public class ProductActivity extends BaseActivity {
    private static int cart_count = 0;
    Data data;
    LoketAdapter mAdapter;
    String Tag = "List";
    private RecyclerView recyclerView;
    String[] id;
    String[] title;
    String[] image;
    List<Loket> loketList = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        changeActionBarTitle(getSupportActionBar());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        //upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        cart_count = cartCount();
        recyclerView = findViewById(R.id.product_rv);
        getLoketList();

    }

    public void getLoketList(){
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        String type= getIntent().getStringExtra("type");
        Log.i("debug", "onResponse: Host-Data " +type);

        Call<ModelLoket> call = apiService.getLoketbyCat(type);

        call.enqueue(new Callback<ModelLoket>() {
            @Override
            public void onResponse(Call<ModelLoket> call, Response<ModelLoket> response) {
                if (response.isSuccessful()){
                    ModelLoket databody = response.body();
                    List<Loket> data = databody.getResult();
                    id = new String[data.size()];
                    title = new String[data.size()];
                    image = new String[data.size()];
                    int i = 0;
                    for (Loket d: data) {


                        Loket h = new Loket(d.getId(),d.getTitle(),d.getImage());
                        loketList.add(h);

                        Log.i("debug", "onResponse: Host-Data " +title[i]);
                        i++;
                    }
                    updateNew(loketList);
                }else{
                    Log.i("debug", "onResponse: Host-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelLoket> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }

    private void updateNew(List<Loket> loketList) {
        mAdapter = new LoketAdapter(loketList, ProductActivity.this, Tag);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void changeActionBarTitle(ActionBar actionBar) {
        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        TextView tv = new TextView(getApplicationContext());
        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTypeface(null, Typeface.BOLD);
        // Set text to display in TextView
        tv.setText("Lokets"); // ActionBar title text
        tv.setTextSize(20);

        // Set the text color of TextView to red
        // This line change the ActionBar title text color
        tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        // Set the ActionBar display option
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // Finally, set the newly created TextView as ActionBar custom view
        actionBar.setCustomView(tv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case R.id.cart_action:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(ProductActivity.this, cart_count, R.drawable.ic_shopping_basket));
        return true;
    }


    @Override
    public void onAddProduct() {
        cart_count++;
        invalidateOptionsMenu();

    }

    @Override
    public void onRemoveProduct() {
        cart_count--;
        invalidateOptionsMenu();

    }

}

package com.wartatv.yukantree.activity;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wartatv.yukantree.R;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.helper.Converter;
import com.wartatv.yukantree.model.DataItem;
import com.wartatv.yukantree.model.Loket;
import com.wartatv.yukantree.model.ResponseHistory;
import com.wartatv.yukantree.model.ResponseHistory;
import com.wartatv.yukantree.util.Preferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

import static com.wartatv.yukantree.fragment.ProfileFragment.parseDate;

public class QueueActivity extends BaseActivity {
    private static int cart_count = 0;

    TextView title, description, txtnumber,txtstatus, txttype, discount, attribute, dateTimeDisplay;
    TextView category, host, phone, address, city, schedule,quota, price;

    ImageView imageView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        changeActionBarTitle(getSupportActionBar());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        cart_count = cartCount();

        txtnumber = findViewById(R.id.apv_number);
        txtstatus = findViewById(R.id.apv_status);
        txttype = findViewById(R.id.apv_type);

        category = findViewById(R.id.apv_category);

        host = findViewById(R.id.apv_host);
        address = findViewById(R.id.apv_address);
        city = findViewById(R.id.apv_city);
        phone = findViewById(R.id.apv_phone);

        schedule = findViewById(R.id.apv_schedule);
        quota = findViewById(R.id.apv_quota);
        price = findViewById(R.id.apv_price);

        title = findViewById(R.id.apv_title);
        imageView = findViewById(R.id.apv_image);
        progressBar = findViewById(R.id.progressbar);
        dateTimeDisplay = findViewById(R.id.date_display);

        getTransactionDetail();

    }

    private void getTransactionDetail() {
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Integer userId;
        userId = Preferences.getUserId(QueueActivity.this);

        Call<ResponseHistory> call = apiService.getUserTransaction(userId);

        call.enqueue(new retrofit2.Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                if (response.isSuccessful()){
                    ResponseHistory databody = response.body();
                    List<DataItem> dataList = databody.getData();
                    Log.i("debug", "onResponse: Loket-Data"+ databody.getMessage());
                    Log.i("debug", "onResponse: Loket-Data"+ dataList);


                    if (dataList.size() > 0) {

                        title.setText(dataList.get(0).getLoket().getTitle());

                        String dateStr = dataList.get(0).getTanggal();
                        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
                        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss");
                        String outputDateStr = "";
                        outputDateStr = parseDate(dateStr , ymdFormat, df);

                        dateTimeDisplay.setText(outputDateStr);
                        txtnumber.setText(dataList.get(0).getNumber());
                        txtstatus.setText(dataList.get(0).getStatus().getTitle());

                        category.setText(dataList.get(0).getCategoryname());

                        host.setText(dataList.get(0).getHostname());
                        address.setText(dataList.get(0).getAddress());
                        city.setText(dataList.get(0).getCity());
                        phone.setText(dataList.get(0).getPhone());

                        schedule.setText(dataList.get(0).getLoket().getSchedule());
                        quota.setText(String.valueOf(dataList.get(0).getLoket().getQuota()));
                        price.setText(String.valueOf(dataList.get(0).getLoket().getPrice()));

                        if ( dataList.get(0).getLoket().getImage() != null) {
                            Picasso.get().load(dataList.get(0).getLoket().getImage()).error(R.drawable.no_image).into(imageView, new Callback() {
                                @Override
                                public void onSuccess() {
                                    progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onError(Exception e) {
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                        }

                    } else {
                        Toast toast = Toast.makeText(QueueActivity.this, "Data Empty",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Log.i("debug", "onResponse: Loket-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
    }

    private void changeActionBarTitle(ActionBar actionBar) {
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        TextView tv = new TextView(getApplicationContext());
        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTypeface(null, Typeface.BOLD);
        // Set text to display in TextView
        tv.setText("Queue Status"); // ActionBar title text
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

                Intent intent = new Intent(QueueActivity.this, MainActivity.class);
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
        menuItem.setIcon(Converter.convertLayoutToImage(QueueActivity.this, cart_count, R.drawable.ic_shopping_basket));
        return true;
    }

}

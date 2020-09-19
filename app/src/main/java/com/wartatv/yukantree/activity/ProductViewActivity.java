package com.wartatv.yukantree.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;


import com.wartatv.yukantree.R;
import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.helper.Converter;
import com.wartatv.yukantree.model.Cart;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.wartatv.yukantree.model.Loket;
import com.wartatv.yukantree.model.ModelLoket;
import com.wartatv.yukantree.model.ResponseTransaksi;
import com.wartatv.yukantree.model.ModelUser;
import com.wartatv.yukantree.util.Preferences;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by .
 * www.wartatv.com
 */
public class ProductViewActivity extends BaseActivity {
    private static int cart_count = 0;
    public TextView quantity, inc, dec;
    String _id;
    String _title;
    int userId;
    String loketId;
    TextView title, description, txtnumber,txtstatus, txttype, discount, attribute, dateTimeDisplay;
    ImageView imageView;
    ProgressBar progressBar;
    LinearLayout addToCart, share;
    RelativeLayout quantityLL;
    List<Cart> cartList = new ArrayList<>();
    int cartId;
    Cart cart;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    private static Button updateBuy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        changeActionBarTitle(getSupportActionBar());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        //upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        cart_count = cartCount();

        txtnumber = findViewById(R.id.apv_number);
        txtstatus = findViewById(R.id.apv_status);
        txttype = findViewById(R.id.apv_type);

        title = findViewById(R.id.apv_title);
        description = findViewById(R.id.apv_description);
//        price = findViewById(R.id.apv_price);
        attribute = findViewById(R.id.apv_attribute);
        discount = findViewById(R.id.apv_discount);
        imageView = findViewById(R.id.apv_image);
        progressBar = findViewById(R.id.progressbar);
        share = findViewById(R.id.apv_share);
        quantityLL = findViewById(R.id.quantity_rl);
        quantity = findViewById(R.id.quantity);
        inc = findViewById(R.id.quantity_plus);
        dec = findViewById(R.id.quantity_minus);
        dateTimeDisplay = findViewById(R.id.date_display);

        discount.setText("Open");
        getLoketDetail();
        calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEE, d MMMM yyyy HH:mm:ss");
        date = df.format(calendar.getTime());
        dateTimeDisplay.setText(date);
        updateBuy = findViewById(R.id.updateBuy);
        updateBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNumber();
            }
        });

//
//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog = new ProgressDialog(ProductViewActivity.this);
////                        dialog.setTitle("Profile");
//                dialog.setMessage("Number Updated");
//                dialog.show();
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//                    public void run() {
//                        dialog.dismiss();
//                    }
//                }, 1000); // 5000 milliseconds
//                Log.i("debug", "onResponse: BERHASIL");
//            }
//        });
//

//        addToCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                _price = price.getText().toString();
//
//                cart = new Cart(_id, _title, _image, _currency, _price, _attribute, "1", _price);
//                cartList.add(cart);
//                String cartStr = gson.toJson(cartList);
//                //Log.d("CART", cartStr);
////                localStorage.setCart(cartStr);
//                onAddProduct();
//                addToCart.setVisibility(View.GONE);
//                quantityLL.setVisibility(View.VISIBLE);
//            }
//        });
//
//
//        inc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                _price = price.getText().toString();
//
//                // int total_item = Integer.parseInt(cartList.get(cartId).getQuantity());
//                int total_item = Integer.parseInt(quantity.getText().toString());
//                total_item++;
//                Log.d("totalItem", total_item + "");
//                quantity.setText(total_item + "");
//                String subTotal = String.valueOf(Double.parseDouble(_price) * total_item);
//                cartList.get(cartId).setQuantity(quantity.getText().toString());
//                cartList.get(cartId).setSubTotal(subTotal);
//                cartList.get(cartId).setAttribute(attribute.getText().toString());
//                cartList.get(cartId).setPrice(_price);
//                String cartStr = gson.toJson(cartList);
//                //Log.d("CART", cartStr);
////                localStorage.setCart(cartStr);
//            }
//        });
//
//        dec.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                _price = price.getText().toString();
//
//                //int total_item = Integer.parseInt(quantity.getText().toString());
//                int total_item = Integer.parseInt(quantity.getText().toString());
//                if (total_item != 1) {
//                    total_item--;
//                    quantity.setText(total_item + "");
//                    Log.d("totalItem", total_item + "");
//                    String subTotal = String.valueOf(Double.parseDouble(_price) * total_item);
//
//                    cartList.get(cartId).setQuantity(quantity.getText().toString());
//                    cartList.get(cartId).setSubTotal(subTotal);
//                    cartList.get(cartId).setAttribute(attribute.getText().toString());
//                    cartList.get(cartId).setPrice(_price);
//                    String cartStr = gson.toJson(cartList);
//                    //Log.d("CART", cartStr);
////                    localStorage.setCart(cartStr);
//                }
//            }
//        });


    }

    private void updateNumber() {
        userId = Preferences.getUserId(ProductViewActivity.this);
        loketId= _id;
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ResponseTransaksi> call = apiService.AddAntri(
                userId,
                loketId
        );

        call.enqueue(new retrofit2.Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                if (response.isSuccessful()){
                    ResponseTransaksi databody = response.body();

                    Toast toast = Toast.makeText(ProductViewActivity.this, "Get Number Updated",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    txtnumber.setVisibility(View.VISIBLE);
                    txtstatus.setVisibility(View.VISIBLE);
                    txtnumber.setText(databody.getData().getNumber());

                    startActivity(new Intent(ProductViewActivity.this, QueueActivity.class));
                    ProductViewActivity.this.finish();
                    ProductViewActivity.this.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

                    Log.i("debug", "onResponse: BERHASIL"+loketId);
                } else {
                    Log.i("debug", "onResponse: GA BERHASIL");
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksi> call, Throwable t) {
                Log.e("debug", "onFailure: ERROR > " + t.getMessage());

            }

        });
    }

    public void getLoketDetail(){
        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Intent intent = getIntent();
        _id = intent.getStringExtra("id");
        Call<ModelLoket> call = apiService.getLoketDetail(_id);

        call.enqueue(new retrofit2.Callback<ModelLoket>() {
            @Override
            public void onResponse(Call<ModelLoket> call, Response<ModelLoket> response) {
                if (response.isSuccessful()){
                    ModelLoket databody = response.body();
                    List<Loket> loketList = databody.getResult();
                    if (loketList.size() > 0) {
                        _title =loketList.get(0).getTitle();
                        title.setText(loketList.get(0).getTitle());
                        description.setText(loketList.get(0).getTitle());
                        attribute.setText(loketList.get(0).getTitle());

                        if ( loketList.get(0).getImage() != null) {
                            Picasso.get().load(loketList.get(0).getImage()).error(R.drawable.no_image).into(imageView, new Callback() {
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
                        Log.i("debug", "onResponse: Loket-Data"+ _id);

                    } else {
                        Toast toast = Toast.makeText(ProductViewActivity.this, "Data Empty",
                                Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }else{
                    Log.i("debug", "onResponse: Loket-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelLoket> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });
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
        tv.setText(_title); // ActionBar title text
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

                Intent intent = new Intent(ProductViewActivity.this, MainActivity.class);
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
        menuItem.setIcon(Converter.convertLayoutToImage(ProductViewActivity.this, cart_count, R.drawable.ic_shopping_basket));
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

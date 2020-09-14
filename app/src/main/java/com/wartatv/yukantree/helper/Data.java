package com.wartatv.yukantree.helper;

import android.util.Log;

import com.wartatv.yukantree.api.BaseApiService;
import com.wartatv.yukantree.api.RetrofitClient;
import com.wartatv.yukantree.model.Category;
import com.wartatv.yukantree.model.Loket;
import com.wartatv.yukantree.model.ModelCategory;
import com.wartatv.yukantree.model.ModelProduct;
import com.wartatv.yukantree.model.Offer;
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
public class Data {
    List<Category> categoryList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<Product> newList = new ArrayList<>();
    List<Loket> popularList = new ArrayList<>();
    List<Offer> offerList = new ArrayList<>();

    String[] id;
    String[] title;
    String[] image;

    public List<Category> getCategoryList() {

        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelCategory> call = apiService.getCategory();

        call.enqueue(new Callback<ModelCategory>() {
            @Override
            public void onResponse(Call<ModelCategory> call, Response<ModelCategory> response) {
                if (response.isSuccessful()){
                    ModelCategory databody = response.body();
                    List<Category> data = databody.getResult();

                    id = new String[data.size()];
                    title = new String[data.size()];
                    image = new String[data.size()];
                    int i = 0;
                    for (Category d: data) {
                        id[i] = d.getId();
                        title[i] = d.getTitle();
                        image[i] = d.getImage();

                        prepareCategory(d.getId(),d.getTitle(),d.getImage());
                        Log.i("debug", "onResponse: Category-Data " +title[i]);
                        i++;
                    }
                }else{
                    Log.i("debug", "onResponse: Category-Data Not Found");
                }
            }

            @Override
            public void onFailure(Call<ModelCategory> call, Throwable throwable) {
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());
            }
        });

        return categoryList;
    }


    public List<Product> getProductList() {
        return productList;
    }

    public List<Product> getNewList() {

        BaseApiService apiService = RetrofitClient.getInstanceRetrofit();
        Call<ModelProduct> call = apiService.getHost();

        call.enqueue(new Callback<ModelProduct>() {
            @Override
            public void onResponse(Call<ModelProduct> call, Response<ModelProduct> response) {
                if (response.isSuccessful()){
                    ModelProduct databody = response.body();
                    List<Product> data = databody.getResult();

                    id = new String[data.size()];
                    title = new String[data.size()];
                    image = new String[data.size()];
                    int i = 0;
                    for (Product d: data) {
                        id[i] = d.getId();
                        title[i] = d.getTitle();
                        image[i] = d.getImage();

                        prepareProduct(d.getId(),d.getTitle(),d.getImage());
                        Log.i("debug", "onResponse: Host-Data " +title[i]);
                        i++;
                    }
                }else{
//                    Toast.makeText(ListVenue.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                    Log.i("debug", "onResponse: Host-Data Not Found");

                }
            }

            @Override
            public void onFailure(Call<ModelProduct> call, Throwable throwable) {
//                Toast.makeText(ListVenue.this, "Koneksi Gagal", Toast.LENGTH_SHORT).show();
                Log.e("debug", "onFailure: ERROR > " + throwable.getMessage());

            }
        });
        return newList;
    }

    public List<Loket> getPopularList() {
        Loket loket = new Loket("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "http://103.16.199.187/idogo/public/storage/files/shares/image/energy-drink.png");
        popularList.add(loket);
        loket = new Loket("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "http://103.16.199.187/idogo/public/storage/files/shares/image/energy-drink.png");
        popularList.add(loket);
        loket = new Loket("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "http://103.16.199.187/idogo/public/storage/files/shares/image/energy-drink.png");
        popularList.add(loket);
        loket = new Loket("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "http://103.16.199.187/idogo/public/storage/files/shares/image/energy-drink.png");
        popularList.add(loket);
        loket = new Loket("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "http://103.16.199.187/idogo/public/storage/files/shares/image/energy-drink.png");
        popularList.add(loket);
        return popularList;
    }

    public List<Offer> getOfferList() {
        Offer offer = new Offer("http://103.16.199.187/idogo/public/storage/files/shares/image/slider5.jpg");
        offerList.add(offer);
        offer = new Offer("http://103.16.199.187/idogo/public/storage/files/shares/image/slider4.jpg");
        offerList.add(offer);
        offer = new Offer("http://103.16.199.187/idogo/public/storage/files/shares/image/slider3.jpg");
        offerList.add(offer);
        offer = new Offer("http://103.16.199.187/idogo/public/storage/files/shares/image/slider2.jpg");
        offerList.add(offer);
        offer = new Offer("http://103.16.199.187/idogo/public/storage/files/shares/image/slider1.jpg");
        offerList.add(offer);
        return offerList;
    }

    private void prepareCategory(String id, String title,String image){
        Category c = new Category(id,title,image);
        categoryList.add(c);
    }

    private void prepareProduct(String id, String title,String image){
        Product h = new Product(id,title,image);
        newList.add(h);
    }
}

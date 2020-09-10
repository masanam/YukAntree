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
//        Product product = new Product("1", "1", "Apple", "", "1 Kg", "Rs.", "20", "10% OFF", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("2", "1", "Banana", "", "1 Bounch", "Rs.", "10", "20% OFF", "https://images-na.ssl-images-amazon.com/images/I/21DejQuoT2L.jpg");
//        productList.add(product);
//        product = new Product("3", "2", "House Clean Liquid", "", "1 Lit.", "Rs.", "25", "", "http://sunsetcleaningcia.com/wp-content/uploads/2016/05/houseclean.png");
//        productList.add(product);
//        product = new Product("4", "2", "House Clean Brush", "", "1 Piece", "Rs.", "10", "", "https://www.clean-hoouse.com/wp-content/uploads/2017/09/13.png");
//        productList.add(product);
//        product = new Product("5", "3", "Pampers", "", "1 Piece", "Rs.", "20", "10% OFF", "https://cdn.bmstores.co.uk/images/hpcProductImage/imgFull/311448-Pampers-Baby-Dry-Size-4-Maxi-251.jpg");
//        productList.add(product);
//        product = new Product("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "https://www.fortunaonline.net/media/catalog/product/cache/1/small_image/295x/040ec09b1e35df139433887a97daa66f/n/k/nkbcp12_-_xia-shib-ao-baby-oil-200ml.png");
//        productList.add(product);
//        product = new Product("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("11", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("12", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
//        productList.add(product);
//        product = new Product("13", "1", "Litche", "", "1 Kg", "Rs.", "20", "30%OFF", "https://cdn.shopify.com/s/files/1/0665/4989/products/lichee-485x400_grande.jpg");
//        productList.add(product);
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
        Loket loket = new Loket("6", "3", "Baby Oil", "", "500 Ml", "Rs.", "31", "", "https://www.fortunaonline.net/media/catalog/product/cache/1/small_image/295x/040ec09b1e35df139433887a97daa66f/n/k/nkbcp12_-_xia-shib-ao-baby-oil-200ml.png");
        popularList.add(loket);
        loket = new Loket("7", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(loket);
        loket = new Loket("8", "4", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(loket);
        loket = new Loket("9", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(loket);
        loket = new Loket("10", "5", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(loket);
        loket = new Loket("11", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(loket);
        loket = new Loket("12", "6", "Apple", "", "1 Kg", "Rs.", "20", "", "https://storage.googleapis.com/zopnow-static/images/products/320/fresh-apple-red-delicious-v-500-g.png");
        popularList.add(loket);
        loket = new Loket("13", "1", "Litche", "", "1 Kg", "Rs.", "20", "30%OFF", "https://cdn.shopify.com/s/files/1/0665/4989/products/lichee-485x400_grande.jpg");
        popularList.add(loket);
        return popularList;
    }

    public List<Offer> getOfferList() {
        Offer offer = new Offer("http://1.bp.blogspot.com/-MMt-60IWEdw/VqZsh45Dv8I/AAAAAAAAMHg/70D9tVowlyc/s1600/askmegrocery-republic-day-offer.jpg");
        offerList.add(offer);
        offer = new Offer("http://www.lootkaro.com/wp-content/uploads/2016/05/as1.jpg");
        offerList.add(offer);
        offer = new Offer("https://453xbcknr3t3ahr522mms518-wpengine.netdna-ssl.com/wp-content/themes/iga-west/images/banner-save-more.jpg");
        offerList.add(offer);
        offer = new Offer("https://images-eu.ssl-images-amazon.com/images/G/31/img16/Grocery/SVD/July18/750x375.png");
        offerList.add(offer);
        offer = new Offer("https://images-eu.ssl-images-amazon.com/images/G/31/img16/Grocery/BreakfastStore/kmande_2018-06-15T12-00_f010a5_1121973_grocery_750x375.jpg");
        offerList.add(offer);
        offer = new Offer("http://www.enjoygrocery.com/images/enjoygrocery-offer.jpg");
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

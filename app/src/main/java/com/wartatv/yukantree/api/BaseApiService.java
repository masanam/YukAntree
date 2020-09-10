package com.wartatv.yukantree.api;

import com.wartatv.yukantree.model.ModelCategory;
import com.wartatv.yukantree.model.ModelLoket;
import com.wartatv.yukantree.model.ModelProduct;
import com.wartatv.yukantree.model.ModelSlider;
import com.wartatv.yukantree.model.ModelUser;
import com.wartatv.yukantree.model.ResponseLogin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by wartatv
 */
public interface BaseApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> loginRequest(@Field("email") String email,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String password_confirmation);

    @GET("admin/sliders")
    Call<ModelSlider> getSlider();

    @GET("admin/categories")
    Call<ModelCategory> getCategory();

    @GET("admin/hosts")
    Call<ModelProduct> getHost();

    @GET("admin/lokets")
    Call<ModelLoket> getLoket();

    @FormUrlEncoded
    @POST("admin/getHostbyCat")
    Call<ModelProduct> getHostbyCat(
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("admin/getLoketbyCat")
    Call<ModelLoket> getLoketbyCat(
            @Field("type") String type
    );

    @FormUrlEncoded
    @POST("admin/getUserProfile")
    Call<ModelUser> getUserProfile(
            @Field("email") String email
    );

}

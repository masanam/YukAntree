package com.wartatv.yukantree.api;

import com.wartatv.yukantree.model.ModelCategory;
import com.wartatv.yukantree.model.ModelLoket;
import com.wartatv.yukantree.model.ModelProduct;

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
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String password_confirmation);

    @GET("admin/categories")
    Call<ModelCategory> getCategory();

    @GET("admin/hosts")
    Call<ModelProduct> getHost();

    @GET("admin/lokets")
    Call<ModelLoket> getLoket();

//    @GET("dosen/{namadosen}")
//    Call<ResponseDosenDetail> getDetailDosen(@Path("namadosen") String namadosen);
//
//    @GET("matkul")
//    Call<ResponseMatkul> getSemuaMatkul();
//
//    @FormUrlEncoded
//    @POST("matkul")
//    Call<ResponseBody> simpanMatkulRequest(@Field("nama_dosen") String namadosen,
//                                           @Field("matkul") String namamatkul);
//
//    @DELETE("matkul/{idmatkul}")
//    Call<ResponseBody> deteleMatkul(@Path("idmatkul") String idmatkul);
}

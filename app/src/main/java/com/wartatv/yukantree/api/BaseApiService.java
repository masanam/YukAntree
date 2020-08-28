package com.wartatv.yukantree.api;

//import com.wartatv.yukantree.model.ResponseDosen;
//import com.wartatv.yukantree.model.ResponseDosenDetail;
//import com.wartatv.yukantree.model.ResponseMatkul;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by fariz ramadhan.
 * website : www.farizdotid.com
 * github : https://github.com/farizdotid
 */
public interface BaseApiService {

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("signup")
    Call<ResponseBody> registerRequest(@Field("name") String name,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String password_confirmation)

    ;
//
//    @GET("semuadosen")
//    Call<ResponseDosen> getSemuaDosen();
//
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

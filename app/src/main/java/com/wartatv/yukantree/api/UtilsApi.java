package com.wartatv.yukantree.api;

/**
 * Created by fariz ramadhan.
 * website : www.farizdotid.com
 * github : https://github.com/farizdotid

public class UtilsApi {

    // 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "http://http://49.128.186.34:2025/api/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
 */
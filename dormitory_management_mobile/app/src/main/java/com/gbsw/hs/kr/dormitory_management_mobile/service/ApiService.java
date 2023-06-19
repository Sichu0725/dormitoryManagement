package com.gbsw.hs.kr.dormitory_management_mobile.service;

import com.gbsw.hs.kr.dormitory_management_mobile.api.ServerApi;
import com.gbsw.hs.kr.dormitory_management_mobile.model.JWTModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.ResponseModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.NotificationModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private String BASE_URL = "http://172.16.1.197:8080/";

    private static ApiService instance;

    public ServerApi api = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ServerApi.class);

    public static ApiService getInstance() {
        if (instance == null) {
            instance = new ApiService();
        }
        return instance;
    }

    public Single<ResponseModel<JWTModel>> login(HashMap<String, Object> param) {
        return api.login(param);
    }

    public Single<ResponseModel<List<NotificationModel>>> getNotificationList(int page) {
        return api.getNotificationList(page);
    }

    public Single<ResponseModel<NotificationModel>> getNotification(Long idx) {
        return api.getNotification(idx);
    }



}

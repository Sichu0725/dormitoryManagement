package com.gbsw.hs.kr.dormitory_management_mobile.api;

import com.gbsw.hs.kr.dormitory_management_mobile.model.JWTModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.NotificationModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.ResponseModel;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServerApi {
    @POST("api/v1/user/login")
    Single<ResponseModel<JWTModel>> login(@Body HashMap<String, Object> param);

    @GET("api/v1/notification/list")
    Single<ResponseModel<List<NotificationModel>>> getNotificationList(@Query("page") int page);

    @GET("api/v1/notification/get-notification")
    Single<ResponseModel<NotificationModel>> getNotification(@Query("idx") Long idx);
}

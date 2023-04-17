package com.gbsw.hs.kr.dormitory_management_mobile.model;

import com.google.gson.annotations.SerializedName;

public class ResponseModel<T> {
    @SerializedName("status")
    private String responseStatus;

    @SerializedName("data")
    private T responseData;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }
}

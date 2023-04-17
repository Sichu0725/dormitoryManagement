package com.gbsw.hs.kr.dormitory_management_mobile.model;

import com.google.gson.annotations.SerializedName;


public class JWTModel {
    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("grantType")
    private String grantType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }
}

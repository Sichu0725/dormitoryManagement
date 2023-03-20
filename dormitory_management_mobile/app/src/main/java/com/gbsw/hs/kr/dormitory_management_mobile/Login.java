package com.gbsw.hs.kr.dormitory_management_mobile;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

public class Login {

    public boolean getUserInfo(String key) {

        try {
            String url = "http://localhost:8080/api/v1/user" + key;

            OkHttpClient client = new OkHttpClient();

            Request.Builder builder = new Request.Builder().url(url).get();
            builder.addHeader("password", "");
            Request request = builder.build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body != null) {
                    System.out.println("Response:" + body.string());
                }
            }
            else
                System.err.println("Error Occurred");

            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}

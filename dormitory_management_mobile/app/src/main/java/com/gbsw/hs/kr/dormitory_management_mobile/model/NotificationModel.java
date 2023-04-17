package com.gbsw.hs.kr.dormitory_management_mobile.model;

import android.annotation.SuppressLint;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class NotificationModel {

    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");

    @SerializedName("idx")
    private Long idx;

    @SerializedName("title")
    private String title;

    @SerializedName("contents")
    private String contents;

    @SerializedName("createdAt")
    private String createdAt;

    public Long getIdx() {
        return idx;
    }

    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getCreatedAt() {
        try {
            return createdAt.substring(0,10);
        } catch (Exception e) {
            e.printStackTrace();
            return "0000-00-00";
        }
    }

}

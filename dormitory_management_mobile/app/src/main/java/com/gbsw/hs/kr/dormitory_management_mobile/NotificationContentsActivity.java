package com.gbsw.hs.kr.dormitory_management_mobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gbsw.hs.kr.dormitory_management_mobile.model.JWTModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.NotificationModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.ResponseModel;
import com.gbsw.hs.kr.dormitory_management_mobile.service.ApiService;
import com.gbsw.hs.kr.dormitory_management_mobile.service.PreferenceManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NotificationContentsActivity extends Activity {

    private TextView title;
    private TextView contents;
    private TextView createdAt;

    private Context context;

    public ApiService apiService = ApiService.getInstance();
    private final CompositeDisposable disposable = new CompositeDisposable();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_contents);

        Intent intent = getIntent();
        Long idx = intent.getLongExtra("notificationIdx", 0);

        context = this;

        ImageButton backBtn = (ImageButton) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_inIntent = new Intent(context, MainActivity.class);

                main_inIntent.putExtra("frag", "notification fragement");

                startActivity(main_inIntent);
            }
        });
        fetchNotification(idx);
    }

    public void fetchNotification(Long idx) {
        disposable.add(apiService
            .getNotification(idx)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<ResponseModel<NotificationModel>>() {

                @Override
                public void onSuccess(ResponseModel<NotificationModel> notificationModelResponseModel) {
                    NotificationModel notificationModel = notificationModelResponseModel.getResponseData();
                    System.out.println("title : " + notificationModel.getTitle());

                    title = (TextView) findViewById(R.id.notificationTitle);
                    contents = (TextView) findViewById(R.id.notificationContents);
                    createdAt = (TextView) findViewById(R.id.notificationCreatedAt);

                    title.setText(notificationModel.getTitle());
                    contents.setText(notificationModel.getContents());
                    createdAt.setText(notificationModel.getCreatedAt() + "에 작성된 공지입니다.");
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    e.printStackTrace();
                }}));
    }
}

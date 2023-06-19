package com.gbsw.hs.kr.dormitory_management_mobile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.gbsw.hs.kr.dormitory_management_mobile.model.JWTModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.ResponseModel;
import com.gbsw.hs.kr.dormitory_management_mobile.fragment.my_info_fragment;
import com.gbsw.hs.kr.dormitory_management_mobile.fragment.notification_fragment;
import com.gbsw.hs.kr.dormitory_management_mobile.fragment.student_card_fragment;
import com.gbsw.hs.kr.dormitory_management_mobile.service.ApiService;
import com.gbsw.hs.kr.dormitory_management_mobile.service.PreferenceManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity{


    private final Context mContext = this;
    private final Fragment student_card = new student_card_fragment();
    private final Fragment notification = new notification_fragment();
    private final Fragment my_info = new my_info_fragment();

    private String token = "";

    private String id;
    private String pw;

    public ApiService apiService = ApiService.getInstance();
    private final CompositeDisposable disposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            token = PreferenceManager.getString(mContext, "token");
        } catch (NullPointerException e) {
            token = "";
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!Objects.equals(token, "")) {
            Log.i("firstToken", token);
            setMainActivity();
        } else {
            setLoginAcitivy();
        }
    }

    public void setLoginAcitivy() {
            setContentView(R.layout.activity_login);


        final EditText editId = findViewById(R.id.editId);
        final EditText editPw = findViewById(R.id.editPw);
        final Button login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(view -> {
            id = editId.getText().toString();
            pw = editPw.getText().toString();

            try {
                HashMap<String,Object> param = new HashMap<>();
                param.put("id", id);
                param.put("password", pw);

                //todo 로그인
                fetchLogin(param);

            } catch (Exception e) {
                // todo 로그인 실패
            }

        });
    }

    private void setFrag(int n)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.MainFrame,student_card);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.MainFrame,notification);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.MainFrame,my_info);
                ft.commit();
                break;


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setMainActivity() {
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.student_card:
                        setFrag(0);
                        break;
                    case R.id.notification:
                        setFrag(1);
                        break;
                    case R.id.my_info:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });

        try {
            Intent intent = getIntent();

            if (intent.getStringExtra("frag").equals("notification fragement"))
                setFrag(1);
            else
                setFrag(0);
        } catch (Exception e) {
            setFrag(0);
        }
    }

    private void fetchLogin(HashMap<String, Object> param) {
        disposable.add(apiService
            .login(param)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<ResponseModel<JWTModel>>() {

                @Override
                public void onSuccess(
                        @io.reactivex.annotations.NonNull ResponseModel<JWTModel> jwtModelResponseModel) {
                    token = jwtModelResponseModel.getResponseData().getAccessToken();
                    Log.i("login token", token);
                    PreferenceManager.setString(mContext, "token", token);

                    String a = PreferenceManager.getString(mContext, "token");
                    Log.i("saved token", a);
                    // todo 메인으로 다시 가도록 onCreate 함수 리팩터링...
                    setMainActivity();
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.w("login failed", "");
                    e.printStackTrace();
                }}));
    }


}
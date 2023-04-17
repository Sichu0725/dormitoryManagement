package com.gbsw.hs.kr.dormitory_management_mobile.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gbsw.hs.kr.dormitory_management_mobile.R;
import com.gbsw.hs.kr.dormitory_management_mobile.adapter.RecyclerViewAdapter;
import com.gbsw.hs.kr.dormitory_management_mobile.model.JWTModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.NotificationModel;
import com.gbsw.hs.kr.dormitory_management_mobile.model.ResponseModel;
import com.gbsw.hs.kr.dormitory_management_mobile.service.ApiService;
import com.gbsw.hs.kr.dormitory_management_mobile.service.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class notification_fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private final ArrayList<NotificationModel> items = new ArrayList<>();
    private View v;
    private int pageIdx = 0;
    private boolean lastItem = false;

    private final ApiService apiService = ApiService.getInstance();
    private final CompositeDisposable disposable = new CompositeDisposable();


    private boolean isLoading = false;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);


        if (items.size() == 0)
            fetchNotification(pageIdx++);
        initAdapter();
        initScrollListener();
        return v;
    }


    private void initAdapter() {
        adapter = new RecyclerViewAdapter(items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(v.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initScrollListener() {
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!isLoading && !lastItem) {
                    if (layoutManager != null && layoutManager.findLastCompletelyVisibleItemPosition() == items.size() - 1) {
                        //리스트 마지막
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                fetchNotification(pageIdx++);
                                isLoading = true;

                            }
                        });
                    }
                }
            }
        });
    }


    public void fetchNotification(int page) {
        disposable.add(apiService
            .getNotification(page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableSingleObserver<ResponseModel<List<NotificationModel>>>() {


                /**
                 * @param listResponseModel the item emitted by the Single
                 */
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onSuccess(ResponseModel<List<NotificationModel>> listResponseModel) {
                    Log.i("fetch start", "fetch starty");
                    items.add(null);
                    adapter.notifyItemInserted(items.size() - 1);

                    Handler handler = new Handler();
                    handler.postDelayed(() -> {
                        items.remove(items.size() - 1);
                        int scrollPosition = items.size();
                        adapter.notifyItemRemoved(scrollPosition);

                        List<NotificationModel> notificationModelList = listResponseModel.getResponseData();

                        if (notificationModelList.size() == 0)
                            lastItem = true;

                        items.addAll(notificationModelList);

                        adapter.notifyDataSetChanged();
                        isLoading = false;
                    }, 0);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    Log.w("fetch failed", "");
                    e.printStackTrace();
                }}));
    }
}

package com.gbsw.hs.kr.dormitory_management_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView _tv;
    private Intent _cardService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _tv = (TextView) findViewById(R.id._textView);

        _cardService = new Intent(this, CardService.class);
        startService(_cardService);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(_cardService);
    }

    public void onClickUpdate(View v) {
        _tv.setText("Count : " + Counter.GetCurrentCout());
    }
}
package com.omg.service.android.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.omg.service.android.servicedemo.service.CloudService;

public class MainActivity extends AppCompatActivity {

    private static final String CLOUD_URL = "http://vigilant-armor-145505.appspot.com/";
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.info);
        button = (Button) findViewById(R.id.button1);
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                String dateString = bundle.getString("date");
                textView.setText(dateString);
            } else {
                textView.setText("Error");
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(CloudService.NOTIFICATION_SERVICE));
    }
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, CloudService.class);
        intent.putExtra("urlpath", CLOUD_URL);
        startService(intent);
    }
}

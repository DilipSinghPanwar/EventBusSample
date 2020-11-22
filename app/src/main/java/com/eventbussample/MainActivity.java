package com.eventbussample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    public void actionClick(View view) {
        EventBus.getDefault().post(new MessageEvent("dilip"));
        EventBus.getDefault().post(new CallEvent("singh"));
    }

    public static class MessageEvent { /* Additional fields if needed */
        String dataModel;
        public MessageEvent(String dataModel) {
            this.dataModel = dataModel;
        }
    }

    public static class CallEvent { /* Additional fields if needed */
        String dataModel;

        public CallEvent(String dataModel) {
            this.dataModel = dataModel;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
        Log.e("TAG", "onMessageEvent: >>" + event.dataModel);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCallEvent(CallEvent event) {
        Log.e("TAG", "onCallEvent: >>" + event.dataModel);
    }
}
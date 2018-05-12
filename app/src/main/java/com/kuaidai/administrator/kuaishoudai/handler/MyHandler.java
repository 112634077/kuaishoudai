package com.kuaidai.administrator.kuaishoudai.handler;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.kuaidai.administrator.kuaishoudai.MainActivity;

import java.lang.ref.WeakReference;

public class MyHandler extends Handler{
    private WeakReference<MainActivity> mActivity;
    private OnHandler onHandler = null;
    public MyHandler(MainActivity activity){
        mActivity = new WeakReference<>(activity);
    }
    @Override
    public void handleMessage(Message msg) {
        MainActivity mainActivity = mActivity.get();
        if (mainActivity == null){

            return;
        }
        if (onHandler != null){
           onHandler.setHandlerMessage(msg.what);
        }

    }

    public void setOnHandler(OnHandler onHandler){
        this.onHandler = onHandler;
    }

    public interface OnHandler{
        void setHandlerMessage(int message);
    }
}

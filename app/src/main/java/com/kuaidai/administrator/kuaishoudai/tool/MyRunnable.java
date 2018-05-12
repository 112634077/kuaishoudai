package com.kuaidai.administrator.kuaishoudai.tool;

import android.os.Looper;
import android.os.Message;

import com.kuaidai.administrator.kuaishoudai.handler.MyHandler;

public class MyRunnable implements Runnable{
    private int index ;
    private MyHandler myHandler;
    public MyRunnable(MyHandler myHandler,int index){
        this.myHandler = myHandler;
        this.index = index;
    }
    @Override
    public void run() {
        Looper.prepare();
        doStep();
        Looper.loop();
    }

    private void doStep(){
        Message msg = new Message();
        msg.what = index;
        myHandler.sendMessage(msg);
    }
}

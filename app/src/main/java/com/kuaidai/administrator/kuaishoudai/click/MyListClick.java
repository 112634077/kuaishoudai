package com.kuaidai.administrator.kuaishoudai.click;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.kuaidai.administrator.kuaishoudai.R;
import com.kuaidai.administrator.kuaishoudai.activity.CameraActivity;
import com.kuaidai.administrator.kuaishoudai.tool.Utils;

public class MyListClick implements AdapterView.OnItemClickListener {
    private Dialog mDownloadDialog;
    private Context mContext;
    public MyListClick(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (Utils.userId == 0)
            showDialog();
        else
            return;
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.list_tishi);
        builder.setMessage(R.string.list_messer);
        //取消
        builder.setPositiveButton(R.string.list_cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.list_camera, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //开通会员
                mContext.startActivity(new Intent(mContext, CameraActivity.class));
            }
        });
        mDownloadDialog = builder.create();
        mDownloadDialog.show();
    }

    /*
     <item name="buttonBarPositiveButtonStyle">@style/positive</item>
    *     <style name="positive">
        <item name="android:textColor">@color/black</item>
    </style>
    */
}

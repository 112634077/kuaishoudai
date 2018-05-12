package com.kuaidai.administrator.kuaishoudai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kuaidai.administrator.kuaishoudai.R;
import com.kuaidai.administrator.kuaishoudai.tool.Tool;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Tool> mTool;

    public MyAdapter(Context context,List<Tool> mTool){
        this.context = context;
        this.mTool = mTool;
    }
    @Override
    public int getCount() {
        return mTool.size();
    }

    @Override
    public Object getItem(int position) {
        return mTool.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Tool tool = mTool.get(position);
        Glide.with(context).load(tool.getLogo()).into(viewHolder.logo);
        viewHolder.title.setText(tool.getTitle());
        viewHolder.quota.setText(tool.getQuota());
        viewHolder.rate.setText(tool.getRate());
        viewHolder.hint.setText(tool.getHint());
        return convertView;
    }

    class ViewHolder{
        View view;
        ImageView logo;
        TextView title;
        TextView quota;
        TextView rate;
        TextView hint;

        public ViewHolder(View view){
            this.view = view;
            initView();
        }

        protected void initView(){
            logo = (ImageView)view.findViewById(R.id.item_image);
            title = (TextView)view.findViewById(R.id.item_title);
            quota = (TextView)view.findViewById(R.id.item_quota);
            rate = (TextView)view.findViewById(R.id.item_rate);
            hint = (TextView)view.findViewById(R.id.item_hint);
        }
    }
}

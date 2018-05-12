package com.kuaidai.administrator.kuaishoudai.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kuaidai.administrator.kuaishoudai.R;
import com.kuaidai.administrator.kuaishoudai.adapter.MyAdapter;
import com.kuaidai.administrator.kuaishoudai.click.MyListClick;
import com.kuaidai.administrator.kuaishoudai.tool.Tool;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ListView homeList;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    protected void initView(){
        homeList = (ListView)getActivity().findViewById(R.id.home_list);

        homeList.setAdapter(new MyAdapter(getActivity(),init()));
        homeList.setOnItemClickListener(new MyListClick(getActivity()));
    }

    protected List<Tool> init(){
        List<Tool> mTool = new ArrayList<Tool>();
        for (int i = 0; i < 20; i++){
            Tool tool = new Tool();
            tool.setLogo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523720400284&di=dfcff6a4e0fd0bd7f7d5bf037356b848&imgtype=0&src=http%3A%2F%2Fpic.qiantucdn.com%2F58pic%2F25%2F89%2F46%2F58749495cf588_1024.jpg");
            tool.setTitle("快手贷款");
            tool.setQuota("8百-3千");
            tool.setRate("0.03%");
            tool.setHint("芝麻分580,1000到手");
            mTool.add(tool);
        }
        return mTool;
    }
}

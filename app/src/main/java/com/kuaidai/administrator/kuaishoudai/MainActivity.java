package com.kuaidai.administrator.kuaishoudai;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kuaidai.administrator.kuaishoudai.activity.CameraActivity;
import com.kuaidai.administrator.kuaishoudai.activity.CustomerActivity;
import com.kuaidai.administrator.kuaishoudai.activity.GalleryActivity;
import com.kuaidai.administrator.kuaishoudai.activity.LoginActivity;
import com.kuaidai.administrator.kuaishoudai.activity.MyWebViewActivity;
import com.kuaidai.administrator.kuaishoudai.fragment.HomeFragment;
import com.kuaidai.administrator.kuaishoudai.fragment.QuotaFragment;
import com.kuaidai.administrator.kuaishoudai.fragment.RateFragment;
import com.kuaidai.administrator.kuaishoudai.handler.MyHandler;
import com.kuaidai.administrator.kuaishoudai.retrofit.UpdateManager;
import com.kuaidai.administrator.kuaishoudai.tool.MyRunnable;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener,MyHandler.OnHandler{

    private ImageView userLogo;
    private TextView userText;
    private Toolbar toolbar;
    private BottomNavigationView navigation;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    private Fragment[] fragments;
    private int lastShowFragment = 0;
    protected Fragment homeFragment;
    protected Fragment quotaFragment;
    protected Fragment rateFragment;

    private UpdateManager updateManager = null;
    private MyHandler myHandler = null;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (lastShowFragment != 0) {
                        switchFrament(lastShowFragment, 0);
                        lastShowFragment = 0;
                    }
                    return true;
                case R.id.navigation_dashboard:
                    if (lastShowFragment != 1) {
                        switchFrament(lastShowFragment, 1);
                        lastShowFragment = 1;
                    }
                    return true;
                case R.id.navigation_notifications:
                    if (lastShowFragment != 2) {
                        switchFrament(lastShowFragment, 2);
                        lastShowFragment = 2;
                    }
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updateManager = new UpdateManager(this);

//        new Thread(new MyRunnable(this,1)).start();
        myHandler = new MyHandler(MainActivity.this);
        myHandler.setOnHandler(this);
        Message message = new Message();
        message.what = 1;
        myHandler.sendMessage(message);

        initView();
        init();
        initFragments();
    }

    protected void initView(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        userLogo = (ImageView)findViewById(R.id.user_logo);
        userText = (TextView)navigationView.getHeaderView(0).findViewById(R.id.user_text);

    }

    protected void init(){
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        BNVEffect.disableShiftMode(navigation);

        userText.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            startActivity(new Intent(this,CameraActivity.class));
        } else if (id == R.id.nav_customer){
            startActivity(new Intent(this,CustomerActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this,GalleryActivity.class));
        } else if (id == R.id.nav_share) {
            shareText("分享","欢迎使用快手贷","我最近正在使用@快手贷，可以贷款办理信用卡，利息很低，额度高，非常方面，你也来试试吧。http://wwww.");
        } else if (id == R.id.nav_update) {
           new Thread(new MyRunnable(myHandler,2)).start();
        } else if (id == R.id.nav_disclaimer) {
            startActivity(new Intent(this,MyWebViewActivity.class));
        } else if (id == R.id.nav_about){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void switchFrament(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.message, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();

    }

    private void initFragments() {
        homeFragment = new HomeFragment();
        quotaFragment = new QuotaFragment();
        rateFragment = new RateFragment();
        fragments = new Fragment[]{homeFragment,quotaFragment,rateFragment};

        lastShowFragment = 0;
        getSupportFragmentManager() .beginTransaction() .add(R.id.message, homeFragment) .show(homeFragment) .commit();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,LoginActivity.class));
    }

    /**
     * 分享文字内容
     *
     * @param dlgTitle
     *            分享对话框标题
     * @param subject
     *            主题
     * @param content
     *            分享内容（文字）
     */
    private void shareText(String dlgTitle, String subject, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        if (subject != null && !"".equals(subject)) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);

        // 设置弹出框标题
        if (dlgTitle != null && !"".equals(dlgTitle)) { // 自定义标题
            startActivity(Intent.createChooser(intent, dlgTitle));
        } else { // 系统默认标题
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        updateManager = null;
    }

    @Override
    public void setHandlerMessage(int message) {
        if (message == 1){
            updateManager.isCheckUpdate();
        }else if (message == 2){
            updateManager.checkUpdate();
        }
    }
}

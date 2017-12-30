package com.lzy.mywheels.checkupdate;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lzy.mywheels.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewPagerActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {


    @BindView(R.id.content_layout)
    FrameLayout contentLayout;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private List<BaseFragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_pager);
        ButterKnife.bind(this);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        initView();
    }

    private void initView() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);       //固定大小
//        bottomNavigationBar.setMode(BottomNavigationBar.MODE_SHIFTING);    //不固定大小
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE); //设置背景颜色
        bottomNavigationBar.setBarBackgroundColor(R.color.app);
        bottomNavigationBar.setActiveColor(R.color.white);
//        bottomNavigationBar.setInActiveColor(R.color.go);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.two, "购物车"))
                .addItem(new BottomNavigationItem(R.mipmap.one, "发现"))
                .addItem(new BottomNavigationItem(R.mipmap.three, "扫一扫"))
                .addItem(new BottomNavigationItem(R.mipmap.four,"视频"))
                .addItem(new BottomNavigationItem(R.mipmap.five,"我的"))
                .setFirstSelectedPosition(0)
                .initialise();
        fragments = getFragments();
        addFragmentToActivity(fragments.get(0), R.id.content_layout);
        bottomNavigationBar.setTabSelectedListener(ViewPagerActivity.this);
    }

    private ArrayList<BaseFragment> getFragments() {


        ArrayList<BaseFragment> fragments = new ArrayList<>();
        OneFragment aFragment = new OneFragment();
        TwoFragment bFragment = new TwoFragment();
        ThreeFragment cFragment = new ThreeFragment();

        fragments.add(aFragment);
        fragments.add(bFragment);
        fragments.add(cFragment);
        return fragments;
    }


    /**
     * 将 Fragment添加到Acitvtiy
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(frameId, fragment);
        }
        transaction.commit();
    }


    @Override
    public void onTabSelected(int position) {
        if (fragments != null) {
            if (position < fragments.size()) {
                BaseFragment fragment = fragments.get(position);
                this.addFragmentToActivity(fragment, R.id.content_layout);
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {

        if (fragments != null) {
            if (position < fragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                BaseFragment fragment = fragments.get(position);
                ft.hide(fragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }
}

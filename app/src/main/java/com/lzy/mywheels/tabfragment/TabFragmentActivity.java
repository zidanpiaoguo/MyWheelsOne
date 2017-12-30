package com.lzy.mywheels.tabfragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.lzy.mywheels.R;

public class TabFragmentActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{

    private TabLayout tabLayout;
    private Fragment[] fragments;
    private String[] titles = {"天九", "地八", "人七", "和五"};
    private int position;
    FragmentManager fragmentManager;//获取fragment管理器
    FragmentTransaction fragmentTransaction;//获取事务

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_fragment);
        initView();
    }


    /**
     * 初始化
     */
    private void initView() {
        fragmentManager = getSupportFragmentManager();//获取fragment管理器
        fragments = new Fragment[titles.length];
        tabLayout = (TabLayout) findViewById(R.id.tab_nav);

        tabLayout.setOnTabSelectedListener(this);//这一句监听放在添加tab之前，才可以默认加载第一页
        //添加tab
        for (int i = 0; i < titles.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            tab.setText(titles[i]);
            tab.setIcon(R.mipmap.ic_launcher);
            tabLayout.addTab(tab, i, i == position);//这一句要先有监听才能选中fragment
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //刚进来没有执行
        Fragment fragmentFrom = fragments[position];//获取当前显示的fragment
        Fragment fragmentTo = (Fragment) tab.getTag();
        if (fragmentTo == null) {
            fragmentTo = getItem(tab.getPosition());
            tab.setTag(fragmentTo);
        }
        position = tab.getPosition();//给position赋新值
        switchFragment(fragmentFrom, fragmentTo);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 切换显示fragment
     *
     * @param fragmentFrom
     * @param fragmentTo
     */
    private void switchFragment(Fragment fragmentFrom, Fragment fragmentTo) {
        fragmentTransaction = fragmentManager.beginTransaction();//获取事务
        //隐藏from
        if (fragmentFrom != null) {
            fragmentTransaction.hide(fragmentFrom);
        }
        //先查找fragmentTo是否已经被装载
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTo.getClass().getName());
        //如果fragmentTo不存在就装载，存在就显示
        if (fragment == null) {
            fragmentTransaction.add(R.id.frame_container, fragmentTo);
        } else {
            fragmentTransaction.show(fragmentTo);
        }
        fragmentTransaction.commit();
    }

    /**
     * 创建fragment
     *
     * @param position
     * @return
     */
    private Fragment getItem(int position) {
        Fragment fragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", titles[position]);
        fragment.setArguments(bundle);
        fragments[position] = fragment;
        return fragment;
    }



}

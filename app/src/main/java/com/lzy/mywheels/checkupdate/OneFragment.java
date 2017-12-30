package com.lzy.mywheels.checkupdate;


import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.mywheels.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by zidan on 2017/9/20.
 */

public class OneFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_titleNumble)
    TextView tvTitleNumble;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_back)
    LinearLayout llBack;

    @BindView(R.id.ckb_all)
    CheckBox ckbAll;
    @BindView(R.id.tv_totalprice)
    TextView tvTotalprice;
    @BindView(R.id.tv_totaljifen)
    TextView tvTotaljifen;
    @BindView(R.id.tv_jie)
    TextView tvJie;
    Unbinder unbinder;
    @BindView(R.id.list)
    ExpandableListView list;
    ExpandableAdapter adapter;

    private ShopCarData mShopCarData;
    @Override
    protected int setLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initViews() {
        list.setGroupIndicator(null);
        getData();

        adapter = new ExpandableAdapter(getActivity(),mShopCarData);
        list.setAdapter(adapter);
        adapterLister();
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            list.expandGroup(i);
        }
    }
    private void adapterLister() {
        adapter.setOnAllCheckedBoxNeedChangeListener(new ExpandableAdapter.OnAllCheckedBoxNeedChangeListener(){

            @Override
            public void onCheckedBoxNeedChange(boolean allParentIsChecked) {
                ckbAll.setChecked(allParentIsChecked);
            }
        });

        ckbAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) v;
                    adapter.setupAllChecked(checkBox.isChecked());
                }
            }
        });
    }



    public void getData(){
        ShopCarData.CartGoodsBean.ValidListBean one11 =
                new ShopCarData.CartGoodsBean.ValidListBean(100,10);
        ShopCarData.CartGoodsBean.ValidListBean one12 =
                new ShopCarData.CartGoodsBean.ValidListBean(23.3,20);
        ShopCarData.CartGoodsBean.ValidListBean one13 =
                new ShopCarData.CartGoodsBean.ValidListBean(45.6,10);




        ShopCarData.CartGoodsBean.ValidListBean one21 =
                new ShopCarData.CartGoodsBean.ValidListBean(153,65);
        ShopCarData.CartGoodsBean.ValidListBean one22 =
                new ShopCarData.CartGoodsBean.ValidListBean(789.3,20);
        ShopCarData.CartGoodsBean.ValidListBean one23 =
                new ShopCarData.CartGoodsBean.ValidListBean(200.0,6);


        ShopCarData.CartGoodsBean.ValidListBean one31 =
                new ShopCarData.CartGoodsBean.ValidListBean(123.3,6);
        ShopCarData.CartGoodsBean.ValidListBean one32 =
                new ShopCarData.CartGoodsBean.ValidListBean(45.0,20);
        ShopCarData.CartGoodsBean.ValidListBean one33 =
                new ShopCarData.CartGoodsBean.ValidListBean(300,30);

        List<ShopCarData.CartGoodsBean.ValidListBean>  list = new ArrayList<>();
        List<ShopCarData.CartGoodsBean.ValidListBean>  list2 = new ArrayList<>();
        List<ShopCarData.CartGoodsBean.ValidListBean>  list3 = new ArrayList<>();

        list.add(one11);
        list.add(one12);
        list.add(one13);
        list2.add(one21);
        list2.add(one22);
//        list2.add(one23);
        list3.add(one31);
        list3.add(one32);
        list3.add(one33);


        ShopCarData.CartGoodsBean two = new ShopCarData.CartGoodsBean(list);
        ShopCarData.CartGoodsBean two2 = new ShopCarData.CartGoodsBean(list2);
        ShopCarData.CartGoodsBean two3 = new ShopCarData.CartGoodsBean(list3);
        List<ShopCarData.CartGoodsBean> listone = new ArrayList<>();
        listone.add(two);
        listone.add(two2);
        listone.add(two3);
        mShopCarData = new ShopCarData(listone);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void setClickEvent() {

    }

}

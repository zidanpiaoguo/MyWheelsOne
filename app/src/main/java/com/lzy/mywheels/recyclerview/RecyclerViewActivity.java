package com.lzy.mywheels.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.lzy.mywheels.App.BaseActivity;
import com.lzy.mywheels.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecyclerViewActivity extends BaseActivity {


    @BindView(R.id.rv_1)
    RecyclerView rv1;
    private List<String> mDatas = new ArrayList<>();
    private CommonAdapter<String> madapter;


    @Override
    protected void initContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_recycler_view);

    }

    @Override
    protected void initTitle() {


    }

    @Override
    protected void initView() {

//        //列表
//        rv1.addItemDecoration(new SimplePaddingDecoration(this,SimplePaddingDecoration.VERTICAL_LIST));
//
//        rv1.setLayoutManager(new LinearLayoutManager(this));

//        //表格
//        rv1.addItemDecoration(new DividerGridItemDecoration(this));
//        rv1.setLayoutManager(new GridLayoutManager(this, 4));


        //四列
        rv1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

//            // 四行
//        rv1.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL));

//        madapter = new RecyclerAdapter(this, mDatas);


        madapter = new CommonAdapter<String>(this, R.layout.item_goods_list, mDatas) {

            @Override
            public void convert(ViewHolder holder, String s) {
//                holder.setText(R.id.id_num,s);


            }
        };

        rv1.setAdapter(madapter);

    }

    @Override
    protected void initDate() {

        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        madapter.notifyDataSetChanged();
    }

}

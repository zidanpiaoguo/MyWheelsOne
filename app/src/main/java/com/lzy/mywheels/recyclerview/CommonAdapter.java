package com.lzy.mywheels.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zidan on 2017/9/22.
 */

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context mcontext;
    protected int mLayoutId;
    private List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context mcontext, int mLayoutId, List<T> mDatas) {
        this.mcontext = mcontext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.createViewHolder(mcontext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        convert(holder,mDatas.get(position));
    }

    public abstract void convert(ViewHolder holder,T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

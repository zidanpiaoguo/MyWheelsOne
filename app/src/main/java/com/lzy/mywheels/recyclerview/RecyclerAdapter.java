package com.lzy.mywheels.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.mywheels.R;

import java.util.List;

/**
 * Created by zidan on 2017/9/8.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";

    private Context context;
    private List<String> mdata;

    public RecyclerAdapter(Context context, List<String> mdata) {
        this.context = context;
        this.mdata = mdata;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tv.setText(mdata.get(position));
    }

    @Override
    public int getItemCount() {
        return mdata.size();

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public ViewHolder(View view) {
            super(view);
            tv = view.findViewById(R.id.id_num);
        }
    }




}

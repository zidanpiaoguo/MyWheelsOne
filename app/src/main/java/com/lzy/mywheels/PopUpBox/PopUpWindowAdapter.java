package com.lzy.mywheels.PopUpBox;

import android.content.Context;
import android.widget.TextView;

import com.lzy.mywheels.R;

import java.util.List;

/**
 * Created by zidan on 2017/10/10.
 */

public class PopUpWindowAdapter extends CommonAdapter<String> {


    public PopUpWindowAdapter(Context context, List mDatas, int mItemLayoutId) {
        super(context, mDatas, mItemLayoutId);
    }


    @Override
    public void convert(ViewHolder helper, String item) {
        TextView textView = helper.getView(R.id.tv_text);
        textView.setText(item);
    }

}

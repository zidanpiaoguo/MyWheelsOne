package com.lzy.mywheels.myview;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.lzy.mywheels.R;

/**
 * Created by zidan on 2017/10/14.
 */

public class LoadingDialog {
    private Context mContext;
    private CustomDialog mDialog;

    public LoadingDialog(Context mContext) {
        this.mContext = mContext;
        initView(mContext);
    }
    private void initView(Context context) {
        final View contentView= LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
        mDialog=new CustomDialog(context).setContentView(contentView, Gravity.CENTER).setCancelable(false)
                .setCanceledOnTouchOutside(false);

    }

    public void show(){
        if (!mDialog.isShowing()){

            mDialog.show();

        }
    }

    public void dismiss(){
        if (mDialog.isShowing()){
            mDialog.dismiss();
        }
    }

}

package com.lzy.mywheels.checkupdate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.mywheels.R;


/**
 * 自定义组件：购买数量，带减少增加按钮
 * Created by houJia 2017年5月18日
 */
public class AmountView extends LinearLayout implements View.OnClickListener {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量
    private int goods_storage = 1; //商品库存

    private OnAmountChangeListener mListener;

    private TextView tvAmount;
    private ImageView btnDecrease;
    private ImageView btnIncrease;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        tvAmount = (TextView) findViewById(R.id.tv_count);
        btnDecrease = (ImageView) findViewById(R.id.btn_decrease);
        btnIncrease = (ImageView) findViewById(R.id.btn_increase);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);

    }


    /**
     * 获取当前数量
     *
     * @return
     */
    public int getCurrentCount() {
        return amount;
    }

    public void add() {

    }

    public void sub() {

    }


    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {

        this.goods_storage = goods_storage;

    }
    public void setAmount(int amount) {
        this.amount = amount;
        tvAmount.setText(String.valueOf(amount));
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_decrease:
                if (amount > 1) {
                    amount--;
                    tvAmount.setText(String.valueOf(amount));
                } else {
                    Toast.makeText(getContext(), "最少买一件", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_increase:

                if (amount < goods_storage) {
                    amount++;
                    tvAmount.setText(String.valueOf(amount));
                } else {
                    Toast.makeText(getContext(), "已经最多啦，不能在加了", Toast.LENGTH_SHORT).show();
                }

                break;
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}

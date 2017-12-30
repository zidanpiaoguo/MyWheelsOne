package com.lzy.mywheels.PopUpBox;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lzy.mywheels.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zidan on 2017/10/10.
 */

public class SelectPopupWindow extends PopupWindow{

    private View mMenuView;
    private TextView bottom_text;
    private ListView listView_text;
    private PopUpWindowAdapter adapter;
    private List<String> list = new ArrayList<>();

    /**
     *
     * @param context
     * @param type  类型
     */
    public SelectPopupWindow(Context context, int type) {
        super(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        switch (type){
            case 1:
                mMenuView = inflater.inflate(R.layout.view_pop_up_window, null);
                bottom_text = mMenuView.findViewById(R.id.bottomBtn);
                listView_text = mMenuView.findViewById(R.id.lv_text);
                adapter = new PopUpWindowAdapter(context,list,R.layout.item_text);
                listView_text.setAdapter(adapter);
                break;
            case 2:


        }


        setTextListener();


        this.setContentView(mMenuView);

        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);

        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        this.setFocusable(true);

        this.setAnimationStyle(R.style.PopupAnimation);

        ColorDrawable dw = new ColorDrawable(0x00000000);

        this.setBackgroundDrawable(dw);

        //点击灰色外部，则取消弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });

    }



    /**
     * 最下面按钮的文字，默认是"取消"
     * @param s
     */
    public void setBottomText(String s){
        bottom_text.setText(s);
    }



    /**
     * 可以重新的最下面的一个按钮
     * @param onClickListener
     */
    public void setTextListener(View.OnClickListener onClickListener){
        bottom_text.setOnClickListener(onClickListener);

    }



    /**
     * 最下面一般的是取消按钮，所以这里直接实现
     */
    public void setTextListener(){
        bottom_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /**
     * 实现每个按钮的监听
     * @param itemClickListener
     */
    public void setListListener(AdapterView.OnItemClickListener itemClickListener){
        listView_text.setOnItemClickListener(itemClickListener);
    }

    /**
     * 设置数据
     * @param list
     */
    public void setListData(List<String> list){
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

}

package com.lzy.mywheels.PopUpBox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lzy.mywheels.App.MyToast;
import com.lzy.mywheels.R;

import java.util.ArrayList;
import java.util.List;

public class PopUpTestActivity extends AppCompatActivity {

    private Button button;
    private SelectPopupWindow menuWindow;
    private LinearLayout mainLayout;
    private List<String> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_test);
        initView();
    }

    private void initView() {
        button = findViewById(R.id.bt_pop_up);
        mainLayout  =findViewById(R.id.mainLayout);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuWindow = new SelectPopupWindow(PopUpTestActivity.this,1);
                menuWindow.setListListener(itemlistener);
                menuWindow.setTextListener();
                menuWindow.setListData(SetList2());
                menuWindow.showAtLocation(mainLayout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
    }

    public List<String> SetList(){
        list.clear();
        list.add("发布时间从早到晚");
        list.add("发布时间从晚到早");
        list.add("评论时间从高到低");
        list.add("评论时间从低到高");
        list.add("点赞时间从高到低");
        list.add("点赞时间从低到高");
        return list;
    }

    public List<String> SetList2(){
        list.clear();
        list.add("支付宝支付");
        list.add("微信支付");
        list.add("银行支付");
        list.add("联通支付");
        return list;
    }
    private AdapterView.OnItemClickListener itemlistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            switch (i){
                case 0:
                    MyToast.makeText("支付宝支付");
                    break;
                case 1:
                    MyToast.makeText("微信支付");
                    break;
                case 2:
                    MyToast.makeText("银行支付");
                    break;
                case 3:
                    MyToast.makeText("联通支付");
                    break;
                default:
                    break;

            }
//            menuWindow.dismiss();
//            TextView textView = view.findViewById(R.id.tv_text);
//            MyToast.makeText(textView.getText().toString().trim());

        }
    };

}

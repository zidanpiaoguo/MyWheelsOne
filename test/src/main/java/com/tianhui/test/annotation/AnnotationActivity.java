package com.tianhui.test.annotation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tianhui.test.R;
import com.tianhui.test.app.MyToast;

@live(R.layout.activity_annotation)
public class AnnotationActivity extends AppCompatActivity {

    @ViewNew( R.id.bt)
    Button bt;

    @ViewNew(R.id.tv)
    TextView tv;



    @ViewOnClick(R.id.bt)
    public void On(View view){
        Toast.makeText(this, "login password ", Toast.LENGTH_SHORT).show();
        tv.setText("我又一次已经改变了");
        MyToast.makeText("你点击了我");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnnotationTest.inject(this);
        tv.setText("我已经改变了");
    }


}

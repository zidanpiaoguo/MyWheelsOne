package com.lzy.mywheels.tabfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.mywheels.R;

/**
 *
 * Created by zidan on 2017/9/30.
 */

public class MyFragment extends Fragment {

    private TextView textView;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        String title= (String) getArguments().get("title");
        textView= (TextView) view.findViewById(R.id.txt_title);
        textView.setText(title);
    }

}

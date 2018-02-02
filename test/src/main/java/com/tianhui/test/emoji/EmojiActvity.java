package com.tianhui.test.emoji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tianhui.test.R;
import com.tianhui.test.app.MyToast;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by bullet on 2018/1/31.
 */

public class EmojiActvity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private EditText etText;
    private TextView text2;
    private Button btText;
    private TextView text3;

    String oldText, OnText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
    }

    private void init() {

        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                try {
                    text2.setText(EmojiUtils.emojiConvert(etText.getText().toString().trim()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        btText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    text3.setText(EmojiUtils.emojiRecovery(text2.getText().toString().trim()));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }






    private void set() {
        etText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int index = etText.getSelectionStart() - 1;
                Log.d(TAG, "afterTextChanged: " + index);
                if (index > 0) {
                    if (isEmojiCharacter(s.charAt(index))) {
                        Editable edit = etText.getText();
                        Log.d(TAG, "afterTextChanged: " + TextUtils.substring(edit.toString(),
                                s.length() - 2, s.length()));
                        String text = TextUtils.substring(edit.toString(), s.length() - 2, s.length());
                        MyToast.makeText(TextUtils.substring("Hello world!", s.length() - 2, s.length()));
//                        edit.delete(s.length() - 2, s.length());

                        try {
                            oldText = URLEncoder.encode(TextUtils.substring(edit.toString(),
                                    s.length() - 2, s.length()).trim(), "utf-8");
                            Log.d(TAG, "afterTextChanged:---- " + oldText);

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

//                        OnText = UnicodeFilter


                        edit.replace(s.length() - 2, s.length(), "你好");

                        Log.d(TAG, "afterTextChanged: ====== " + edit.toString());
                        MyToast.makeText("不支持输入表情符号");

                    }
                }

            }
        });
    }


    public static boolean isEmojiCharacter(char codePoint) {

        return !((codePoint == 0x0) ||
                (codePoint == 0x9)
                || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20)
                && codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000)
                && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));

    }

    private void initView() {
        etText = (EditText) findViewById(R.id.et_text);
        text2 = (TextView) findViewById(R.id.text2);
        btText = (Button) findViewById(R.id.bt_text);
        text3 = (TextView) findViewById(R.id.text3);
    }
}

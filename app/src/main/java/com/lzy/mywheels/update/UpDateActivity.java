
package com.lzy.mywheels.update;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lzy.mywheels.R;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class UpDateActivity extends AppCompatActivity {

    private static final String TAG = "UpDateActivity";
    @BindView(R.id.tv_update)
    TextView tvUpdate;

    private boolean confirmDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_date);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog("1.0", "http://106.14.249.176:8080/app/freebuy.apk");
            }
        });
    }

    //更新窗口
    private void showUpdateDialog(final String versionInfo, final String downloadUrl) {
        final AlertDialog mDialog = new AlertDialog.Builder(this).create();
        mDialog.show();
        mDialog.setCancelable(false);
        mDialog.getWindow().setContentView(R.layout.dialog_update_view);
        mDialog.getWindow().findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                finish();
            }
        });

        final TextView mTvCell = (TextView) mDialog.getWindow().findViewById(R.id.tv_detail);
        mTvCell.setText(versionInfo);

        mDialog.getWindow().findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Wifi下载
                if (isWIFIConnected()) {
                    Log.d("lzy", "wifi下载中");

                    Log.d("lzy", "downloadUrl======" + downloadUrl);
                    Log.d("lzy", "versionInfo======" + versionInfo);
                    downloadFile(downloadUrl, versionInfo);
                    mDialog.dismiss();
                } else {
                    mTvCell.setText("当前网络不是WIFI，确认下载？");
                    if (confirmDownload) {
                        //第二次点击
                        downloadFile(downloadUrl, versionInfo);
                        mDialog.dismiss();
                    }
                    confirmDownload = !confirmDownload;
                }
            }
        });
    }

    //下载应用
    public void downloadFile(String url, String version) {

//        if (!isNETConnected()) {
//            //跳转到应用市场
//            Intent intent = new Intent("android.intent.action.VIEW");
//            String market = "market://details?id=" + getPackageName();
//            intent.setData(Uri.parse(market));
//            startActivity(intent);
//            return;
//        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("下载中...");
        progressDialog.setMax(100);
        progressDialog.show();

        String fileDir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = "bullet_" + version + ".apk";

        Log.d("lzy", "url======" + url);
        OkHttpUtils.get().url(url).build()
                .execute(new FillCallBack(fileDir, fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        progressDialog.dismiss();
                        //跳转到应用市场
                        //我这里只是到百度。
                        Intent intent = new Intent("android.intent.action.VIEW");
                        String market = "www.baidu.com";
                        intent.setData(Uri.parse(market));
                        startActivity(intent);
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        progressDialog.dismiss();
                        String dirPath = response.getAbsolutePath(); //文件需有可读权限
                        Log.d(TAG, "onResponse: "+dirPath);
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                        //intent.setAction(android.content.Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.parse("file://" + dirPath), "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void inProgress(float progress) {
                        progressDialog.setProgress((int) (100 * progress));
                    }
                });
    }


    /**
     * 判断网络是否是WIFI
     */
    private boolean isWIFIConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo ni : info) {
                    if (ni.getState() == NetworkInfo.State.CONNECTED) {
                        Log.d("NET", "type = " + (ni.getType() == 0 ? "mobile" : ((ni.getType() == 1) ? "wifi" : "none")));
                        return (ni.getType() != 0 && ((ni.getType() == 1)));
                    }
                }
            }
        }
        return false;
    }


    /**
     * 判断网络是否是移动流量
     *
     * @return
     */
    public boolean checkNetworkConnection() {
        final ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable() || mobile.isAvailable())  //getState()方法是查询是否连接了数据网络
            return true;
        else
            return false;
    }

    private boolean isNETConnected() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo ni : info) {
                    if (ni.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

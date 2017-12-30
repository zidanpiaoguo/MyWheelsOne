package com.lzy.mywheels.okhttputils;

import android.content.Context;
import android.util.Log;



import org.json.JSONObject;

import java.util.Map;

/**
 * Created by zidan on 2017/9/11.
 */

public class ProtocolHelp {

//    /**
//     * SUCCESS：本次请求成功，服务端响应成功
//     * UNAUTH：用户未登录
//     * UNTOKEN：token验证失败/失效
//     * ERROR：服务端业务逻辑处理错误，需模态框显示响应结果中的describe字段，引导用户校正逻辑
//     * EXCEPTION：服务端出现不可预知的异常，需记录本次请求的日志信息
//     */
//    public enum Event {
//
//        SUCCESS,
//        UNAUTH,
//        UNTOKEN,
//        ERROR,
//        EXCEPTION,
//    }


    private static ProtocolHelp mInstance;
    private Context context;
//    private static LoadingDialog loadingDialog;

    public static ProtocolHelp getInstance(Context context) {

//        loadingDialog = new LoadingDialog(context);
        if (mInstance == null) {
            synchronized (ProtocolHelp.class) {
                if (mInstance == null) {
                    mInstance = new ProtocolHelp(context);
                }
            }
        }
        return mInstance;
    }

    private ProtocolHelp(Context context) {
        this.context = context;
    }

    /**
     * @param param        post的参数map
     * @param url          url
     * @param httpMethod   请求方式，（post/get）
     * @param cla          需要gson解析的class格式
     * @param httpCallBack 回调的错误信息message
     * @param isList       是否返回list
     */

    public void protocolHelp(Map<String, String> param, String url, ProtocolManager.HttpMethod httpMethod, final Class cla, final HttpCallBack httpCallBack, final boolean isList) {
//        loadingDialog.show();
        ProtocolManager.getInstance().request(param, url, new ProtocolManager.ReponseCallback() {
            @Override
            public void fail(String e) {
//                loadingDialog.dismiss();
                //                Toast.makeText(MyApplication.getInstance(), e, Toast.LENGTH_SHORT).show();
                if (httpCallBack != null) {
                    httpCallBack.fail(e);
                }
            }

            @Override
            public void success(String response) {
//                loadingDialog.dismiss();
                Log.i("request", response);
                JSONObject objData = JsonUtils.string2JSON(response);
                boolean bizSucc = objData.optBoolean("bizSucc");
                if (bizSucc) {
                    Object result = null;
                    if (cla == null) {
                        httpCallBack.success(response);
                    } else {
                        if (isList) {
                            result = JsonUtils.fromJson(response, cla, "");
                        } else {
                            result = JsonUtils.fromJson(response, cla);
                        }
                        if (result != null) {
                            if (httpCallBack != null) {
                                httpCallBack.success(result);
                            }
                        } else {
                            httpCallBack.fail("链接超时，请稍后重试");
                        }
                    }
                } else {
                    String errMsg = objData.optString("errMsg");

                    String errCode = objData.optString("errCode");
                    if (errCode != null) {
                    }

                    if (errMsg != null) {
                        httpCallBack.fail(errMsg);
                    } else {
                        httpCallBack.fail("链接超时，请稍后重试");
                    }

                }
            }
        }, httpMethod);

    }


    /**
     * 对象
     *
     * @param param        post的参数map
     * @param url          url
     * @param httpMethod   请求方式，（post/get）
     * @param cla          需要gson解析的class格式
     * @param httpCallBack 回调的错误信息message
     */
    public void protocolHelp(Map<String, String> param, String url, ProtocolManager.HttpMethod httpMethod, final Class cla, final HttpCallBack httpCallBack) {
        //        loadingDialog.show();
        ProtocolManager.getInstance().request(param, url, new ProtocolManager.ReponseCallback() {
            @Override
            public void fail(String e) {
//                loadingDialog.dismiss();
                //                Toast.makeText(MyApplication.getInstance(), e, Toast.LENGTH_SHORT).show();
                if (httpCallBack != null) {
                    httpCallBack.fail(e);
                }
            }

            @Override
            public void success(String response) {

                httpCallBack.success(response);
//                loadingDialog.dismiss();
//                ReturnData returnData = JsonUtils.fromJson(response, ReturnData.class);

//                String event = returnData.getEvent();
//                if (event.equals("SUCCESS")) {
//                    Object result = null;
//                    if (cla == null) {
//                        httpCallBack.success(response);
//                    } else {
//                        result = JsonUtils.fromJson(returnData.getData(), cla);
//                        if (result != null) {
//                            if (httpCallBack != null) {
//                                httpCallBack.success(response);
//                            }
//                        } else {
//                            httpCallBack.fail("链接超时，请稍后重试");
//                        }
                    }
//                } else {
//
//                }
//            }
        }, httpMethod);

    }


    public interface HttpCallBack {
        void fail(String message);

        void success(Object object);
    }


}

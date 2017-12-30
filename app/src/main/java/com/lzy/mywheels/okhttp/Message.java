package com.lzy.mywheels.okhttp;

import java.util.List;

/**
 * Created by zidan on 2017/9/16.
 */

public class Message {

    /**
     * data : [{"mid":1,"mcontent":"国庆节来临之际，公司上层特发此通知：国庆节共放8天假期（10月1日至10月8日包含中秋节），望各部门做好准备。","mname":"关于国庆节放假通知","mtime":"2017/09/16 10:08"},{"mid":2,"mcontent":"国庆节来临之际，公司上层特发此通知：国庆节共放8天假期（10月1日至10月8日包含中秋节），望各部门做好准备。","mname":"关于国庆节放假通知","mtime":"2017/09/15 13:55"}]
     * massage : 查询成功
     * status : 0
     */

    private String massage;
    private int status;
    /**
     * mid : 1
     * mcontent : 国庆节来临之际，公司上层特发此通知：国庆节共放8天假期（10月1日至10月8日包含中秋节），望各部门做好准备。
     * mname : 关于国庆节放假通知
     * mtime : 2017/09/16 10:08
     */

    private List<DataBean> data;

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int mid;
        private String mcontent;
        private String mname;
        private String mtime;

        public int getMid() {
            return mid;
        }

        public void setMid(int mid) {
            this.mid = mid;
        }

        public String getMcontent() {
            return mcontent;
        }

        public void setMcontent(String mcontent) {
            this.mcontent = mcontent;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getMtime() {
            return mtime;
        }

        public void setMtime(String mtime) {
            this.mtime = mtime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "mid=" + mid +
                    ", mcontent='" + mcontent + '\'' +
                    ", mname='" + mname + '\'' +
                    ", mtime='" + mtime + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "massage='" + massage + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }
}

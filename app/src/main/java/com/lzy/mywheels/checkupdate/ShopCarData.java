package com.lzy.mywheels.checkupdate;

import java.util.List;

/**
 * Created by zidan on 2017/9/21.
 */

public class ShopCarData {


    public ShopCarData(List<CartGoodsBean> cartGoods) {
        this.cartGoods = cartGoods;
    }

    private List<CartGoodsBean> cartGoods;


    public List<CartGoodsBean> getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(List<CartGoodsBean> cartGoods) {

        this.cartGoods = cartGoods;

    }

    public static class CartGoodsBean {


        public CartGoodsBean( List<ValidListBean> validList) {

            this.validList = validList;
        }

        /** 是否被选中 */
        private boolean isChecked;
        /** 是否是编辑状态 */
        private boolean isEditing;
        //
        public List<ValidListBean> validList;

        public List<ValidListBean> getValidList() {
            return validList;
        }
        public void setValidList(List<ValidListBean> validList) {
            this.validList = validList;
        }

        public static class ValidListBean {
            public ValidListBean() {

            }

            public ValidListBean(double stockPrice, int goodNum) {
                this.stockPrice = stockPrice;
                this.goodNum = goodNum;
            }

            /** 是否被选中 */
            private boolean isChecked;
            /** 是否是编辑状态 */
            private boolean isEditing;

            //价格
            public double stockPrice;

            //最大数量
            public int goodNum;

            public double getStockPrice() {
                return stockPrice;
            }

            public void setStockPrice(double stockPrice) {
                this.stockPrice = stockPrice;
            }

            public int getGoodNum() {
                return goodNum;
            }

            public void setGoodNum(int goodNum) {
                this.goodNum = goodNum;
            }

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public boolean isEditing() {
                return isEditing;
            }

            public void setEditing(boolean editing) {
                isEditing = editing;
            }
        }


        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public boolean isEditing() {
            return isEditing;
        }

        public void setEditing(boolean editing) {
            isEditing = editing;
        }
    }
}

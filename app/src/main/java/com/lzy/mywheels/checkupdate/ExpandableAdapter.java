package com.lzy.mywheels.checkupdate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.mywheels.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zidan on 2017/9/20.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;
//    private String[] group = {"衣服", "食品", "水果"};
//    private String[] cloths = {"衬衫", "西裤", "内衣"};
//    private String[] food = {"面包", "蛋糕", "可乐", "橙汁"};
//    private String[] fruits = {"苹果", "香蕉", "草莓"};
    private ShopCarData mShopCarData;
//    private List<String> groupList = null;

    OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener;

    //点击编辑的回掉
    OnGoodsCheckedChangeListener onGoodsCheckedChangeListener;

    int totalCount = 0;
    double totalPrice = 0.00;
    private List<ShopCarData.CartGoodsBean> parentMapList = null;
    private List<List<ShopCarData.CartGoodsBean.ValidListBean>> childMaplist = null;
//    private List<List<String>> itemList = null;

    protected ExpandableAdapter(Context context,ShopCarData data) {
        this.context = context;
        mShopCarData = data;
        parentMapList = new ArrayList<>();
        childMaplist = new ArrayList<>();
        if (mShopCarData!=null){
            if(mShopCarData.getCartGoods()!=null){
                for (ShopCarData.CartGoodsBean item : mShopCarData.getCartGoods()){
                    //提供父列表的数据
                    item.setChecked(false);
                    item.setEditing(false);
                    parentMapList.add(item);
                    //当前父列表的子列表
                    List<ShopCarData.CartGoodsBean.ValidListBean> childList =
                            new ArrayList<ShopCarData.CartGoodsBean.ValidListBean>();
                    for (int i = 0; i < item.getValidList().size(); i++) {
                        ShopCarData.CartGoodsBean.ValidListBean goods = item.getValidList().get(i);
                        goods.setEditing(false);
                        goods.setChecked(false);
                        childList.add(goods);
                    }
                    childMaplist.add(childList);
                }
            }
        }

//        setListData();
    }

    /**
     * 向list中添加数据
     */

    //获取当前父item的数据数量
    @Override
    public int getGroupCount() {
        //返回组数量
        return parentMapList.size();
    }
    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        //返回当前组的节点数量
        return childMaplist.get(groupPosition).size();
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        //返回分组对象，用于一些数据传递，在事件处理时可直接取得和分组相关的数据
        return parentMapList.get(groupPosition);
    }

    //得到子item需要关联的数据

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //设置子节点数据
        return childMaplist.get(groupPosition).get(childPosition);
    }

    //        获取指定分组的ID, 这个ID必须是唯一的
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;

    }

    //      分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。

    @Override
    public boolean hasStableIds() {
        return false;
    }
    //  获取显示指定分组的视图
    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        //设置组显示View，这里可是设置布局文件引用
        GroupViewHolder holder = null;
        if (convertView == null) {
            holder = new GroupViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_elv_group,parent,false);
            holder.mCbBrand = convertView.findViewById(R.id.cb_brand);
            convertView.setTag(holder);

        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }
        final ShopCarData.CartGoodsBean data = parentMapList.get(groupPosition);
        holder.mCbBrand.setChecked(data.isChecked());
        final boolean nowBeanChecked = data.isChecked();


        holder.mCbBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setupOneParentAllChildChecked(!nowBeanChecked, groupPosition);

                //控制总checkedbox 接口
                onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
            }
        });

        return convertView;
    }
    class GroupViewHolder {
        ImageView mIvBrand;
        TextView mTvBrandName;
        CheckBox mCbBrand;
        RelativeLayout mRlGroupItem;

    }


    // 获取显示指定分组中的指定子选项的视图
    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        //设置节点显示View，这里可是设置布局文件引用
        ChildViewHolder holder = null;
        if (convertView == null) {
            holder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order_elv_child,parent,false);
            holder.iv_check_good = convertView.findViewById(R.id.iv_check_good);
            holder.mAmountView = convertView.findViewById(R.id.amount_view);
            holder.mTvGoodsPrice =  convertView.findViewById(R.id.tv_goods_price);
            convertView.setTag(holder);

        } else {
            holder = (ChildViewHolder) convertView.getTag();
        }
        final ShopCarData.CartGoodsBean.ValidListBean data =
                childMaplist.get(groupPosition).get(childPosition);
        if(data !=null){

            holder.iv_check_good.setChecked(data.isChecked());
            holder.mAmountView.setGoods_storage(data.getGoodNum());
            holder.mTvGoodsPrice.setText("¥" + data.stockPrice);
            holder.mAmountView.setAmount(1);

            holder.iv_check_good.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean nowBeanChecked = data.isChecked();
                    //更新数据
                    data.setChecked(!nowBeanChecked);

                    boolean isOneParentAllChildIsChecked = dealOneParentAllChildIsChecked(groupPosition);

                    ShopCarData.CartGoodsBean  storeBean =  parentMapList.get(groupPosition);
                    storeBean.setChecked(isOneParentAllChildIsChecked);
                    notifyDataSetChanged();
                    //控制总checkedbox 接口
                    onAllCheckedBoxNeedChangeListener.onCheckedBoxNeedChange(dealAllParentIsChecked());
                    dealPrice();
                }
            });
        }
        return convertView;
    }

    class ChildViewHolder {

        //购买的数量
        ImageView mIvGoods;
        TextView mTvGoodsName;
        TextView mTvGoodsParam;
        TextView mTvGoodsPrice;
        TextView mTvMonth;
        TextView mTvPriceOld;
        AmountView mAmountView;
        LinearLayout mLlChildItem;
        CheckBox iv_check_good;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    /**
     * 判断商铺的选中状态
     * @param groupPosition
     * @return
     */

    public boolean dealOneParentAllChildIsChecked(int groupPosition) {
        // StoreBean storeBean= (StoreBean) parentMapList.get(groupPosition).get("parentName");
        List<ShopCarData.CartGoodsBean.ValidListBean> childMapList = childMaplist.get(groupPosition);
        for (int j = 0; j < childMapList.size(); j++) {
            ShopCarData.CartGoodsBean.ValidListBean goodsBean =  childMapList.get(j);
            if (!goodsBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }


    /**
     * 点击总的商店则全选
     * @param isChecked
     * @param groupPosition
     */
    private void setupOneParentAllChildChecked(boolean isChecked, int groupPosition) {

        ShopCarData.CartGoodsBean storeBean = parentMapList.get(groupPosition);
        storeBean.setChecked(isChecked);

        List<ShopCarData.CartGoodsBean.ValidListBean> childMapList = childMaplist.get(groupPosition);
        Log.d("lzy","第二个"+childMaplist.get(1).get(1).isChecked());
        for (int j = 0; j < childMapList.size(); j++) {
            Log.d("lzy","childMapList.size()==="+childMapList.size());
            ShopCarData.CartGoodsBean.ValidListBean goodsBean =  childMapList.get(j);
            goodsBean.setChecked(isChecked);
        }
        notifyDataSetChanged();
        dealPrice();
    }

    /**
     * 判断总按钮选中状态
     *
     * @return
     */
    public boolean dealAllParentIsChecked() {
        for (int i = 0; i < parentMapList.size(); i++) {
            ShopCarData.CartGoodsBean storeBean =  parentMapList.get(i);
            if (!storeBean.isChecked()) {
                return false;//如果有一个没选择  就false
            }
        }
        return true;
    }


    //选中商品的数量
    List<String> mCartNumList = new ArrayList<>();
    //选中商品的id
    List<String> mCartNoList = new ArrayList<>();


    /**
     * 计算总钱数
     */
    public void dealPrice() {
        mCartNumList.clear();
        mCartNoList.clear();
        // showList();
        totalCount = 0;
        totalPrice = 0.00;
        for (int i = 0; i < parentMapList.size(); i++) {
            //StoreBean storeBean= (StoreBean) parentMapList.get(i).get("parentName");
            List<ShopCarData.CartGoodsBean.ValidListBean> childMapList = childMaplist.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                ShopCarData.CartGoodsBean.ValidListBean goodsBean =  childMapList.get(j);
//                int count = 1;
//                if (goodsBean.stockPrice!=0){
//                    discountPrice=goodsBean.stockPrice;
//                }else {
//                    discountPrice = goodsBean.price;
//                }
//                if (goodsBean.isChecked) {
//                    totalCount=totalCount+goodsBean.goodNum;//选择多商品
//                    totalPrice += discountPrice * goodsBean.goodNum;
//                    String selectGoods = goodsBean.cartNo + "_" + goodsBean.goodNum;
//                    mCartNumList.add(selectGoods);
//                    mCartNoList.add(goodsBean.cartNo);
//                }

            }
        }
        //计算回调
//        onGoodsCheckedChangeListener.onGoodsCheckedChange(totalCount, totalPrice,mCartNumList,mCartNoList );
    }

    /**
     * 供全选按钮调用
     * @param isChecked
     */

    public void setupAllChecked(boolean isChecked) {
        for (int i = 0; i < parentMapList.size(); i++) {
            ShopCarData.CartGoodsBean storeBean = parentMapList.get(i);
            storeBean.setChecked(isChecked);
            List<ShopCarData.CartGoodsBean.ValidListBean> childMapList = childMaplist.get(i);
            for (int j = 0; j < childMapList.size(); j++) {
                ShopCarData.CartGoodsBean.ValidListBean goodsBean =  childMapList.get(j);
                goodsBean.setChecked(isChecked);
            }
        }
        notifyDataSetChanged();
        dealPrice();
    }

    /**
     * 点击编辑的毁掉接口
     */


    public interface OnGoodsCheckedChangeListener {
        void onGoodsCheckedChange(int totalCount, double totalPrice, List<String> mCartNumList, List<String> mCartNoList);
    }


    /**
     * 总选中按钮回掉接口
     */

    public interface OnAllCheckedBoxNeedChangeListener {
        void onCheckedBoxNeedChange(boolean allParentIsChecked);
    }

    public void setOnAllCheckedBoxNeedChangeListener(OnAllCheckedBoxNeedChangeListener onAllCheckedBoxNeedChangeListener) {
        this.onAllCheckedBoxNeedChangeListener = onAllCheckedBoxNeedChangeListener;
    }

}

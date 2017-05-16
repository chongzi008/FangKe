package fangke.com.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fangke.com.activity.R;
import fangke.com.bean.CityBean;

/**
 * 定位地图的adapter
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>implements View.OnClickListener {
    private Context mContext;
    private List<CityBean> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener = null;

    public CityAdapter(Context mContext, List<CityBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.locationmap_item_city, parent, false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CityBean cityBean = mDatas.get(position);
        holder.tvCity.setText(cityBean.getCity());
        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setBackgroundResource(R.drawable.cityadapter_item_bg);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    public  interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.locationmap_tvCity);
        }
    }


}

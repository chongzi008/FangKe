package fangke.com.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fangke.com.activity.HomeActivity;
import fangke.com.activity.R;
import fangke.com.bean.CycleImageBean;
import fangke.com.view.ImageCycleViewLinearLayout;
import utils.DispalyUtil;

/**
 * 我的
 *
 * @author ChongZi007
 * @time 2017/3/31 10:08
 * @参数
 * @return
 */

public class MineFragment extends Fragment {

    private View view;
    private HomeActivity mActivity;
    private int[] imageIds;
    private ImageView[] mImageViews;
    /**
     * 手机密度
     */
    private float mScale;
    private ImageView mImageView;
    private LinearLayout mine_viewGroup;
    private int mImageIndex;
    private GridView mine_gv;
    private String[] title;
    private int[] imags;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mActivity = (HomeActivity) getActivity();
        initdatas();
        initViews(inflater);
        return view;


    }

    private void initdatas() {
        imageIds = new int[]{R.drawable.newhouse1, R.drawable.newhouse2, R.drawable.newhouse3};

    }

    private void initViews(LayoutInflater inflater) {
        title = new String[]{"优惠", "订单", "我的房子", "经纪人动态", "通讯录", "我的留言", "我的问答", "房贷计算器", "公积金查询"
                , "意见反馈", "看房记录", "设置", "58同城"};
        imags = new int[]{
                R.drawable.mine_grid_youhui, R.drawable.mine_grid_dingdan, R.drawable.mine_grid_house,
                R.drawable.mine_grid_agent, R.drawable.mine_grid_tongxinlu, R.drawable.mine_grid_liuyan,
                R.drawable.mine_grid_anser, R.drawable.mine_grid__calculate, R.drawable.mine_grid_gongjijin,
                R.drawable.mine_grid__feedback, R.drawable.mine_grid_record, R.drawable.mine_grid_setting, R.drawable.mine_grid_58
        };
        view = inflater.inflate(R.layout.fragment_mine, null);
        mScale = mActivity.getResources().getDisplayMetrics().density;
        ViewPager mine_pager = (ViewPager) view.findViewById(R.id.mine_pager);//viewpager
        mine_viewGroup = (LinearLayout) view.findViewById(R.id.mine_viewGroup);
        mine_gv = (GridView) view.findViewById(R.id.mine_gv);
        mine_gv.setAdapter(new mGridViewAdapter());
        View nestedScrollView = view.findViewById(R.id.nestedScrollView);
        mImageViews = new ImageView[imageIds.length];
        for (int i = 0; i < imageIds.length; i++) {
            mImageView = new ImageView(mActivity);
            int imageParams = (int) (mScale * 20 + 0.5f);// XP与DP转换，适应不同分辨率
            int imagePadding = (int) (mScale * 5 + 0.5f);
            LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            layout.setMargins(3, 0, 3, 0);
            mImageView.setLayoutParams(layout);
            //mImageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
            mImageViews[i] = mImageView;
            if (i == 0) {
                mImageViews[i].setBackgroundResource(R.drawable.overseas_icon_point_pre);
            } else {
                mImageViews[i].setBackgroundResource(R.drawable.overseas_icon_point);
            }
            mine_viewGroup.addView(mImageViews[i]);
        }
        mine_pager.setAdapter(new MyPagerAdapter());
        mine_pager.addOnPageChangeListener(new GuidePageChangeListener());
    }

    private class MyPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return imageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = View.inflate(mActivity, R.layout.mine_pager_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.mine_pager_img);
            imageView.setImageResource(imageIds[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);

        }

    }

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int index) {
            // 设置图片滚动指示器背景
            mImageIndex = index;
            mImageViews[index % imageIds.length].setBackgroundResource(R.drawable.overseas_icon_point_pre);
            for (int i = 0; i < mImageViews.length; i++) {
                if (index % imageIds.length != i) {
                    mImageViews[i].setBackgroundResource(R.drawable.overseas_icon_point);
                }
            }

        }

    }


    /**
     * 实现gridview用的适配器
     */
    class mGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return imags.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GridViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(mActivity, R.layout.mine_grid_item, null);
                 viewHolder = new GridViewHolder();
                viewHolder.tlt = (TextView) convertView.findViewById(R.id.mine_grid_tv);
                viewHolder.image = (ImageView) convertView.findViewById(R.id.mine_grid_img);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (GridViewHolder) convertView.getTag();
            }
            viewHolder.tlt.setText(title[position]);
            viewHolder.image.setImageResource(imags[position]);
            ViewGroup.LayoutParams params = viewHolder.image.getLayoutParams();
            params.width = DispalyUtil.dip2px(mActivity, 30);
            params.height = DispalyUtil.dip2px(mActivity, 30);
            viewHolder.image.setLayoutParams(params);


            return convertView;
        }
    }

    class GridViewHolder {
        public TextView tlt;
        public ImageView image;
    }
}

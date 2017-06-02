package fangke.com.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import fangke.com.fragment.HomeFragment;
import fangke.com.fragment.MineFragment;
import fangke.com.fragment.RecommendFragment;
import fangke.com.fragment.WeChatFragment;

/**
 * 主页面的开发
 */
public class HomeActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private MyFragmentStatemPagerAdapter mAdapter;
    private ImageView imgHome;
    private ImageView imgWeChat;
    private ImageView imgTuiJian;
    private ImageView imgMime;
    private TextView guide_tvhome;
    private TextView guide_tvWechat;
    private TextView guide_tvtuijian;
    private TextView guide_tvmime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        mViewPager = (ViewPager) findViewById(R.id.home_viewpager);
        //3.0及其以上版本，只需继承Activity，通过getFragmentManager获取事物
        FragmentManager fm = getSupportFragmentManager();
        //初始化自定义适配器
        mAdapter = new MyFragmentStatemPagerAdapter(fm);
        //绑定自定义适配器
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(4);
        imgHome = (ImageView) findViewById(R.id.home_home);//首页
        guide_tvhome = (TextView) findViewById(R.id.home_guide_tvhome);//首页
        imgWeChat = (ImageView) findViewById(R.id.home_wechact); //微聊
        guide_tvWechat = (TextView) findViewById(R.id.home_guide_tvWechat); //微聊
        imgTuiJian = (ImageView) findViewById(R.id.home_tuijian);//推荐
        guide_tvtuijian = (TextView) findViewById(R.id.home_guide_tvtuijian); //推荐
        imgMime = (ImageView) findViewById(R.id.home_mime);//我的
        guide_tvmime = (TextView) findViewById(R.id.home_guide_tvmime); //我的
        HomeUiChange();
        //处理最下面四个功能的点击事件
        imgHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //跳转到主页面
                mViewPager.setCurrentItem(0);
            }
        });
        guide_tvhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到主页面
                mViewPager.setCurrentItem(0);
            }
        });

        guide_tvWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到微聊页面
                mViewPager.setCurrentItem(1);
            }
        });
        imgWeChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到微聊页面
                mViewPager.setCurrentItem(1);
            }
        });

        guide_tvtuijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //跳转到推荐页面
                mViewPager.setCurrentItem(2);
            }
        });

        imgTuiJian.setOnClickListener(new View.OnClickListener() {
            //跳转到推荐页面
            @Override
            public void onClick(View v) {

                mViewPager.setCurrentItem(2);
            }
        });
        imgMime.setOnClickListener(new View.OnClickListener() {
            //跳转到 我的 页面
            @Override
            public void onClick(View v) {

                mViewPager.setCurrentItem(3);
            }
        });

        guide_tvmime.setOnClickListener(new View.OnClickListener() {
            //跳转到 我的 页面
            @Override
            public void onClick(View v) {

                mViewPager.setCurrentItem(3);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    HomeUiChange();

                } else if (position == 1) {

                    WechatUiChange();
                } else if (position == 2) {
                    TuijianUiChange();

                } else if (position == 3) {
                    MineUiChange();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void HomeUiChange() {
        imgHome.setImageResource(R.drawable.home_select_home);
        guide_tvhome.setTextColor(getResources().getColor(R.color.mblue));
        imgWeChat.setImageResource(R.drawable.home_wechact);
        guide_tvWechat.setTextColor(getResources().getColor(R.color.gray_dark));
        imgTuiJian.setImageResource(R.drawable.home_tuijian);
        guide_tvtuijian.setTextColor(getResources().getColor(R.color.gray_dark));
        imgMime.setImageResource(R.drawable.home_my);
        guide_tvmime.setTextColor(getResources().getColor(R.color.gray_dark));

    }

    private void WechatUiChange() {
        imgHome.setImageResource(R.drawable.home_mhome);
        guide_tvhome.setTextColor(getResources().getColor(R.color.gray_dark));
        imgWeChat.setImageResource(R.drawable.home_select_wechat);
        guide_tvWechat.setTextColor(getResources().getColor(R.color.mblue));
        imgTuiJian.setImageResource(R.drawable.home_tuijian);
        guide_tvtuijian.setTextColor(getResources().getColor(R.color.gray_dark));
        imgMime.setImageResource(R.drawable.home_my);
        guide_tvmime.setTextColor(getResources().getColor(R.color.gray_dark));

    }

    private void TuijianUiChange() {
        imgHome.setImageResource(R.drawable.home_mhome);
        guide_tvhome.setTextColor(getResources().getColor(R.color.gray_dark));
        imgWeChat.setImageResource(R.drawable.home_wechact);
        guide_tvWechat.setTextColor(getResources().getColor(R.color.gray_dark));
        imgTuiJian.setImageResource(R.drawable.home_select_tuijian);
        guide_tvtuijian.setTextColor(getResources().getColor(R.color.mblue));
        imgMime.setImageResource(R.drawable.home_my);
        guide_tvmime.setTextColor(getResources().getColor(R.color.gray_dark));

    }

    private void MineUiChange() {
        imgHome.setImageResource(R.drawable.home_mhome);
        guide_tvhome.setTextColor(getResources().getColor(R.color.gray_dark));
        imgWeChat.setImageResource(R.drawable.home_wechact);
        guide_tvWechat.setTextColor(getResources().getColor(R.color.gray_dark));
        imgTuiJian.setImageResource(R.drawable.home_tuijian);
        guide_tvtuijian.setTextColor(getResources().getColor(R.color.gray_dark));
        imgMime.setImageResource(R.drawable.home_select_mime);
        guide_tvmime.setTextColor(getResources().getColor(R.color.mblue));

    }


    class MyFragmentStatemPagerAdapter extends FragmentStatePagerAdapter {

        public MyFragmentStatemPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new WeChatFragment();
                case 2:
                    return new RecommendFragment();
                case 3:
                    return new MineFragment();

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}

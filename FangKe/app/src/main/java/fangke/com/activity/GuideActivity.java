package fangke.com.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
/**
 * 引导页页面
 *@author ChongZi007
 *@time 2017/3/30 20:58
 *@参数
 *@return
*/
public class GuideActivity extends Activity {


    ViewPager viewPager;
    private int[] res = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4};
    private ArrayList imageList;
    private Button button;
    private LinearLayout llPointGroup;
    private View viewRedPoint;
    private int mPointWidth;//圆点距离

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        viewRedPoint = findViewById(R.id.view_red_point);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        button = (Button) findViewById(R.id.btn);
        initViews();
        viewPager.setAdapter(new mPageAdapter());
         viewPager.setOnPageChangeListener(new GuidePageListener());

    }

    /**
     * 点击跳转到主页面
     * @param v
     */
    public void click(View v) {

        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    //初始化view
    private void initViews() {
        imageList = new ArrayList();
        for (int i = 0; i < res.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(res[i]);
            imageList.add(imageView);
        }

        // 初始化引导页的小圆点
        for (int i = 0; i < imageList.size(); i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);// 设置引导页默认圆点

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    30, 30);
            if (i > 0) {
                params.leftMargin = 10;// 设置圆点间隔
            }

            point.setLayoutParams(params);// 设置圆点的大小

            llPointGroup.addView(point);// 将圆点添加给线性布局

        }

        // 获取视图树, 对layout结束事件进行监听
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    // 当layout执行结束后回调此方法
                    @Override
                    public void onGlobalLayout() {
                        System.out.println("layout 结束");
                        llPointGroup.getViewTreeObserver()
                                .removeGlobalOnLayoutListener(this);
                        mPointWidth = llPointGroup.getChildAt(1).getLeft()
                                - llPointGroup.getChildAt(0).getLeft();
                        System.out.println("圆点距离:" + mPointWidth);
                    }
                });

    }

        class mPageAdapter extends PagerAdapter {


            @Override
            public int getCount() {
                return imageList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView((View) imageList.get(position));
                return imageList.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        }

    class GuidePageListener extends PagerAdapter implements ViewPager.OnPageChangeListener {

        // 滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

            int len = (int) (mPointWidth * positionOffset) + position
                    * mPointWidth;
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint
                    .getLayoutParams();// 获取当前红点的布局参数
            params.leftMargin = len;// 设置左边距

            viewRedPoint.setLayoutParams(params);// 重新给小红点设置布局参数
        }

        // 某个页面被选中
        @Override
        public void onPageSelected(int position) {
            if (position == imageList.size() - 1) {// 最后一个页面
                button.setVisibility(View.VISIBLE);// 显示开始体验的按钮
            } else {

                button.setVisibility(View.INVISIBLE);
            }
        }

        // 滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
    }
    }

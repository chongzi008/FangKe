package fangke.com.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import fangke.com.bean.CycleImageBean;
import fangke.com.view.ImageCycleViewLinearLayout;
import fangke.com.view.PullLeftLoadMoreLayout;

/**
 * 海外房产页面
 *
 * @author ChongZi007
 * @time 2017/3/30 20:59
 * @参数
 * @return
 */

public class OverseasHouseActivity extends Activity {

    private ListView overseas_lv;
    private ArrayList<CycleImageBean> infos = new ArrayList<CycleImageBean>();
    private int[] imageIds = new int[]{R.drawable.overseas1, R.drawable.overseas2, R.drawable.overseas3};
    private ImageCycleViewLinearLayout cycleImageview;
    private static int[] images;
    private LinearLayout oversears_container_ll;
    private RecyclerView mRecyclerView;
    private PullLeftLoadMoreLayout mPullLeftLoadMoreLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overseas_house);
        initDatas();
        initViews();
        initListener();
    }


    private void initListener() {
    }

    private void initDatas() {


        for (int i = 0; i < imageIds.length; i++) {
            CycleImageBean info = new CycleImageBean();
            info.setImgId(imageIds[i]);
            infos.add(info);
        }

        images = new int[]{R.drawable.usa, R.drawable.england, R.drawable.putaoya,
                R.drawable.xinjiapo, R.drawable.malaixiya, R.drawable.cannada, R.drawable.morearea};
    }

    private void initViews() {
        overseas_lv = (ListView) findViewById(R.id.overseas_lv);
        View headView = View.inflate(OverseasHouseActivity.this, R.layout.overseashouse_headview, null);
        overseas_lv.addHeaderView(headView);
        oversears_container_ll = (LinearLayout) headView.findViewById(R.id.oversears_container_ll);
        cycleImageview = (ImageCycleViewLinearLayout) headView.findViewById(R.id.overseas_cycleImageview);
        cycleImageview.setImageResources(infos, null);
        //中下部左拉加载配置基本参数
        mRecyclerView = (RecyclerView) findViewById(R.id.overseas_area_recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter());


        mPullLeftLoadMoreLayout = (PullLeftLoadMoreLayout) findViewById(R.id.overseas_area_leftloadmorelyout);
        mPullLeftLoadMoreLayout.addView(getResources().getDimensionPixelOffset(R.dimen.item_img));
        mPullLeftLoadMoreLayout.setFillLoadingColor(ContextCompat.getColor(this, R.color.mblue));
        mPullLeftLoadMoreLayout.setOnGoListener(new PullLeftLoadMoreLayout.OnNoticeGoListener() {
            @Override
            public void go() {
                Toast.makeText(OverseasHouseActivity.this, "这是你的加载更多页面", Toast.LENGTH_SHORT).show();
            }
        });


        overseas_lv.setAdapter(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        cycleImageview.startImageCycle();
    }

    ;

    @Override
    protected void onPause() {
        super.onPause();
        cycleImageview.pushImageCycle();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cycleImageview.pushImageCycle();
    }


    static class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.overseas_item_recyclerview, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            viewHolder.mImg = (ImageView) v.findViewById(R.id.overseas_area_recyclerview_item_image);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mImg.setImageResource(images[position]);
        }

        @Override
        public int getItemCount() {
            return images.length;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mImg;

            public ViewHolder(View itemView) {
                super(itemView);
            }

        }
    }

}

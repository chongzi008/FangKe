package fangke.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import fangke.com.adapter.CityAdapter;
import fangke.com.bean.CityBean;
import fangke.com.decorlation.DividerItemDecoration;
import fangke.com.decorlation.TitleItemDecoration;
import fangke.com.view.IndexBar;


/**
 * 这是定位页面
 *
 * @author ChongZi007
 * @time 2017/5/10 9:19
 * @参数
 * @return
 */
public class LocationMapActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas;
    private TitleItemDecoration mDecoration;
    private TextView mTvSideBarHint;
    private IndexBar mIndexBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_map);

        mRv = (RecyclerView) findViewById(R.id.locationmap_rec);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        initDatas();
        mRv.setAdapter(mAdapter = new CityAdapter(this, mDatas));
        mRv.addItemDecoration(mDecoration = new TitleItemDecoration(this, mDatas));
        //如果add两个，那么按照先后顺序，依次渲染。
        mRv.addItemDecoration(new DividerItemDecoration(LocationMapActivity.this, DividerItemDecoration.VERTICAL_LIST));
        //使用indexBar
        mTvSideBarHint = (TextView) findViewById(R.id.tvSideBarHint);//HintTextView
        mIndexBar = (IndexBar) findViewById(R.id.indexBar);//IndexBar
        mIndexBar.setmPressedShowTextView(mTvSideBarHint)//设置HintTextView
                .setNeedRealIndex(true)//设置需要真实的索引
                .setmLayoutManager(mManager)//设置RecyclerView的LayoutManager
                .setmSourceDatas(mDatas);//设置数据源
    }

    private void initDatas() {
        mDatas = new ArrayList<>();
        mDatas.add(new CityBean("安徽"));
        mDatas.add(new CityBean("北京"));
        mDatas.add(new CityBean("福建"));
        mDatas.add(new CityBean("广东"));
        mDatas.add(new CityBean("甘肃"));
        mDatas.add(new CityBean("贵州"));
        mDatas.add(new CityBean("广西"));
        mDatas.add(new CityBean("河南"));
        mDatas.add(new CityBean("湖北"));
        mDatas.add(new CityBean("湖南"));
        mDatas.add(new CityBean("河北"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("江苏"));
        mDatas.add(new CityBean("日本"));
        mDatas.add(new CityBean("日本"));
        mDatas.add(new CityBean("日本"));
        mDatas.add(new CityBean("巴东"));
        mDatas.add(new CityBean("八公山区"));
        mDatas.add(new CityBean("沧县"));
        mDatas.add(new CityBean("大金山"));
        mDatas.add(new CityBean("峨眉山"));
        mDatas.add(new CityBean("港口区"));
        mDatas.add(new CityBean("海南"));
        mDatas.add(new CityBean("京东"));
        mDatas.add(new CityBean("美国"));
        mDatas.add(new CityBean("南山"));
        mDatas.add(new CityBean("仙山"));
        mDatas.add(new CityBean("重庆"));
        mDatas.add(new CityBean("长沙"));
        mDatas.add(new CityBean("长白山"));


    }
}

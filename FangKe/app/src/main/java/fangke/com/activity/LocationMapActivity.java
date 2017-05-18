package fangke.com.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

import java.util.ArrayList;
import java.util.List;

import fangke.com.adapter.CityAdapter;
import fangke.com.bean.CityBean;
import fangke.com.decorlation.DividerItemDecoration;
import fangke.com.decorlation.TitleItemDecoration;
import fangke.com.view.IndexBar;

import static android.R.attr.data;


/**
 * 这是定位页面
 *
 * @author ChongZi007
 * @time 2017/5/10 9:19
 * @参数
 * @return
 */
public class LocationMapActivity extends Activity {
    private RecyclerView mRv;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mManager;
    private List<CityBean> mDatas;
    private TitleItemDecoration mDecoration;
    private TextView mTvSideBarHint;
    private IndexBar mIndexBar;
    final String[] PERMISSION = new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,  //定位权限
    };
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_location_map);
        sp = getSharedPreferences("maps", Context.MODE_PRIVATE);
/**
 * 设置Android6.0的权限申请
 */
        if (ContextCompat.checkSelfPermission(LocationMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Android 6.0申请权限
            ActivityCompat.requestPermissions(LocationMapActivity.this, PERMISSION, 1);
            startLocation();

        } else {
            startLocation();
        }


        mRv = (RecyclerView) findViewById(R.id.locationmap_rec);
        mRv.setLayoutManager(mManager = new LinearLayoutManager(this));
        initDatas();
        mAdapter = new CityAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
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

        ((CityAdapter) mAdapter).setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(LocationMapActivity.this, mDatas.get(position).getCity(), Toast.LENGTH_SHORT).show();
            }
        });

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

        //为热门城市
        mDatas.add(new CityBean("#不灭山"));
        mDatas.add(new CityBean("#不老山"));
        mDatas.add(new CityBean("#长龙山"));
        mDatas.add(new CityBean("#百岁山"));
        //为gprs定位
        mDatas.add(new CityBean("*---"));

    }

    public void startLocation() {
        mLocationListener = new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //可在其中解析amapLocation获取相应内容。
                        //把数据保存在sahareprefess中
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("location",aMapLocation.getCity());
                        editor.commit();



                    } else {
                        //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                        Log.e("AmapError", "location Error, ErrCode:"
                                + aMapLocation.getErrorCode() + ", errInfo:"
                                + aMapLocation.getErrorInfo());
                    }
                }
            }
        };
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(true);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();


    }
}

package fangke.com.activity;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;


/**
 * 地图页面
 *
 * @author ChongZi007
 * @time 2017/3/30 20:59
 * @参数
 * @return
 */

public class MapActivity extends Activity {

    private MapView mMapView;
    private ImageView map_mycurrentposition;
    private AMap aMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initViews(savedInstanceState);
        initListener();

    }

    /**
     * 初始化视图
     *
     * @param savedInstanceState
     */
    private void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_map);
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);// 此方法必须重写
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        map_mycurrentposition = (ImageView) findViewById(R.id.map_mycurrentposition);

    }

    /**
     * 初始化组件监听事件
     */
    private void initListener() {
        //初始化我的位置的监听事件
        map_mycurrentposition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击时候让地图显示到自己的当前位置
                MyLocationStyle myLocationStyle;
                myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
                myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                        .fromResource(R.drawable.map_icon_currentlocation));// 设置小蓝点的图标

                myLocationStyle.strokeColor(Color.BLUE);// 设置圆形的边框颜色
                myLocationStyle.interval(20000000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
                aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
                //aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
                aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
                myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
                myLocationStyle.showMyLocation(true);//设置是否显示定位小蓝点
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，实现地图生命周期管理
        mMapView.onSaveInstanceState(outState);
    }
}

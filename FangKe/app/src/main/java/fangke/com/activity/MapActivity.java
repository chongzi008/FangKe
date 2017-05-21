package fangke.com.activity;


import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;

import fangke.com.bean.City;


/**
 * 地图页面
 *
 * @author ChongZi007
 * @time 2017/3/30 20:59
 * @参数
 * @return
 */

public class MapActivity extends Activity implements PoiSearch.OnPoiSearchListener {

    private MapView mMapView;
    private ImageView map_mycurrentposition;
    private AMap aMap;
    private UiSettings mUiSettings;//定义一个UiSettings对象
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private ArrayList<City> cityLatLonPointList;
    private ArrayList<City> citydatalist;
    private int startSetMarker=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initViews(savedInstanceState);
        initListener();
        initDatas();
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
        mUiSettings = aMap.getUiSettings();//实例化UiSettings类对象
        mUiSettings.setZoomControlsEnabled(false);//不显示缩放按钮
        //设置希望展示的地图缩放级别
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(10);
        aMap.moveCamera(mCameraUpdate);
        map_mycurrentposition = (ImageView) findViewById(R.id.map_mycurrentposition);

    }

    /**
     * 初始化数据出
     */
    private void initDatas() {
        //先从网上下数据异步操作
        getCityData();


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

    public void getCityData() {
        //异步下载得到citydata后
        citydatalist = new ArrayList<City>();
        citydatalist.add(new City("香洲区", 10086, 1));
        citydatalist.add(new City("坦洲", 9000, 2));
        citydatalist.add(new City("横琴", 12345, 3));
        citydatalist.add(new City("金湾区", 12345, 3));
        //根据数据得到对应的经纬度
        for (int i = 0; i < citydatalist.size(); i++) {
            query = new PoiSearch.Query(citydatalist.get(i).getCityName(), "", "");
            poiSearch = new PoiSearch(this, query);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.searchPOIAsyn();

        }


    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int code) {
        if (code == 1000) {
            startSetMarker++;
            if (null != poiResult && poiResult.getPois().size() > 0) {
                ArrayList<PoiItem> pois = poiResult.getPois();
                PoiItem poiItem = pois.get(0);
                LatLonPoint latLonPoint = poiItem.getLatLonPoint();
                for (int i = 0; i < citydatalist.size(); i++) {

                    if (poiResult.getPois().get(0).getTitle().contains(citydatalist.get(i).getCityName())) {
                        citydatalist.get(i).setLongitude(latLonPoint.getLongitude());
                        citydatalist.get(i).setLatitude(latLonPoint.getLatitude());
                    }
                }
                if (startSetMarker==citydatalist.size()) {
                    //到了这就要启动设置标记的方法了
                    setMyMarker();
                }

            }

        }
    }

    private void setMyMarker() {

        for (int i=0;i<citydatalist.size();i++){
            City city = citydatalist.get(i);
            System.out.println("+++++"+city.getCityName()+city.getLevel()+"--"+city.getNum()+"--"+city.getLatitude()+"--"+city.getLongitude());
            LatLng latLng = new LatLng(city.getLatitude(),city.getLongitude());
            MarkerOptions markerOption = new MarkerOptions();
            markerOption.position(latLng);
            markerOption.title(city.getCityName()).snippet("我是"+city.getCityName());
            markerOption.draggable(false);//设置Marker可拖动

            markerOption.icon(BitmapDescriptorFactory.
                    fromView(getMyView(city)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption.setFlat(true);//设置marker平贴地图效果
            aMap.addMarker(markerOption);
        }



    }

    protected View getMyView(City city) {
        View view = getLayoutInflater().inflate(R.layout.map_mymarker, null);
        ImageView img_level = (ImageView) view.findViewById(R.id.map_img_level);
        TextView tv_name = (TextView) view.findViewById(R.id.map_marker_tv_name);
        TextView tv_num = (TextView) view.findViewById(R.id.map_marker_tv_num);
        tv_name.setText(city.getCityName());
        tv_num.setText(city.getNum()+"");
        if(city.getLevel()==1){
            img_level.setBackgroundResource(R.drawable.shape_mymarker_recommandlevel_1);
            tv_name.setTextColor(this.getResources().getColor(R.color.markerlever_2));
            tv_num.setTextColor(this.getResources().getColor(R.color.markerlever_2));
        }else if(city.getLevel()==2){
            img_level.setBackgroundResource(R.drawable.shape_mymarker_recommandlevel_2);
            tv_name.setTextColor(this.getResources().getColor(R.color.markerlever_1));
            tv_num.setTextColor(this.getResources().getColor(R.color.markerlever_1));
        }else{
            img_level.setBackgroundResource(R.drawable.shape_mymarker_recommandlevel_3);
            tv_name.setTextColor(this.getResources().getColor(R.color.markerlever_1));
            tv_num.setTextColor(this.getResources().getColor(R.color.markerlever_1));
        }
        return view;
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int code) {

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

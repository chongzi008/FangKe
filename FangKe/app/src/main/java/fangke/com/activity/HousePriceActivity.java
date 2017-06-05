package fangke.com.activity;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fangke.com.bean.HousePriceConditionBean;
import fangke.com.bean.NewHouseConditionBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.HttpUtil;

/**
 * 小区房价页面
 *@author ChongZi007
 *@time 2017/3/30 20:59
 *@参数
 *@return
*/
public class HousePriceActivity extends AppCompatActivity {
    public final static int Type_1 = 0;
    public final static int Type_2 = 1;
    public final static int Type_3 = 2;
    private ListView lv;
    private ArrayList list;
    private TextView tv_near;
    private ImageView img_near;
    private TextView tv_type;
    private ImageView img_type;
    private float mLastY = 0;
    ArrayList<HashMap<String, Object>> arrayList;
    private HousePriceConditionBean conditionBean;
    private TextView tv_age;
    private ImageView img_age;
    private TextView tv_rank;
    private ImageView img_rank;
    private ImageView narrow_left;
    private Gson gson;
    private PopupWindow pricePopupWindow;
    private PopupWindow nearPopupWindow;
    private PopupWindow agePopupWindow;
    private PopupWindow rankPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_price);
        initData();
        initViews();
        initListener();
    }

    private void initViews() {
        //得到popupwindow的所有按钮
        tv_near = (TextView) findViewById(R.id.village_tv_near);
        img_near = (ImageView) findViewById(R.id.village_img_near);
        tv_type = (TextView) findViewById(R.id.village_tv_type);
        img_type = (ImageView) findViewById(R.id.village_img_price);
        tv_age= (TextView) findViewById(R.id.village_tv_age);
        img_age= (ImageView) findViewById(R.id.village_img_age);
        tv_rank = (TextView) findViewById(R.id.village_tv_rank);
        img_rank = (ImageView) findViewById(R.id.village_img_rank);
        narrow_left = (ImageView) findViewById(R.id.house_price_narrow_left);
        gson= new Gson();


        lv = (ListView) findViewById(R.id.village_lv);
        lv.setAdapter(new HousePriceActivity.MyAdapter());
    }

    private void initListener() {
        //监听主页面列表滑动时弹出收回底部window
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 触摸按下时的操作
                        //创建window

                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 触摸移动时的操作
                        float currentY = event.getRawY();


                        mLastY = currentY;
                        break;
                    case MotionEvent.ACTION_UP:
                        // 触摸抬起时的操作
                        mLastY = 0;
                        break;
                }
                return false;
            }
        });
        //初始化新房附近的监听。
        tv_near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击附近的时候我们就弹出下拉窗口
                showNearWindow();
            }
        });
        img_near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击附近的时候我们就弹出下拉窗口
                showNearWindow();
            }
        });
        //初始化价格的监听
        tv_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeWindow();
            }
        });
        img_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeWindow();
            }
        });

        //初始化户型的监听
        tv_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAgeWindow();
            }
        });
        img_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAgeWindow();
            }
        });

        //初始化更多的window
        tv_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreWindow();
            }
        });
        img_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreWindow();
            }
        });
        narrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
    }

    //显示更多的window
    private void showMoreWindow() {
        View moreView = View.inflate(HousePriceActivity.this, R.layout.village_popupwindow_rank, null);
        //处理几百个按钮的点击事件
        ListView lv_rank = (ListView) moreView.findViewById(R.id.village_popupview_rank_lv);
        SimpleAdapter priceAdapter = new SimpleAdapter(HousePriceActivity.this, getRankWindowData(), R.layout.popupwindow_room_item,
                new String[]{"title"}, new int[]{R.id.popupwindow_room_item_tv});
        lv_rank.setAdapter(priceAdapter);
        lv_rank.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_rank.setText(arrayList.get(i).get("title").toString());
                tv_rank.setTextColor(getResources().getColor(R.color.mblue));
                img_rank.setImageResource(R.drawable.newhouse_blue_downarrow);
                if(rankPopupWindow!=null){
                    rankPopupWindow.dismiss();
                }

                //当我们点击时候 条件存进bean中对应地方 需要判断的是如果点击的是不限我们就设为null 或者空字符串即可
                if(conditionBean==null){
                    conditionBean = new HousePriceConditionBean();
                }
                conditionBean.setRank(arrayList.get(i).get("title").toString());

                String item = gson.toJson(conditionBean, HousePriceConditionBean.class);
                HttpUtil.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });
        rankPopupWindow = new PopupWindow(moreView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        rankPopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        rankPopupWindow.setTouchable(true);
        rankPopupWindow.showAtLocation(tv_type, Gravity.TOP, 0, 0);
        rankPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    //显示房龄的Window
    private void showAgeWindow() {
        View roomView = View.inflate(HousePriceActivity.this, R.layout.village_popupwindow_age, null);
        ListView lv_age= (ListView) roomView.findViewById(R.id.village_popupwindow_age_lv);
        SimpleAdapter priceAdapter = new SimpleAdapter(HousePriceActivity.this, getAgeWindowData(), R.layout.popupwindow_room_item,
                new String[]{"title"}, new int[]{R.id.popupwindow_room_item_tv});
        lv_age.setAdapter(priceAdapter);
        lv_age.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               tv_age.setText(arrayList.get(i).get("title").toString());
                tv_age.setTextColor(getResources().getColor(R.color.mblue));
                img_age.setImageResource(R.drawable.newhouse_blue_downarrow);
                if(agePopupWindow!=null){
                   agePopupWindow.dismiss();
                }

                //当我们点击时候 条件存进bean中对应地方 需要判断的是如果点击的是不限我们就设为null 或者空字符串即可
                if(conditionBean==null){
                    conditionBean = new HousePriceConditionBean();
                }
                conditionBean.setAge(arrayList.get(i).get("title").toString());

                String item = gson.toJson(conditionBean, HousePriceConditionBean.class);
                HttpUtil.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

        });
        agePopupWindow = new PopupWindow(roomView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        agePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        agePopupWindow.setTouchable(true);
        agePopupWindow.showAtLocation(tv_type, Gravity.TOP, 0, 0);
        agePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }



    //显示类型的window
    private void showTypeWindow() {
        View priceView = View.inflate(HousePriceActivity.this, R.layout.village_popupwindow_type, null);
        final ListView lv_price = (ListView) priceView.findViewById(R.id.village_popupview_type_lv);
        final SimpleAdapter priceAdapter = new SimpleAdapter(HousePriceActivity.this, getTypeWindowData(), R.layout.popupwindow_price_item,
                new String[]{"title"}, new int[]{R.id.popupwindow_price_item_tv});
        lv_price.setAdapter(priceAdapter);
        lv_price.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(HousePriceActivity.this,arrayList.get(i).get("title").toString(), Toast.LENGTH_SHORT).show();
                priceAdapter.notifyDataSetChanged();
                tv_type.setText(arrayList.get(i).get("title").toString());
                tv_type.setTextColor(getResources().getColor(R.color.mblue));
                img_type.setImageResource(R.drawable.newhouse_blue_downarrow);
                if(pricePopupWindow!=null){
                    pricePopupWindow.dismiss();
                        }

                //当我们点击时候 条件存进bean中对应地方 需要判断的是如果点击的是不限我们就设为null 或者空字符串即可
            if(conditionBean==null){
                conditionBean = new HousePriceConditionBean();
                }
                    conditionBean.setRoomtpye(arrayList.get(i).get("title").toString());

                String item = gson.toJson(conditionBean, HousePriceConditionBean.class);
                HttpUtil.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });

        pricePopupWindow = new PopupWindow(priceView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_type, Gravity.TOP, 0, 0);
        pricePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }


    //显示附近的window
    private void showNearWindow() {
        //弹出附近窗口
        View nearview = View.inflate(HousePriceActivity.this, R.layout.village_popupwindow_near, null);
        final ListView near_lv_left = (ListView) nearview.findViewById(R.id.village_popupview_near_lv_left);
        final ListView near_lv_right = (ListView) nearview.findViewById(R.id.village_popupview_near_lv_right);

        SimpleAdapter nearAdapter = new SimpleAdapter(HousePriceActivity.this, getNearWindowData(), R.layout.popupwindow_near_left_item,
                new String[]{"title"}, new int[]{R.id.popupwindow_near_left});
        near_lv_left.setAdapter(nearAdapter);
        near_lv_left.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    near_lv_right.setVisibility(View.INVISIBLE);
                } else if (position == 1) {
                    if (near_lv_right.getVisibility() == View.VISIBLE) {

                    } else {

                        near_lv_right.setVisibility(View.VISIBLE);
                        arrayList = new ArrayList<HashMap<String, Object>>();
                        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
                        tempHashMap1.put("title", "不限");
                        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
                        tempHashMap2.put("title", "香洲");
                        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
                        tempHashMap3.put("title", "金湾");
                        HashMap<String, Object> tempHashMap4 = new HashMap<String, Object>();
                        tempHashMap4.put("title", "斗门");
                        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
                        tempHashMap5.put("title", "中山市");
                        HashMap<String, Object> tempHashMap6 = new HashMap<String, Object>();
                        tempHashMap6.put("title", "横琴");
                        HashMap<String, Object> tempHashMap7 = new HashMap<String, Object>();
                        tempHashMap7.put("title", "高栏港经济区");
                        HashMap<String, Object> tempHashMap8 = new HashMap<String, Object>();
                        tempHashMap8.put("title", "其他");

                        arrayList.add(tempHashMap1);
                        arrayList.add(tempHashMap2);
                        arrayList.add(tempHashMap3);
                        arrayList.add(tempHashMap4);
                        arrayList.add(tempHashMap5);
                        arrayList.add(tempHashMap6);
                        arrayList.add(tempHashMap7);
                        arrayList.add(tempHashMap8);
                        SimpleAdapter nearRightAdapter = new SimpleAdapter(HousePriceActivity.this, arrayList, R.layout.popupwindow_near_right_item,
                                new String[]{"title"}, new int[]{R.id.popupwindow_near_right});
                        near_lv_right.setAdapter(nearRightAdapter);

                    }

                } else if (position == 2) {
                    near_lv_right.setVisibility(View.VISIBLE);
                }
            }
        });
        near_lv_right.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_near.setText(arrayList.get(i).get("title").toString());
                tv_near.setTextColor(getResources().getColor(R.color.mblue));
                img_near.setImageResource(R.drawable.newhouse_blue_downarrow);
                if( nearPopupWindow !=null){
                    nearPopupWindow .dismiss();
                }

                //当我们点击时候 条件存进bean中对应地方 需要判断的是如果点击的是不限我们就设为null 或者空字符串即可
                if(conditionBean==null){
                    conditionBean = new HousePriceConditionBean();
                }
                conditionBean.setNear(arrayList.get(i).get("title").toString());

                String item = gson.toJson(conditionBean, HousePriceConditionBean.class);
                HttpUtil.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        HousePriceActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(HousePriceActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }
        });

        nearPopupWindow = new PopupWindow(nearview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        nearPopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        nearPopupWindow.setTouchable(true);
        nearPopupWindow.showAtLocation(tv_near, Gravity.TOP, 0, 0);
        nearPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    private ArrayList<HashMap<String, Object>> getNearWindowData() {
       arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "附近");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "区域");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "地铁");

        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);


        return arrayList;

    }

    private void initData() {

        list = new ArrayList();
        ArrayList list1 = new ArrayList();
        ArrayList list2 = new ArrayList();
        ArrayList list3 = new ArrayList();
        ArrayList list4 = new ArrayList();
        ArrayList list5 = new ArrayList();
        ArrayList list6 = new ArrayList();
        ArrayList list7 = new ArrayList();
        ArrayList list8 = new ArrayList();

        list1.add(R.drawable.newhouse1);
        list1.add("丽景湾花园");
        list1.add("金湾-金湾");
        list1.add("17500m/m2");
        list1.add("刚需房 低密度 ");
        list1.add("110m2");

        list2.add(R.drawable.newhouse2);
        list2.add("中海湖畔兰亭");
        list2.add("金湾-金湾");
        list2.add("17500m/m2");
        list2.add("刚需房 低密度 ");
        list2.add("110m2");

        list3.add(R.drawable.newhouse3);
        list3.add("山海一品海岸花园");
        list3.add("金湾-金湾");
        list3.add("17500m/m2");
        list3.add("刚需房 低密度 ");
        list3.add("110m2");

        list4.add(R.drawable.newhouse4);
        list4.add("翰林公寓");
        list4.add("金湾-金湾");
        list4.add("17500m/m2");
        list4.add("刚需房 低密度 ");
        list4.add("110m2");


        list5.add(R.drawable.newhouse1);
        list5.add("海贼王");
        list5.add("金湾-金湾");
        list5.add("17500m/m2");
        list5.add("刚需房 低密度 ");
        list5.add("110m2");

        list6.add(R.drawable.newhouse1);
        list6.add("海贼王");
        list6.add("金湾-金湾");
        list6.add("17500m/m2");
        list6.add("刚需房 低密度 ");
        list6.add("110m2");

        list7.add(R.drawable.newhouse1);
        list7.add("海贼王");
        list7.add("金湾-金湾");
        list7.add("17500m/m2");
        list7.add("刚需房 低密度 ");
        list7.add("110m2");

        list8.add(R.drawable.newhouse1);
        list8.add("海贼王");
        list8.add("金湾-金湾");
        list8.add("17500m/m2");
        list8.add("刚需房 低密度 ");
        list8.add("110m2");

        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);
        list.add(list5);
        list.add(list6);
        list.add(list7);
        list.add(list8);
        list.add(list8);
        list.add(list8);

    }

    private ArrayList<HashMap<String, Object>> getTypeWindowData() {
        arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "不限");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "普通住宅");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "别墅");
        HashMap<String, Object> tempHashMap4 = new HashMap<String, Object>();
        tempHashMap4.put("title", "公寓");
        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
        tempHashMap5.put("title", "其他");
        HashMap<String, Object> tempHashMap6 = new HashMap<String, Object>();
        tempHashMap6.put("title", "平房");


        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);
        arrayList.add(tempHashMap4);
        arrayList.add(tempHashMap5);
        arrayList.add(tempHashMap6);


        return arrayList;

    }

    private ArrayList<HashMap<String, Object>> getAgeWindowData() {
         arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "不限");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "2年内");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "2-5年");
        HashMap<String, Object> tempHashMap4 = new HashMap<String, Object>();
        tempHashMap4.put("title", "5-10年");
        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
        tempHashMap5.put("title", "10年以上");

        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);
        arrayList.add(tempHashMap4);
        arrayList.add(tempHashMap5);


        return arrayList;

    }

    private ArrayList<HashMap<String, Object>> getRankWindowData() {
        arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "全部");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "均价从低到高");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "均价从高到低");
        HashMap<String, Object> tempHashMap4= new HashMap<String, Object>();
        tempHashMap4.put("title", "涨跌幅从低到高");
        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
        tempHashMap5.put("title", "涨跌幅从高到低");
        HashMap<String, Object> tempHashMap6 = new HashMap<String, Object>();
        tempHashMap6.put("title", "竣工时间顺序");
        HashMap<String, Object> tempHashMap7 = new HashMap<String, Object>();
        tempHashMap7.put("title", "竣工时间倒叙");
        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);
        arrayList.add(tempHashMap4);
        arrayList.add(tempHashMap5);
        arrayList.add(tempHashMap6);
        arrayList.add(tempHashMap7);

        return arrayList;

    }


    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public int getItemViewType(int position) {

            //暂时没有真实的返回数据 先让数据的前四条为类型一 后面为类型四
            if (position == 0) {
                return Type_1;
            } else if (position >= 1 && position <= 4) {
                return Type_2;
            } else if (position == 5) {
                return Type_3;
            } else {
                return Type_2;
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            int type = getItemViewType(position);
            HousePriceActivity.ViewHolderHead viewHolderHead = null;
            HousePriceActivity.ViewHolderMiddle viewHolderMiddle = null;
            HousePriceActivity.ViewHolderNormal viewHolderNormal = null;
            if (convertView == null) {
                //没有缓存我们需要新创出各个需要的控件
                //根据当前的类型创建对应的布局
                switch (type) {
                    case Type_1:
                        convertView = View.inflate(HousePriceActivity.this, R.layout.village_lv_headview, null);
                        viewHolderHead = new HousePriceActivity.ViewHolderHead();
                        viewHolderHead.tv = (TextView) convertView.findViewById(R.id.village_tv_headview);
                        convertView.setTag(viewHolderHead);
                        break;
                    case Type_2:
                        convertView = View.inflate(HousePriceActivity.this, R.layout.village_lv_normalview, null);
                        viewHolderNormal = new HousePriceActivity.ViewHolderNormal();
                        viewHolderNormal.img = (ImageView) convertView.findViewById(R.id.village_img_normalview);
                        viewHolderNormal.left = (TextView) convertView.findViewById(R.id.village_tv_normalview_left);
                        viewHolderNormal.title = (TextView) convertView.findViewById(R.id.village_tv_normalview_title);
                        viewHolderNormal.area = (TextView) convertView.findViewById(R.id.village_tv_normalview_area);
                        viewHolderNormal.discount = (TextView) convertView.findViewById(R.id.village_tv_normalview_discount);
                        viewHolderNormal.money = (TextView) convertView.findViewById(R.id.village_tv_normalview_money);
                        convertView.setTag(viewHolderNormal);
                        break;
                    case Type_3:
                        convertView = View.inflate(HousePriceActivity.this, R.layout.village_lv_middleview, null);
                        viewHolderMiddle = new HousePriceActivity.ViewHolderMiddle();
                        viewHolderMiddle.btn = (Button) convertView.findViewById(R.id.village_btn_middleview_btn);
                        convertView.setTag(viewHolderMiddle);
                        break;
                    default:
                        break;
                }
            } else {
                switch (type) {
                    case Type_1:
                        viewHolderHead = (HousePriceActivity.ViewHolderHead) convertView.getTag();
                        break;
                    case Type_2:
                        viewHolderNormal = (HousePriceActivity.ViewHolderNormal) convertView.getTag();
                        break;
                    case Type_3:
                        viewHolderMiddle = (HousePriceActivity.ViewHolderMiddle) convertView.getTag();
                        break;
                    default:
                        break;
                }

            }

            //设置资源
            switch (type) {
                case Type_1:
                    viewHolderHead.tv.setText("找到4个楼盘吧");
                    break;
                case Type_2:
                    ArrayList l = (ArrayList) list.get(position - 1);

                    viewHolderNormal.img.setBackgroundResource((Integer) l.get(0));
                    viewHolderNormal.title.setText((String) l.get(1));
                    viewHolderNormal.area.setText((String) l.get(2));
                    viewHolderNormal.money.setText((String) l.get(3));


                    break;
                case Type_3:
                    viewHolderMiddle.btn.setClickable(true);
                    viewHolderMiddle.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(HousePriceActivity.this, "你真的要订阅吗?", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;

                default:
                    break;
            }

            return convertView;
        }
    }


    // 通过ViewHolder静态类结合缓存convertView优化ListView性能
// 使用 ViewHolder 的好处是缓存了显示数据的视图（View），加快了 UI 的响应速度。
    static class ViewHolderHead {
        //头view的holder
        private TextView tv;
    }

    static class ViewHolderMiddle {
        //中间标题view的holder
        private Button btn;
    }

    static class ViewHolderNormal {
        //一般的holder
        private ImageView img;
        private TextView left;
        private TextView title;
        private TextView area;
        private TextView discount;
        private TextView money;

    }
}

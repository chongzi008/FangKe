package fangke.com.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

/**
 * 二手房页面
 *
 * @author ChongZi007
 * @time 2017/3/30 21:00
 * @参数
 * @return
 */

public class SecondHandHouseActivity extends Activity {

    private ListView lv;
    private List list;
    private TextView tv_quyu;
    private ImageView img_quyu;
    private TextView tv_price;
    private ImageView img_price;
    private TextView tv_room;
    private ImageView img_room;
    private TextView tv_more;
    private ImageView img_more;
    private TextView tv_search;
    private int mHeight;
    private ImageView img_search;
    private TextView tv_xiaoqufangjia;
    private ImageView img_xiaoqufangjia;
    private TextView tv_area;
    private ImageView img_area;
    private float mLastY=0;
    private float mLastX=0;
    private RelativeLayout rl_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_second_hand_house);
        initData();
        initViews();
        initListener();
    }


    private void initViews() {
        //得到区域 房价 售价 更多的控件
        tv_quyu = (TextView) findViewById(R.id.secondhandhouse_middle_tv_quyu);
        img_quyu = (ImageView) findViewById(R.id.secondhandhouse_middle_img_quyu);
        tv_price = (TextView) findViewById(R.id.secondhandhouse_middle_tv_price);
        img_price = (ImageView) findViewById(R.id.secondhandhouse_middle_img_price);
        tv_room = (TextView) findViewById(R.id.secondhandhouse_middle_tv_room);
        img_room = (ImageView) findViewById(R.id.secondhandhouse_middle_img_room);
        tv_more = (TextView) findViewById(R.id.secondhandhouse_middle_tv_more);
        img_more = (ImageView) findViewById(R.id.secondhandhouse_middle_img_more);
        //得到底部悬浮框的控件
        tv_search = (TextView) findViewById(R.id.secondhandhouse_bottom_tv_search);
        img_search = (ImageView) findViewById(R.id.secondhandhouse_bottom_img_search);
        tv_xiaoqufangjia = (TextView) findViewById(R.id.secondhandhouse_bottom_tv_xiaoqufangjia);
        img_xiaoqufangjia = (ImageView) findViewById(R.id.secondhandhouse_bottom_img_xiaoqufangjia);
        tv_area = (TextView) findViewById(R.id.secondhandhouse_bottom_tv_area);
        img_area = (ImageView) findViewById(R.id.secondhandhouse_bottom_img_area);

        //获取底部栏
        rl_bottom = (RelativeLayout) findViewById(R.id.secondhandhouse_bottom_item);
        //为listview填充数据
        lv = (ListView) findViewById(R.id.secondhandhouse_lv);//获取listview
        lv.setAdapter(new MyAdapter());
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
    }

    private void initListener() {

        //初始化区域的监听
        tv_quyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuYuWindow();
            }
        });
        img_quyu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showQuYuWindow();
            }
        });
        //初始化售价的监听
        tv_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPriceWindow();
            }
        });
        img_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPriceWindow();
            }
        });
        //初始化房型的监听
        tv_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomWindw();
            }
        });

        img_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomWindw();
            }
        });
        //初始化更多的监听
        tv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreWindow();
            }
        });
        img_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreWindow();
            }
        });

        //初始化悬浮框控件的监听
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_xiaoqufangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        img_xiaoqufangjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        img_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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
                        float currentX = event.getRawX();
                        //左右滑动跟上下滑动
                        if(Math.abs((currentY-mLastY))>Math.abs((currentX-mLastX))){
                            //上下滑动

                            if (currentY - mLastY != currentY) {

                                if (currentY - mLastY > 0) {
                                    //下滑
                                    //显示底部框
                                    showBottomView();
                                } else if (currentY - mLastY < 0) {
                                    //上滑 隐藏底部框
                                    hideBottomView();

                                } else {
                                    //其他我们就让它直接显示就好了
                                    showBottomView();
                                }

                            }
                        }else{
                            //左右滑动不做处理即可

                        }


                        mLastY = currentY;
                        mLastX=currentX;
                        break;
                    case MotionEvent.ACTION_UP:
                        // 触摸抬起时的操作
                        mLastY = 0;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        mHeight = rl_bottom.getMeasuredHeight();
    }

    /**
     * 隐藏底部栏
     */
    private void hideBottomView() {
        TranslateAnimation botromTranslation = new TranslateAnimation(0, 0, 0, mHeight);
        botromTranslation.setDuration(500);
        rl_bottom.startAnimation(botromTranslation);
        rl_bottom.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示底部栏
     */
    private void showBottomView() {
        TranslateAnimation botromTranslation = new TranslateAnimation(0, 0, mHeight, 0);
        botromTranslation.setDuration(500);
        rl_bottom.startAnimation(botromTranslation);
        rl_bottom.setVisibility(View.VISIBLE);
    }

    private void showMoreWindow() {
        View moreView = View.inflate(SecondHandHouseActivity.this, R.layout.secondhandhouse_popupwindow_more, null);
        //处理几百个按钮的点击事件

        PopupWindow pricePopupWindow = new PopupWindow(moreView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
    }

    //弹出房型的窗口
    private void showRoomWindw() {
        View roomView = View.inflate(SecondHandHouseActivity.this, R.layout.secondhandhouse_popupwindow_room, null);
        ListView lv_room = (ListView) roomView.findViewById(R.id.newhouse_popupwindow_room_lv);
        SimpleAdapter priceAdapter = new SimpleAdapter(SecondHandHouseActivity.this, getRoomWindowData(), R.layout.secondhandhouse_popupwindow_room_item,
                new String[]{"title"}, new int[]{R.id.secondhandhouse_popupwindow_room_item_tv});
        lv_room.setAdapter(priceAdapter);
        PopupWindow pricePopupWindow = new PopupWindow(roomView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
        pricePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    private ArrayList<HashMap<String, Object>> getRoomWindowData() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "不限");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "一室");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "二室");
        HashMap<String, Object> tempHashMap4 = new HashMap<String, Object>();
        tempHashMap4.put("title", "三室");
        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
        tempHashMap5.put("title", "四室");
        HashMap<String, Object> tempHashMap6 = new HashMap<String, Object>();
        tempHashMap6.put("title", "五室以上");
        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);
        arrayList.add(tempHashMap4);
        arrayList.add(tempHashMap5);
        arrayList.add(tempHashMap6);

        return arrayList;

    }

    //弹出售价的窗口

    private void showPriceWindow() {

        View priceView = View.inflate(SecondHandHouseActivity.this, R.layout.secondhandhouse_popupwindow_price, null);
        ListView lv_price = (ListView) priceView.findViewById(R.id.newhouse_popupview_price_lv);
        SimpleAdapter priceAdapter = new SimpleAdapter(SecondHandHouseActivity.this, getPriceWindowData(), R.layout.secondhandhouse_popupwindow_price_item,
                new String[]{"title"}, new int[]{R.id.secondhandhouse_popupwindow_price_item_tv});
        lv_price.setAdapter(priceAdapter);
        PopupWindow pricePopupWindow = new PopupWindow(priceView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
        pricePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    private ArrayList<HashMap<String, Object>> getPriceWindowData() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "不限");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "30万元以下");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "30-50万元");
        HashMap<String, Object> tempHashMap4 = new HashMap<String, Object>();
        tempHashMap4.put("title", "50-80万元");
        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
        tempHashMap5.put("title", "80-100万元");
        HashMap<String, Object> tempHashMap6 = new HashMap<String, Object>();
        tempHashMap6.put("title", "100-120万元");
        HashMap<String, Object> tempHashMap7 = new HashMap<String, Object>();
        tempHashMap7.put("title", "120-150万元");
        HashMap<String, Object> tempHashMap8 = new HashMap<String, Object>();
        tempHashMap8.put("title", "150-200万元");
        HashMap<String, Object> tempHashMap9 = new HashMap<String, Object>();
        tempHashMap9.put("title", "200-300万元");
        HashMap<String, Object> tempHashMap10 = new HashMap<String, Object>();
        tempHashMap10.put("title", "300万元以上");
        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);
        arrayList.add(tempHashMap4);
        arrayList.add(tempHashMap5);
        arrayList.add(tempHashMap6);
        arrayList.add(tempHashMap7);
        arrayList.add(tempHashMap8);
        arrayList.add(tempHashMap9);
        arrayList.add(tempHashMap10);
        return arrayList;

    }

    //弹出区域的window
    private void showQuYuWindow() {
        View quyuview = View.inflate(SecondHandHouseActivity.this, R.layout.secondhandhouse_popupwindow_near, null);
        final ListView near_lv_left = (ListView) quyuview.findViewById(R.id.newhouse_popupview_near_lv_left);
        final ListView near_lv_right = (ListView) quyuview.findViewById(R.id.newhouse_popupview_near_lv_right);

        SimpleAdapter nearAdapter = new SimpleAdapter(SecondHandHouseActivity.this, getQuYuWindowData(), R.layout.secondhandhouse_popupwindow_near_left_item,
                new String[]{"title"}, new int[]{R.id.secondhandhouse_popupwindow_near_left});

        near_lv_left.setAdapter(nearAdapter);

        near_lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    near_lv_right.setVisibility(View.INVISIBLE);
                } else if (position == 1) {
                    if (near_lv_right.getVisibility() == View.VISIBLE) {

                    } else {

                        near_lv_right.setVisibility(View.VISIBLE);
                        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
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
                        SimpleAdapter nearRightAdapter = new SimpleAdapter(SecondHandHouseActivity.this, arrayList, R.layout.popupwindow_near_right_item,
                                new String[]{"title"}, new int[]{R.id.popupwindow_near_right});
                        near_lv_right.setAdapter(nearRightAdapter);

                    }

                } else if (position == 2) {
                    near_lv_right.setVisibility(View.VISIBLE);
                }
            }
        });


        PopupWindow quyuPopupWindow = new PopupWindow(quyuview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        quyuPopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        quyuPopupWindow.setTouchable(true);
        quyuPopupWindow.showAtLocation(tv_quyu, Gravity.TOP, 0, 0);
        quyuPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    private ArrayList<HashMap<String, Object>> getQuYuWindowData() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "附近");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "区域");
        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);


        return arrayList;

    }

    //创建listview的适配器
    class MyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return list.size();
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
            MyViewHolder holder = null;
            if (convertView == null) {
                //缓存为空 需要news资源
                convertView = View.inflate(SecondHandHouseActivity.this, R.layout.secondhandhouse_lv_item, null);
                holder = new MyViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.secondhandhouse_lv_img_normalview);
                holder.left = (TextView) convertView.findViewById(R.id.secondhandhouse_tv_normalview_left);
                holder.title = (TextView) convertView.findViewById(R.id.secondhandhouse_tv_normalview_title);
                holder.area = (TextView) convertView.findViewById(R.id.secondhandhouse_tv_normalview_area);
                holder.discount = (TextView) convertView.findViewById(R.id.secondhandhouse_tv_normalview_discount);
                holder.money = (TextView) convertView.findViewById(R.id.secondhandhouse_tv_normalview_money);
                convertView.setTag(holder);

            } else {
                //缓存不为空直接取
                holder = (SecondHandHouseActivity.MyViewHolder) convertView.getTag();
            }
            //设置资源
            ArrayList l = (ArrayList) list.get(position);
            holder.img.setBackgroundResource((int) l.get(0));
            holder.title.setText((String) l.get(1));
            holder.area.setText((String) l.get(2));
            holder.money.setText((String) l.get(3));
            return convertView;
        }
    }

    static class MyViewHolder {

        private ImageView img;
        private TextView left;
        private TextView title;
        private TextView area;
        private TextView discount;
        private TextView money;

    }
}

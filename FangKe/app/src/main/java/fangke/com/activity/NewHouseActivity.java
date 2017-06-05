package fangke.com.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import fangke.com.bean.NewHouseConditionBean;
import fangke.com.bean.RightListviewJsonBean;
import fangke.com.view.NoRepeatButton;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import utils.HttpUtils;
import utils.IOStreamUtils;
import utils.TypeWritingUtils;

/**
 * 新房页面
 *
 * @author ChongZi007
 * @time 2017/3/30 20:59
 * @参数
 * @return
 */
public class NewHouseActivity extends Activity {
    public final static int Type_1 = 0;
    public final static int Type_2 = 1;
    public final static int Type_3 = 2;
    public final static int POPUPWINDOW_DATA = 3;
    public final static int HOUSELIST_DATA = 4;
    private ListView lv;
    private ArrayList list;
    private TextView tv_near;
    private ImageView img_near;
    private TextView tv_price;
    private ImageView img_price;
    private float mLastY = 0;
    private float mLastX = 0;
    private RelativeLayout rl_bottom;
    private float mY = 0;
    private int mHeight;
    private TextView tv_room;
    private ImageView img_room;
    private TextView tv_more;
    private ImageView img_more;
    private TextView tv_new;
    private ImageView img_new;
    private TextView tv_discount;
    private ImageView img_discount;
    private TextView tv_clever;
    private ImageView img_clever;
    private TextView tv_freelook;
    private ImageView img_freelook;
    private ImageView narrow_left;
    private ArrayList<ArrayList<String>> finalList;
    private ArrayList lefttList;
    private PopupWindow nearPopupWindow;
    private ArrayList<RightListviewJsonBean.Areas> rightList_region;
    private ArrayList<RightListviewJsonBean.Areas> rightList_subway;
    private ArrayList<ArrayList<RightListviewJsonBean.Areas>> righttList;
    private MyFinalListViewAdapter myFinalListViewAdapter;
    private int leftSelectPosition = 0;
    private int righttSelectPosition = 0;
    private int finalSelectPosition = 0;
    private int priceSelectPosition = 0;
    private int roomSelectPosition = 0;
    private ArrayList<String> priceList;
    private PopupWindow pricePopupWindow;
    private ArrayList<String> roomList;
    private PopupWindow roomPopupWindow;
    private HashSet<NoRepeatButton> featureList;
    private NoRepeatButton more_wechat;
    private NoRepeatButton more_shapanlou;
    private NoRepeatButton more_videohouse;
    private HashSet<NoRepeatButton> areaList;
    private NoRepeatButton more_60;
    private NoRepeatButton more_80;
    private NoRepeatButton more_100;
    private NoRepeatButton more_120;
    private NoRepeatButton more_150;
    private NoRepeatButton more_200;
    private NoRepeatButton more_250;
    private HashSet<NoRepeatButton> workList;
    private NoRepeatButton more_zhuzhai;
    private NoRepeatButton more_bieshu;
    private NoRepeatButton more_shangzhu;
    private NoRepeatButton more_shangpu;
    private NoRepeatButton more_xiezilou;
    private HashSet<NoRepeatButton> hotList;
    private NoRepeatButton more_gangxu;
    private NoRepeatButton more_youhui;
    private NoRepeatButton more_buxiangou;
    private NoRepeatButton more_jijiangkaipan;
    private NoRepeatButton more_feimaopi;
    private NoRepeatButton more_gaishanfang;
    private NoRepeatButton more_resize;
    private NoRepeatButton more_check;
    private LinearLayout ll_popupwindow;
    private NewHouseConditionBean conditionBean;
    private Gson gson;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case POPUPWINDOW_DATA:
                    //解析一下json文件
                    String zhuhaiDatas = (String) msg.obj;
                    Gson gson = new Gson();
                    List<RightListviewJsonBean> zhuhai = gson.fromJson(zhuhaiDatas, new TypeToken<List<RightListviewJsonBean>>() {
                    }.getType());
                    for (RightListviewJsonBean bean : zhuhai) {
                        if (rightList_region == null) {
                            rightList_region = bean.getRegion();
                        }//实为中间listview数据源
                        if (rightList_subway == null) {
                            rightList_subway = bean.getSubway();
                        }//实为中间listview数据源
                    }
                    righttList.add(rightList_region);
                    finalList.add(rightList_region.get(1).getAreas());
                    ll_popupwindow.setVisibility(View.VISIBLE);

                    break;
                case HOUSELIST_DATA:

                    break;

                default:
                    break;
            }
        }
    };
    private PopupWindow morePopupWindow;
    private HashSet<NoRepeatButton> btns;
    private LinearLayout ll_top;
    private TextView tv_map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_house);
        initViews();
        initData();
        initListener();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        int[] location = new int[2];
        rl_bottom.getLocationOnScreen(location);//获取在整个屏幕内的绝对坐标
        mY = location[1];
        mHeight = rl_bottom.getMeasuredHeight();
    }

    private void initViews() {
        narrow_left = (ImageView) findViewById(R.id.newhouse_narrow_left);
        //得到popupwindow的所有按钮
        tv_near = (TextView) findViewById(R.id.newhouse_tv_near);
        img_near = (ImageView) findViewById(R.id.newhouse_img_near);
        tv_price = (TextView) findViewById(R.id.newhouse_tv_price);
        img_price = (ImageView) findViewById(R.id.newhouse_img_price);
        rl_bottom = (RelativeLayout) findViewById(R.id.newhouse_bottom_item);
        tv_room = (TextView) findViewById(R.id.newhouse_tv_room);
        img_room = (ImageView) findViewById(R.id.newhouse_img_room);
        tv_more = (TextView) findViewById(R.id.newhouse_tv_more);
        img_more = (ImageView) findViewById(R.id.newhouse_img_more);
        //获取底部的按钮
        tv_new = (TextView) findViewById(R.id.newhouse_bottom_tv_new);
        img_new = (ImageView) findViewById(R.id.newhouse_bottom_img_new);
        tv_discount = (TextView) findViewById(R.id.newhouse_bottom_tv_discount);
        img_discount = (ImageView) findViewById(R.id.newhouse_bottom_img_discount);
        tv_clever = (TextView) findViewById(R.id.newhouse_bottom_tv_clever);
        img_clever = (ImageView) findViewById(R.id.newhouse_bottom_img_clever);
        tv_freelook = (TextView) findViewById(R.id.newhouse_bottom_tv_freelook);
        img_freelook = (ImageView) findViewById(R.id.newhouse_bottom_img_freelook);
        //包含四个window的布局
        ll_popupwindow = (LinearLayout) findViewById(R.id.newhouse_popupwindow);
        ll_top = (LinearLayout) findViewById(R.id.newhouse_top_ll);
        lv = (ListView) findViewById(R.id.newhouse_lv);
        tv_map = (TextView) findViewById(R.id.newhouse_tv_map);
        featureList = new HashSet<>();
        workList = new HashSet<>();
        areaList = new HashSet<>();
        hotList = new HashSet<>();
        gson = new Gson();

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
                        float currentX = event.getRawX();
                        //左右滑动跟上下滑动
                        if (Math.abs((currentY - mLastY)) > Math.abs((currentX - mLastX))) {
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
                        } else {
                            //左右滑动不做处理即可

                        }


                        mLastY = currentY;
                        mLastX = currentX;
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

        //初始化户型的监听
        tv_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomWindow();
            }
        });
        img_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomWindow();
            }
        });

        //初始化更多的window
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

        narrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(NewHouseActivity.this,NewhouseSearchActivity.class);
                startActivity(intent);
            }
        });

        tv_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(NewHouseActivity.this,MapActivity.class);
                startActivity(intent);
            }
        });

       //  tv_new;
       //  img_new;
        // tv_discount;
       //  img_discount;
       //  tv_clever;
       //  img_clever;
      //   tv_freelook;
       //  img_freelook;
    }

    //显示更多的window
    private void showMoreWindow() {
        View moreView = View.inflate(NewHouseActivity.this, R.layout.newhouse_popupwindow_more, null);
        //处理几百个按钮的点击事件
        //这里使用每一类都用一个arraylist来保存点击过的button 点击过的按钮改变响应的颜色
        //下面是属于特色一类的
        btns = new HashSet<>();
        //微聊
        more_wechat = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_wechat);
        btns.add(more_wechat);
        //沙盘楼
        more_shapanlou = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_shapanlou);
        btns.add(more_shapanlou);
        //视频看房
        more_videohouse = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_videohouse);
        btns.add(more_videohouse);
        //下面是属于面积一类的
        //60
        more_60 = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_60);
        btns.add(more_60);
        //60-80
        more_80 = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_80);
        btns.add(more_80);
        //more_100
        more_100 = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_100);
        btns.add(more_100);
        //more_120
        more_120 = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_120);
        btns.add(more_120);
        //more_150
        more_150 = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_150);
        btns.add(more_150);
        //more_200
        more_200 = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_200);
        btns.add(more_200);
        //more_250
        more_250 = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_250);
        btns.add(more_250);
        //下面是属于物业一类的
        //more_zhuzhai
        more_zhuzhai = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_zhuzhai);
        btns.add(more_zhuzhai);
        //more_bieshu
        more_bieshu = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_bieshu);
        btns.add(more_bieshu);
        //more_shangzhu
        more_shangzhu = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_shangzhu);
        btns.add(more_shangzhu);
        //more_shangpu
        more_shangpu = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_shangpu);
        btns.add(more_shangpu);
        //more_xiezilou
        more_xiezilou = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_xiezilou);
        btns.add(more_xiezilou);
        //下面是属于热门标签一类的
        //more_gangxu
        more_gangxu = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_gangxu);
        btns.add(more_gangxu);
        //more_youhui
        more_youhui = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_youhui);
        btns.add(more_youhui);
        //more_buxiangou
        more_buxiangou = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_buxiangou);
        btns.add(more_buxiangou);
        //more_jijiangkaipan
        more_jijiangkaipan = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_jijiangkaipan);
        btns.add(more_jijiangkaipan);
        //more_feimaopi
        more_feimaopi = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_feimaopi);
        btns.add(more_feimaopi);
        //more_gaishanfang
        more_gaishanfang = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_gaishanfang);
        btns.add(more_gaishanfang);
        //重置跟确定的按钮
        //more_resize
        more_resize = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_resize);
        //more_check
        more_check = (NoRepeatButton) moreView.findViewById(R.id.newhouse_popupview_more_check);

        if (conditionBean != null && conditionBean.getMore() != null && conditionBean.getMore().getFeature() != null
                && conditionBean.getMore().getSize() != null && conditionBean.getMore().getWork() != null
                && conditionBean.getMore().getHottag() != null) {
            for (NoRepeatButton btn : btns) {
                for (String s : conditionBean.getMore().getFeature()) {
                    if ((btn.getText().equals(s))) {
                        btn.setTextColor(getResources().getColor(R.color.white));
                        btn.setBackgroundResource(R.color.mblue);
                        if (!featureList.contains(btn)) {
                            featureList.add(btn);
                        }
                    }
                }
                for (String s : conditionBean.getMore().getSize()) {
                    if ((btn.getText().equals(s))) {
                        btn.setTextColor(getResources().getColor(R.color.white));
                        btn.setBackgroundResource(R.color.mblue);
                        if (!areaList.contains(btn)) {
                            areaList.add(btn);
                        }
                    }
                }
                for (String s : conditionBean.getMore().getWork()) {
                    if ((btn.getText().equals(s))) {
                        btn.setTextColor(getResources().getColor(R.color.white));
                        btn.setBackgroundResource(R.color.mblue);
                        if (!workList.contains(btn)) {
                            workList.add(btn);
                        }
                    }
                }
                for (String s : conditionBean.getMore().getHottag()) {
                    if ((btn.getText().equals(s))) {
                        btn.setTextColor(getResources().getColor(R.color.white));
                        btn.setBackgroundResource(R.color.mblue);
                        if (!hotList.contains(btn)) {
                            hotList.add(btn);
                        }
                    }
                }

            }
        }
        morePopupWindow = new PopupWindow(moreView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        morePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        morePopupWindow.setTouchable(true);
        morePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
        morePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    public void click(View v) {
        //处理所有按钮点击事件
        switch (v.getId()) {
            case R.id.newhouse_popupview_more_wechat:
                //点击时添加该对象进入集合分类中 同时改变按钮背景颜色 改变字体颜色 去掉该对象
                if (featureList.contains(more_wechat)) {
                    //说明已经点击了此时应该相当于还原初始状态
                    more_wechat.setTextColor(getResources().getColor(R.color.mblue));
                    more_wechat.setBackgroundResource(R.color.more_button_green);
                    featureList.remove(more_wechat);
                } else if (!featureList.contains(more_wechat)) {
                    //说明为未选中状态
                    more_wechat.setTextColor(getResources().getColor(R.color.white));
                    more_wechat.setBackgroundResource(R.color.mblue);
                    featureList.add(more_wechat);
                }
                break;
            case R.id.newhouse_popupview_more_shapanlou:
                if (featureList.contains(more_shapanlou)) {
                    more_shapanlou.setTextColor(getResources().getColor(R.color.mblue));
                    more_shapanlou.setBackgroundResource(R.color.more_button_green);
                    featureList.remove(more_shapanlou);
                } else if (!featureList.contains(more_shapanlou)) {
                    more_shapanlou.setTextColor(getResources().getColor(R.color.white));
                    more_shapanlou.setBackgroundResource(R.color.mblue);
                    featureList.add(more_shapanlou);
                }
                break;
            case R.id.newhouse_popupview_more_videohouse:
                if (featureList.contains(more_videohouse)) {
                    more_videohouse.setTextColor(getResources().getColor(R.color.mblue));
                    more_videohouse.setBackgroundResource(R.color.more_button_green);
                    featureList.remove(more_videohouse);
                } else if (!featureList.contains(more_videohouse)) {
                    more_videohouse.setTextColor(getResources().getColor(R.color.white));
                    more_videohouse.setBackgroundResource(R.color.mblue);
                    featureList.add(more_videohouse);
                }
                break;
            //上面是特色的点击事件
            case R.id.newhouse_popupview_more_60:
                if (areaList.contains(more_60)) {
                    more_60.setTextColor(getResources().getColor(R.color.mblue));
                    more_60.setBackgroundResource(R.color.more_button_green);
                    areaList.remove(more_60);
                } else if (!areaList.contains(more_60)) {
                    more_60.setTextColor(getResources().getColor(R.color.white));
                    more_60.setBackgroundResource(R.color.mblue);
                    areaList.add(more_60);
                }
                break;
            case R.id.newhouse_popupview_more_80:
                if (areaList.contains(more_80)) {
                    more_80.setTextColor(getResources().getColor(R.color.mblue));
                    more_80.setBackgroundResource(R.color.more_button_green);
                    areaList.remove(more_80);
                } else if (!areaList.contains(more_80)) {
                    more_80.setTextColor(getResources().getColor(R.color.white));
                    more_80.setBackgroundResource(R.color.mblue);
                    areaList.add(more_80);
                }
                break;
            case R.id.newhouse_popupview_more_100:
                if (areaList.contains(more_100)) {
                    more_100.setTextColor(getResources().getColor(R.color.mblue));
                    more_100.setBackgroundResource(R.color.more_button_green);
                    areaList.remove(more_100);
                } else if (!areaList.contains(more_100)) {
                    more_100.setTextColor(getResources().getColor(R.color.white));
                    more_100.setBackgroundResource(R.color.mblue);
                    areaList.add(more_100);
                }
                break;
            case R.id.newhouse_popupview_more_120:
                if (areaList.contains(more_120)) {
                    more_120.setTextColor(getResources().getColor(R.color.mblue));
                    more_120.setBackgroundResource(R.color.more_button_green);
                    areaList.remove(more_120);
                } else if (!areaList.contains(more_120)) {
                    more_120.setTextColor(getResources().getColor(R.color.white));
                    more_120.setBackgroundResource(R.color.mblue);
                    areaList.add(more_120);
                }
                break;
            case R.id.newhouse_popupview_more_150:
                if (areaList.contains(more_150)) {
                    more_150.setTextColor(getResources().getColor(R.color.mblue));
                    more_150.setBackgroundResource(R.color.more_button_green);
                    areaList.remove(more_150);
                } else if (!areaList.contains(more_150)) {
                    more_150.setTextColor(getResources().getColor(R.color.white));
                    more_150.setBackgroundResource(R.color.mblue);
                    areaList.add(more_150);
                }
                break;
            case R.id.newhouse_popupview_more_200:
                if (areaList.contains(more_200)) {
                    more_200.setTextColor(getResources().getColor(R.color.mblue));
                    more_200.setBackgroundResource(R.color.more_button_green);
                    areaList.remove(more_200);
                } else if (!areaList.contains(more_200)) {
                    more_200.setTextColor(getResources().getColor(R.color.white));
                    more_200.setBackgroundResource(R.color.mblue);
                    areaList.add(more_200);
                }
                break;
            case R.id.newhouse_popupview_more_250:
                if (areaList.contains(more_250)) {
                    more_250.setTextColor(getResources().getColor(R.color.mblue));
                    more_250.setBackgroundResource(R.color.more_button_green);
                    areaList.remove(more_250);
                } else if (!areaList.contains(more_250)) {
                    more_250.setTextColor(getResources().getColor(R.color.white));
                    more_250.setBackgroundResource(R.color.mblue);
                    areaList.add(more_250);
                }
                break;
            //以上为面积的分类点击
            case R.id.newhouse_popupview_more_zhuzhai:
                if (workList.contains(more_zhuzhai)) {
                    more_zhuzhai.setTextColor(getResources().getColor(R.color.mblue));
                    more_zhuzhai.setBackgroundResource(R.color.more_button_green);
                    workList.remove(more_zhuzhai);
                } else if (!workList.contains(more_zhuzhai)) {
                    more_zhuzhai.setTextColor(getResources().getColor(R.color.white));
                    more_zhuzhai.setBackgroundResource(R.color.mblue);
                    workList.add(more_zhuzhai);
                }
                break;
            case R.id.newhouse_popupview_more_bieshu:
                if (workList.contains(more_bieshu)) {
                    more_bieshu.setTextColor(getResources().getColor(R.color.mblue));
                    more_bieshu.setBackgroundResource(R.color.more_button_green);
                    workList.remove(more_bieshu);
                } else if (!workList.contains(more_bieshu)) {
                    more_bieshu.setTextColor(getResources().getColor(R.color.white));
                    more_bieshu.setBackgroundResource(R.color.mblue);
                    workList.add(more_bieshu);
                }
                break;
            case R.id.newhouse_popupview_more_shangzhu:
                if (workList.contains(more_shangzhu)) {
                    more_shangzhu.setTextColor(getResources().getColor(R.color.mblue));
                    more_shangzhu.setBackgroundResource(R.color.more_button_green);
                    workList.remove(more_shangzhu);
                } else if (!workList.contains(more_shangzhu)) {
                    more_shangzhu.setTextColor(getResources().getColor(R.color.white));
                    more_shangzhu.setBackgroundResource(R.color.mblue);
                    workList.add(more_shangzhu);
                }
                break;
            case R.id.newhouse_popupview_more_shangpu:
                if (workList.contains(more_shangpu)) {
                    more_shangpu.setTextColor(getResources().getColor(R.color.mblue));
                    more_shangpu.setBackgroundResource(R.color.more_button_green);
                    workList.remove(more_shangpu);
                } else if (!workList.contains(more_shangpu)) {
                    more_shangpu.setTextColor(getResources().getColor(R.color.white));
                    more_shangpu.setBackgroundResource(R.color.mblue);
                    workList.add(more_shangpu);
                }
                break;
            case R.id.newhouse_popupview_more_xiezilou:
                if (workList.contains(more_xiezilou)) {
                    more_xiezilou.setTextColor(getResources().getColor(R.color.mblue));
                    more_xiezilou.setBackgroundResource(R.color.more_button_green);
                    workList.remove(more_xiezilou);
                } else if (!workList.contains(more_xiezilou)) {
                    more_xiezilou.setTextColor(getResources().getColor(R.color.white));
                    more_xiezilou.setBackgroundResource(R.color.mblue);
                    workList.add(more_xiezilou);
                }
                break;
            //上面是物业的分类点击
            case R.id.newhouse_popupview_more_gangxu:
                if (hotList.contains(more_gangxu)) {
                    more_gangxu.setTextColor(getResources().getColor(R.color.mblue));
                    more_gangxu.setBackgroundResource(R.color.more_button_green);
                    hotList.remove(more_gangxu);
                } else if (!hotList.contains(more_gangxu)) {
                    more_gangxu.setTextColor(getResources().getColor(R.color.white));
                    more_gangxu.setBackgroundResource(R.color.mblue);
                    hotList.add(more_gangxu);
                }
                break;
            case R.id.newhouse_popupview_more_youhui:
                if (hotList.contains(more_youhui)) {
                    more_youhui.setTextColor(getResources().getColor(R.color.mblue));
                    more_youhui.setBackgroundResource(R.color.more_button_green);
                    hotList.remove(more_youhui);
                } else if (!hotList.contains(more_youhui)) {
                    more_youhui.setTextColor(getResources().getColor(R.color.white));
                    more_youhui.setBackgroundResource(R.color.mblue);
                    hotList.add(more_youhui);
                }
                break;
            case R.id.newhouse_popupview_more_buxiangou:
                if (hotList.contains(more_buxiangou)) {
                    more_buxiangou.setTextColor(getResources().getColor(R.color.mblue));
                    more_buxiangou.setBackgroundResource(R.color.more_button_green);
                    hotList.remove(more_buxiangou);
                } else if (!hotList.contains(more_buxiangou)) {
                    more_buxiangou.setTextColor(getResources().getColor(R.color.white));
                    more_buxiangou.setBackgroundResource(R.color.mblue);
                    hotList.add(more_buxiangou);
                }
                break;
            case R.id.newhouse_popupview_more_jijiangkaipan:
                if (hotList.contains(more_jijiangkaipan)) {
                    more_jijiangkaipan.setTextColor(getResources().getColor(R.color.mblue));
                    more_jijiangkaipan.setBackgroundResource(R.color.more_button_green);
                    hotList.remove(more_jijiangkaipan);
                } else if (!hotList.contains(more_jijiangkaipan)) {
                    more_jijiangkaipan.setTextColor(getResources().getColor(R.color.white));
                    more_jijiangkaipan.setBackgroundResource(R.color.mblue);
                    hotList.add(more_jijiangkaipan);
                }
                break;
            case R.id.newhouse_popupview_more_feimaopi:
                if (hotList.contains(more_feimaopi)) {
                    more_feimaopi.setTextColor(getResources().getColor(R.color.mblue));
                    more_feimaopi.setBackgroundResource(R.color.more_button_green);
                    hotList.remove(more_feimaopi);
                } else if (!hotList.contains(more_feimaopi)) {
                    more_feimaopi.setTextColor(getResources().getColor(R.color.white));
                    more_feimaopi.setBackgroundResource(R.color.mblue);
                    hotList.add(more_feimaopi);
                }
                break;
            case R.id.newhouse_popupview_more_gaishanfang:
                if (hotList.contains(more_gaishanfang)) {
                    more_gaishanfang.setTextColor(getResources().getColor(R.color.mblue));
                    more_gaishanfang.setBackgroundResource(R.color.more_button_green);
                    hotList.remove(more_gaishanfang);
                } else if (!hotList.contains(more_gaishanfang)) {
                    more_gaishanfang.setTextColor(getResources().getColor(R.color.white));
                    more_gaishanfang.setBackgroundResource(R.color.mblue);
                    hotList.add(more_gaishanfang);
                }
                break;
            //以上是热门标签分类的点击
            case R.id.newhouse_popupview_more_resize:
                //所谓重置就是循环遍历所有分类的集合 依次把他们从集合中去除并且还原原来的颜色
                for (NoRepeatButton btn : btns) {
                    btn.setTextColor(getResources().getColor(R.color.mblue));
                    btn.setBackgroundResource(R.color.more_button_green);
                }
                featureList.clear();
                workList.clear();
                areaList.clear();
                hotList.clear();

                if (conditionBean != null) {
                    if (conditionBean.getMore() != null) {
                        conditionBean.getMore().getFeature().clear();
                        conditionBean.getMore().getSize().clear();
                        conditionBean.getMore().getWork().clear();
                        conditionBean.getMore().getHottag().clear();
                    }
                }

                break;
            case R.id.newhouse_popupview_more_check:
                if (conditionBean == null) {
                    conditionBean = new NewHouseConditionBean();
                }
                if (conditionBean.getMore() == null) {
                    NewHouseConditionBean.More m = new NewHouseConditionBean.More();
                    conditionBean.setMore(m);
                }
                conditionBean.getMore().setFeature(new ArrayList<String>());
                conditionBean.getMore().setSize(new ArrayList<String>());
                conditionBean.getMore().setWork(new ArrayList<String>());
                conditionBean.getMore().setHottag(new ArrayList<String>());


                //遍历所有的已选信息
                Iterator<NoRepeatButton> featureIterator_check = featureList.iterator();
                while (featureIterator_check.hasNext()) {
                    NoRepeatButton btn = featureIterator_check.next();
                    conditionBean.getMore().getFeature().add((String) btn.getText());
                }

                Iterator<NoRepeatButton> areaIterator_check = areaList.iterator();
                while (areaIterator_check.hasNext()) {
                    NoRepeatButton btn = areaIterator_check.next();
                    conditionBean.getMore().getSize().add((String) btn.getText());
                }
                Iterator<NoRepeatButton> workIterator_check = workList.iterator();
                while (workIterator_check.hasNext()) {
                    NoRepeatButton btn = workIterator_check.next();
                    conditionBean.getMore().getWork().add((String) btn.getText());
                }
                Iterator<NoRepeatButton> hotIterator_check = hotList.iterator();
                while (hotIterator_check.hasNext()) {
                    NoRepeatButton btn = hotIterator_check.next();
                    conditionBean.getMore().getHottag().add((String) btn.getText());
                }
                tv_more.setTextColor(getResources().getColor(R.color.mblue));
                img_more.setImageResource(R.drawable.newhouse_blue_downarrow);
                //最后访问数据库
                if (morePopupWindow != null) {
                    morePopupWindow.dismiss();
                }

                String item = gson.toJson(conditionBean, NewHouseConditionBean.class);
                System.out.println("我的最终数据时" + item);
                HttpUtils.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(NewHouseActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(NewHouseActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;

            default:
                break;
        }

    }

    //显示户型好的Window
    private void showRoomWindow() {
        View roomView = View.inflate(NewHouseActivity.this, R.layout.newhouse_popupwindow_room, null);
        ListView lv_room = (ListView) roomView.findViewById(R.id.newhouse_popupwindow_room_lv);
        getRoomWindowData();
        final MyroomListViewAdapter myroomListViewAdapter = new MyroomListViewAdapter();
        lv_room.setAdapter(myroomListViewAdapter);
        lv_room.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                roomSelectPosition = position;
                myroomListViewAdapter.notifyDataSetChanged();
                tv_room.setText(roomList.get(position));
                tv_room.setTextColor(getResources().getColor(R.color.mblue));
                img_room.setImageResource(R.drawable.newhouse_blue_downarrow);
                if (roomPopupWindow != null) {
                    roomPopupWindow.dismiss();
                }
                if (conditionBean == null) {
                    conditionBean = new NewHouseConditionBean();
                }
                //当我们点击时候 条件存进bean中对应地方 需要判断的是如果点击的是不限我们就设为null 或者空字符串即可
                if (roomList.get(position).equals("不限")) {
                    conditionBean.setRoom("不限");
                } else {
                    conditionBean.setRoom(roomList.get(position));
                }
                String item = gson.toJson(conditionBean, NewHouseConditionBean.class);
                HttpUtils.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(NewHouseActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(NewHouseActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        roomPopupWindow = new PopupWindow(roomView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        roomPopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        roomPopupWindow.setTouchable(true);
        roomPopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
        roomPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    //隐藏底部框
    private void hideBottomView() {
        TranslateAnimation botromTranslation = new TranslateAnimation(0, 0, 0, mHeight);
        botromTranslation.setDuration(500);
        rl_bottom.startAnimation(botromTranslation);
        rl_bottom.setVisibility(View.INVISIBLE);
    }

    //显示底部框
    private void showBottomView() {
        TranslateAnimation botromTranslation = new TranslateAnimation(0, 0, mHeight, 0);
        botromTranslation.setDuration(500);
        rl_bottom.startAnimation(botromTranslation);
        rl_bottom.setVisibility(View.VISIBLE);
    }

    //显示价格的window
    private void showPriceWindow() {
        final View priceView = View.inflate(NewHouseActivity.this, R.layout.newhouse_popupwindow_price, null);
        ListView lv_price = (ListView) priceView.findViewById(R.id.newhouse_popupview_price_lv);
        TextView et_bottomprice = (TextView) priceView.findViewById(R.id.newhouse_et_bottomprice);
        TextView et_topprice = (TextView) priceView.findViewById(R.id.newhouse_et_topprice);
        Button btn_check = (Button) priceView.findViewById(R.id.newhouse_price_btn_check);

        getPriceWindowData();
        final MyPriceListViewAdapter myPriceListViewAdapter = new MyPriceListViewAdapter();
        lv_price.setAdapter(myPriceListViewAdapter);
        lv_price.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                priceSelectPosition = position;
                myPriceListViewAdapter.notifyDataSetChanged();
                tv_price.setText(priceList.get(position));
                tv_price.setTextColor(getResources().getColor(R.color.mblue));
                img_price.setImageResource(R.drawable.newhouse_blue_downarrow);
                if (pricePopupWindow != null) {
                    pricePopupWindow.dismiss();
                }
                if (conditionBean == null) {
                    conditionBean = new NewHouseConditionBean();
                }
                //当我们点击时候 条件存进bean中对应地方 需要判断的是如果点击的是不限我们就设为null 或者空字符串即可
               if(priceList.get(position).equals("两万以下")) {
                    conditionBean.setPrice("0-20000");
                }else if(priceList.get(position).equals("2-3万")) {
                    conditionBean.setPrice("20000-30000");
                }else if(priceList.get(position).equals("3-5万")) {
                    conditionBean.setPrice("30000-50000");
                }else if(priceList.get(position).equals("5-8万")) {
                    conditionBean.setPrice("50000-80000");
                }else if(priceList.get(position).equals("8-10万")) {
                    conditionBean.setPrice("80000-100000");
                }else{
                   conditionBean.setPrice(priceList.get(position));
               }
                String item = gson.toJson(conditionBean, NewHouseConditionBean.class);
                HttpUtils.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(NewHouseActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(NewHouseActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        pricePopupWindow = new PopupWindow(priceView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);

    }

    public void priceclick(View v) {

        switch (v.getId()) {
            case R.id.newhouse_et_bottomprice:
                showPriceBtnWindow(0);
                break;
            case R.id.newhouse_et_topprice:
                showPriceBtnWindow(1);
                break;
            default:
                break;
        }

    }

    private void showPriceBtnWindow(int position) {
        final View priceBtnView = View.inflate(NewHouseActivity.this, R.layout.newhouse_price_btn_window, null);
        final EditText window_bottomprice = (EditText) priceBtnView.findViewById(R.id.newhouse_btn_window_bottomprice);
        final EditText window_topprice = (EditText) priceBtnView.findViewById(R.id.newhouse_btn_window_topprice);
        final Button window_check = (Button) priceBtnView.findViewById(R.id.newhouse_btn_window_check);
        ImageView window_btn_img = (ImageView) priceBtnView.findViewById(R.id.newhouse_price_btn_img);
        final PopupWindow priceBtnWindow = new PopupWindow(priceBtnView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        priceBtnWindow.setAnimationStyle(R.style.popwin_price_btn_anim_style);
        priceBtnWindow.setTouchable(true);
        //设置点击窗口外边窗口消失
        priceBtnWindow.setOutsideTouchable(false);
        // 设置此参数获得焦点，否则无法点击
        priceBtnWindow.setFocusable(true);
        priceBtnWindow.showAtLocation(ll_popupwindow, Gravity.BOTTOM, 0, 0);
        priceBtnWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        TypeWritingUtils.UpOrOffTypeWritting(NewHouseActivity.this);//打开或关闭输入法
        window_btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (priceBtnWindow != null) {
                    priceBtnWindow.dismiss();
                }
                TypeWritingUtils.UpOrOffTypeWritting(NewHouseActivity.this);//打开或关闭输入法
            }
        });
        priceBtnView.setFocusable(true);//设置view能够接听事件
        // 重写onKeyListener
        priceBtnView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (priceBtnWindow != null) {
                        priceBtnWindow.dismiss();
                    }
                }
                return false;
            }
        });

        if (position == 0) {
            window_bottomprice.requestFocus();

        } else {
            window_topprice.requestFocus();

        }
        window_bottomprice.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (priceBtnWindow != null) {
                        priceBtnWindow.dismiss();
                    }
                }
                return false;
            }
        });
        window_topprice.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (priceBtnWindow != null) {
                        priceBtnWindow.dismiss();
                    }
                }
                return false;
            }
        });

        window_bottomprice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String condition=s.toString();
                if(TextUtils.isEmpty(condition)){
                  window_check.setEnabled(false);
                }else {
                    window_check.setEnabled(true);
                }
            }
        });

        window_topprice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String condition=s.toString();
                if(TextUtils.isEmpty(condition)){
                  window_check.setEnabled(false);
                }else {
                    window_check.setEnabled(true);
                }
            }
        });

        window_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isReq=true;
                if (conditionBean == null) {
                    conditionBean = new NewHouseConditionBean();
                }
                String bottom_data = window_bottomprice.getText().toString();
                String top_data = window_topprice.getText().toString();
                //当我们点击时候 条件存进bean中对应地方
                String data="";
                if(TextUtils.isEmpty(bottom_data)&&!TextUtils.isEmpty(top_data)){
                    data=top_data+"元以下";
                    tv_price.setText(data);//左边为空右边不为空
                    conditionBean.setPrice(data);
                }else if(!TextUtils.isEmpty(bottom_data)&&TextUtils.isEmpty(top_data)){
                    data=bottom_data+"元以上";
                    tv_price.setText(data);//左边不为空右边为空
                    conditionBean.setPrice(data);
                }else{
                    int b_data =Integer.parseInt(bottom_data);
                    int t_data =Integer.parseInt(top_data);
                    if(b_data>t_data){
                        //数字区间有问题需要判断一下
                        isReq=false;
                        Toast.makeText(NewHouseActivity.this,"您输入的价格区间有问题",Toast.LENGTH_SHORT).show();
                    }else{
                        data=bottom_data+"-"+top_data+"元";
                        tv_price.setText(data);//左边不为空右边为空
                        conditionBean.setPrice(data);
                    }

                }
                if(isReq){
                    tv_price.setTextColor(getResources().getColor(R.color.mblue));
                    img_price.setImageResource(R.drawable.newhouse_blue_downarrow);
                    if (pricePopupWindow != null) {
                        pricePopupWindow.dismiss();
                    }
                    if (priceBtnWindow != null) {
                        priceBtnWindow.dismiss();
                    }
                    String item = gson.toJson(conditionBean, NewHouseConditionBean.class);
                    HttpUtils.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            NewHouseActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NewHouseActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            NewHouseActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NewHouseActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }

            }
        });
    }

    //显示附近的window
    private void showNearWindow() {
        //弹出附近窗口
        View nearview = View.inflate(NewHouseActivity.this, R.layout.newhouse_popupwindow_near, null);
        final ListView near_lv_left = (ListView) nearview.findViewById(R.id.newhouse_popupview_near_lv_left);
        final ListView near_lv_right = (ListView) nearview.findViewById(R.id.newhouse_popupview_near_lv_right);
        final ListView near_final = (ListView) nearview.findViewById(R.id.newhouse_popupview_near_final);
        final MyLeftListViewAdapter myLeftListViewAdapter = new MyLeftListViewAdapter();
        near_lv_left.setAdapter(myLeftListViewAdapter);
        final MyRightListViewAdapter myRightListViewAdapter = new MyRightListViewAdapter();
        near_lv_right.setAdapter(myRightListViewAdapter);
        myFinalListViewAdapter = new MyFinalListViewAdapter();
        near_final.setAdapter(myFinalListViewAdapter);

        near_lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //这是定位暂时就直接改变一下颜色
                    leftSelectPosition = 0;
                    tv_near.setTextColor(getResources().getColor(R.color.mblue));
                    img_near.setImageResource(R.drawable.newhouse_blue_downarrow);
                    if (nearPopupWindow != null) {
                        nearPopupWindow.dismiss();
                    }
                    if (conditionBean == null) {
                        conditionBean = new NewHouseConditionBean();
                    }
                    if (conditionBean.getArea() == null) {
                        NewHouseConditionBean.Area area = new NewHouseConditionBean.Area();
                        conditionBean.setArea(area);
                    }

                    //点击的是附近 其实只需要把Area四个属性都设置为空即可
                    conditionBean.getArea().setQu("附近");
                    conditionBean.getArea().setZhen("");
                    conditionBean.getArea().setSubwayarea("");
                    conditionBean.getArea().setSubwayline("");
                    String item = gson.toJson(conditionBean, NewHouseConditionBean.class);
                    HttpUtils.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            NewHouseActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NewHouseActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            NewHouseActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(NewHouseActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                } else if (position == 1) {
                    righttList.clear();
                    leftSelectPosition = 1;
                    //关键是这一句，激情了，它可以让listview改动过的数据重新加载一遍，以达到你想要的效果
                    myLeftListViewAdapter.notifyDataSetChanged();
                    righttList.add(rightList_region);
                    myRightListViewAdapter.notifyDataSetChanged();
                    near_lv_right.setVisibility(View.VISIBLE);
                    near_final.setVisibility(View.GONE);
                    near_lv_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            finalList.clear();//清空数据源
                            righttSelectPosition = position;
                            ArrayList<String> datas = righttList.get(0).get(position).getAreas();
                            if (datas.size() == 0) {
                                //如果数据源为空 那么就是第一个直接就问数据库 关闭窗口
                                tv_near.setTextColor(getResources().getColor(R.color.black));
                                img_near.setImageResource(R.drawable.newhouse_gray_downarrow);
                                if (nearPopupWindow != null) {
                                    nearPopupWindow.dismiss();
                                }
                                if (conditionBean == null) {
                                    conditionBean = new NewHouseConditionBean();
                                }
                                if (conditionBean.getArea() == null) {
                                    NewHouseConditionBean.Area area = new NewHouseConditionBean.Area();
                                    conditionBean.setArea(area);
                                }
                                //这里点击的就是不限 只需要设置全部设置为空即可
                                conditionBean.getArea().setQu("");
                                conditionBean.getArea().setZhen("");
                                conditionBean.getArea().setSubwayarea("");
                                conditionBean.getArea().setSubwayline("");

                                String item = gson.toJson(conditionBean, NewHouseConditionBean.class);
                                HttpUtils.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(NewHouseActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        NewHouseActivity.this.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(NewHouseActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                            } else {
                                //有数据 此时需要显示第三个listview 同时刷新数据 还要默认选第一项
                                finalList.add(datas);
                                myFinalListViewAdapter.notifyDataSetChanged();
                                near_final.setVisibility(View.VISIBLE);
                                //设置第三个listview的联动
                                near_final.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        finalSelectPosition = position;
                                        myFinalListViewAdapter.notifyDataSetChanged();
                                        tv_near.setText(finalList.get(0).get(position));
                                        tv_near.setTextColor(getResources().getColor(R.color.mblue));
                                        img_near.setImageResource(R.drawable.newhouse_blue_downarrow);
                                        if (nearPopupWindow != null) {
                                            nearPopupWindow.dismiss();
                                        }
                                        if (conditionBean == null) {
                                            conditionBean = new NewHouseConditionBean();
                                        }
                                        if (conditionBean.getArea() == null) {
                                            NewHouseConditionBean.Area area = new NewHouseConditionBean.Area();
                                            conditionBean.setArea(area);
                                        }
                                        conditionBean.getArea().setQu(righttList.get(0).get(righttSelectPosition).getName());
                                        conditionBean.getArea().setZhen(finalList.get(0).get(position));
                                        conditionBean.getArea().setSubwayarea("");
                                        conditionBean.getArea().setSubwayline("");
                                        String item = gson.toJson(conditionBean, NewHouseConditionBean.class);
                                        HttpUtils.sendOkHttpRequestForForm("http://192.168.191.1:8080/house/newhouse_near_getWindowRequestData.action", "condition", item, new Callback() {
                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                NewHouseActivity.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(NewHouseActivity.this, "请求数据失败", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                NewHouseActivity.this.runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(NewHouseActivity.this, "请求数据成功啦", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });

                            }
                            myRightListViewAdapter.notifyDataSetChanged();

                        }
                    });

                } else {
                    leftSelectPosition = 2;
                    myLeftListViewAdapter.notifyDataSetChanged();
                    righttList.clear();
                    righttList.add(rightList_subway);
                    myRightListViewAdapter.notifyDataSetChanged();
                    near_lv_right.setVisibility(View.VISIBLE);
                    near_final.setVisibility(View.GONE);
                }
            }

        });
        nearPopupWindow = new PopupWindow(nearview, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        nearPopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        nearPopupWindow.setTouchable(true);
        nearPopupWindow.showAtLocation(tv_near, Gravity.TOP, 0, 0);
        nearPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    private void initData() {
        lefttList = new ArrayList();
        righttList = new ArrayList();
        lefttList.add("附近");
        lefttList.add("区域");
        lefttList.add("地铁");
        finalList = new ArrayList();
        requestPopupWindowData("http://192.168.191.1:8080/house/newhouse_near_getNearData.action");
        requestHouseListData("http://192.168.191.1:8080/house/newhouse_near_getNearData.action");
        lv.setAdapter(new MyAdapter());
    }

    /**
     * 请求房源信息
     *
     * @param address
     */
    private void requestHouseListData(String address) {

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

    private void requestPopupWindowData(String address) {

        HttpUtils.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                NewHouseActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NewHouseActivity.this, "不好意思，访问失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                String data = IOStreamUtils.readFromInputStream(inputStream);
                Message message = Message.obtain();
                message.what = POPUPWINDOW_DATA;
                message.obj = data;
                handler.sendMessage(message);
            }
        });
    }

    private void getPriceWindowData() {
        priceList = new ArrayList<String>();
        priceList.add("不限");
        priceList.add("优惠楼盘");
        priceList.add("两万以下");
        priceList.add("2-3万");
        priceList.add("3-5万");
        priceList.add("5-8万");
        priceList.add("8-10万");
        priceList.add("10万以上");
    }

    private void getRoomWindowData() {
        roomList = new ArrayList<String>();
        roomList.add("不限");
        roomList.add("一室");
        roomList.add("二室");
        roomList.add("三室");
        roomList.add("四室");
        roomList.add("五室以上");

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
            ViewHolderHead viewHolderHead = null;
            ViewHolderMiddle viewHolderMiddle = null;
            ViewHolderNormal viewHolderNormal = null;
            if (convertView == null) {
                //没有缓存我们需要新创出各个需要的控件
                //根据当前的类型创建对应的布局
                switch (type) {
                    case Type_1:
                        convertView = View.inflate(NewHouseActivity.this, R.layout.newhouse_lv_headview, null);
                        viewHolderHead = new ViewHolderHead();
                        viewHolderHead.tv = (TextView) convertView.findViewById(R.id.newhouse_tv_headview);
                        convertView.setTag(viewHolderHead);
                        break;
                    case Type_2:
                        convertView = View.inflate(NewHouseActivity.this, R.layout.newhouse_lv_normalview, null);
                        viewHolderNormal = new ViewHolderNormal();
                        viewHolderNormal.img = (ImageView) convertView.findViewById(R.id.newhouse_img_normalview);
                        viewHolderNormal.left = (TextView) convertView.findViewById(R.id.newhouse_tv_normalview_left);
                        viewHolderNormal.title = (TextView) convertView.findViewById(R.id.newhouse_tv_normalview_title);
                        viewHolderNormal.area = (TextView) convertView.findViewById(R.id.newhouse_tv_normalview_area);
                        viewHolderNormal.discount = (TextView) convertView.findViewById(R.id.newhouse_tv_normalview_discount);
                        viewHolderNormal.money = (TextView) convertView.findViewById(R.id.newhouse_tv_normalview_money);
                        convertView.setTag(viewHolderNormal);
                        break;
                    case Type_3:
                        convertView = View.inflate(NewHouseActivity.this, R.layout.newhouse_lv_middleview, null);
                        viewHolderMiddle = new ViewHolderMiddle();
                        viewHolderMiddle.btn = (Button) convertView.findViewById(R.id.newhouse_btn_middleview_btn);
                        convertView.setTag(viewHolderMiddle);
                        break;
                    default:
                        break;
                }
            } else {
                switch (type) {
                    case Type_1:
                        viewHolderHead = (ViewHolderHead) convertView.getTag();
                        break;
                    case Type_2:
                        viewHolderNormal = (ViewHolderNormal) convertView.getTag();
                        break;
                    case Type_3:
                        viewHolderMiddle = (ViewHolderMiddle) convertView.getTag();
                        break;
                    default:
                        break;
                }

            }

            //设置资源
            switch (type) {
                case Type_1:
                    viewHolderHead.tv.setText("找到4个楼盘");
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
                            Toast.makeText(NewHouseActivity.this, "你真的要订阅吗?", Toast.LENGTH_SHORT).show();
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

    class MyRightListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return (righttList.get(0)).size();

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
            View view;
            if (convertView == null) {
                view = View.inflate(NewHouseActivity.this, R.layout.newhouse_pop_near_left_lv_item, null);

            } else {
                view = convertView;
            }
            TextView item_tv = (TextView) view.findViewById(R.id.newhouse_left_lv_item_tv);
            // System.out.println("我的点击选项是+"+righttSelectPosition);
            if (righttList.get(0).size() != 0) {
                item_tv.setText(righttList.get(0).get(position).getName());
                if (righttSelectPosition == position) {
                    item_tv.setTextColor(getResources().getColor(R.color.mblue));
                } else {
                    item_tv.setTextColor(getResources().getColor(R.color.black));
                }
            }
            return view;

        }
    }

    class MyFinalListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return finalList.get(0).size();
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
            View view;
            if (convertView == null) {
                view = View.inflate(NewHouseActivity.this, R.layout.newhouse_pop_near_left_lv_item, null);

            } else {
                view = convertView;
            }
            TextView item_tv = (TextView) view.findViewById(R.id.newhouse_left_lv_item_tv);
            item_tv.setText(finalList.get(0).get(position));
            if (finalSelectPosition == position) {
                item_tv.setTextColor(getResources().getColor(R.color.mblue));
            } else {
                item_tv.setTextColor(getResources().getColor(R.color.black));
            }
            return view;
        }
    }

    class MyLeftListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lefttList.size();
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
            View view;
            if (convertView == null) {
                view = View.inflate(NewHouseActivity.this, R.layout.newhouse_pop_near_left_lv_item, null);

            } else {
                view = convertView;
            }

            TextView item_tv = (TextView) view.findViewById(R.id.newhouse_left_lv_item_tv);
            item_tv.setText((String) (lefttList.get(position)));
            if (leftSelectPosition == position) {
                item_tv.setTextColor(getResources().getColor(R.color.mblue));
            } else {
                item_tv.setTextColor(getResources().getColor(R.color.black));
            }
            return view;
        }
    }

    class MyPriceListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return priceList.size();
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
            View view;
            if (convertView == null) {
                view = View.inflate(NewHouseActivity.this, R.layout.newhouse_pop_near_left_lv_item, null);

            } else {
                view = convertView;
            }

            TextView item_tv = (TextView) view.findViewById(R.id.newhouse_left_lv_item_tv);
            item_tv.setText(priceList.get(position));
            if (priceSelectPosition == position) {
                item_tv.setTextColor(getResources().getColor(R.color.mblue));
            } else {
                item_tv.setTextColor(getResources().getColor(R.color.black));
            }
            return view;
        }
    }

    class MyroomListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return roomList.size();
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
            View view;
            if (convertView == null) {
                view = View.inflate(NewHouseActivity.this, R.layout.newhouse_pop_near_left_lv_item, null);

            } else {
                view = convertView;
            }

            TextView item_tv = (TextView) view.findViewById(R.id.newhouse_left_lv_item_tv);
            item_tv.setText(roomList.get(position));
            if (roomSelectPosition == position) {
                item_tv.setTextColor(getResources().getColor(R.color.mblue));
            } else {
                item_tv.setTextColor(getResources().getColor(R.color.black));
            }
            return view;
        }
    }


}


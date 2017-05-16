package fangke.com.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 商品写字楼页面
 *@author ChongZi007
 *@time 2017/3/30 21:00
 *@参数
 *@return
*/
public class ShopOfficeActivity extends Activity {
    public final static int Type_1 = 0;
    public final static int Type_2 = 1;
    public final static int Type_3 = 2;
    private ListView lv;
    private ArrayList list;
    private TextView tv_near;
    private ImageView img_near;
    private TextView tv_price;
    private ImageView img_price;
    private float mLastY = 0;
    private RelativeLayout rl_bottom;
    private float mY = 0;
    private int mHeight;
    private TextView tv_area;
    private ImageView img_area;
    private TextView tv_rank;
    private ImageView img_rank;
    private TextView tv_new;
    private ImageView img_new;
    private TextView tv_discount;
    private ImageView img_discount;
    private TextView tv_clever;
    private ImageView img_clever;
    private TextView tv_freelook;
    private ImageView img_freelook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_shop_office);
        initData();
        initViews();
        initListener();

    }



    private void initViews() {
        //得到popupwindow的所有按钮
        tv_near = (TextView) findViewById(R.id.office_tv_near);
        img_near = (ImageView) findViewById(R.id.office_img_near);
        tv_price = (TextView) findViewById(R.id.office_tv_price);
        img_price = (ImageView) findViewById(R.id.office_img_price);
        tv_area = (TextView) findViewById(R.id.office_tv_area);
        img_area = (ImageView) findViewById(R.id.office_img_area);
        tv_rank = (TextView) findViewById(R.id.office_tv_rank);
        img_rank = (ImageView) findViewById(R.id.office_img_rank);



        lv = (ListView) findViewById(R.id.office_lv);
        lv.setAdapter(new ShopOfficeActivity.MyAdapter());
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
        tv_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomWindow();
            }
        });
        img_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRoomWindow();
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
    }

    //显示更多的window
    private void showMoreWindow() {
        View moreView = View.inflate(ShopOfficeActivity.this, R.layout.office_popupwindow_rank, null);
        //处理几百个按钮的点击事件

        PopupWindow pricePopupWindow = new PopupWindow(moreView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
        pricePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    //显示户型好的Window
    private void showRoomWindow() {
        View roomView = View.inflate(ShopOfficeActivity.this, R.layout.office_popupwindow_area, null);
        ListView lv_room = (ListView) roomView.findViewById(R.id.office_popupwindow_area_lv);
        SimpleAdapter priceAdapter = new SimpleAdapter(ShopOfficeActivity.this, getRoomWindowData(), R.layout.popupwindow_room_item,
                new String[]{"title"}, new int[]{R.id.popupwindow_room_item_tv});
        lv_room.setAdapter(priceAdapter);
        PopupWindow pricePopupWindow = new PopupWindow(roomView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
        pricePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }




    //显示价格的window
    private void showPriceWindow() {
        View priceView = View.inflate(ShopOfficeActivity.this, R.layout.office_popupwindow_price, null);
        ListView lv_price = (ListView) priceView.findViewById(R.id.office_popupview_price_lv);
        SimpleAdapter priceAdapter = new SimpleAdapter(ShopOfficeActivity.this, getPriceWindowData(), R.layout.popupwindow_price_item,
                new String[]{"title"}, new int[]{R.id.popupwindow_price_item_tv});
        lv_price.setAdapter(priceAdapter);
        PopupWindow pricePopupWindow = new PopupWindow(priceView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pricePopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        pricePopupWindow.setTouchable(true);
        pricePopupWindow.showAtLocation(tv_price, Gravity.TOP, 0, 0);
        pricePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    //显示附近的window
    private void showNearWindow() {
        //弹出附近窗口
        View nearview = View.inflate(ShopOfficeActivity.this, R.layout.office_popupwindow_near, null);
        final ListView near_lv_left = (ListView) nearview.findViewById(R.id.office_popupview_near_lv_left);
        final ListView near_lv_right = (ListView) nearview.findViewById(R.id.office_popupview_near_lv_right);

        SimpleAdapter nearAdapter = new SimpleAdapter(ShopOfficeActivity.this, getNearWindowData(), R.layout.popupwindow_near_left_item,
                new String[]{"title"}, new int[]{R.id.popupwindow_near_left});
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
                        SimpleAdapter nearRightAdapter = new SimpleAdapter(ShopOfficeActivity.this, arrayList, R.layout.popupwindow_near_right_item,
                                new String[]{"title"}, new int[]{R.id.popupwindow_near_right});
                        near_lv_right.setAdapter(nearRightAdapter);

                    }

                } else if (position == 2) {
                    near_lv_right.setVisibility(View.VISIBLE);
                }
            }
        });


        PopupWindow nearPopupWindow = new PopupWindow(nearview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        nearPopupWindow.setAnimationStyle(R.style.popwin_near_anim_style);
        nearPopupWindow.setTouchable(true);
        nearPopupWindow.showAtLocation(tv_near, Gravity.TOP, 0, 0);
        nearPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }

    private ArrayList<HashMap<String, Object>> getNearWindowData() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
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

    private ArrayList<HashMap<String, Object>> getPriceWindowData() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> tempHashMap1 = new HashMap<String, Object>();
        tempHashMap1.put("title", "不限");
        HashMap<String, Object> tempHashMap2 = new HashMap<String, Object>();
        tempHashMap2.put("title", "优惠楼盘");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "一万以下");
        HashMap<String, Object> tempHashMap4 = new HashMap<String, Object>();
        tempHashMap4.put("title", "1-1.5万");
        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
        tempHashMap5.put("title", "1.5到2万");
        HashMap<String, Object> tempHashMap6 = new HashMap<String, Object>();
        tempHashMap6.put("title", "2万-3万");
        HashMap<String, Object> tempHashMap7 = new HashMap<String, Object>();
        tempHashMap7.put("title", "3万-4万");
        HashMap<String, Object> tempHashMap8 = new HashMap<String, Object>();
        tempHashMap8.put("title", "4万以上");

        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);
        arrayList.add(tempHashMap4);
        arrayList.add(tempHashMap5);
        arrayList.add(tempHashMap6);
        arrayList.add(tempHashMap7);
        arrayList.add(tempHashMap8);

        return arrayList;

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
            ShopOfficeActivity.ViewHolderHead viewHolderHead = null;
            ShopOfficeActivity.ViewHolderMiddle viewHolderMiddle = null;
            ShopOfficeActivity.ViewHolderNormal viewHolderNormal = null;
            if (convertView == null) {
                //没有缓存我们需要新创出各个需要的控件
                //根据当前的类型创建对应的布局
                switch (type) {
                    case Type_1:
                        convertView = View.inflate(ShopOfficeActivity.this, R.layout.office_lv_headview, null);
                        viewHolderHead = new ShopOfficeActivity.ViewHolderHead();
                        viewHolderHead.tv = (TextView) convertView.findViewById(R.id.office_tv_headview);
                        convertView.setTag(viewHolderHead);
                        break;
                    case Type_2:
                        convertView = View.inflate(ShopOfficeActivity.this, R.layout.office_lv_normalview, null);
                        viewHolderNormal = new ShopOfficeActivity.ViewHolderNormal();
                        viewHolderNormal.img = (ImageView) convertView.findViewById(R.id.office_img_normalview);
                        viewHolderNormal.left = (TextView) convertView.findViewById(R.id.office_tv_normalview_left);
                        viewHolderNormal.title = (TextView) convertView.findViewById(R.id.office_tv_normalview_title);
                        viewHolderNormal.area = (TextView) convertView.findViewById(R.id.office_tv_normalview_area);
                        viewHolderNormal.discount = (TextView) convertView.findViewById(R.id.office_tv_normalview_discount);
                        viewHolderNormal.money = (TextView) convertView.findViewById(R.id.office_tv_normalview_money);
                        convertView.setTag(viewHolderNormal);
                        break;
                    case Type_3:
                        convertView = View.inflate(ShopOfficeActivity.this, R.layout.office_lv_middleview, null);
                        viewHolderMiddle = new ShopOfficeActivity.ViewHolderMiddle();
                        viewHolderMiddle.btn = (Button) convertView.findViewById(R.id.office_btn_middleview_btn);
                        convertView.setTag(viewHolderMiddle);
                        break;
                    default:
                        break;
                }
            } else {
                switch (type) {
                    case Type_1:
                        viewHolderHead = (ShopOfficeActivity.ViewHolderHead) convertView.getTag();
                        break;
                    case Type_2:
                        viewHolderNormal = (ShopOfficeActivity.ViewHolderNormal) convertView.getTag();
                        break;
                    case Type_3:
                        viewHolderMiddle = (ShopOfficeActivity.ViewHolderMiddle) convertView.getTag();
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
                            Toast.makeText(ShopOfficeActivity.this, "你真的要订阅吗?", Toast.LENGTH_SHORT).show();
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

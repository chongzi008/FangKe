package fangke.com.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 租房页面
 *
 * @author ChongZi007
 * @time 2017/3/30 21:00
 * @参数
 * @return
 */
public class RentalHouseActivity extends Activity {

    private TextView tv_quyu;
    private ImageView img_quyu;
    private TextView tv_price;
    private ImageView img_price;
    private TextView tv_more;
    private ImageView img_more;
    private ListView rental_lv;
    private ArrayList list;
    private RelativeLayout rl_top;
    private ImageView narrow_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_house);
        initData();
        initViews();
        initListener();
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

    private void initViews() {
        //得到区域 售价 更多的控件
        tv_quyu = (TextView) findViewById(R.id.rental_middle_tv_quyu);
        img_quyu = (ImageView) findViewById(R.id.rental_middle_img_quyu);
        tv_price = (TextView) findViewById(R.id.rental_middle_tv_price);
        img_price = (ImageView) findViewById(R.id.rental_middle_img_price);
        narrow_left = (ImageView) findViewById(R.id.rental_house_narrow_left);
        tv_more = (TextView) findViewById(R.id.rental_middle_tv_more);
        img_more = (ImageView) findViewById(R.id.rental_middle_img_more);
        //得到lv 设置adapter
        rental_lv = (ListView) findViewById(R.id.rental_lv);
        rental_lv.setAdapter(new MyAdapter());
        //得到顶部相对布局
        rl_top = (RelativeLayout) findViewById(R.id.rentalhouse_rl_top);
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
        narrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //弹出区域的window
    private void showQuYuWindow() {
        View quyuview = View.inflate(RentalHouseActivity.this, R.layout.rentalhouse_popupwindow_near, null);
        final ListView near_lv_left = (ListView) quyuview.findViewById(R.id.rental_popupview_near_lv_left);
        final ListView near_lv_right = (ListView) quyuview.findViewById(R.id.rental_popupview_near_lv_right);

        SimpleAdapter nearAdapter = new SimpleAdapter(RentalHouseActivity.this, getQuYuWindowData(), R.layout.rentalhouse_popupwindow_more_left_item,
                new String[]{"title"}, new int[]{R.id.rentalhouse_popupwindow_more_left});

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
                        SimpleAdapter nearRightAdapter = new SimpleAdapter(RentalHouseActivity.this, arrayList, R.layout.popupwindow_near_right_item,
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

    //弹出售价的窗口

    private void showPriceWindow() {

        View priceView = View.inflate(RentalHouseActivity.this, R.layout.rentalhandhouse_popupwindow_price, null);
        ListView lv_price = (ListView) priceView.findViewById(R.id.rentalhouse_popupview_price_lv);
        SimpleAdapter priceAdapter = new SimpleAdapter(RentalHouseActivity.this, getPriceWindowData(), R.layout.rentalhandhouse_popupwindow_price_item,
                new String[]{"title"}, new int[]{R.id.rentalhandhouse_popupwindow_price_item_tv});
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
        tempHashMap2.put("title", "0-500");
        HashMap<String, Object> tempHashMap3 = new HashMap<String, Object>();
        tempHashMap3.put("title", "500-800");
        HashMap<String, Object> tempHashMap4 = new HashMap<String, Object>();
        tempHashMap4.put("title", "800-1000");
        HashMap<String, Object> tempHashMap5 = new HashMap<String, Object>();
        tempHashMap5.put("title", "1000-1500");
        HashMap<String, Object> tempHashMap6 = new HashMap<String, Object>();
        tempHashMap6.put("title", "1500-2000");
        HashMap<String, Object> tempHashMap7 = new HashMap<String, Object>();
        tempHashMap7.put("title", "2000-3000");
        HashMap<String, Object> tempHashMap8 = new HashMap<String, Object>();
        tempHashMap8.put("title", "3000-5000");
        HashMap<String, Object> tempHashMap9 = new HashMap<String, Object>();
        tempHashMap9.put("title", "5000以上");
        arrayList.add(tempHashMap1);
        arrayList.add(tempHashMap2);
        arrayList.add(tempHashMap3);
        arrayList.add(tempHashMap4);
        arrayList.add(tempHashMap5);
        arrayList.add(tempHashMap6);
        arrayList.add(tempHashMap7);
        arrayList.add(tempHashMap8);
        arrayList.add(tempHashMap9);
        return arrayList;

    }

    //弹出更多的窗口
    private void showMoreWindow() {
        View moreview = View.inflate(RentalHouseActivity.this, R.layout.rentalhouse_popupwindow_more, null);
        final ListView rental_near_lv_left = (ListView) moreview.findViewById(R.id.rental_popupview_more_lv_left);
        final ListView rental_near_lv_right = (ListView) moreview.findViewById(R.id.rental_popupview_more_lv_right);


        ArrayAdapter<String> adapterRight = new ArrayAdapter<String>(RentalHouseActivity.this,
                R.layout.rentalhouse_popupwindow_more_right_simple_item, getRightData());

        ArrayAdapter<String> adapterLeft = new ArrayAdapter<String>(RentalHouseActivity.this,
                R.layout.rentalhouse_popupwindow_more_left_simple_item, getLeftData());
        rental_near_lv_right.setAdapter(adapterRight);

        rental_near_lv_left.setAdapter(adapterLeft);

        rental_near_lv_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                    ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(RentalHouseActivity.this,
                            R.layout.rentalhouse_popupwindow_more_right_simple_item, getRightData());
                    rental_near_lv_right.setAdapter(adapter0);
                } else if (position == 1) {

                    ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(RentalHouseActivity.this,
                            R.layout.rentalhouse_popupwindow_more_right_simple_item, getRight_1_Data());
                    rental_near_lv_right.setAdapter(adapter1);

                } else if (position == 2) {
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(RentalHouseActivity.this,
                            R.layout.rentalhouse_popupwindow_more_right_simple_item, getRight_2_Data());
                    rental_near_lv_right.setAdapter(adapter2);

                } else if (position == 3) {
                    ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(RentalHouseActivity.this,
                            R.layout.rentalhouse_popupwindow_more_right_simple_item, getRight_3_Data());
                    rental_near_lv_right.setAdapter(adapter3);

                } else if (position == 4) {
                    ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(RentalHouseActivity.this,
                            R.layout.rentalhouse_popupwindow_more_right_simple_item, getRight_4_Data());
                    rental_near_lv_right.setAdapter(adapter4);

                }
            }
        });


        PopupWindow morePopupWindow = new PopupWindow(moreview, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        morePopupWindow.setAnimationStyle(R.style.rentalhouse_popwin_near_anim_style);
        morePopupWindow.setTouchable(true);
        morePopupWindow.showAsDropDown(rl_top, 0, 0);
        morePopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));

    }


    private String[] getLeftData() {
        String[] array = new String[]{
                "房型",
                "整租/合租",
                "来源",
                "装修",
                "排序"
        };
        return array;
    }
    private String[] getRightData() {
        String[] array = new String[]{
                "不限",
                "1室",
                "二室",
                "三室",
                "四室+"
        };
        return array;
    }
    private String[] getRight_1_Data() {
        String[] array = new String[]{
                "不限",
                "整租",
                "合租",

        };
        return array;
    }
    private String[] getRight_2_Data() {
        String[] array = new String[]{
                "不限",
                "个人",
                "经纪人",
        };
        return array;
    }
    private String[] getRight_3_Data() {
        String[] array = new String[]{
                "不限",
                "毛坯",
                "简单装修",
                "中等装修",
                "高等装修",
                "豪华装修"
        };
        return array;
    }
    private String[] getRight_4_Data() {
        String[] array = new String[]{
                "默认排序",
                "租金从低到高",
                "租金从高到低",

        };
        return array;
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
            RentalHouseActivity.MyViewHolder holder = null;
            if (convertView == null) {
                //缓存为空 需要news资源
                convertView = View.inflate(RentalHouseActivity.this, R.layout.rental_lv_item, null);
                holder = new RentalHouseActivity.MyViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.rental_lv_img_normalview);
                holder.left = (TextView) convertView.findViewById(R.id.rental_tv_normalview_left);
                holder.title = (TextView) convertView.findViewById(R.id.rental_tv_normalview_title);
                holder.area = (TextView) convertView.findViewById(R.id.rental_tv_normalview_area);
                holder.discount = (TextView) convertView.findViewById(R.id.rental_tv_normalview_discount);
                holder.money = (TextView) convertView.findViewById(R.id.rental_tv_normalview_money);
                convertView.setTag(holder);

            } else {
                //缓存不为空直接取
                holder = (RentalHouseActivity.MyViewHolder) convertView.getTag();
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

package fangke.com.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
    private ArrayList list;
    private ImageView narrow_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overseas_house);
        initDatas();
        initViews();
        initListener();
    }


    private void initListener() {
        narrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initDatas() {


        for (int i = 0; i < imageIds.length; i++) {
            CycleImageBean info = new CycleImageBean();
            info.setImgId(imageIds[i]);
            infos.add(info);
        }

        images = new int[]{R.drawable.usa, R.drawable.england, R.drawable.putaoya,
                R.drawable.xinjiapo, R.drawable.malaixiya, R.drawable.cannada, R.drawable.morearea};

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
        overseas_lv = (ListView) findViewById(R.id.overseas_lv);
        narrow_left = (ImageView) findViewById(R.id.oversears_narrow_left);
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


        overseas_lv.setAdapter(new MyAdapter());
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
            OverseasHouseActivity.MyViewHolder holder = null;
            if (convertView == null) {
                //缓存为空 需要news资源
                convertView = View.inflate(OverseasHouseActivity.this, R.layout.overseashouse_lv_item, null);
                holder = new OverseasHouseActivity.MyViewHolder();
                holder.img = (ImageView) convertView.findViewById(R.id.overseashouse_lv_img_normalview);
                holder.left = (TextView) convertView.findViewById(R.id.overseashouse_tv_normalview_left);
                holder.title = (TextView) convertView.findViewById(R.id.overseashouse_tv_normalview_title);
                holder.area = (TextView) convertView.findViewById(R.id.overseashouse_tv_normalview_area);
                holder.discount = (TextView) convertView.findViewById(R.id.overseashouse_tv_normalview_discount);
                holder.money = (TextView) convertView.findViewById(R.id.overseashouse_tv_normalview_money);
                convertView.setTag(holder);

            } else {
                //缓存不为空直接取
                holder = (OverseasHouseActivity.MyViewHolder) convertView.getTag();
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

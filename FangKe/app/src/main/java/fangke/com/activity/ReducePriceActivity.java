package fangke.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



/**
 * 降价楼盘页面
 *@author ChongZi007
 *@time 2017/3/30 21:00
 *@参数
 *@return
*/
public class ReducePriceActivity extends AppCompatActivity {
    private ArrayList list;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reduce_price);
        initData();
        initViews();
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

    private void initViews(){
        lv = (ListView) findViewById(R.id.reduce_price_lv);
        lv.setAdapter(new MyAdapter());
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
        public View getView(int position, View convertView, ViewGroup parent){

            ViewHolder viewHolder = null;
            if(convertView==null){
                convertView =  View.inflate(ReducePriceActivity.this,R.layout.reduce_price_lv,null);
                viewHolder = new ViewHolder();
                viewHolder.img = (ImageView) convertView.findViewById(R.id.reduce_price_img_normalview);
                viewHolder.left = (TextView) convertView.findViewById(R.id.reduce_price_tv_normalview_left);
                viewHolder.title = (TextView) convertView.findViewById(R.id.reduce_price_tv_normalview_title);
                viewHolder.area = (TextView) convertView.findViewById(R.id.reduce_price_tv_normalview_area);
                viewHolder.discount = (TextView) convertView.findViewById(R.id.reduce_price_tv_normalview_discount);
                viewHolder.money = (TextView) convertView.findViewById(R.id.reduce_price_tv_normalview_money);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            ArrayList l = (ArrayList) list.get(position);
            viewHolder.img.setBackgroundResource((Integer) l.get(0));
            viewHolder.title.setText((String) l.get(1));
            viewHolder.area.setText((String) l.get(2));
            viewHolder.money.setText((String) l.get(3));
            return convertView;
        }

        class ViewHolder {
            //一般的holder
            private ImageView img;
            private TextView left;
            private TextView title;
            private TextView area;
            private TextView discount;
            private TextView money;

        }

    }
}

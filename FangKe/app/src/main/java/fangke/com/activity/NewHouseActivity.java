package fangke.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * 新房页面
 *
 * @author ChongZi007
 * @time 2017/3/30 20:59
 * @参数
 * @return
 */
public class NewHouseActivity extends AppCompatActivity {
    public final static int Type_1 = 0;
    public final static int Type_2 = 1;
    public final static int Type_3 = 2;
    private ListView lv;
    private ArrayList list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_house);
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

    private void initViews() {

        lv = (ListView) findViewById(R.id.newhouse_lv);
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
                        viewHolderNormal.money= (TextView) convertView.findViewById(R.id.newhouse_tv_normalview_money);
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
                    viewHolderHead.tv.setText("找到4个楼盘吧");
                    break;
                case Type_2:
                    ArrayList l = (ArrayList) list.get(position-1);
                    viewHolderNormal.img.setBackgroundResource((Integer) l.get(0));
                    viewHolderNormal.title.setText((String)l.get(1));
                    viewHolderNormal.area.setText((String)l.get(2));
                    viewHolderNormal.money.setText((String)l.get(3));


                    break;
                case Type_3:
                    viewHolderMiddle.btn.setClickable(true);
                    viewHolderMiddle.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(NewHouseActivity.this,"你真的要订阅吗?",Toast.LENGTH_SHORT).show();
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


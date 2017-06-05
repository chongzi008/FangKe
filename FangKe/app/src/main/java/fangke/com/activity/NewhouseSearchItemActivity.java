package fangke.com.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class NewhouseSearchItemActivity extends AppCompatActivity {

    private ImageView narraw_left;
    private TextView searct_tv;
    private ListView searct_lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newhouse_search_item);
        initViews();
        initData();
        initListner();
    }

    private void initListner() {
   narraw_left.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           finish();
       }
   });
    }

    private void initData() {
        Bundle myBundle = getIntent().getExtras();
        String title  = myBundle.getString("title");
        searct_tv.setText(title);
    }

    private void initViews() {
        narraw_left = (ImageView) findViewById(R.id.newhouse_search_item_left);
        searct_tv = (TextView) findViewById(R.id.newhouse_search_item_);
        searct_lv = (ListView) findViewById(R.id.newhouse_search_item_lv);

    }
}

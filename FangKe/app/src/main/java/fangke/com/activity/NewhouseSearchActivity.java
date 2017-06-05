package fangke.com.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import fangke.com.view.NoRepeatButton;

/**
 * 新房搜索页面
 */
public class NewhouseSearchActivity extends AppCompatActivity {

    private EditText search_ed;
    private TextView search_cancel;
    private NoRepeatButton search_item1;
    private NoRepeatButton search_item2;
    private NoRepeatButton search_item3;
    private NoRepeatButton search_item4;
    private NoRepeatButton search_item5;
    private NoRepeatButton search_item6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newhouse_search);
        initViews();
        initData();
        initListner();
    }

    private void initViews() {
        search_ed = (EditText) findViewById(R.id.newhouse_search_ed);
        search_cancel = (TextView) findViewById(R.id.newhouse_search_cancel);
        search_item1 = (NoRepeatButton) findViewById(R.id.newhouse_search_item1);
        search_item2 = (NoRepeatButton) findViewById(R.id.newhouse_search_item2);
        search_item3 = (NoRepeatButton) findViewById(R.id.newhouse_search_item3);
        search_item4 = (NoRepeatButton) findViewById(R.id.newhouse_search_item4);
        search_item5 = (NoRepeatButton) findViewById(R.id.newhouse_search_item5);
        search_item6 = (NoRepeatButton) findViewById(R.id.newhouse_search_item6);
    }

    private void initData() {

    }

    private void initListner() {
        search_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.newhouse_search_item1:
                Intent intent=new Intent(NewhouseSearchActivity.this,NewhouseSearchItemActivity.class);
                startMyActivity(intent,(String) ((NoRepeatButton)v).getText());

                break;
            case R.id.newhouse_search_item2:
                Intent intent2=new Intent(NewhouseSearchActivity.this,NewhouseSearchItemActivity.class);
                startMyActivity(intent2,(String) ((NoRepeatButton)v).getText());
                break;
            case R.id.newhouse_search_item3:
                Intent intent3=new Intent(NewhouseSearchActivity.this,NewhouseSearchItemActivity.class);
                startMyActivity(intent3,(String) ((NoRepeatButton)v).getText());
                break;
            case R.id.newhouse_search_item4:
                Intent intent4=new Intent(NewhouseSearchActivity.this,NewhouseSearchItemActivity.class);
                startMyActivity(intent4,(String) ((NoRepeatButton)v).getText());
                break;
            case R.id.newhouse_search_item5:
                Intent intent5=new Intent(NewhouseSearchActivity.this,NewhouseSearchItemActivity.class);
                startMyActivity(intent5,(String) ((NoRepeatButton)v).getText());
                break;
            case R.id.newhouse_search_item6:
                Intent intent6=new Intent(NewhouseSearchActivity.this,NewhouseSearchItemActivity.class);
                startMyActivity(intent6,(String) ((NoRepeatButton)v).getText());
                break;

            default:
                break;
        }
    }

    public void startMyActivity(Intent intent ,String title) {
        //用Bundle携带数据
        Bundle bundle=new Bundle();
        //传递name参数为
        bundle.putString("title",title);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

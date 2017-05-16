package fangke.com.activity;

import android.app.Activity;
import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TabHost;

/**
 * 卖房页面
 *@author ChongZi007
 *@time 2017/3/30 21:00
 *@参数
 *@return
*/
public class SellHouseActivity extends TabActivity {

    private TabHost tabhost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sell_house);
        //从TabActivity上面获取放置Tab的TabHost
        tabhost = getTabHost();

        tabhost.addTab(tabhost
                //创建新标签one
                .newTabSpec("one")
                //设置标签标题
                .setIndicator("出售")
                //设置该标签的布局内容
                .setContent(R.id.sell_house));
        tabhost.addTab(tabhost
                .newTabSpec("two")
                .setIndicator("出租")
                .setContent(R.id.rental_house));
    }
}

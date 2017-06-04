package fangke.com.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amap.api.services.a.v;

/**
 * 独家优惠页面
 *@author ChongZi007
 *@time 2017/3/30 20:55
 *@参数
 *@return
*/
public class DiscountActivity extends AppCompatActivity {

    private ImageView fanhui;
    private View home;
    private EditText telephoneet;
    private Button sendmessage;
    private CheckBox tongyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);


        home = findViewById(R.id.linerlayout);
        fanhui = (ImageView)findViewById(R.id.fanhui);
        telephoneet = (EditText) findViewById(R.id.telephone);
        sendmessage = (Button) findViewById(R.id.sendmessage);
        tongyi = (CheckBox) findViewById(R.id.tongyi);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("点击成功");
            }
        });

        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent = new Intent(DiscountActivity.this,HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);;
                startActivity(intent);
                finish();
            }
        });
        tongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tongyi.isChecked()){
                    sendmessage.setEnabled(true);
                }else {
                    sendmessage.setEnabled(false);
                }
            }
        });

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = telephoneet.getText().toString();
                if(telephoneet.length()!=11){
                    Toast.makeText(DiscountActivity.this,"请输入正确的手机号码",Toast.LENGTH_SHORT).show();
                }else {
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(phone, null, "这是你要的信息", null, null);
                    Toast.makeText(DiscountActivity.this,"发送成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}


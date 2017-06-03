package fangke.com.activity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import utils.IOStreamUtils;

/**
 * 闪屏页面
 *@author ChongZi007
 *@time 2017/3/30 21:00
 *@参数
 *@return
*/
public class SplashActivity extends Activity {
    private int versionCode;
    private String versionName;
    private static final int CODE_UPDATE_DIALOG = 0;//进入更新
    private static final int CODE_URL_ERROR = 1;//url错误
    private static final int CODE_NET_ERROR = 2;//网络异常
    private static final int CODE_JSON_ERROR = 3;//json数据解析失败
    private static final int CODE_ENTER_HOME = 4;// 进入主页面

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case CODE_UPDATE_DIALOG:
                    showUpdateDialog();
                    break;
                case CODE_URL_ERROR:
                    Toast.makeText(SplashActivity.this, "url错误", Toast.LENGTH_SHORT)
                            .show();
                    enterHome();
                    break;
                case CODE_NET_ERROR:
                    Toast.makeText(SplashActivity.this, "网络错误", Toast.LENGTH_SHORT)
                            .show();
                    enterHome();
                    break;
                case CODE_JSON_ERROR:
                    Toast.makeText(SplashActivity.this, "数据解析错误",
                            Toast.LENGTH_SHORT).show();
                    enterHome();
                    break;
                case CODE_ENTER_HOME:
                    enterHome();
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        TextView tv_version = (TextView) findViewById(R.id.tv_version);
        tv_version.setText(getVersionName());
        checkVersion();
    }

    /**
     * 获取当前版本名字
     *
     * @return
     */
    private String getVersionName() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 获取当前版本名字
     *
     * @return
     */
    private int getVersionCode() {
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 核对当前版本是否是最新
     * 为了不阻塞主线程 我们要在子线程中核对
     */
    private void checkVersion() {

        new Thread() {
            final long startTime = System.currentTimeMillis();

            @Override
            public void run() {
                super.run();
                Message message = Message.obtain();
                HttpURLConnection conn = null;
                try {
                    URL url = new URL("http://192.168.1.118:8080/CleverBug/versionServlet?method=a");
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    conn.connect();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200) {
                        //连接成功
                        InputStream in = conn.getInputStream();
                        String rs = IOStreamUtils.readFromInputStream(in);
                        //现在就要解析从服务器得到的json数据
                        JSONObject jo = new JSONObject(rs);

                        // versionCode = jo.getInt("versionCode");
                        versionCode = 2;
                        versionName = jo.getString("versionName");
                        if (versionCode > getVersionCode()) {
                            //说明有更新,弹出一个对话框
                            message.what = CODE_UPDATE_DIALOG;
                        } else {
                            //无更新
                            message.what = CODE_ENTER_HOME;
                        }
                    }
                } catch (MalformedURLException e) {
                    message.what = CODE_URL_ERROR;
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    message.what = CODE_NET_ERROR;
                } catch (JSONException e) {
                    message.what = CODE_JSON_ERROR;
                    e.printStackTrace();
                } catch (IOException e1) {
                    message.what = CODE_NET_ERROR;
                    e1.printStackTrace();
                } finally {
                    long endTime = System.currentTimeMillis();
                    long timeUsed = endTime - startTime;// 访问网络花费的时间
                    if (timeUsed < 2000) {
                        // 强制休眠一段时间,保证闪屏页展示2秒钟
                        try {
                            Thread.sleep(2000 - timeUsed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    mhandler.sendMessage(message);
                    if (conn != null) {
                        conn.disconnect();
                    }
                }

            }

        }.start();


    }

    private void enterHome() {
        Intent intent = new Intent(this, GuideActivity.class);
        startActivity(intent);
        finish();
    }

    private void showUpdateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("最新版本:" + versionName);
        builder.setMessage("最新的智慧虫子版本，更强大功能在等着你");
        builder.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(SplashActivity.this, "更新完成，马上体验吧", Toast.LENGTH_SHORT).show();
                enterHome();
            }
        });

        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                enterHome();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                enterHome();
            }
        });
        builder.show();
    }

}

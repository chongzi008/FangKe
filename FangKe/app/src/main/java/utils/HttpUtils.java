package utils;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ChongZi007 on 2017/6/2.
 */

public class HttpUtils {
    /**
     * 发送请求
     *
     * @param address
     * @param callback
     */
    public static void sendOkHttpRequest(String address, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    /**
     * 发起Post请求
     * @param address
     * @param dataKey
     * @param dataValue
     * @param callback
     */
    public static void sendOkHttpRequestForForm(String address,String dataKey,String dataValue, Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .add(dataKey,dataValue)
                .build();
        Request request = new Request.Builder().url(address)
                .post(formBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }



}

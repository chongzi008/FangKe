package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ChongZi007 on 2017/2/9.
 * 读取流信息
 */

public class StreamUtils {

    public static String readFromInputStream(InputStream in) {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }

            String result=bos.toString();
            in.close();
            bos.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}

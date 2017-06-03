package utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by ChongZi007 on 2017/2/9.
 * 读取流信息,
 * 读取某路径下的json文件
 */

public class IOStreamUtils {

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

    public static String getJson(String fileName,Context context) {
        //将json数据变成字符串
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //获取assets资源管理器
            AssetManager assetManager = context.getAssets();
            //通过管理器打开文件并读取
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


    // 高效字节流一次读写一个字节数组：
    public static void method4(String srcString, String destString)
            throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                srcString));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destString));

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = bis.read(bys)) != -1) {
            bos.write(bys, 0, len);
        }

        bos.close();
        bis.close();
    }

    // 高效字节流一次读写一个字节：
    public static void method3(String srcString, String destString)
            throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                srcString));
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(destString));

        int by = 0;
        while ((by = bis.read()) != -1) {
            bos.write(by);

        }

        bos.close();
        bis.close();
    }

    // 基本字节流一次读写一个字节数组
    public static void method2(String srcString, String destString)
            throws IOException {
        FileInputStream fis = new FileInputStream(srcString);
        FileOutputStream fos = new FileOutputStream(destString);

        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }

        fos.close();
        fis.close();
    }

    // 基本字节流一次读写一个字节
    public static void method1(String srcString, String destString)
            throws IOException {
        FileInputStream fis = new FileInputStream(srcString);
        FileOutputStream fos = new FileOutputStream(destString);

        int by = 0;
        while ((by = fis.read()) != -1) {
            fos.write(by);
        }

        fos.close();
        fis.close();
    }


    // 字符缓冲流一次读写一个字符串
    private static void method5(String srcString, String destString)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        String line = null;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
            bw.flush();
        }

        bw.close();
        br.close();
    }

    // 字符缓冲流一次读写一个字符数组
    private static void method6(String srcString, String destString)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        char[] chs = new char[1024];
        int len = 0;
        while ((len = br.read(chs)) != -1) {
            bw.write(chs, 0, len);
        }

        bw.close();
        br.close();
    }

    // 字符缓冲流一次读写一个字符
    private static void method7(String srcString, String destString)
            throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(srcString));
        BufferedWriter bw = new BufferedWriter(new FileWriter(destString));

        int ch = 0;
        while ((ch = br.read()) != -1) {
            bw.write(ch);
        }

        bw.close();
        br.close();
    }

    // 基本字符流一次读写一个字符数组
    private static void method8(String srcString, String destString)
            throws IOException {
        FileReader fr = new FileReader(srcString);
        FileWriter fw = new FileWriter(destString);

        char[] chs = new char[1024];
        int len = 0;
        while ((len = fr.read(chs)) != -1) {
            fw.write(chs, 0, len);
        }

        fw.close();
        fr.close();
    }

    // 基本字符流一次读写一个字符
    private static void method9(String srcString, String destString)
            throws IOException {
        FileReader fr = new FileReader(srcString);
        FileWriter fw = new FileWriter(destString);

        int ch = 0;
        while ((ch = fr.read()) != -1) {
            fw.write(ch);
        }

        fw.close();
        fr.close();
    }

}

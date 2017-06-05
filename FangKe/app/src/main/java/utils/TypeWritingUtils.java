package utils;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by ChongZi007 on 2017/6/4.
 * 输入法工具类
 */

public class TypeWritingUtils {

    public static void UpOrOffTypeWritting(Context context){
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}

package fangke.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by ChongZi007 on 2017/5/7.
 */

public class NotHorienzantalListView extends ListView {
    private int rawX;
    private int rawY;
    public NotHorienzantalListView(Context context) {
        super(context);
    }

    public NotHorienzantalListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NotHorienzantalListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /**
     * 重写该方法处理滑动冲突的问题
     *@author ChongZi007
     *@time 2017/3/28 10:28
     *@参数
     *@return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawX = (int) ev.getRawX();
                rawY = (int) ev.getRawY();
                return false;
            case MotionEvent.ACTION_MOVE:
                int endY = (int) ev.getRawY();
                int endX = (int) ev.getRawX();
                if (Math.abs(endX - rawX) > Math.abs(endY - rawY)) {
                    //左右滑动
                    return false;
                } else {
                    return false;
                }

            case MotionEvent.ACTION_UP:
                return false;
            default:
                return false;
        }
    }
}

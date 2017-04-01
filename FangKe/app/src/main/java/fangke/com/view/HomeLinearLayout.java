package fangke.com.view;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * Created by ChongZi007 on 2017/3/23.
 */

public class HomeLinearLayout extends LinearLayout {
    private int rawX;
    private int rawY;

    public HomeLinearLayout(Context context) {
        super(context);
    }


    public HomeLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

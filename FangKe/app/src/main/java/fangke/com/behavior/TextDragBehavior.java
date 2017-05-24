package fangke.com.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import fangke.com.activity.R;
import utils.DispalyUtil;

/**
 * Created by ChongZi007 on 2017/5/23.
 */

public class TextDragBehavior extends CoordinatorLayout.Behavior<TextView> {

    private Context mContext;
    //字的最终大小
    private float mCustomFinalsize;
    //最终头像的Y
    private float mFinalY;
    private float mFinalX;
    private float mStartAvatarY;
    private float mStartY;
    private float mStartAvatarX;
    private int mAvatarMaxHeight;
    private float mStartsize;

    public TextDragBehavior(Context context, AttributeSet attrs) {
        mContext = context;
            //获取缩小以后的大小
            mCustomFinalsize = DispalyUtil.sp2px(mContext, 14);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        //初始化属性
        if (mFinalX == 0) {
            mFinalX = parent.getWidth() - DispalyUtil.dip2px(mContext, 15) - child.getWidth();////最终x坐标
        }
        if (mFinalY == 0) {
            mFinalY = DispalyUtil.dip2px(mContext, 21);//最终y坐标
        }

        if (mStartAvatarY == 0) {
            mStartAvatarY = dependency.getY();//依赖view初始y坐标
        }
        if (mStartAvatarX == 0) {
            mStartAvatarX = child.getX();//初始x坐标
        }

        if (mAvatarMaxHeight == 0) {
            mAvatarMaxHeight = child.getHeight();//最大长度
        }
        if (mStartY == 0) {
            mStartY = child.getY();//初始y坐标
        }

        if (mStartsize == 0) {
            mStartsize = child.getTextSize();//初始字体大小
        }

        float percent = dependency.getY() / mStartAvatarY;//得到移动百分比

        //如果没有超过制定的最终y坐标我们就给他动起来
                  if (dependency.getY()-mStartAvatarY<0){
                //上滑
                if (mStartY * percent > mFinalY) {
                    child.setY(mStartY * percent);
                }else if(mStartY * percent <= mFinalY){
                    child.setY(mFinalY);
                }

                if (mStartAvatarX * percent < mFinalX) {
                    child.setX(mStartAvatarX * (1 - percent) + mStartAvatarX);
                }else if(mStartAvatarX * percent >= mFinalX){
                    child.setX(mFinalX);
                }

            }else{
                //下滑
                if (mStartY * percent > mFinalY) {
                    child.setY(mStartY * percent);
                }else if(mStartY * percent <= mFinalY){
                    child.setY(mFinalY);
                }

            if (mStartAvatarX * percent < mFinalX) {
                child.setX(mStartAvatarX * (1 - percent) + mStartAvatarX);
            }else if(mStartAvatarX * percent >= mFinalX){
                child.setX(mFinalX);
            }
        }






        return true;
    }


}

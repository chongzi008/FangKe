package fangke.com.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;
import fangke.com.activity.R;
import utils.DispalyUtil;


//泛型为child类型
public class CustomBehavior extends CoordinatorLayout.Behavior<CircleImageView> {
    private Context mContext;
    //头像的最终大小
    private float mCustomFinalHeight;
    //最终头像的Y
    private float mFinalAvatarY;
    private float mFinalAvatarX;
    private float mStartAvatarY;
    private float mStartY;
    private float mStartAvatarX;
    private int mAvatarMaxHeight;


    public CustomBehavior(Context context, AttributeSet attrs) {
        mContext = context;
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomBehavior);
            //获取缩小以后的大小
            mCustomFinalHeight = a.getDimension(R.styleable.CustomBehavior_finalHeight, 0);
            a.recycle();
        }
        mFinalAvatarY = DispalyUtil.dip2px(mContext, 5);//照片最终y坐标
        mFinalAvatarX = DispalyUtil.dip2px(mContext, 5);//照片最终y坐标
    }


    // 如果dependency为Toolbar
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        return dependency instanceof NestedScrollView;
    }


    //当dependency变化的时候调用
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        //初始化属性

        if (mStartAvatarY == 0) {
            mStartAvatarY = dependency.getY();//依赖view初始y坐标
        }
        if (mStartAvatarX == 0) {
            mStartAvatarX = child.getX();//图片初始x坐标
        }

        if (mAvatarMaxHeight == 0) {
            mAvatarMaxHeight = child.getHeight();//图片最大长度
        }
        if (mStartY == 0) {
            mStartY = child.getY();//图片初始y坐标
        }


        float percent = dependency.getY() / mStartAvatarY;//得到移动百分比
        //如果没有超过制定的最终y坐标我们就给他动起来
        //如果没有超过制定的最终y坐标我们就给他动起来
        if (dependency.getY() - mStartAvatarY < 0) {
            //上滑
            if (mStartY * percent > mFinalAvatarY) {
                child.setY(mStartY * percent);
            } else if (mStartY * percent <= mFinalAvatarY) {
                child.setY(mFinalAvatarY);
            }

            if (mStartAvatarX * percent > mFinalAvatarX) {
                child.setX(mStartAvatarX * percent);
            } else if (mStartAvatarX * percent <= mFinalAvatarX) {
                child.setX(mFinalAvatarX);
            }

        } else {
            //下滑
            if (mStartY * percent > mFinalAvatarY) {
                child.setY(mStartY * percent);
            } else if (mStartY * percent <= mFinalAvatarY) {
                child.setY(mFinalAvatarY);
            }

            if (mStartAvatarX * percent > mFinalAvatarX) {
                child.setX(mStartAvatarX * percent);
            } else if (mStartAvatarX * percent <= mFinalAvatarX) {
                child.setX(mFinalAvatarX);
            }
        }


        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        layoutParams.height = (int) ((mAvatarMaxHeight - mCustomFinalHeight) * percent + mCustomFinalHeight);
        layoutParams.width = (int) ((mAvatarMaxHeight - mCustomFinalHeight) * percent + mCustomFinalHeight);
        child.setLayoutParams(layoutParams);

        return true;
    }



}

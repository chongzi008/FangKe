package fangke.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by ChongZi007 on 2017/6/3.
 */

public class NoRepeatButton extends Button {

    public NoRepeatButton(Context context) {
        super(context);
    }

    public NoRepeatButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoRepeatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean equals(Object o) {
        boolean tag = false;
        NoRepeatButton btn = (NoRepeatButton) o;
        if (this.getId() == (btn.getId())) {
            tag = true;
        }
        if (o == null || getClass() != o.getClass()) {
            tag = false;
        }
        if(this==o){
            tag=true;
        }
        return tag;
    }

    @Override
    public int hashCode() {
        int result = this.getId();
        result = 31 * result ;
        return result;
    }
}

package fangke.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fangke.com.activity.HomeActivity;
import fangke.com.activity.R;

/**
 * 我的
 *@author ChongZi007
 *@time 2017/3/31 10:08
 *@参数
 *@return
*/

public class MineFragment extends Fragment {

    private View view;
    private HomeActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mActivity = (HomeActivity) getActivity();
        initViews(inflater);
        return view;


    }

    private void initViews(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_mine, null);


    }
}

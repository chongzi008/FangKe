package fangke.com.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import fangke.com.activity.R;

/**
 * 微聊
 *
 * @author ChongZi007
 * @time 2017/3/31 10:08
 * @参数
 * @return
 */
public class WeChatFragment extends Fragment {

    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wechat, null);
        initViews();
        return view;
    }

    private void initViews() {
    }

}

package com.wdx.center.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.alibaba.android.arouter.launcher.ARouter;
import com.wdx.center.R;
import com.wdx.kotlin.TestActivity;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/1 18:03
 */
public class ThirdFragment extends Fragment {

    public ThirdFragment() {
    }

    public ThirdFragment(int contentLayoutId) {
        super(contentLayoutId);
    }
    View layout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        layout = inflater.inflate(R.layout.fragment_third,null);
        return layout;
    }
    TextView tv_back;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv_back =layout.findViewById(R.id.tv_back);
        tv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(layout).navigate(R.id.fragment_first);
                Intent intent=new Intent(getActivity(),TestActivity.class);
                getActivity().startActivity(intent);

            }
        });
    }
}

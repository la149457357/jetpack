package com.wdx.center.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.wdx.center.R;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/1 16:46
 */
public class NavFragment extends Fragment {

    public NavFragment() {
    }

    public NavFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {


        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main,container);
    }
}

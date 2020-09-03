package com.wdx.center.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.wdx.center.R;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/1 18:03
 */
public class SecondFragment extends Fragment {

    public SecondFragment() {
    }

    public SecondFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    View layout;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        layout = inflater.inflate(R.layout.fragment_second,null);

        return layout;
    }

    TextView tv_second;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_second=layout.findViewById(R.id.tv_second);
        tv_second.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(layout).navigate(R.id.fragment_third);
            }
        });
    }
}

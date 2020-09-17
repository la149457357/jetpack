package com.wdx.tv;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Loads {@link PlaybackVideoFragment}.
 */

public class PlaybackActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(android.R.id.content, new PlaybackVideoFragment())
                    .commit();
        }
    }
}

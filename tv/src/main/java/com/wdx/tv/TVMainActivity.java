package com.wdx.tv;

import android.app.Activity;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;

/*
 * Main Activity class that loads {@link MainFragment}.
 */
@Route(path = "/tv/playbackactivity")
public class TVMainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

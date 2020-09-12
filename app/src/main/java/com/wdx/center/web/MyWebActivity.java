package com.wdx.center.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.wdx.center.R;

/**
 * @ Description:
 * @ Author: wdx
 * @ CreateDate: 2020/9/12 11:34
 */
public class MyWebActivity extends Activity {

    WebView webView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        webView = findViewById(R.id.web_view);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);

        String url=getIntent().getStringExtra("url");
        if(TextUtils.isEmpty(url)){
            url = "https://m.baidu.com/";
        }
        webView.loadUrl(url);

/*        webView.goBack();//后退
        webView.goForward();//前进
        webView.reload();//刷新*/
    }

    public void OnFinishClick(View view){
        finish();
    }
    public void OnForwardClick(View view){
        webView.goForward();//前进
    }
    public void OnBackClick(View view){
        webView.goBack();
    }
}

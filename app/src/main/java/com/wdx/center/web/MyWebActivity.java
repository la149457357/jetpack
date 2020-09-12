package com.wdx.center.web;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.wdx.center.R;
import java.io.UnsupportedEncodingException;

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
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("wdx","url = " + url);
                 if(url.contains("baiduboxapp://v1/browser/open?")||url.contains("baiduboxlite")){
                    url= Uri.decode(url);
                     Log.e("wdx","url00000 = " + url);
                    url= url.substring(url.indexOf("url=")+4);
                     Log.e("wdx","url11111 = " + url);
                 }else {
                     webView.loadUrl(url);
                 }
                Log.e("wdx","urlresult = " + url);

                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                return true;
            }
        });
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        settings.setSupportZoom(true); // 支持缩放
        settings.setBuiltInZoomControls(true);//设置支持两指缩放手势
        settings.setDisplayZoomControls(false);//隐藏缩放按钮

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

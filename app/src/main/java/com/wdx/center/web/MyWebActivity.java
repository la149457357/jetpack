package com.wdx.center.web;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.wdx.center.BuildConfig;
import com.wdx.center.R;
import com.wdx.common.utils.LogUtils;
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
                 }else if(url.contains("baiduhaokan")){
                     webView.loadUrl("https://haokan.baidu.com");
                 }else{
                     webView.loadUrl(url);
                 }
                Log.e("wdx","urlresult = " + url);

                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                return true;
            }
        });

        String url=getIntent().getStringExtra("url");
        if(TextUtils.isEmpty(url)){
            url = "https://m.baidu.com/";
        }
        webView.loadUrl(url);
        //传递对象给JavaScript
        webView.addJavascriptInterface(MyWebActivity.this, "activity");

        init(this);

/*        webView.goBack();//后退
        webView.goForward();//前进
        webView.reload();//刷新*/
    }
    private void init(Context context) {
        WebSettings setting = webView.getSettings();
        setting.setGeolocationEnabled(true);
        setting.setJavaScriptEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setAllowFileAccess(true);
        //设置自适应屏幕，两者合用（下面这两个方法合用）
        setting.setUseWideViewPort(true); //将图片调整到适合webview的大小
        setting.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        setting.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        setting.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        setting.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        setting.setCacheMode(WebSettings.LOAD_DEFAULT); //（默认）根据cache-control决定是否从网络上取数据。
        setting.setAllowFileAccess(true); //设置可以访问文件
        setting.setJavaScriptCanOpenWindowsAutomatically(false); //不支持通过JS打开新窗口
        setting.setLoadsImagesAutomatically(true); //支持自动加载图片
        setting.setDefaultTextEncodingName("utf-8");//设置编码格式

        setting.setAppCacheMaxSize(1024 * 1024 * 10);
        setting.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        setting.setDatabaseEnabled(true);   //开启 database storage API 功能
        setting.setAppCacheEnabled(true);//开启 Application Caches 功能
        setting.setTextSize(WebSettings.TextSize.NORMAL);
        String appCachePath = context.getCacheDir().getAbsolutePath();
        setting.setAppCachePath(appCachePath);//设置  Application Caches 缓存目录
        setting.setSavePassword(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //android5.0以后webview默认不在保存cookie所以会导致第三方登录状态无法保存
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setting.setAllowUniversalAccessFromFileURLs(true);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // setting.setSupportMultipleWindows(true);
        webView.setOnLongClickListener(new OnLongClickListener() {

            public boolean onLongClick(View v) {
                WebView.HitTestResult result = webView.getHitTestResult();

                if (null == result) {
                    return false;
                }
                int type = result.getType();
                String extra = result.getExtra();
                LogUtils.e("TAG", "extra--" + extra);
                switch (type) {
                    case WebView.HitTestResult.EDIT_TEXT_TYPE: // 选中的文字类型
                        break;
                    case WebView.HitTestResult.PHONE_TYPE:     // 处理拨号
                        break;
                    case WebView.HitTestResult.EMAIL_TYPE:     // 处理Email
                        break;
                    case WebView.HitTestResult.GEO_TYPE:       // 地图类型
                        break;
                    case WebView.HitTestResult.SRC_ANCHOR_TYPE:// 超链接

                    case WebView.HitTestResult.UNKNOWN_TYPE:   //未知
                        break;

                }
                return false;

            }

        });

    }
    /**
     * 暴露给JavaScript的方法
     *
     * @param msg
     */
    @JavascriptInterface
    public void showToast(String msg) {
        Toast.makeText(MyWebActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @android.webkit.JavascriptInterface
    public void actionFromJsWithParam(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyWebActivity.this, "js调用了Native函数传递参数：" + str, Toast.LENGTH_SHORT).show();

            }
        });

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

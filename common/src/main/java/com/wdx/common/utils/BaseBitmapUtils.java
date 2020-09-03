package com.wdx.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by admin on 2018/3/29.
 */

public class BaseBitmapUtils {
    public static void loadBitmap(Context context,String url,ImageView imageView){
        if(url!=null && !TextUtils.isEmpty(url)){
            if(url.contains("http")){
                Picasso.with(context).load(url).resize(200,200).into(imageView);
            }else {
                Picasso.with(context).load("file://"+url).resize(200,200).into(imageView);
            }
        }
    }
    public static void loadBitmap(Context context,String url,ImageView imageView,int defaultImg){
        if(url!=null && !TextUtils.isEmpty(url)) {
            if (url.contains("http")) {
                Picasso.with(context).load(url).placeholder(defaultImg).resize(200, 200).into(imageView);
            } else {
                Picasso.with(context).load("file://" + url).placeholder(defaultImg).resize(200, 200).into(imageView);
            }
        }else {
            if(defaultImg!=0){
                Picasso.with(context).load(defaultImg).placeholder(defaultImg).resize(200, 200).into(imageView);
            }
        }
    }
}

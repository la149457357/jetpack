package com.wdx.common.utils;


import com.wdx.common.http.BaseInfo;

/**
 * Created by admin on 2018/3/23.
 */

public class BitmapUploadInfo extends BaseInfo {
    public String img_url;
    public boolean is_take_picture;

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public boolean is_take_picture() {
        return is_take_picture;
    }

    public void setIs_take_picture(boolean is_take_picture) {
        this.is_take_picture = is_take_picture;
    }
}

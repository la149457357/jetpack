package com.wdx.common.http;

import android.util.Log;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/4/4.
 */

public class MyRequestParams {
    public String req_type;    //10为PC端，20为App端
    public String token;//登录接口不需要
    public String method;//	请求方式
    public Bodyparams bodyparams;    //Bodyparams对象下方具体字段解释
    public Map<String, Object> data;        //各个接口业务参数
    public MyRequestParams() {
        req_type = "20";
        token= ".token";
        method="post";
        bodyparams =new Bodyparams();
        getParamsInstance();
    }

    public void getParamsInstance() {
        if (data == null) {
            data = new HashMap<>();
        }
    }

    public void addParams(String name, Object value) {
        this.getParamsInstance();
        data.put(name, value);
    }

    public void setMethodToGet() {
        Log.e("wdx","get");
        method="get";
    }
    public void setMethodToPost() {
        Log.e("wdx","post");
        method="post";
    }
    public void setMethodToPut() {
        Log.e("wdx","put");
        method="put";
    }
    public void setMethodToDelete() {
        Log.e("wdx","delete");
        method="delete";
    }
    public class Bodyparams {
        public String regionid;//	地区码
        public String sid;//	唯一识别码
        public String platform;//	手机系统
        public String deviceid;//	设备号
        public String softver;//	app版本号
        public String sysver;//	系统版本
        public String brand;//	手机品牌
        public String network;//	网络类型 wifi  4g
        public long requesttime;//	请求时间
        public String apkchannel;//	用户下载渠道
        public String serialnumber;//	手机序列号
        public String phonemodel;//	手机型号
        public String imei;//	手机IMEI+IMSI
        public String apiver;//	请求版本
        public Bodyparams(){
            regionid= HttpConfig.regionid;//	地区码
            sid= HttpConfig.sid;//	唯一识别码
            platform= HttpConfig.platform;//	手机系统
            deviceid= HttpConfig.deviceid;//	设备号
            softver= HttpConfig.softver;//	app版本号
            sysver= HttpConfig.sysver;//	系统版本
            brand= HttpConfig.brand;//	手机品牌
            network= HttpConfig.network;//	网络类型 wifi  4g
            requesttime=System.currentTimeMillis()/1000;//	请求时间
            apkchannel= HttpConfig.apkchannel;//	用户下载渠道
            serialnumber= HttpConfig.serialnumber;//	手机序列号
            phonemodel= HttpConfig.phonemodel;//	手机型号
            imei= HttpConfig.imei;//	手机IMEI+IMSI
            apiver= HttpConfig.apiver;//	请求版本
        }
    }

}

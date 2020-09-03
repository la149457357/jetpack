package com.wdx.common.base;

/**
 * Created by admin on 2017/12/20.
 */

public class BaseConfig {
    public final static boolean DEBUG = true; // 是否打印测试数据
    public final static String IP = "http://10.18.15.114:8060";
    public final static String PORT = "8060";
//    public final static String RequestUrl = "http://" + BaseConfig.IP + ":" + BaseConfig.PORT;

    public final static String RequestUrl = "http://10.18.15.114:8060";
  // public final static String RequestUrl = "http://10.18.15.131:8080";
    //接口请求路径
    public final static String BillPath = "/billpath";//服务端请求路径
    public final static String BusinessPath = "/businesspath";//服务端请求路径
    public final static String BaseType = "/uk-bsc/v1/";
    public final static String BaseFeeAccountType = "/uk-fybx/v1/";
    public final static String LOGIN = BaseType + "login/";//用户登陆
    public final static String SENDMSG = BaseType + "sendmsg/";//根据手机号获取短信验证码---
    public final static String LOGOUT = BaseType + "logout/";//用户注销
    public final static String CONFIGRES = BaseType + "configers/";//--描述:点击费用报销时，获取费用报销单据模板字段信息（）及当前服务器时间基础平台参数配置
    public final static String SYSCOSTPROJS = BaseType + "syscostprojs/";//描述：绑定基础平台-基础档案-费用类档案-费用项目字段。添加费用项目，可以选择费用项目档案(内置+自维护)
    public final static String SYSDEPTS = BaseType + "sysdepts/";//分摊部门列表---
    public final static String USERS = BaseType + "users/";//分摊人员列表---
    public final static String VALID = BaseType + "valid/";//费用项目预算校验
    public final static String SYSDICTIONARYS = BaseType + "sysdictionarys/";//支付方式

    public final static String SYSSUPPLIERINFOS = BaseType+"syssupplierinfos/";//系统供应商列表

    public final static String SYSBANKACCOUNTS = BaseType+"sysbankaccounts/";//个人账户信息
    public final static String SYSSUPPACCOUNT = BaseType+"syssuppaccount/";//显示供应商关联银行账户列表
    public final static String BILLMAINDATAS = BaseFeeAccountType+"billmaindatas/";//--费用报销 获取 提交 及确认单据总接口
    public final static String MODULES = BaseType+"modules/";//modules 首页模块搜索
    public final static String INVOICETITLES = BaseType+"invoicetitles/";//发票抬头
    public final static String SHOWINDEXS = BaseType+"showindexs/";//公告---

    public final static String BILLNUMS = BaseType+"billnums/";//首页单据数量
    public final static String BILLS = BaseType+"bills/";//单据列表

    public final static String USERCOMMINFO = BaseType+"usercomminfo/";//常用人员列表
    public final static String SUPPCOMMINFO = BaseType+"suppcomminfo/";//常用供应商
    public final static String CURRENCYARCHIVES = BaseType+"currencyarchives/";//请求币种
}

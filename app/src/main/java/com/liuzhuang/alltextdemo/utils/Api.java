package com.liuzhuang.alltextdemo.utils;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liuzhuang on 2017/5/1.
 */

public class Api {
    /**
     * api访问方法（post/get）
     */

    public enum  API_METHOD{
        POST,GET
    }
    public  static  String HOST = "http://test.daqianjietong.com/dianapi.php";
    public  static  String AGREEMENT = "/Register/agreement";
    private static final String LOGIN="/Login/login";
    private static final String GETCODE="/Register/getCode";
    private static final String USERREGISTER="/Register/userRegister";
    private static final String FORGETPASS="/User/forgetPass";
    private static final String GETPICTURES="/User/getPictures";
    private static final String INDEX="/User/index";
    private static final String UPUSERNAME="/User/upUsername";
    private static final String ADDCAR="/User/addCar";
    private static final String SETUSERPHOTO="/User/setUserPhoto";
    private static final String LOGOUT="/Login/logout";
    private static final String UPPASSWORD="/User/upPassword";
    private static final String PARKINGINDEX="/Parking/index";
    private static final String PARKLIST="/Parking/parkList";
    private static final String PARKINGCOUPON="/User/parkingCoupon";
    private static final String RECEIVECOUPON="/User/receiveCoupon";
    private static final String MYCOUPON="/User/myCoupon";
    private static final String RESERVEPARK="/Parking/reservePark";
    private static final String PAYLIST="/Parking/payList";
    private static final String UNSETRESERVE="/Parking/unsetReserve";
    private static final String ORDERLIST="/User/orderList";

    /**
     * 是否debug调试（切换测试环境和生产环境）
     */
    private static final boolean Debug= false;


    private static  Api api;

    static {
        if(Debug){
            HOST = "";
        }
    }

    public static Api getInstance(){
        if(api == null)
            api = new Api();

        return api;
    }

    /**
     * 示例  登陆接口调用；
     * @param username
     * @param password
     * @param listenter
     */
  public  void login(String username, String password, HttpUtil.URLListenter<String> listenter){
      Map<String,String> params = new HashMap<>();
      params.put("txt_phone",username);
      params.put("txt_password",password);
      HttpUtil httpUtil=new HttpUtil();
      httpUtil.doRequest(API_METHOD.GET,HOST+LOGIN,params,listenter,new TypeToken<String>(){}.getType(),false);
  }


    /**
     * 示例1  测试百度接口调用；（默认返回String类型的数据）
     * @param listenter  回调监听传入需要解析数据类型
     */
    public  void test( HttpUtil.URLListenter<String> listenter){
        /**
         * setMethod()设置post get方法
         * setParams(Map params)设置请求参数
         * setUrl(string url)设置请求url地址
         * setClassType(Class<\?> clazz)设置解析类实体类型 传如数据如 UserInfoBean.class;
         * setTypeToken(new TypeToken<T></>(){}.gettype())
         * 传入解析的数据类型，在未调用setClassType方法时起作用（针对列表数据类型解析）
         * 用法：将方法中T替换成想要解析的类或者List<T></>传入需要解析类
         * seturlLisenter()设置回掉监听.
         *
         */

        HttpUtil httpUtil = new HttpUtil<String>();
        httpUtil.setUrl(HOST).setMethod(API_METHOD.GET).seturllisenter(listenter).start();
    }
    /**
     * 示例2  测试百度接口调用；（设置返回解析后的list数据）
     * @param listenter  回调监听传入需要解析数据类型
     */
    public  void testList(HttpUtil.URLListenter<ArrayList<String>>listenter){
        /**
         * setMethod()设置post get方法
         * setParams(Map params)设置请求参数
         * setUrl(string url)设置请求url地址
         * setClassType(Class<\?> clazz)设置解析类实体类型 传如数据如 UserInfoBean.class;
         * setTypeToken(new TypeToken<T></>(){}.gettype())
         * 传入解析的数据类型，在未调用setClassType方法时起作用（针对列表数据类型解析）
         * 用法：将方法中T替换成想要解析的类或者List<T></>传入需要解析类
         * seturlLisenter()设置回掉监听.
         * 获取list数据时示例如下：
         */
        HttpUtil httpUtil = new HttpUtil<String>();
        httpUtil.setUrl(HOST).setMethod(API_METHOD.GET).setTypetoken(new TypeToken<ArrayList<String>>(){}.getType()).seturllisenter(listenter).start();
    }

}

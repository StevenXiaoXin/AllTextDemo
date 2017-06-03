package com.liuzhuang.alltextdemo.utils;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/9.
 */

public class BaseHttpEntity implements Serializable{
    public int code;
    public String message;
    public String data;
}

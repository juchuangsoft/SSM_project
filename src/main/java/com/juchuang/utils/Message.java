package com.juchuang.utils;

import java.util.HashMap;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Project : SSM_project
 * @Description :  [返回工具类]
 * @Package Name :com.juchuang.utils
 * @Author : 1643091610@qq.com
 * @Date :2019 年 02月 24 日 21:24
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
public class Message {

    //状态码 100-成功 200-失败
    private int code;
    //提示信息
    private String message;

    //用户要返回浏览器的数据
    private HashMap<String,Object> extend= new HashMap<String, Object>();

    public static Message success(){
        Message message=new Message();
        message.setCode(100);
        message.setMessage("处理成功！");
        return message;
    }

    public static Message fail(){
        Message message=new Message();
        message.setCode(200);
        message.setMessage("处理失败！");
        return message;
    }

    public Message add(String key,Object value){
        this.getExtend().put(key,value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(HashMap<String, Object> extend) {
        this.extend = extend;
    }
}

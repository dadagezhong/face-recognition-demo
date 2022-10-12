package com.dazhong.utils;


public class JsonData {

    private boolean ret;
    private String msg;
    private Object data;
    public JsonData(boolean ret) {
        this.ret = ret;
    }
    public static JsonData success(Object object, String msg) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        jsonData.msg = msg;
        return jsonData;
    }
    public static JsonData success(Object object) {
        JsonData jsonData = new JsonData(true);
        jsonData.data = object;
        return  jsonData;
    }
}

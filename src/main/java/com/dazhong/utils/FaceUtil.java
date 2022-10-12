package com.dazhong.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author PDX
 * @website https://blog.csdn.net/Gaowumao
 * @Date 2022-05-07 22:44
 * @Description face++
 */
public class FaceUtil {

        private static HashMap<String, String> map = new HashMap<>();
        static {
            Properties prop = new Properties();
            InputStream resourceAsStream = FaceUtil.class.getClassLoader().getResourceAsStream("application.properties");
            try {
                prop.load(resourceAsStream);
                map.put("api_key", prop.getProperty("face.api_key"));
                map.put("api_secret", prop.getProperty("face.api_secret"));
                map.put("display_name", prop.getProperty("face.display_name"));
                map.put("outer_id",prop.getProperty("face.outer_id"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("读取配置文件错误！");
            }
        }



        public static String create() throws Exception {
            String url = "https://api-cn.faceplusplus.com/facepp/v3/faceset/create";
            byte[] bacd = HttpUtil.post(url, map, null);
            //符号json格式的字符串
            String str = new String(bacd);
            System.out.println(str);
            if (str.indexOf("error_message") != -1){
                return null;
            }
            //转换json对象
            JSONObject object = JSONObject.parseObject(str);
            String outerId = object.getString("outer_id");
            return outerId;
        }


    /**
     * 人脸搜索
     * @param faceToken
     * @return
     * @throws Exception
     */
    public static boolean search(String faceToken) throws Exception {
        String url = "https://api-cn.faceplusplus.com/facepp/v3/search";
        map.put("face_token",faceToken);
        byte[] bacd = HttpUtil.post(url, map, null);
        //符号json格式的字符串
        String str = new String(bacd);
        System.out.println(str);
        if (str.indexOf("error_message") != -1){
            return false;
        }
        //转换为json对象
        JSONObject jsonObject =JSONObject.parseObject(str);
        JSONObject array = (JSONObject) jsonObject.get("thresholds");
        double le5 = array.getDoubleValue("1e-5");//十万分之一的阈值
        JSONArray resArr = (JSONArray) jsonObject.get("results");
        if (resArr != null && resArr.size() >=1){
            JSONObject res = (JSONObject) resArr.get(0);//取出 数组中的第一个对象
            double confidence = res.getDoubleValue("confidence");
            if (confidence > le5) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据传入的图片进行人脸检测
     * @param file 传入的人脸图片
     * @return 返回人脸的faceToken 如果为空，图片有问题
     */
    public static String detect(File file) throws Exception {
        byte[] buff = HttpUtil.getBytesFromFile(file);
        String url = "https://api-cn.faceplusplus.com/facepp/v3/detect";
        HashMap<String, byte[]> byteMap = new HashMap<>();

        byteMap.put("image_file", buff);
        byte[] bacd = HttpUtil.post(url, map, byteMap);
        //符号json格式的字符串
        String str = new String(bacd);
        System.out.println(str);
        if (str.indexOf("error_message") != -1){
            System.out.println("请求发生了错误！");
            return null;
        }
        //转换为json对象
        JSONObject jsonObject = JSONObject.parseObject(str);
        int num = jsonObject.getIntValue("face_num");
        if (num == 1){
            JSONArray array = (JSONArray) jsonObject.get("faces");
            JSONObject face = (JSONObject) array.get(0);
            String faceToken = face.getString("face_token");
            return faceToken;
        }
        return null;
    }
}

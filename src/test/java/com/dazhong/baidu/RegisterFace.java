package com.dazhong.baidu;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.util.Base64Util;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


/**
 * @author PDX
 * @website https://blog.csdn.net/Gaowumao
 * @Date 2022-05-08 14:43
 * @Description
 */
public class RegisterFace {

    //人脸注册
    @Test
    public void testFaceRegister() throws IOException {
        //1. 创建Java代码和百度云交互的Client对象
        AipFace client = new AipFace("27853257","zpAynL7q2BpsxZMtfIOVcHEO","3RPhZt1RkZenlit25hLu1NliutH4WhSf");
        //2. 参数设置
        HashMap<String,String> map = new HashMap<>();
        map.put("quality_control","NORMAL");//图片质量
        map.put("liveness_control","LOW");//活体检测
        //3. 构造图片
        String path = "C:\\Users\\Stephen·jj\\Pictures\\测试图片\\20221012101839.jpg";
        //上传的图片  两种格式：url地址 Base64字符串形式
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);
        //4.调用api方法完成人脸注册
        /**
         * 参数1：图片的url或者base64字符串
         * 参数2：图片形式（URL，BASE64）
         * 参数3：组Id（固定一个字符串）
         * 参数4：用户Id
         * 参数5：hashMap基本参数配置
         */
        JSONObject res = client.addUser(encode, "BASE64", "dazhong", "1001", map);
        System.out.println(res.toString());
    }

    /**
     * 人脸检测 判断图片中是否有面部信息
     */
    @Test
    public void testFaceCheck() throws IOException {
        //1. 创建Java代码和百度云交互的Client对象
        AipFace client = new AipFace("27853257","zpAynL7q2BpsxZMtfIOVcHEO","3RPhZt1RkZenlit25hLu1NliutH4WhSf");
        //2. 构造图片
        String path = "C:\\Users\\Stephen·jj\\Pictures\\测试图片\\20221012101839.jpg";
        //上传的图片  两种格式：url地址 Base64字符串形式
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);

        //调用Api方法进行人脸检测
        /**
         * 参数1：图片的url或者base64字符串
         * 参数2：图片形式（URL，BASE64）
         * 参数3：hashMap中的基本参数配置（null:使用默认配置）
         */
        JSONObject res = client.detect(encode, "BASE64", null);
        try {
            System.out.println(res.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 人脸搜索：根据用户上传的图片和指定人脸库中的所有人脸进行比较
     *          获取相似度最高的一个或者某几个的评分
     *
     * 说明：返回值（数据，只需要第一条，相似度最高的数据）
     *      score：相似度评分（80分以上可以认为是同一个人）
     */
    @Test
    public void testFaceSearch() throws IOException {
        //1. 创建Java代码和百度云交互的Client对象
        AipFace client = new AipFace("27853257","zpAynL7q2BpsxZMtfIOVcHEO","3RPhZt1RkZenlit25hLu1NliutH4WhSf");
        //2. 构造图片
        String path = "C:\\Users\\Stephen·jj\\Pictures\\测试图片\\20221012101839.jpg";
        //上传的图片  两种格式：url地址 Base64字符串形式
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);
        //人脸搜索
        JSONObject res = client.search(encode, "BASE64", "dazhong", null);
        try {
            System.out.println(res.toString(2));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 人脸更新：更新人脸库中的照片
     */
    @Test
    public void testFaceUpdate() throws IOException {
        //1. 创建Java代码和百度云交互的Client对象
        AipFace client = new AipFace("27853257","zpAynL7q2BpsxZMtfIOVcHEO","3RPhZt1RkZenlit25hLu1NliutH4WhSf");
        //2. 参数设置
        HashMap<String,String> map = new HashMap<>();
        map.put("quality_control","NORMAL");//图片质量
        map.put("liveness_control","LOW");//活体检测
        //3. 构造图片
        String path = "本地图片路径地址";
        //上传的图片  两种格式：url地址 Base64字符串形式
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        String encode = Base64Util.encode(bytes);
        //4.调用api方法完成人脸注册
        /**
         * 参数1：图片的url或者base64字符串
         * 参数2：图片形式（URL，BASE64）
         * 参数3：组Id（固定一个字符串）
         * 参数4：用户Id
         * 参数5：hashMap基本参数配置
         */
        JSONObject res = client.updateUser(encode, "BASE64", "dazhong", "1000", map);
        System.out.println(res.toString());
    }

}

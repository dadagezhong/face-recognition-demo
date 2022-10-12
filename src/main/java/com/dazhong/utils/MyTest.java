package com.dazhong.utils;

import java.io.File;

/**
 * @author PDX
 * @website https://blog.csdn.net/Gaowumao
 * @Date 2022-05-07 22:25
 * @Description face++
 */
public class MyTest {
    public static void main(String[] args) {
        File file = new File("本地图片地址");
        try {
            String outerId = FaceUtil.create();
            System.out.println("outerId ==>"+outerId);
            String faceToken = FaceUtil.detect(file);
            System.out.println("faceToken是："+faceToken);
            boolean search = FaceUtil.search(faceToken);
            System.out.println(search);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

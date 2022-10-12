package com.dazhong.service;

import com.dazhong.utils.BaiduAiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class FaceLoginService {
    @Autowired
    private BaiduAiUtils baiduAiUtils;

    //扫描人脸登录
    public String loginByFace(StringBuffer imagebast64) throws Exception{
        String image = imagebast64.substring(imagebast64.indexOf(",") + 1, imagebast64.length());
        String userId = baiduAiUtils.faceSearch(image);
        return userId;
    }
    //扫描人脸注册
    public String registerFace(String userId, StringBuffer imagebast64, HttpServletRequest request){
        String image = imagebast64.substring(imagebast64.indexOf(",") + 1, imagebast64.length());
        Boolean isSuccess = baiduAiUtils.faceRegister(userId, image);
        if (isSuccess){
            request.getSession().setAttribute("RuserId","dazhong");
            request.getSession().setAttribute("Rusername","dazhong的朋友");
            return "ok";
        }else {
            request.getSession().setAttribute("RuserId","未知");
            request.getSession().setAttribute("Rusername","你不是dazhong的朋友");
            return "no";
        }
    }
}

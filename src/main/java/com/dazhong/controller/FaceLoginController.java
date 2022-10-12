package com.dazhong.controller;

import com.alibaba.fastjson.JSON;
import com.dazhong.service.FaceLoginService;
import com.dazhong.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/face")
public class FaceLoginController {

    @Autowired
    private FaceLoginService faceLoginService;

    /**
     * 首页
     * @return
     */
    @RequestMapping("/")
    public String toLogin(){
        return "index";
    }

    /**
     * 人脸登录
     * @return
     */
    @RequestMapping("/toFace")
    public String toFace(){
        return "getface.html";
    }

    /**
     * 人脸注册
     */
    @RequestMapping("/toRegisterFace")
    public String toRegisterFace(){
        return "register-face.html";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

    @RequestMapping("/resuccess")
    public String Rsuccess(){
        return "R-success";
    }

    /**
     * 人脸登录
     * @return
     * @throws Exception
     */
    @RequestMapping("/face-login")
    @ResponseBody
    public  String searchface(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, HttpServletRequest request) throws Exception {
        String userId = faceLoginService.loginByFace(imagebast64);
        request.getSession().setAttribute("userId",userId);
        request.getSession().setAttribute("username","dadagezhong");
        return userId;
    }

    /**
     * 人脸登录注册
     * @return
     * @throws Exception
     */
    @RequestMapping("/face-register")
    @ResponseBody
    public String registerFace(@RequestBody @RequestParam(name = "imagebast64") StringBuffer imagebast64, HttpServletRequest request) throws Exception {
        String str = faceLoginService.registerFace("dazhong",imagebast64,request);
        return JSON.toJSONString(JsonData.success(str));
    }
}

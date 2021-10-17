package com.xiexin.controller;

import com.xiexin.respcode.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userinfo")
public class UserinfoController {
    @RequestMapping("/loginByShiro") //  //admin/loginByShiro
    public Result loginByShiro(@RequestBody Map map) {
        System.out.println("username" + map.get("username"));
        //登录交给shiro的securitManager管理
        Subject subject = SecurityUtils.getSubject();//subject是根据过滤器拿到的
        UsernamePasswordToken token = new UsernamePasswordToken((String)map.get("username"),(String) map.get("password"));

        try {
            if ((Boolean)map.get("rememberMe")){
                System.out.println(" 执行 记住我   " );
                token.setRememberMe(true);
            }

            subject.login(token);//ok

            return new Result();
        } catch (UnknownAccountException e) { //账号错误
            e.printStackTrace();
            return new Result(40001,"账号不对");
        } catch (IncorrectCredentialsException e) { //密码错误
            e.printStackTrace();
            return new Result(40002,"密码不对");
        }
    }
}

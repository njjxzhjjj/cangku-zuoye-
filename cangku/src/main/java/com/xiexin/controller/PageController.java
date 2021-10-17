package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/login") // /page/login
    public String login() {
        return "login";
    }

    @RequestMapping("/reg") // /page/reg
    public String reg() {
        return "reg";
    }

    @RequestMapping("/storelist") // /page/storelist
    public String storelist() {
        return "storelist";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }


    @RequestMapping("/add")
    public String add() {
        return "add";
    }


    @RequestMapping("/update")
    public String update() {
        return "update";
    }

}

package com.csy.webmvc.controller;

import com.csy.webmvc.constant.UrlPrefix;
import com.csy.webmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(UrlPrefix.NO_NEED_LOGIN_PRE + "register")
    public String register(@RequestParam String mobile, @RequestParam String password) {
        return userService.register(mobile, password);
    }

    @RequestMapping(UrlPrefix.NO_NEED_LOGIN_PRE + "login")
    public String login(@RequestParam String mobile, @RequestParam String password) {
        return userService.login(mobile, password);
    }
}

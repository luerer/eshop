package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by luerer on 28/06/2017.
 */
@Controller
@RequestMapping(value = "/home")
public class IndexController {
    @Autowired
    IUserDao iUserDao;

    /*@RequestMapping(value = "/")
    public String index()
    {
        return "home";
    }*/

    @RequestMapping(value = "/login")
    public String login()
    {
        return "login";
    }

    @RequestMapping(value = "/register")
    public String register()
    {
        return "register";
    }

}


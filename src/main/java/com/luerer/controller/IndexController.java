package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.dao.ItermDao;
import com.luerer.model.Iterm;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luerer on 28/06/2017.
 */
@Controller
@RequestMapping(value = "/home")
public class IndexController {
    @Autowired
    IUserDao iUserDao;
    @Autowired
    ItermDao itermDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap,
                        @RequestParam(value = "user", required = false) User user) {
        modelMap.put("itermList",itermDao.listall());
        if(user!=null) modelMap.put("user",user);
        return "home";
    }

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


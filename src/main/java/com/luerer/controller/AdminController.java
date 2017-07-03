package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by luerer on 03/07/2017.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IUserDao iUserDao;
    @RequestMapping("/adduser")
    public String adminAddUserPage(@ModelAttribute("user") User user){
        //iUserDao.addUser(user);
        return "admin_adduser";
    }

    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public String adminAddUser(User user){
        iUserDao.addUser(user);
        return "redirect:/login/admin";
    }
}

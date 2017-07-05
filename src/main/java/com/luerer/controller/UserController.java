package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by luerer on 05/07/2017.
 */
@Controller
@RequestMapping(value = "/custom")
public class UserController {
    @Autowired
    IUserDao iUserDao;

    @RequestMapping
    public String profilepage(HttpSession session,
                              ModelMap modelMap){
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            String username = user.getUsername();
            modelMap.put("username",username);
        }else{
            return "not_found";
        }
        return "custom_page";
    }

    @RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
    public @ResponseBody String updateUserInfo(HttpSession session,
                                               User user){
        if(user==null){
            return "错误！";
        }
        String username = user.getUsername();
        try{
            iUserDao.updateUser(user);
        }catch (Exception e){
            return "更新信息出错";
        }
        User new_user = iUserDao.searchByName(username);
        session.setAttribute("user",null);
        session.setAttribute("user",new_user);
        return "更新信息成功";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public @ResponseBody String changePassword(HttpSession session,
                                               @RequestParam(value = "old_password", required = false) String old_password,
                                               @RequestParam(value = "new_password", required = false) String new_password){
        User user = (User) session.getAttribute("user");
        String password = user.getPassword();
        String username = user.getUsername();

        if(password.equals(old_password)){
            try{
                iUserDao.changePassword(username,new_password);
            }catch(Exception e){
                return "修改密码出错。";
            }
        }else{
            return "旧密码错误！";
        }
        User new_user = iUserDao.searchByName(username);
        session.setAttribute("user",null);
        return "修改密码成功，请重新登录。";
    }


}

package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.model.User;
import com.luerer.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.tools.jconsole.HTMLPane;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by luerer on 29/06/2017.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    LoginService loginservice;
    @Autowired
    IUserDao iUserDao;

    @RequestMapping(method = RequestMethod.POST)
    public String logincheck(HttpSession session, ModelMap modelMap,
                             @RequestParam(value = "username", required = false) String username,
                             @RequestParam(value = "password", required = false) String password){
        //String username = request.getParameter("username");
        //String password = request.getParameter("password");
        boolean code = loginservice.login(username,password);
        if(code){
            User user = iUserDao.searchByName(username);
            session.setAttribute("user",user);
            return "redirect:/home";
        }else{
            modelMap.put("message","用户名/密码错误！");
            return "fail";
        }
    }


    @RequestMapping("/logout")
    public  String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user",null);
        return "redirect:/home";
    }

}

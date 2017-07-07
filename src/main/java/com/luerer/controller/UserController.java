package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.dao.IitemDao;
import com.luerer.dao.IorderDao;
import com.luerer.model.Item;
import com.luerer.model.Order;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luerer on 05/07/2017.
 */
@Controller
@RequestMapping(value = "/custom")
public class UserController {
    @Autowired
    IUserDao iUserDao;
    @Autowired
    IorderDao iorderDao;
    @Autowired
    IitemDao iitemDao;

    @RequestMapping
    public String profilePage(HttpSession session,
                              ModelMap modelMap){
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            if(user.getId()!=2){
                modelMap.put("errMsg","对不起，身份不正确。");
                return "not_found";
            }else{
                modelMap.put("username", user.getUsername());
            }

        }else{
            modelMap.put("errMsg","对不起，请先登录。");
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

    @RequestMapping(value = "/order")
    public String customOrder(HttpSession session,
                              ModelMap modelMap){
        List<Order> orderList = null;
        User user = (User)session.getAttribute("user");
        if(user==null){
            modelMap.put("errMsg","对不起,请登录。");
            return "not_found";
        }else if(user.getId()!=2){
            modelMap.put("errMsg","对不起,您不是买家。");
            return "not_found";
        }
        try{
            orderList = iorderDao.searchByCustom(user.getUsername());
        }catch (Exception e){
            modelMap.put("errMsg","查询订单出错。");
            return "not_found";
        }
        modelMap.put("orderList", orderList);
        return "custom_order";


    }

    @RequestMapping(value = "/receiveItem",method = RequestMethod.POST)
    public @ResponseBody String receiveItem(@RequestParam("order_id") long order_id){
        try{
            iorderDao.receive(order_id);
        }catch (Exception e){
            return "收货出错。。。";
        }
        return "收货成功";
    }

    @RequestMapping(value = "/returnItem",method = RequestMethod.POST)
    public @ResponseBody String returnItem(@RequestParam("order_id") long order_id,
                                           @RequestParam("msg") String msg){
        try{
            iorderDao.setStatus(order_id,2,msg);
        }catch (Exception e){
            return "申请退款出错。";
        }
        return "申请退款成功";
    }
}

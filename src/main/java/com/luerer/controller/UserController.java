package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.dao.IcartDao;
import com.luerer.dao.IitemDao;
import com.luerer.dao.IorderDao;
import com.luerer.model.Cart;
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
import javax.xml.soap.SAAJResult;
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
    @Autowired
    IcartDao icartDao;

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

    @RequestMapping(value = "/addCartItem", method = RequestMethod.POST)
    public @ResponseBody String addCartItem(HttpSession session,
                                            @RequestParam("item_id") int item_id,
                                            @RequestParam("item_num") int item_num){
        User user = (User) session.getAttribute("user");
        Item item = null;
        if(user==null){
            return "用户未登录。";
        }
        try{
            item = iitemDao.searchById(item_id);

        }catch (Exception e){
            return "商品出错。。";
        }
        int stock = item.getItem_stock();
        if(stock<item_num){
            return "对不起，商品库存不足。";
        }

        Cart cart = new Cart();
        cart.setCustom_name(user.getUsername());
        cart.setItem_id(item.getItem_id());
        cart.setItem_name(item.getItem_name());
        cart.setItem_price(item.getItem_price());
        cart.setItem_num(item_num);

        try{
            item.setItem_stock(stock-item_num);
            iitemDao.updateItem(item);
            icartDao.addCart(cart);
        }catch (Exception e){
            return "加入购物车出错。";
        }
        return "添加购物车成功";

    }

    @RequestMapping(value = "/cart")
    public String cart(HttpSession session,
                       ModelMap modelMap){
        //check user
        User user = (User) session.getAttribute("user");
        List<Cart> cartList = icartDao.searchByCustom(user.getUsername());
        float total= 0;
        for(Cart cart:cartList){
            total+=cart.getItem_price()*cart.getItem_num();
        }
        modelMap.put("cartList",cartList);
        modelMap.put("total",total);
        return "custom_cart";
    }

    @RequestMapping(value = "/deleteCart",method = RequestMethod.POST)
    public @ResponseBody String deleteCart(@RequestParam("item_id") int item_id,
                                           @RequestParam("item_num") int item_num,
                                           HttpSession session){
        User user = (User) session.getAttribute("user");
        Item item = iitemDao.searchById(item_id);
        int stock = item.getItem_stock();
        item.setItem_stock(stock+item_num);
        try {
            iitemDao.updateItem(item);
            icartDao.deleteCart(user.getUsername(),item_id);
        }catch (Exception e){
            return "删除商品失败。";
        }
        return "删除成功。";
    }

    @RequestMapping(value = "/confirmCart")
    public String confirmCart(HttpSession session,
                              ModelMap modelMap){
        User user = (User) session.getAttribute("user");

        List<Cart> cartList = icartDao.searchByCustom(user.getUsername());
        List<Order> orderList = new ArrayList<Order>();

        for(Cart cart:cartList){
            Item item = iitemDao.searchById(cart.getItem_id());
            boolean flag1 = item.getItem_name().equals(cart.getItem_name());
            boolean flag2 = item.getItem_price()==cart.getItem_price();
            if(flag1&&flag2){
                Order order = new Order();
                order.setItem_name(item.getItem_name());
                order.setItem_price(item.getItem_price());
                order.setItem_type(item.getItem_type());
                order.setItem_num(cart.getItem_num());
                order.setCustom_phone(user.getPhone());
                order.setCustom_address(user.getAddress());
                order.setCustom_name(user.getUsername());
                orderList.add(order);
            }else {
                modelMap.put("errMsg","对不起，商品已经被修改，请重新购买。");
                icartDao.deleteCart(user.getUsername(),item.getItem_id());
                int stock = item.getItem_stock();
                item.setItem_stock(stock+cart.getItem_num());
                iitemDao.updateItem(item);
                return "not_found";
            }
        }

        try {
            for(Order order:orderList){
                iorderDao.addOrder(order);
            }
            icartDao.confirmCart(user.getUsername());
        }catch (Exception e){
            modelMap.put("errMsg","购买出错。");
            return "not_found";
        }
        return "custom_page";
    }
}

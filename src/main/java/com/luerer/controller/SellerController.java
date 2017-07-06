package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.dao.IitemDao;
import com.luerer.dao.IorderDao;
import com.luerer.model.Item;
import com.luerer.model.Order;
import com.luerer.model.User;
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
@RequestMapping(value = "/seller")
public class SellerController {
    @Autowired
    IorderDao iorderDao;
    @Autowired
    IitemDao iitemDao;
    @Autowired
    IUserDao iUserDao;

    @RequestMapping
    public String sellerPage(HttpSession session,
                             ModelMap modelMap){
        User user = (User)session.getAttribute("user");
        if(user==null){
            modelMap.put("errMsg","对不起,请登录。");
            return "not_found";
        }else if(user.getId()!=1){
            modelMap.put("errMsg","对不起,您不是商家。");
            return "not_found";
        }
        return "seller_item";
    }

    @RequestMapping(value = "/order")
    public String sellerOrder(ModelMap modelMap,
                              HttpSession session){
        List<Order> orderList = null;
        User user = (User)session.getAttribute("user");
        if(user==null){
            modelMap.put("errMsg","对不起,请登录。");
            return "not_found";
        }else if(user.getId()!=1){
            modelMap.put("errMsg","对不起,您不是商家。");
            return "not_found";
        }
        try{
            orderList = iorderDao.listAll();
        }catch (Exception e){
            modelMap.put("errMsg","查询订单出错。");
            return "not_found";
        }

        if(orderList==null){
            modelMap.put("seller_orders", null);
            return "seller_order";
        }else {
            List seller_orders = new ArrayList();
            try {
                for (Order order : orderList) {
                    Map tmp = new HashMap();
                    Item item = iitemDao.searchById(order.getItem_id());
                    User tmp_user = iUserDao.searchByName(order.getCustom());
                    tmp.put("order_id", order.getId());
                    tmp.put("order_custom", order.getCustom());
                    tmp.put("order_item", item.getItem_name());
                    tmp.put("order_address", tmp_user.getAddress());
                    tmp.put("order_num", order.getItem_num());
                    tmp.put("order_status", order.isSend());
                    seller_orders.add(tmp);
                }
            } catch (Exception e) {
                modelMap.put("errMsg", "加载订单出错");
                return "not_found";
            }
            modelMap.put("seller_orders", seller_orders);
            return "seller_order";
        }
    }

    @RequestMapping(value = "/sendItem",method = RequestMethod.POST)
    public @ResponseBody String sendItem(@RequestParam("order_id") long order_id){
        try{
            iorderDao.send(order_id);
        }catch (Exception e){
            return "发货出错。。。";
        }
        return "发货成功";
    }
}

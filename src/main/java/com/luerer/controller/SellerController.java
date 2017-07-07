package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.dao.IitemDao;
import com.luerer.dao.IorderDao;
import com.luerer.dao.ItypeDao;
import com.luerer.model.Item;
import com.luerer.model.Order;
import com.luerer.model.Type;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    ItypeDao itypeDao;

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
        try{
            List<Item> itemList = iitemDao.listall();
            modelMap.put("itemList",itemList);
        }catch (Exception e){
            modelMap.put("errMsg","商品查询出错");
            return "not_found";
        }

        return "seller_item";
    }

    @RequestMapping(value = "/order")
    public String sellerOrder(ModelMap modelMap,
                              HttpSession session){

        //check user
        User user = (User)session.getAttribute("user");
        List<Order> orderList=null;
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

        if(orderList.size()==0){
            modelMap.put("orderList", null);
            return "seller_order";
        }else {
            modelMap.put("orderList",orderList);
            return "seller_order";
        }
    }
///////
    @RequestMapping(value = "/sendItem",method = RequestMethod.POST)
    public @ResponseBody String sendItem(@RequestParam("order_id") long order_id,
                                         @RequestParam("msg") String msg){
        try{
            iorderDao.send(order_id);
            iorderDao.setStatus(order_id,0,msg);
        }catch (Exception e){
            return "发货出错。。。";
        }
        return "发货成功";
    }

    @RequestMapping(value = "/rejectItem",method = RequestMethod.POST)
    public @ResponseBody String rejectItem(@RequestParam("order_id") long order_id,
                                           @RequestParam("msg") String msg){
        try{
            iorderDao.setStatus(order_id,1,msg);
        }catch (Exception e){
            return "订单出错。";
        }
        return "已拒绝。";
    }

    @RequestMapping(value = "/confirmReturn",method = RequestMethod.POST)
    public @ResponseBody String confirmReturn(@RequestParam("order_id") long order_id){
        Order order = iorderDao.searchById(order_id);
        if(order.getStatus()!=2)
            return "订单出错。";
        try{
            iorderDao.setStatus(order_id,4,"退款成功");
        }catch (Exception e){
            return "订单出错。";
        }
        return "已同意退款";
    }

    @RequestMapping(value = "/deleteItem")
    public @ResponseBody String deleteItem(@RequestParam("item_id") int item_id){

        try{
            Item item = iitemDao.searchById(item_id);
            String item_type = item.getItem_type();
            Type type = itypeDao.searchByName(item_type);
            int sum = type.getType_sum();
            if(sum>0)
            {
                type.setType_sum(sum-1);
                itypeDao.updateType(type);
            }
            iitemDao.deleteItem(item_id);
        }catch (Exception e){
            return "删除商品出错。";
        }
        return "删除商品成功";
    }

    @RequestMapping(value = "/updateItem/{item_id}")
    public String updateItem(@PathVariable int item_id,
                             ModelMap modelMap){
        Item item = iitemDao.searchById(item_id);
        modelMap.put("item",item);
        return "seller_updateItem";
    }

    @RequestMapping(value = "/addType")
    public @ResponseBody String updateConfirm(@RequestParam("newType") String newType,
                                              HttpSession session){
        if(itypeDao.searchByName(newType)!=null){
            return "已经存在该商品种类。";
        }
        try{
            itypeDao.addType(newType);
        }catch (Exception e){
            return "添加种类错误。";
        }
        List<Type> typeList = itypeDao.listAll();
        session.setAttribute("typeList",typeList);
        return "添加种类："+newType+"成功";

    }

    @RequestMapping(value = "/updateConfirm")
    public @ResponseBody String updateConfirm(Item item){
        int id = item.getItem_id();
        String old_type = iitemDao.searchById(id).getItem_type();
        String new_type = item.getItem_type();
        try{
            if(!old_type.equals(new_type)){
               Type old_ = itypeDao.searchByName(old_type);
               Type new_ = itypeDao.searchByName(new_type);
               int old_sum = old_.getType_sum();
               int new_sum = new_.getType_sum();
               old_.setType_sum(old_sum-1);
               new_.setType_sum(new_sum+1);
               itypeDao.updateType(old_);
               itypeDao.updateType(new_);
            }
            iitemDao.updateItem(item);
        }catch (Exception e){
            return "更新失败";
        }
        return "更新成功";

    }

    @RequestMapping(value = "/addItem")
    public String addItem(){
        return "seller_addItem";
    }

    @RequestMapping(value = "/addConfirm")
    public @ResponseBody String addConfirm(Item item){
        if(item==null){
            return "添加商品失败。";
        }
        String item_type = item.getItem_type();
        try{
            Type type = itypeDao.searchByName(item_type);
            int sum = type.getType_sum();
            type.setType_sum(sum+1);
            itypeDao.updateType(type);
            iitemDao.addItem(item);
        }catch (Exception e){
            return "添加失败。";
        }
        return "添加"+item_type+"："+item.getItem_name()+"成功。";
    }


}

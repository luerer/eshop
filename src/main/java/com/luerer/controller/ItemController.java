package com.luerer.controller;

import com.luerer.dao.IitemDao;
import com.luerer.dao.IorderDao;
import com.luerer.dao.ItypeDao;
import com.luerer.model.Item;
import com.luerer.model.Type;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by luerer on 05/07/2017.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    IitemDao iitemDao;
    @Autowired
    ItypeDao itypeDao;
    @Autowired
    IorderDao iorderDao;

    @RequestMapping(value = "/{type_id}")
    public String showItemByType(@PathVariable int type_id,
                                 ModelMap modelMap){
        modelMap.put("default_type",type_id);
        List<Item> itemList = null;
        try{
            String type_name = itypeDao.searchById(type_id);
            String item_type = type_name;
            itemList = iitemDao.searchByType(item_type);
        }catch (Exception e){
            modelMap.put("errMsg","查询错误。");
            return "not_found";
        }
        modelMap.put("itemList",itemList);
        return "home";
    }

    @RequestMapping(value = "/{item_id}/detail")
    public String showItemDetail(@PathVariable int item_id,
                                 ModelMap modelMap){
        Item item = null;
        try{
            item = iitemDao.searchById(item_id);
        }catch (Exception e){
            modelMap.put("errMsg","查询商品出错。");
            return "not_found";
        }
        modelMap.put("item",item);
        return "item_detail";
    }

    @RequestMapping(value = "/buy")
    public @ResponseBody String itemBuy(@RequestParam(value = "item_num", required = false) int item_num,
                                        @RequestParam(value = "item_id",required = false) int item_id,
                                        HttpSession session){
        User user = (User)session.getAttribute("user");
        String custom = user.getUsername();
        Item item = null;
        Type type = null;
        try{
            item = iitemDao.searchById(item_id);

        }catch (Exception e){
            return "商品出错。。";
        }
        int stock = item.getItem_stock();
        if(stock<item_num){
            return "对不起，商品库存不足。";
        }
        try{
            item.setItem_stock(stock-item_num);
            type = itypeDao.searchByName(item.getItem_type());
            int sum = type.getType_sum();
            if(stock-item_num<=0){
                type.setType_sum(sum-1);
                itypeDao.updateType(type);
            }
            iitemDao.updateItem(item);
            iorderDao.addOrder(custom,item.getItem_id(),item_num);
        }catch (Exception e){
            return "购买出错";
        }
        return "购买成功";

    }
}

package com.luerer.controller;

import com.luerer.dao.IitemDao;
import com.luerer.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by luerer on 05/07/2017.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @Autowired
    IitemDao iitemDao;

    @RequestMapping(value = "/{type_name}")
    public String showItemByType(@PathVariable String type_name,
                                 ModelMap modelMap){
        modelMap.put("default_type",type_name);
        List<Item> itemList = null;
        try{
            itemList = iitemDao.searchByType(type_name);
        }catch (Exception e){
            modelMap.put("err","查询错误。");
        }
        modelMap.put("itemList",itemList);
        return "home";
    }
}

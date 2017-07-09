package com.luerer.controller;

import com.luerer.dao.IUserDao;
import com.luerer.dao.IitemDao;
import com.luerer.dao.ItypeDao;
import com.luerer.model.Item;
import com.luerer.model.Type;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by luerer on 28/06/2017.
 */
@Controller
@RequestMapping(value = "/home")
public class IndexController {
    @Autowired
    IUserDao iUserDao;
    @Autowired
    IitemDao iitemDao;
    @Autowired
    ItypeDao itypeDao;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap,
                        HttpSession session) {

        User user = (User) session.getAttribute("user");
        List<Item> itemList = null;
        List<Type> typeList = null;
        try{
            itemList = iitemDao.listall();
            typeList = itypeDao.listAll();
        }catch (Exception e){
            return "not_found";
        }
        session.setAttribute("typeList",typeList);
        modelMap.put("itemList",itemList);
        modelMap.put("defaule_type","");
        if(user!=null) modelMap.put("user",user);
        return "home";
    }

    @RequestMapping(value = "/login")
    public String login(@ModelAttribute("user") User user)
    {
        return "login";
    }

    @RequestMapping(value = "/register")
    public String register()
    {
        return "register";
    }

}


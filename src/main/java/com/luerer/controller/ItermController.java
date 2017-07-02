package com.luerer.controller;

import com.luerer.dao.ItermDao;
import com.luerer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by luerer on 29/06/2017.
 */

@Controller
@RequestMapping({"/home","/iterms"})
public class ItermController {
    @Autowired
    private ItermDao itermdao;
    @RequestMapping(method = RequestMethod.GET)
    public String iterms(ModelMap modelMap,
                         @ModelAttribute User user){
        modelMap.put("itermList",itermdao.listall());
        if(user!=null) modelMap.put("user",user);
        return "home";
    }

}

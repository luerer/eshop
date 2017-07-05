package com.luerer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by luerer on 05/07/2017.
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
    @RequestMapping(value = "/{type_name}")
    public String showItemByType(@PathVariable String type_name,
                                 ModelMap modelMap){
        modelMap.put("default_type",type_name);
        return "home";
    }
}

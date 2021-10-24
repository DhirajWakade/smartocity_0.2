package com.allinone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController 
{
	@GetMapping("/")
    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
      //  model.addAttribute("attribute", "redirectWithRedirectPrefix");
        return new ModelAndView("redirect:/admin/index", model);
    }

}

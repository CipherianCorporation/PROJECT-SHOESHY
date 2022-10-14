package com.edu.graduationproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @GetMapping({ "/", "/home/index" })
    public String home(ModelMap model, @RequestParam(required = false) String message,
            @RequestParam(required = false) Boolean isPaymentSuccess,
            RedirectAttributes redirAttrs) {
        redirAttrs.addFlashAttribute("message", message);
        redirAttrs.addFlashAttribute("isPaymentSuccess", isPaymentSuccess);
        return "redirect:/product/list";
    }

    @GetMapping({ "/admin", "/admin/home/index" })
    public String admin() {
        return "redirect:/admin/index.html";
    }
}

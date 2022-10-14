package com.edu.graduationproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartController.class);

    @RequestMapping("/cart/view")
    public String view() {
        return "cart/view";
    }
}

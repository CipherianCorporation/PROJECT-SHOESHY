package com.edu.graduationproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.edu.graduationproject.service.OrderService;
import com.edu.graduationproject.service.PaypalService;

@Controller
public class PaypalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaypalController.class);

    public static final String PAYPAL_SUCCESS_URL = "/pay/success";
    public static final String PAYPAL_CANCEL_URL = "/pay/cancel";

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private PaypalService paypalService;

    @Autowired
    OrderService orderService;

}

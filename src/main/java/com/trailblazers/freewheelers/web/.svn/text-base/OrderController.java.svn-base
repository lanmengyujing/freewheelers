package com.trailblazers.freewheelers.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(OrderController.URL)
public class OrderController {

    protected static final String URL = "/order/summary";
    private static final String PAGE = "orderSummary";

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public String summary() {
        return PAGE;
    }
}
